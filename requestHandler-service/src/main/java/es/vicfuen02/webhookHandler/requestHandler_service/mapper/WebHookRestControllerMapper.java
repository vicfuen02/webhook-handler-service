package es.vicfuen02.webhookHandler.requestHandler_service.mapper;

import es.vicfuen02.webhookHandler.requestHandler_service.controller.dto.WebHookReqDto;
import es.vicfuen02.webhookHandler.requestHandler_service.service.model.WebHookModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class WebHookRestControllerMapper {

    public abstract WebHookModel toModel(WebHookReqDto dto);

}
