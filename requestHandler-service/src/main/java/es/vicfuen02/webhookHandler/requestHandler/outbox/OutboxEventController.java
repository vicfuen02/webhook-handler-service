package es.vicfuen02.webhookHandler.requestHandler.outbox;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/outbox")
public class OutboxEventController {

    private OutboxEventHelperImpl outboxEventHelper;

    @GetMapping
    public List<OutboxModel> outbox() {
        return outboxEventHelper.getOutboxEvents();
    }

}
