package es.vicfuen02.webhookHandler.requestHandler.mapper;

import es.vicfuen02.webhookHandler.common.model.WebHookModel;
import es.vicfuen02.webhookHandler.requestHandler.controller.dto.WebHookReqDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class WebHookRestControllerMapper {

    public abstract WebHookModel toModel(WebHookReqDto dto);

}
