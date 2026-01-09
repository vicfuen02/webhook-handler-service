package es.vicfuen02.webhookHandler.requestHandler_service.controller;

import es.vicfuen02.webhookHandler.requestHandler_service.controller.dto.WebHookReqDto;
import es.vicfuen02.webhookHandler.requestHandler_service.mapper.WebHookRestControllerMapper;
import es.vicfuen02.webhookHandler.requestHandler_service.service.WebHookService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@AllArgsConstructor
@RestController("/webhook")
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
