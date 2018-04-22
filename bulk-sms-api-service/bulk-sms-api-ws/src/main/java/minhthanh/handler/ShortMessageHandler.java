package minhthanh.handler;

import com.clct.chainable.ChainableService;
import com.clct.models.ShortMessage;
import com.clct.queues.AbstractConsumer;
import minhthanh.queue.WsQueueInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import smpp.handles.DeliverSmHandler;

/**
 * Created by luyenchu on 6/21/16.
 */
public abstract class ShortMessageHandler extends AbstractConsumer<ShortMessage> {
    private static final Logger LOG = LoggerFactory.getLogger(DeliverSmHandler.class);

    public ShortMessageHandler(ChainableService<ShortMessage> processor) {
        super(WsQueueInstance.DEF.incomingMessageQueue, processor);
    }

    public void handleInterruptedException(InterruptedException ex) {
        LOG.info("InterruptedException: " + ex);
    }

}
