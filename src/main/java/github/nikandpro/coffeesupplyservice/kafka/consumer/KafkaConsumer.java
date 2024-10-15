package github.nikandpro.coffeesupplyservice.kafka.consumer;

import github.nikandpro.coffeesupplyservice.dto.GrainDto;
import github.nikandpro.coffeesupplyservice.kafka.model.GrainEvent;
import github.nikandpro.coffeesupplyservice.mapper.GrainMapper;
import github.nikandpro.coffeesupplyservice.service.GrainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumer {

    private final GrainMapper grainMapper;
    private final GrainService grainService;

    @KafkaListener(topics = "${spring.data.kafka.topic-name.grain}", groupId = "1")
    public void listener(GrainEvent grainEvent, Acknowledgment ack) {
        GrainDto grainDto = grainMapper.toDto(grainEvent);
        grainService.saveGrain(grainDto);
        ack.acknowledge();
        log.info("save grain event: {}", grainEvent);
    }
}
