package com.andyccs.ntucsrepo;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements
    MainFragment.OnResourceTypeSelectedListener,
    ResourceListFragment.OnResourceSelectedListener {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

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
      transaction.replace(R.id.fragment_container, resourceListFragment);
      transaction.addToBackStack(null);
      transaction.commit();
    }
  }

  @Override
  public void onResourceSelected(int id) {
    System.out.println("hello world: "+ id);
  }
}
