package com.andyccs.ntucsrepo;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.List;


public class ResourceListFragment extends Fragment {
  private static final String TAG = ResourceListFragment.class.getName();
  private static final String RESOURCE_TYPE_PARAM = "resource_type";

  private String resourceType;

  private OnResourceSelectedListener onResourceSelectedListener;
  SetToolbarTitle setToolbarTitle;

  private RecyclerView resourceList;
  private ResourceListAdapter resourceListAdapter;

  /**
   * Use this factory method to create a new instance of
   * this fragment using the provided parameters.
   *
   * @param resourceType Parameter 1.
   * @return A new instance of fragment ResourceListFragment.
   */
  public static ResourceListFragment newInstance(String resourceType) {
    ResourceListFragment fragment = new ResourceListFragment();
    Bundle args = new Bundle();
    args.putString(RESOURCE_TYPE_PARAM, resourceType);
    fragment.setArguments(args);
    return fragment;
  }

  public ResourceListFragment() {
    // Required empty public constructor
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() == null) {
      throw new NullPointerException(
          "You must provide resource type param to ResourceListFragment");
    }

    resourceType = getArguments().getString(RESOURCE_TYPE_PARAM);
    if (resourceType == null) {
      throw new NullPointerException(
          "You must provide resource type param to ResourceListFragment");
    }
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    setToolbarTitle.setToolbarTitle(ResourceType.getName(getActivity(), resourceType));

    View view = inflater.inflate(R.layout.fragment_resource_list, container, false);

    resourceList = (RecyclerView) view.findViewById(R.id.resource_list);
    resourceList.setHasFixedSize(true);

    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
    resourceList.setLayoutManager(linearLayoutManager);

    resourceListAdapter = new ResourceListAdapter();
    resourceList.setAdapter(resourceListAdapter);

    resourceList.addOnItemTouchListener(
        new RecycleItemClickListener(
            getActivity(), new RecycleItemClickListener.OnItemClickListener() {
          @Override
          public void onItemClick(View view, int position) {
            ResourceModel selectedResouce = resourceListAdapter.getItem(position);
            onResourceSelectedListener.onResourceSelected(selectedResouce);

            String link = null;
            if (selectedResouce.getLink() != null) {
              link = selectedResouce.getLink();
            } else if (selectedResouce.getGithub() != null) {
              link = selectedResouce.getGithub();
            }

            if (link == null) {
              Snackbar.make(view, "No resource found", Snackbar.LENGTH_SHORT).show();
              return;
            }
            Intent browserIntent =
                new Intent(Intent.ACTION_VIEW, Uri.parse(link));
            startActivity(browserIntent);
          }
        }));

    if (!isNetworkAvailable()) {
      Snackbar.make(
          getActivity().findViewById(android.R.id.content),
          "No internet connection",
          Snackbar.LENGTH_LONG).show();
    } else {
      // Read data from database
      readFromDatabase();
    }

    return view;
  }

  private void readFromDatabase() {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = database.getReference(resourceType);

    ValueEventListener valueEventListener = new ValueEventListener() {
      @Override
      public void onDataChange(DataSnapshot dataSnapshot) {
        GenericTypeIndicator<List<ResourceModel>> type =
            new GenericTypeIndicator<List<ResourceModel>>() {
            };
        List<ResourceModel> resourceModels = dataSnapshot.getValue(type);
        resourceListAdapter.addAll(resourceModels);
        resourceListAdapter.notifyDataSetChanged();
      }

      @Override
      public void onCancelled(DatabaseError databaseError) {
        // Failed to read value
        Log.w(TAG, "Failed to read value.", databaseError.toException());
      }
    };
    databaseReference.addValueEventListener(valueEventListener);
  }

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    if (context instanceof OnResourceSelectedListener) {
      onResourceSelectedListener = (OnResourceSelectedListener) context;
    } else {
      throw new RuntimeException(context.toString()
          + " must implement OnResourceSelectedListener");
    }

    if (context instanceof SetToolbarTitle) {
      setToolbarTitle = (SetToolbarTitle) context;
    } else {
      throw new RuntimeException(context.toString()
          + " must implement SetToolbarTitle");
    }
  }

  @Override
  public void onDetach() {
    super.onDetach();
    onResourceSelectedListener = null;
  }

  public interface OnResourceSelectedListener {
    void onResourceSelected(ResourceModel resourceModel);
  }

  private boolean isNetworkAvailable() {
    ConnectivityManager connectivityManager
        = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
    return activeNetworkInfo != null && activeNetworkInfo.isConnected();
  }
}
