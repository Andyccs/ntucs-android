package com.andyccs.ntucsrepo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

public class ResourceListAdapter extends RecyclerView.Adapter<ResourceListAdapter.ViewHolder>{
  private ResourceModel[] resources;

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    // create a new view
    RelativeLayout view = (RelativeLayout) LayoutInflater.from(parent.getContext())
        .inflate(R.layout.resource_text_recycle, parent, false);

    TextView textView = (TextView) view.findViewById(R.id.resource_text_1);
    return new ViewHolder(view, textView);
  }

  @Override
  public void onBindViewHolder(ViewHolder holder, int position) {
    holder.textView.setText(resources[position].getName());
  }

  @Override
  public int getItemCount() {
    return resources != null ? resources.length : 0;
  }

  public void addAll(List<ResourceModel> resources) {
    this.resources = new ResourceModel[resources.size()];
    resources.toArray(this.resources);
  }

  public ResourceModel getItem(int position) {
    return this.resources[position];
  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    RelativeLayout view;
    TextView textView;
    public ViewHolder(RelativeLayout view, TextView textView) {
      super(view);
      this.view = view;
      this.textView = textView;
    }
  }
}
