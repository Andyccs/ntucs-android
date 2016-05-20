package com.andyccs.ntucsrepo;

public class ResourceModel {
  private int id;
  private String name;
  private String pdfLink;
  private String githubRepo;
  private String type;

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

  public String getPdfLink() {
    return pdfLink;
  }

  public void setPdfLink(String pdfLink) {
    this.pdfLink = pdfLink;
  }

  public String getGithubRepo() {
    return githubRepo;
  }

  public void setGithubRepo(String githubRepo) {
    this.githubRepo = githubRepo;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  @Override
  public String toString() {
    return getName();
  }
}
