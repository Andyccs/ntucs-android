package com.andyccs.ntucsrepo;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainFragment extends Fragment {

  private ListView resourceList;

  OnResourceTypeSelectedListener onResourceSelectedListener;

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
  }

  @Override
  public void onDetach() {
    super.onDetach();
    onResourceSelectedListener = null;
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View mainView = inflater.inflate(R.layout.fragment_main, container, false);
    resourceList = (ListView) mainView.findViewById(R.id.resource_type_list);

    ArrayAdapter<String> resourceListAdapter = new ArrayAdapter<>(
        getActivity(),
        R.layout.resource_text,
        R.id.resource_text_1,
        ResourceType.getNames());
    resourceList.setAdapter(resourceListAdapter);
    resourceList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        String resourceName = (String) adapterView.getAdapter().getItem(position);
        onResourceSelectedListener.onResourceSelected(ResourceType.getType(resourceName));
      }
    });

    return mainView;
  }

  public interface OnResourceTypeSelectedListener {
    void onResourceSelected(String resource);
  }

}
