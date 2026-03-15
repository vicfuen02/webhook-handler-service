package es.vicfuen02.webhookHandler.requestHandler.controller;

import es.vicfuen02.webhookHandler.requestHandler.controller.dto.WebHookReqDto;
import es.vicfuen02.webhookHandler.requestHandler.mapper.WebHookRestControllerMapper;
import es.vicfuen02.webhookHandler.requestHandler.service.WebHookService;
import es.vicfuen02.webhookHandler.requestHandler.outbox.OutboxModel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/webhook")
public class WebHookController {


    private final WebHookService webHookService;
    private final WebHookRestControllerMapper webHookRestControllerMapper;

    @PostMapping
    public void webhook(@RequestBody WebHookReqDto webHookReqDto
                        , @RequestHeader("X-WebHook-signature") String signature) {

        webHookService.webhook(
                webHookRestControllerMapper.toModel(webHookReqDto)
        );
    }


}
