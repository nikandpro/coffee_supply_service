package github.nikandpro.coffeesupplyservice.service;

import github.nikandpro.coffeesupplyservice.dto.RoastDto;
import github.nikandpro.coffeesupplyservice.mapper.RoastMapper;
import github.nikandpro.coffeesupplyservice.repository.RoastRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoastService {

    private final RoastRepository roastRepository;
    private final RoastMapper roastMapper;

    public void save(RoastDto roastDto) {
        validateRoast(roastDto);
        roastRepository.save(roastMapper.toEntity(roastDto));
        log.info("save Roast");
    }

    protected void validateRoast(RoastDto roastDto) {
        if (roastDto == null) {
            log.info("roast is null");
            throw new NullPointerException("roastDto is null");
        }
    }
}
