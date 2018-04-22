package com.minhthanh.bulk.models;

import com.clct.models.ShortMessage;
import com.clct.rest.models.ObjectRequestModel;

import java.util.List;

/**
 * Created by tung on 7/8/2016.
 */
public class BulkSmsModel extends ObjectRequestModel<List<ShortMessage>> {
    private String subscriptionId;

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }
}

