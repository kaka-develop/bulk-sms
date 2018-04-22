package minhthanh.handler;

import com.clct.chainable.ChainableService;
import com.clct.models.ShortMessage;

/**
 * Created by luyenchu on 7/12/16.
 */
public class DefaultShortMessageHandler extends ShortMessageHandler {
    public DefaultShortMessageHandler(ChainableService<ShortMessage> processor) {
        super(processor);
    }
}
