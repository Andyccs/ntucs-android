package com.andyccs.ntucsrepo;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class ResourceListFragment extends Fragment {
  private static final String RESOURCE_TYPE_PARAM = "resource_type";

  private String resourceType;

  private OnResourceSelectedListener onResourceSelectedListener;

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
    System.out.println("resource type: " + resourceType);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_resource_list, container, false);
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
    void onResourceSelectedListener(int id);
  }
}
