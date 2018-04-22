package minhthanh.queue;

import com.clct.models.ShortMessage;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by tung on 7/11/2016.
 */
public enum WsQueueInstance {
    DEF;

    WsQueueInstance() {
    }

    public BlockingQueue<ShortMessage> incomingMessageQueue = new LinkedBlockingQueue<>();
}
