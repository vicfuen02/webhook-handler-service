package es.vicfuen02.webhookHandler.requestHandler.service;


import es.vicfuen02.webhookHandler.common.model.WebHookModel;
import es.vicfuen02.webhookHandler.requestHandler.service.model.OutboxModel;

import java.util.List;

public interface WebHookService {

    void webhook(WebHookModel webHookModel);

    List<OutboxModel> getOutboxEvents();

}
