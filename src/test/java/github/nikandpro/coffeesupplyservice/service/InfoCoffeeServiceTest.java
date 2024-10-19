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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class InfoCoffeeServiceTest {

    @InjectMocks
    private InfoCoffeeService infoCoffeeService;

    @Mock
    private GrainRepository grainRepository;

    @Mock
    private RoastRepository roastRepository;

    @Mock
    private BrigadeRepository brigadeRepository;

    @Mock
    private CountryStatsRepository countryStatsRepository;

    private Grain grain1, grain2;
    private Roast roast1, roast2, roast3;
    private Brigade brigade1, brigade2, brigade3;
    private CountryStats countryStats1, countryStats2;
    private UUID tetsBrigadeId;

    @BeforeEach
    public void setUp() {
        tetsBrigadeId = UUID.randomUUID();

        // Initialize brigades
        brigade1 = new Brigade(UUID.randomUUID());
        brigade2 = new Brigade(tetsBrigadeId);
        brigade3 = new Brigade(UUID.randomUUID());

        countryStats1 = new CountryStats();
        countryStats1.setId(1L);
        countryStats1.setCountry("Brazil");
        countryStats2 = new CountryStats();
        countryStats2.setId(2L);



        // Initialize grains
        grain1 = new Grain();
        grain1.setId(1L);
        grain2 = new Grain();
        grain2.setId(2L);

        // Initialize roasts
        roast1 = new Roast();
        roast1.setQuantityTaken(100);
        roast1.setWeightOut(80);
        roast1.setGrain(grain1);
        roast1.setBrigade(brigade1);
        roast1.setCountry(countryStats1);

        roast2 = new Roast();
        roast2.setQuantityTaken(200);
        roast2.setWeightOut(150);
        roast2.setGrain(grain1);
        roast2.setBrigade(brigade2);
        roast2.setCountry(countryStats2);

        roast3 = new Roast();
        roast3.setQuantityTaken(300);
        roast3.setWeightOut(250);
        roast3.setGrain(grain2);
        roast3.setBrigade(brigade3);
    }

    @Test
    public void testRemainsCoffeeByCountry_WithCountries() {
        // Arrange
        List<String> countries = Arrays.asList("Brazil", "Colombia");
        when(grainRepository.findByCountryStats(countries)).thenReturn(Arrays.asList(grain1, grain2));
        when(roastRepository.findSumByGrainId(anyList())).thenReturn(350); // 100 + 200 + 300

        // Act
        Integer result = infoCoffeeService.remainsCoffeeByCountry(countries);

        // Assert
        assertEquals(350, result);
        verify(grainRepository, times(1)).findByCountryStats(countries);
        verify(roastRepository, times(1)).findSumByGrainId(Arrays.asList(1L, 2L));
    }

    @Test
    public void testRemainsCoffeeByCountry_EmptyCountries() {
        // Arrange
        List<String> countries = new ArrayList<>();
        when(grainRepository.findAll()).thenReturn(Arrays.asList(grain1, grain2));
        when(roastRepository.findSumByGrainId(anyList())).thenReturn(350); // 100 + 200 + 300

        // Act
        Integer result = infoCoffeeService.remainsCoffeeByCountry(countries);

        // Assert
        assertEquals(350, result);
        verify(grainRepository, times(1)).findAll();
        verify(roastRepository, times(1)).findSumByGrainId(Arrays.asList(1L, 2L));
    }

    @Test
    public void testRemainsCoffeeByTypes_WithTypes() {
        // Arrange
        List<String> types = Arrays.asList("Arabica", "Robusta");
        when(grainRepository.findByCountryStats(types)).thenReturn(Arrays.asList(grain1, grain2));
        when(roastRepository.findSumByGrainId(anyList())).thenReturn(350); // 100 + 200 + 300

        // Act
        Integer result = infoCoffeeService.remainsCoffeeByTypes(types);

        // Assert
        assertEquals(350, result);
        verify(grainRepository, times(1)).findByCountryStats(types);
        verify(roastRepository, times(1)).findSumByGrainId(Arrays.asList(1L, 2L));
    }

    @Test
    public void testRemainsCoffeeByTypes_EmptyTypes() {
        // Arrange
        List<String> types = new ArrayList<>();
        when(grainRepository.findAll()).thenReturn(Arrays.asList(grain1, grain2));
        when(roastRepository.findSumByGrainId(anyList())).thenReturn(350); // 100 + 200 + 300

        // Act
        Integer result = infoCoffeeService.remainsCoffeeByTypes(types);

        // Assert
        assertEquals(350, result);
        verify(grainRepository, times(1)).findAll();
        verify(roastRepository, times(1)).findSumByGrainId(Arrays.asList(1L, 2L));
    }

    @Test
    public void testGetBrigadeLosses() {

        List<Brigade> brigades = Arrays.asList(brigade2);

        // Mocking repository methods.
        when(brigadeRepository.findAll()).thenReturn(brigades);
        when(roastRepository.findAllGrainByBrigades(anyList())).thenReturn(Arrays.asList(roast1, roast2, roast3));

        // Act
        List<BrigadeDto> result = infoCoffeeService.getBrigadeLosses();

        // Assert
        assertEquals(1, result.size());
        assertEquals(tetsBrigadeId, result.get(0).getId());
        assertEquals((double) (200 - 150) / 200 * 100, result.get(0).getLossesCoffee());
    }

    @Test
    public void testGetCountryLosses() {

        List<CountryStats> countries = Arrays.asList(countryStats1);

        when(countryStatsRepository.findAll()).thenReturn(countries);
        when(roastRepository.findAllGrainByCountries(anyList())).thenReturn(Arrays.asList(roast1));

        // Act
        List<CountryDto> result = infoCoffeeService.getCountryLosses();

        // Assert
        assertEquals(1, result.size());
        assertEquals(countryStats1.getId(), result.get(0).getId());
        assertEquals(countryStats1.getCountry(), result.get(0).getName());
        assertEquals((double) (100 - 80) / 100 * 100, result.get(0).getLossesCoffee());
    }
}
