package com.andyccs.ntucsrepo;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MainFragment extends Fragment {

  OnResourceTypeSelectedListener onResourceSelectedListener;
  SetToolbarTitle setToolbarTitle;

  public MainFragment() {
    // Required empty public constructor
  }

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    if (context instanceof OnResourceTypeSelectedListener) {
      onResourceSelectedListener = (OnResourceTypeSelectedListener) context;
    } else {
      throw new RuntimeException(context.toString()
          + " must implement OnResourceTypeSelectedListener");
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

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    setToolbarTitle.setToolbarTitle(getString(R.string.choose_resources));

    View mainView = inflater.inflate(R.layout.fragment_main, container, false);

    RecyclerView resourceList = (RecyclerView) mainView.findViewById(R.id.resource_type_list);

    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
    resourceList.setLayoutManager(linearLayoutManager);

    final StringArrayRecycleViewAdapter resourceListAdapter =
        new StringArrayRecycleViewAdapter(
            getActivity(),
            R.layout.resource_text,
            R.id.resource_text_1,
            ResourceType.getNames(getActivity()));
    resourceList.setAdapter(resourceListAdapter);

    RecycleItemClickListener.OnItemClickListener onItemClickListener =
        new RecycleItemClickListener.OnItemClickListener() {
          @Override
          public void onItemClick(View view, int position) {
            String resourceName = resourceListAdapter.getItem(position);
            onResourceSelectedListener
                .onResourceSelected(ResourceType.getType(getActivity(), resourceName));
          }
        };
    resourceList.addOnItemTouchListener(
        new RecycleItemClickListener(getActivity(), onItemClickListener));

    return mainView;
  }

  public interface OnResourceTypeSelectedListener {
    void onResourceSelected(String resource);
  }

}
