package com.andyccs.ntucsrepo;

import android.os.AsyncTask;

import java.util.ArrayList;

/**
 * Created by andyccs on 20/5/16.
 */
public class DownloadResourcesAsyncTask extends AsyncTask<Integer, Void, ResourceModel[]> {

  private String resourceType;

  void setResourceType(String resourceType) {
    if (!ResourceType.isValidResourceType(resourceType)) {
      throw new IllegalArgumentException("resourceType should be one of "
          + ResourceType.allResourceNamesToString());
    }
    this.resourceType = resourceType;
  }

  @Override
  protected ResourceModel[] doInBackground(Integer... integers) {
    ResourceModel[] resourceModels = MockResourceModels.getMocks();


    if (resourceType == null) {
      return resourceModels;
    }

    ArrayList<ResourceModel> resultArrayList = new ArrayList<>();
    for (ResourceModel model : resourceModels) {
      if (model.getType().equals(resourceType)) {
        resultArrayList.add(model);
      }
    }

    ResourceModel[] results = new ResourceModel[resultArrayList.size()];

    return resultArrayList.toArray(results);
  }
}
