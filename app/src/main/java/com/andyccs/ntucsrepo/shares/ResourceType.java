package com.andyccs.ntucsrepo.shares;

import android.content.Context;
import android.support.annotation.Nullable;

import com.andyccs.ntucsrepo.R;

public class ResourceType {
  private static final String FIREBASE_REFERENCE_FYPS = "fyps";
  private static final String FIREBASE_REFERENCE_PYPS = "pyps";
  private static final String FIREBASE_REFERENCE_PROJECTS = "projects";
  private static final String FIREBASE_REFERENCE_SUMMARIES = "summaries";

  public static String[] getNames(Context context) {
    return new String[] {
        context.getString(R.string.resource_type_projects),
        context.getString(R.string.resource_type_fyps),
        context.getString(R.string.resource_type_pyps),
        context.getString(R.string.resource_type_summaries)
    };
  }

  @Nullable
  public static String getName(Context context, String resourceType) {
    if (resourceType.equals(FIREBASE_REFERENCE_FYPS)) {
      return context.getString(R.string.resource_type_fyps);
    } else if (resourceType.equals(FIREBASE_REFERENCE_PROJECTS)) {
      return context.getString(R.string.resource_type_projects);
    } else if (resourceType.equals(FIREBASE_REFERENCE_PYPS)) {
      return context.getString(R.string.resource_type_pyps);
    } else if (resourceType.equals(FIREBASE_REFERENCE_SUMMARIES)) {
      return context.getString(R.string.resource_type_summaries);
    } else {
      return null;
    }
  }

  @Nullable
  public static String getType(Context context, String resourceName) {
    if (context.getString(R.string.resource_type_fyps).equals(resourceName)) {
      return FIREBASE_REFERENCE_FYPS;
    } else if (context.getString(R.string.resource_type_projects).equals(resourceName)) {
      return FIREBASE_REFERENCE_PROJECTS;
    } else if (context.getString(R.string.resource_type_pyps).equals(resourceName)) {
      return FIREBASE_REFERENCE_PYPS;
    } else if (context.getString(R.string.resource_type_summaries).equals(resourceName)) {
      return FIREBASE_REFERENCE_SUMMARIES;
    }
    return null;
  }
}
