package com.andyccs.ntucsrepo;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements
    MainFragment.OnResourceTypeSelectedListener,
    ResourceListFragment.OnResourceSelectedListener,
    SetToolbarTitle {

  private CollapsingToolbarLayout collapsingToolbarLayout;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);

    // If we recreating this activity, then we don't create MainFragment again.
    if (savedInstanceState != null) {
      return;
    }

    // If we are using layout in portrait mode, then fragment container is not null
    if (findViewById(R.id.fragment_container) != null) {
      MainFragment mainFragment = new MainFragment();
      getSupportFragmentManager().beginTransaction()
          .add(R.id.fragment_container, mainFragment).commit();
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  @Override
  public void onResourceSelected(String resourceType) {
    if (findViewById(R.id.fragment_container) != null) {
      ResourceListFragment resourceListFragment = ResourceListFragment.newInstance(resourceType);
      FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
      transaction.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
      transaction.replace(R.id.fragment_container, resourceListFragment);
      transaction.addToBackStack(null);
      transaction.commit();
    }
  }

  @Override
  public void onResourceSelected(ResourceModel resourceModel) {
    System.out.println("hello world: " + resourceModel);
  }

  @Override
  public void setToolbarTitle(String title) {
    collapsingToolbarLayout.setTitle(title);
  }
}
