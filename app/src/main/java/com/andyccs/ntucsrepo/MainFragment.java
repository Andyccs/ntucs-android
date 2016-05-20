package com.andyccs.ntucsrepo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainFragment extends Fragment {

  private static final String[] RESOURCE_LIST = new String[] {
      "Course Projects",
      "Final Year Project",
      "PYP Solutions",
      "Summaries",
  };
  private ListView resourceList;

  public MainFragment() {
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View mainView = inflater.inflate(R.layout.fragment_main, container, false);
    resourceList = (ListView) mainView.findViewById(R.id.resource_list);

    ArrayAdapter<String> resourceListAdapter = new ArrayAdapter<String>(
        getActivity(),
        R.layout.resource_text,
        R.id.resource_text_1,
        RESOURCE_LIST);
    resourceList.setAdapter(resourceListAdapter);

    return mainView;
  }

}
