package es.vicfuen02.webhookHandler.common.idempotency.mapper;

import es.vicfuen02.webhookHandler.common.idempotency.respository.IdempotencyEntity;
import es.vicfuen02.webhookHandler.common.idempotency.service.IdempotencyModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class IdempotencyMapper {


    public abstract IdempotencyModel toModel(IdempotencyEntity dto);
    public abstract List<IdempotencyModel> toModelList(List<IdempotencyEntity> list);

}

