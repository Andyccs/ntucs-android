package com.andyccs.ntucsrepo;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

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

  private ArrayAdapter<ResourceModel> resourceListAdapter;

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
    View view = inflater.inflate(R.layout.fragment_resource_list, container, false);

    TextView resourceListTitle = (TextView) view.findViewById(R.id.resource_list_title);
    resourceListTitle.setText(ResourceType.getName(resourceType));

    ListView resourceList = (ListView) view.findViewById(R.id.resource_list);
    resourceListAdapter = new ArrayAdapter<>(
        getActivity(),
        R.layout.resource_text,
        R.id.resource_text_1);
    resourceList.setAdapter(resourceListAdapter);

    resourceList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        ResourceModel resource = (ResourceModel) adapterView.getAdapter().getItem(position);
        onResourceSelectedListener.onResourceSelected(resource.getId());
      }
    });

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
    String firebaseReference = ResourceType.getFirebaseReferenceByType(resourceType);
    DatabaseReference databaseReference = database.getReference(firebaseReference);

    ValueEventListener valueEventListener = new ValueEventListener() {
      @Override
      public void onDataChange(DataSnapshot dataSnapshot) {
        GenericTypeIndicator<List<ResourceModel>> type =
            new GenericTypeIndicator<List<ResourceModel>>() {};
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
  }

  @Override
  public void onDetach() {
    super.onDetach();
    onResourceSelectedListener = null;
  }

  public interface OnResourceSelectedListener {
    void onResourceSelected(int id);
  }

  private boolean isNetworkAvailable() {
    ConnectivityManager connectivityManager
        = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
    return activeNetworkInfo != null && activeNetworkInfo.isConnected();
  }
}
