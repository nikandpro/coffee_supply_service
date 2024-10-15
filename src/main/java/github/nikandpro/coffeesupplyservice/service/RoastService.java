package github.nikandpro.coffeesupplyservice.service;

import github.nikandpro.coffeesupplyservice.entity.Roast;
import github.nikandpro.coffeesupplyservice.repository.RoastRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoastService {

    private final RoastRepository roastRepository;

    public void save(Roast roast) {
        roastRepository.save(roast);
        log.info("save Roast");
    }
}
