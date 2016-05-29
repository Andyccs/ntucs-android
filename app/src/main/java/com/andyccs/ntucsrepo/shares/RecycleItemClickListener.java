package com.andyccs.ntucsrepo.shares;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class RecycleItemClickListener implements RecyclerView.OnItemTouchListener {
  private OnItemClickListener listener;

  public interface OnItemClickListener {
    void onItemClick(View view, int position);
  }

  GestureDetector gestureDetector;

  public RecycleItemClickListener(Context context, OnItemClickListener listener) {
    this.listener = listener;
    gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
      @Override public boolean onSingleTapUp(MotionEvent motionEvent) {
        return true;
      }
    });
  }

  @Override
  public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent motionEvent) {
    View childView = view.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
    if (childView != null && listener != null && gestureDetector.onTouchEvent(motionEvent)) {
      listener.onItemClick(childView, view.getChildPosition(childView));
      return true;
    }
    return false;
  }

  @Override
  public void onTouchEvent(RecyclerView view, MotionEvent motionEvent) {

  }

  @Override
  public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

  }
}