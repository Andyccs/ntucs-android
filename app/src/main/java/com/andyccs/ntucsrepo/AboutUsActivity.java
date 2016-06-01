package com.andyccs.ntucsrepo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

public class AboutUsActivity extends AppCompatActivity {
  private static final String GITHUB_LINK = "https://github.com/Andyccs/ntucs-android/";
  private static final String OPEN_SOURCE_LICENSES =
      "file:///android_asset/open_source_licenses.html";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_about_us);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    Button openSource = (Button) findViewById(R.id.button_open_source);
    assert openSource != null;
    openSource.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        WebView view = (WebView) LayoutInflater
            .from(AboutUsActivity.this)
            .inflate(R.layout.dialog_licenses, null);
        view.loadUrl(OPEN_SOURCE_LICENSES);
        new AlertDialog.Builder(AboutUsActivity.this)
            .setTitle(getString(R.string.open_source_licenses))
            .setView(view)
            .setPositiveButton(android.R.string.ok, null)
            .show();
      }
    });

    Button github = (Button) findViewById(R.id.button_github);
    assert github != null;
    github.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(GITHUB_LINK));
        startActivity(browserIntent);
      }
    });

    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
  }

  @Override
  public boolean onSupportNavigateUp() {
    finish();
    return true;
  }
}
