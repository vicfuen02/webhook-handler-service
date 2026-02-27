package es.vicfuen02.webhookHandler.requestHandlerService.mapper;

import es.vicfuen02.webhookHandler.requestHandlerService.controller.dto.WebHookReqDto;
import es.vicfuen02.webhookHandler.requestHandlerService.service.model.WebHookModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class WebHookRestControllerMapper {

    public abstract WebHookModel toModel(WebHookReqDto dto);

}
