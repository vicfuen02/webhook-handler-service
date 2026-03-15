package es.vicfuen02.webhookHandler.requestHandler.idempotency;

import es.vicfuen02.webhookHandler.requestHandler.outbox.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public IdempotencyModel getIdempotentById(Long id, String service) {
        log.info("Getting idempotent message by idempotent id INIT");
        return idempotencyMapper.toModel(
                idempotencyJpaRepository.findByIdempotentIdAndService(id,  service)
        );
    }

    public void save(Long id, IdempotencyEnum status, String service) {
        log.info("Saving in idempotent table INIT");

        idempotencyJpaRepository.save(
                IdempotencyEntity.builder()
                        .idempotentId(id)
                        .service(service)
                        .status(status)
                .build()
        );
        log.info("Saving in idempotent table END");
    }




}
