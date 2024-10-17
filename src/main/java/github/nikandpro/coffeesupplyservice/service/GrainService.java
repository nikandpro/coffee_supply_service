package github.nikandpro.coffeesupplyservice.service;

import github.nikandpro.coffeesupplyservice.dto.GrainDto;
import github.nikandpro.coffeesupplyservice.entity.Grain;
import github.nikandpro.coffeesupplyservice.mapper.GrainMapper;
import github.nikandpro.coffeesupplyservice.repository.GrainRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class GrainService {

    private final GrainRepository grainRepository;
    private final GrainMapper grainMapper;

    public void saveGrain(GrainDto grainDto) {
        validateGrain(grainDto);
        Grain grain = grainMapper.toEntity(grainDto);
        grainRepository.save(grain);
    }

    public void validateGrain(GrainDto grainDto) {
        if (grainDto == null) {
            log.info("Grain is null");
            throw new NullPointerException("grainDto can not be null");
        }
    }
}
