package com.andyccs.ntucsrepo;

public class MockResourceModels {
  static ResourceModel[] getMocks() {
    ResourceModel resource1 = new ResourceModel();
    resource1.setId(1);
    resource1.setName("CZ4024-13-14-2 PYP Solutions");
    resource1.setType(ResourceType.PYP_RESOURCE);

    ResourceModel resource2 = new ResourceModel();
    resource2.setId(2);
    resource2.setName("CZ4034-13-14-2 PYP Solutions");
    resource2.setType(ResourceType.PYP_RESOURCE);

    ResourceModel resource3 = new ResourceModel();
    resource3.setId(3);
    resource3.setName("Machine Learning Summary");
    resource3.setType(ResourceType.SUMARRY_RESOURCE);

    ResourceModel resource4 = new ResourceModel();
    resource4.setId(4);
    resource4.setName("Software Quality Analysis Tools");
    resource4.setType(ResourceType.FYP_RESOURCE);

    ResourceModel resource5 = new ResourceModel();
    resource5.setId(5);
    resource5.setName("CZ3002 Software Engineering Project");
    resource5.setType(ResourceType.PROJECT_RESOURCE);

    ResourceModel[] mocks = new ResourceModel[5];
    mocks[0] = resource1;
    mocks[1] = resource2;
    mocks[2] = resource3;
    mocks[3] = resource4;
    mocks[4] = resource5;
    return mocks;
  }
}
