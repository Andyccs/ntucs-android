package com.andyccs.ntucsrepo;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements
    MainFragment.OnResourceTypeSelectedListener,
    ResourceListFragment.OnResourceSelectedListener,
    SetToolbarTitle,
    FragmentManager.OnBackStackChangedListener {

  private CollapsingToolbarLayout collapsingToolbarLayout;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getSupportFragmentManager().addOnBackStackChangedListener(this);

    setContentView(R.layout.activity_main);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);

    shouldDisplayHomeUp();

    // If we recreating this activity, then we don't create MainFragment again.
    if (savedInstanceState != null) {
      return;
    }

    int fragmentContainerId = -1;
    if (isNormalLayout()) {
      fragmentContainerId = R.id.fragment_container;
    } else if (isLargeLayout()){
      fragmentContainerId = R.id.main_fragment_large;
    }
    MainFragment mainFragment = new MainFragment();
    getSupportFragmentManager().beginTransaction().add(fragmentContainerId, mainFragment).commit();
  }

  private boolean isLargeLayout() {
    return findViewById(R.id.main_fragment_large) != null &&
        findViewById(R.id.resource_list_fragment_large) != null;
  }

  private boolean isNormalLayout() {
    return findViewById(R.id.fragment_container) != null;
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
    int resourceTypeFragmentContainerId = -1;
    boolean addToBackStack = true;
    if (isNormalLayout()) {
      resourceTypeFragmentContainerId = R.id.fragment_container;
    } else if (isLargeLayout()) {
      resourceTypeFragmentContainerId = R.id.resource_list_fragment_large;
      addToBackStack = false;
    }
    ResourceListFragment resourceListFragment = ResourceListFragment.newInstance(resourceType);
    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
    transaction.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
    transaction.replace(resourceTypeFragmentContainerId, resourceListFragment);
    if (addToBackStack) {
      transaction.addToBackStack(null);
    }
    transaction.commit();
  }

  @Override
  public void onResourceSelected(ResourceModel resourceModel) {
    System.out.println("hello world: " + resourceModel);
  }

  @Override
  public void setToolbarTitle(String title) {
    collapsingToolbarLayout.setTitle(title);
  }

  @Override
  public void onBackStackChanged() {
    shouldDisplayHomeUp();
  }

  public void shouldDisplayHomeUp(){
    //Enable Up button only  if there are entries in the back stack
    boolean canback = getSupportFragmentManager().getBackStackEntryCount() > 0;
    getSupportActionBar().setDisplayHomeAsUpEnabled(canback);
  }

  @Override
  public boolean onSupportNavigateUp() {
    getSupportFragmentManager().popBackStack();
    return true;
  }
}
