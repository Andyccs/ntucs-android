package com.andyccs.ntucsrepo;

import java.util.Dictionary;
import java.util.Hashtable;

public class ResourceType {

  static final String PROJECT_RESOURCE = "project";
  private static final String PROJECT_RESOURCE_NAME = "Course Projects";

  static final String FYP_RESOURCE = "fyp";
  private static final String FYP_RESOURCE_NAME = "Final Year Projects";

  static final String PYP_RESOURCE = "pyp";
  private static final String PYP_RESOURCE_NAME = "PYP Solutions";

  static final String SUMARRY_RESOURCE = "summary";
  private static final String SUMMARY_RESOURCE_NAME = "Summaries and Notes";

  private static final String FIREBASE_REFERENCE_FYPS = "fyps";
  private static final String FIREBASE_REFERENCE_PYPS = "pyps";
  private static final String FIREBASE_REFERENCE_PROJECTS = "projects";
  private static final String FIREBASE_REFERENCE_SUMMARIES = "summaries";

  private static final String[] RESOURCE_NAMES = new String[] {
      PROJECT_RESOURCE_NAME,
      FYP_RESOURCE_NAME,
      PYP_RESOURCE_NAME,
      SUMMARY_RESOURCE_NAME
  };

  private static final String RESOURCES_STRING;

  static {
    String comma = ", ";
    StringBuilder builder = new StringBuilder();
    builder.append(PROJECT_RESOURCE)
        .append(comma)
        .append(FYP_RESOURCE)
        .append(comma)
        .append(PYP_RESOURCE)
        .append(comma)
        .append(SUMARRY_RESOURCE);
    RESOURCES_STRING = builder.toString();
  }

  private static final Dictionary<String, String> RESOURCE_TYPE_TO_NAME = new Hashtable<>();

  static {
    RESOURCE_TYPE_TO_NAME.put(PROJECT_RESOURCE, PROJECT_RESOURCE_NAME);
    RESOURCE_TYPE_TO_NAME.put(FYP_RESOURCE, FYP_RESOURCE_NAME);
    RESOURCE_TYPE_TO_NAME.put(PYP_RESOURCE, PYP_RESOURCE_NAME);
    RESOURCE_TYPE_TO_NAME.put(SUMARRY_RESOURCE, SUMMARY_RESOURCE_NAME);
  }

  private static final Dictionary<String, String> RESOURCE_NAME_TO_TYPE = new Hashtable<>();

  static {
    RESOURCE_NAME_TO_TYPE.put(PROJECT_RESOURCE_NAME, PROJECT_RESOURCE);
    RESOURCE_NAME_TO_TYPE.put(FYP_RESOURCE_NAME, FYP_RESOURCE);
    RESOURCE_NAME_TO_TYPE.put(PYP_RESOURCE_NAME, PYP_RESOURCE);
    RESOURCE_NAME_TO_TYPE.put(SUMMARY_RESOURCE_NAME, SUMARRY_RESOURCE);
  }

  private static final
  Dictionary<String, String> RESOURCE_TYPE_TO_FIREBASE_REFERENCES = new Hashtable<>();

  static {
    RESOURCE_TYPE_TO_FIREBASE_REFERENCES.put(PROJECT_RESOURCE, FIREBASE_REFERENCE_PROJECTS);
    RESOURCE_TYPE_TO_FIREBASE_REFERENCES.put(FYP_RESOURCE, FIREBASE_REFERENCE_FYPS);
    RESOURCE_TYPE_TO_FIREBASE_REFERENCES.put(PYP_RESOURCE, FIREBASE_REFERENCE_PYPS);
    RESOURCE_TYPE_TO_FIREBASE_REFERENCES.put(SUMARRY_RESOURCE, FIREBASE_REFERENCE_SUMMARIES);
  }

  static String[] getNames() {
    return RESOURCE_NAMES;
  }

  static String getName(String resourceType) {
    return RESOURCE_TYPE_TO_NAME.get(resourceType);
  }

  static String getType(String resourceName) {
    return RESOURCE_NAME_TO_TYPE.get(resourceName);
  }

  static boolean isValidResourceType(String resourceType) {
    return RESOURCE_TYPE_TO_NAME.get(resourceType) != null;
  }

  static String allResourceNamesToString() {
    return RESOURCES_STRING;
  }

  static String getFirebaseReferenceByType(String resourceType) {
    return RESOURCE_TYPE_TO_FIREBASE_REFERENCES.get(resourceType);
  }
}
