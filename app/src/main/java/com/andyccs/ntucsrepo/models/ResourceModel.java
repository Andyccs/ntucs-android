package com.andyccs.ntucsrepo.models;

public class ResourceModel {
  private int id;
  private String name;
  private String link;
  private String github;
  private String type;
  private String course;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
  }

  public String getGithub() {
    return github;
  }

  public void setGithub(String github) {
    this.github = github;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getCourse() {
    return course;
  }

  public void setCourse(String course) {
    this.course = course;
  }

  @Override
  public String toString() {
    return getName();
  }
}
