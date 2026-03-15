package es.vicfuen02.webhookHandler.requestHandler.service;


import es.vicfuen02.webhookHandler.common.model.WebHookModel;

public interface WebHookService {

    void webhook(WebHookModel webHookModel);

}
