package es.vicfuen02.webhookHandler.common.idempotency.service;

import es.vicfuen02.webhookHandler.common.idempotency.mapper.IdempotencyMapper;
import es.vicfuen02.webhookHandler.common.idempotency.respository.IdempotencyEntity;
import es.vicfuen02.webhookHandler.common.idempotency.respository.IdempotencyJpaRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class IdempotencyHelperImpl {


    private IdempotencyJpaRepository idempotencyJpaRepository;
    private IdempotencyMapper idempotencyMapper;


    public List<IdempotencyModel> getIdempotents() {
        log.info("Getting idempotent messages INIT");
        return idempotencyMapper.toModelList(
                idempotencyJpaRepository.findAll()
        );
    }

    public Optional<IdempotencyModel> getIdempotentById(String idempotentId, String service) {
        log.info("Getting idempotent message by idempotent id INIT");
        return idempotencyJpaRepository.findByIdempotentIdAndService(idempotentId, service)
                .map(idempotencyMapper::toModel);
    }

    public IdempotencyModel save(String idempotentId, IdempotencyEnum status, String service) {
        log.info("Saving in idempotent table");
        return idempotencyMapper.toModel(
                idempotencyJpaRepository.save(
                        IdempotencyEntity.builder()
                                .idempotentId(idempotentId)
                                .service(service)
                                .status(status)
                                .build()
                )
        );
    }




}
