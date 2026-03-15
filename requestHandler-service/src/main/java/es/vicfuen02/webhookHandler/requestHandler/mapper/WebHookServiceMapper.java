package es.vicfuen02.webhookHandler.requestHandler.mapper;

import es.vicfuen02.webhookHandler.requestHandler.outbox.OutboxEntity;
import es.vicfuen02.webhookHandler.requestHandler.outbox.OutboxModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class WebHookServiceMapper {


    public abstract OutboxModel toModel(OutboxEntity dto);
    public abstract List<OutboxModel> toModelList(List<OutboxEntity> list);

}

