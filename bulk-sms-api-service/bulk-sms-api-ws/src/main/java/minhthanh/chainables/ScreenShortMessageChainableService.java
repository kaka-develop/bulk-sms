package minhthanh.chainables;

import com.clct.chainable.ChainableService;
import com.clct.models.ShortMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by luyenchu on 6/24/16.
 */
public class ScreenShortMessageChainableService extends ChainableService<ShortMessage> {
    private static final Logger LOG = LoggerFactory.getLogger(ScreenShortMessageChainableService.class);

    @Override
    public ShortMessage processHeaderAndBody(ShortMessage shortMessage) {
        LOG.info("*********New Short Message*********");
        LOG.info("{}", shortMessage);
        return shortMessage;
    }
}
