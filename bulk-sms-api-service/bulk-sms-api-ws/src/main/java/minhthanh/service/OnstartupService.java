package minhthanh.service;

import minhthanh.handler.ShortMessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by luyenchu on 7/12/16.
 */
public class OnstartupService {
    private static final Logger LOG = LoggerFactory.getLogger(OnstartupService.class);
    private static final String SHORT_MESSAGE_HANDLER_BULK_API_WS = "SHORT_MESSAGE_HANDLER_BULK_API_WS";
    private int noOfHandler;

    private ShortMessageHandler handler;

    public OnstartupService() {
        noOfHandler = 1;
    }

    public OnstartupService(ShortMessageHandler handler, int noOfHandler) {
        this.handler = handler;
        this.noOfHandler = noOfHandler;
    }

    public void init() {
        for (int i = 0; i < noOfHandler; i++) {
            createThread(handler, i);
        }
        LOG.info("HANDLERS FOR INCOMMING SHORTMESSAGE HAVE BEEN STARTED!");
    }

    private void createThread(ShortMessageHandler handler, int i) {
        Thread t = new Thread(handler);
        t.setName(SHORT_MESSAGE_HANDLER_BULK_API_WS + "_" + i);
        t.start();
    }

    public ShortMessageHandler getHandler() {
        return handler;
    }

    public void setHandler(ShortMessageHandler handler) {
        this.handler = handler;
    }

    public int getNoOfHandler() {
        return noOfHandler;
    }

    public void setNoOfHandler(int noOfHandler) {
        this.noOfHandler = noOfHandler;
    }
}
