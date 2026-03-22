package es.vicfuen02.webhookHandler.requestHandler.outbox.mapper;

import es.vicfuen02.webhookHandler.requestHandler.outbox.repository.OutboxEntity;
import es.vicfuen02.webhookHandler.requestHandler.outbox.service.OutboxModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class OutboxEventMapper {


    public abstract OutboxModel toModel(OutboxEntity dto);
    public abstract List<OutboxModel> toModelList(List<OutboxEntity> list);

}

