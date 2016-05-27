package com.andyccs.ntucsrepo;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class StringArrayRecycleViewAdapter extends
    RecyclerView.Adapter<StringArrayRecycleViewAdapter.ViewHolder> {
  private LayoutInflater inflater;
  private int resource;
  private int textViewResourceId;
  private String[] objects;

  public StringArrayRecycleViewAdapter(Context context,
                                       @LayoutRes int resource,
                                       @IdRes int textViewResourceId,
                                       @NonNull String[] objects) {
    this.inflater = LayoutInflater.from(context);
    this.resource = resource;
    this.textViewResourceId = textViewResourceId;
    this.objects = objects;
  }


  public String getItem(int position) {
    return objects[position];
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = inflater.inflate(resource, parent, false);
    TextView textView = (TextView) view.findViewById(textViewResourceId);
    return new ViewHolder(view, textView);
  }

  @Override
  public void onBindViewHolder(ViewHolder holder, int position) {
    holder.textView.setText(objects[position]);
  }

  @Override
  public int getItemCount() {
    return objects.length;
  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    View itemView;
    TextView textView;

    public ViewHolder(View itemView, TextView textView) {
      super(itemView);
      this.itemView = itemView;
      this.textView = textView;
    }
  }
}
