package es.vicfuen02.webhookHandler.requestHandler.outbox;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class OutboxEventMapper {


    public abstract OutboxModel toModel(OutboxEntity dto);
    public abstract List<OutboxModel> toModelList(List<OutboxEntity> list);

}

