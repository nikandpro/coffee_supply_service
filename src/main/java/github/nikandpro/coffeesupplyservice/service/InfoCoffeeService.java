package github.nikandpro.coffeesupplyservice.service;

import github.nikandpro.coffeesupplyservice.dto.CoffeeDto;
import github.nikandpro.coffeesupplyservice.entity.Grain;
import github.nikandpro.coffeesupplyservice.entity.Roast;
import github.nikandpro.coffeesupplyservice.repository.GrainRepository;
import github.nikandpro.coffeesupplyservice.repository.RoastRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InfoCoffeeService {

    private final GrainRepository grainRepository;
    private final RoastRepository roastRepository;

    public List<CoffeeDto> remainsCoffeeByCountries(List<String> countries) {
        if (countries != null) {
            List<Roast> roasts = roastRepository.findByCountryStats(countries);
        } else {
            List<Roast> roasts = roastRepository.findAll();
        }
    }

    public List<CoffeeDto> remainsCoffeeByTypes(String types) {
        if (types == null) {
            List<Grain> grains = grainRepository.findByGrainType(types);
        }
    }
}
