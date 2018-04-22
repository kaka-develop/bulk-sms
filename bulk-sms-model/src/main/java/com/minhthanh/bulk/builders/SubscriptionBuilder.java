package com.minhthanh.bulk.builders;

import com.clct.models.ShortMessage;
import com.minhthanh.bulk.models.SubscriptionModel;

/**
 * Created by luyenchu on 7/12/16.
 */
public class SubscriptionBuilder {
    private String subscriptionId;
    private ShortMessage data;
    private String sessionId;

    private SubscriptionBuilder() {
    }

    public static SubscriptionBuilder aSubscription() {
        return new SubscriptionBuilder();
    }

    public SubscriptionBuilder withSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
        return this;
    }

    public SubscriptionBuilder withData(ShortMessage data) {
        this.data = data;
        return this;
    }

    public SubscriptionBuilder withSessionId(String sessionId) {
        this.sessionId = sessionId;
        return this;
    }

    public SubscriptionBuilder but() {
        return aSubscription().withSubscriptionId(subscriptionId).withData(data).withSessionId(sessionId);
    }

    public SubscriptionModel build() {
        SubscriptionModel subscription = new SubscriptionModel();
        subscription.setSubscriptionId(subscriptionId);
        subscription.setData(data);
        subscription.setSessionId(sessionId);
        return subscription;
    }
}
