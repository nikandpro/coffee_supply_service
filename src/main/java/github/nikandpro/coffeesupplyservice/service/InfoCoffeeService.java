package github.nikandpro.coffeesupplyservice.service;

import github.nikandpro.coffeesupplyservice.dto.BrigadeDto;
import github.nikandpro.coffeesupplyservice.dto.CountryDto;
import github.nikandpro.coffeesupplyservice.entity.Brigade;
import github.nikandpro.coffeesupplyservice.entity.CountryStats;
import github.nikandpro.coffeesupplyservice.entity.Grain;
import github.nikandpro.coffeesupplyservice.entity.Roast;
import github.nikandpro.coffeesupplyservice.repository.BrigadeRepository;
import github.nikandpro.coffeesupplyservice.repository.CountryStatsRepository;
import github.nikandpro.coffeesupplyservice.repository.GrainRepository;
import github.nikandpro.coffeesupplyservice.repository.RoastRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InfoCoffeeService {

    private final GrainRepository grainRepository;
    private final RoastRepository roastRepository;
    private final BrigadeRepository brigadeRepository;
    private final CountryStatsRepository countryStatsRepository;

    @Transactional
    public Integer remainsCoffeeByCountry(List<String> countries) {
        List<Grain> grains;
        if (!countries.isEmpty()) {
            grains = grainRepository.findByCountryStats(countries);
        } else {
            grains = grainRepository.findAll();
        }
        return roastRepository.findSumByGrainId(grains.stream()
                .map(Grain::getId)
                .toList());
    }

    @Transactional
    public Integer remainsCoffeeByTypes(List<String> types) {
        List<Grain> grains;
        if (!types.isEmpty()) {
            grains = grainRepository.findByCountryStats(types);
        } else {
            grains = grainRepository.findAll();
        }
        return roastRepository.findSumByGrainId(grains.stream()
                .map(Grain::getId)
                .toList());
    }

    // некрасиво, но один запрос в бд
    @Transactional
    public List<BrigadeDto> getBrigadeLosses() {
        List<BrigadeDto> brigadeDtos = new ArrayList<>();
        List<Brigade> brigades = brigadeRepository.findAll();

        List<Roast> roasts = roastRepository.findAllGrainByBrigades(brigades.stream()
                .map(Brigade::getId)
                .toList());

        brigades.forEach(b -> {
            List<Roast> roastsByBrigade = roasts.stream()
                    .filter(r -> r.getBrigade().getId().equals(b.getId()))
                    .toList();
            double loss_percentage = foundLossPercentage(roastsByBrigade);
            brigadeDtos.add(new BrigadeDto(b.getId(), loss_percentage));
        });

        return brigadeDtos;
    }

    // один запрос в бд
    @Transactional
    public List<CountryDto> getCountryLosses() {
        List<CountryDto> countryDtos = new ArrayList<>();
        List<CountryStats> countryStats = countryStatsRepository.findAll();

        List<Roast> roasts = roastRepository.findAllGrainByCountries(countryStats.stream()
                .map(CountryStats::getId)
                .toList());

        countryStats.forEach(c -> {
            List<Roast> roastsByBrigade = roasts.stream()
                    .filter(r -> r.getCountry().getId().equals(c.getId()))
                    .toList();
            double loss_percentage = foundLossPercentage(roastsByBrigade);
            countryDtos.add(new CountryDto(c.getId(), c.getCountry(), loss_percentage));
        });

        return countryDtos;
    }

    public Double foundLossPercentage(List<Roast> roasts) {
        int sumQuantityTaken = roasts.stream().mapToInt(Roast::getQuantityTaken).sum();
        int sumWeightOut = roasts.stream().mapToInt(Roast::getWeightOut).sum();

        return ((double) (sumQuantityTaken - sumWeightOut) / sumQuantityTaken) * 100;
    }

}
