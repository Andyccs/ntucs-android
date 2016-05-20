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

/**
 * A placeholder fragment containing a simple view.
 */
public class MainFragment extends Fragment {
  public interface OnResourceSelectedListener {
    void onResourceSelected(String resource);
  }

  private static final String[] RESOURCE_LIST = new String[] {
      "Course Projects",
      "Final Year Project",
      "PYP Solutions",
      "Summaries",
  };

  private static final String[] RESOURCE_VALUE = new String[] {
      "projects",
      "fyp",
      "pyp",
      "summary"
  };

  private ListView resourceList;

  OnResourceSelectedListener onResourceSelectedListener;

  public MainFragment() {
  }

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    try {
      onResourceSelectedListener = (OnResourceSelectedListener) context;
    } catch (ClassCastException e) {
      throw new ClassCastException(context.toString()
          + " must implement OnHeadlineSelectedListener");
    }
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
    resourceList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        onResourceSelectedListener.onResourceSelected(RESOURCE_VALUE[i]);
      }
    });

    return mainView;
  }

}
