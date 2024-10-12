package github.nikandpro.coffeesupplyservice.service;

import github.nikandpro.coffeesupplyservice.dto.GrainDto;
import github.nikandpro.coffeesupplyservice.entity.Grain;
import github.nikandpro.coffeesupplyservice.mapper.GrainMapper;
import github.nikandpro.coffeesupplyservice.repository.GrainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GrainService {

    private final GrainRepository grainRepository;
    private final GrainMapper grainMapper;

    public void saveGrain(GrainDto grainDto) {
        Grain grain = grainMapper.toEntity(grainDto);
        grainRepository.save(grain);
    }
}
