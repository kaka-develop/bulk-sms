package minhthanh.ws;

import com.clct.models.ShortMessageBuilder;
import com.clct.rest.models.ObjectResponseModel;
import com.minhthanh.bulk.builders.SubscriptionBuilder;
import com.minhthanh.bulk.constants.ResponseErrorCode;
import com.minhthanh.bulk.models.SubscriptionModel;
import minhthanh.queue.WsQueueInstance;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Created by tung on 7/5/2016.
 */

@Path("/api-ws")
public class BulkApiWs {

    /**
     * @param subscription as a json format
     *                     {
     *                     "subscriptionId": "subscriptionId",
     *                     "data": {
     *                     "sequenceNumber": 0,
     *                     "destAddress": null,
     *                     "serviceType": null,
     *                     "shortMessage": null,
     *                     "sourceAddress": null,
     *                     "createdTime": null
     *                     },
     *                     "sessionId": "sessionId"
     *                     }
     * @return
     */
    @POST
    @Path("/sendOneSms")
    @Produces(MediaType.APPLICATION_JSON)
    public Response sendOneSms(SubscriptionModel subscription) {
        ObjectResponseModel<Boolean> retVal = new ObjectResponseModel<>();
        try {
            Objects.requireNonNull(subscription);
            Objects.requireNonNull(subscription.getData());
            //@Todo: check if subscription hasn't expired or quantity > 0;
            WsQueueInstance.DEF.incomingMessageQueue.put(subscription.getData());
        } catch (NullPointerException | InterruptedException ex) {
            retVal.setValue(false);
            retVal.setErrorCode(ResponseErrorCode.CONCURRENT_ERROR.errorCode);
            retVal.setErrorMessage(ResponseErrorCode.CONCURRENT_ERROR.message);
        }
        return Response.ok(retVal).build();
    }

    /**
     * @param messages as json
     * @return
     */
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/sendManySms")
    public Response sendManySms(List<SubscriptionModel> messages) {
        ObjectResponseModel<Boolean> retVal = new ObjectResponseModel<>();
        try {
            for (int i = 0; i < messages.size(); i++)
                WsQueueInstance.DEF.incomingMessageQueue.put(messages.get(i).getData());

            retVal.setValue(true);
        } catch (InterruptedException e) {
            retVal.setValue(false);
            retVal.setErrorCode(ResponseErrorCode.CONCURRENT_ERROR.errorCode);
            retVal.setErrorMessage(ResponseErrorCode.CONCURRENT_ERROR.message);
        }

        return Response.ok(retVal).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/sample")
    public Response sampleSubscription() {
        ShortMessageBuilder smb = ShortMessageBuilder.aMO().withCreatedTime(new Date());
        SubscriptionModel subscription = SubscriptionBuilder.aSubscription()
                .withSessionId("sessionId")
                .withSubscriptionId("subscriptionId")
                .withData(smb.build())
                .build();
        return Response.ok(subscription).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/mock")
    public Response mock() {

        return Response.ok("Ok").build();
    }
}
