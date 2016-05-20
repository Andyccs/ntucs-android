package com.andyccs.ntucsrepo;

/**
 * Created by andyccs on 20/5/16.
 */
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

    ResourceModel[] mocks = new ResourceModel[2];
    mocks[0] = resource1;
    mocks[1] = resource2;
    return mocks;
  }
}
