package es.vicfuen02.webhookHandler.requestHandler.idempotency.controller;

import es.vicfuen02.webhookHandler.common.idempotency.service.IdempotencyHelperImpl;
import es.vicfuen02.webhookHandler.common.idempotency.service.IdempotencyModel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/idempotent")
public class IdempotencyController {

    private IdempotencyHelperImpl idempotencyHelper;

    @GetMapping
    public List<IdempotencyModel> outbox() {
        return idempotencyHelper.getIdempotents();
    }

}
