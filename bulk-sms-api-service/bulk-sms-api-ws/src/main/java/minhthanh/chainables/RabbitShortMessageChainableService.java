package minhthanh.chainables;

import com.clct.chainable.ChainableService;
import com.clct.models.ShortMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * Created by luyenchu on 6/24/16.
 */
public class RabbitShortMessageChainableService extends ChainableService<ShortMessage> {
    private static final Logger LOG = LoggerFactory.getLogger(RabbitShortMessageChainableService.class);

    private RabbitTemplate rabbitTemplate;

    @Override
    public ShortMessage processHeaderAndBody(ShortMessage shortMessage) {
        rabbitTemplate.convertAndSend(shortMessage);
        return shortMessage;
    }

    public RabbitTemplate getRabbitTemplate() {
        return rabbitTemplate;
    }

    public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
}
