package github.nikandpro.coffeesupplyservice.controller;

import github.nikandpro.coffeesupplyservice.dto.BrigadeDto;
import github.nikandpro.coffeesupplyservice.dto.CountryDto;
import github.nikandpro.coffeesupplyservice.service.InfoCoffeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/info")
public class InfoCoffeeController {

    private final InfoCoffeeService infoCoffeeService;

    @GetMapping("/sumRestByCountry")
    public ResponseEntity<Integer> getRestCoffeeByCountry(@RequestBody List<String> country) {
        Integer sumOutputWeight = infoCoffeeService.remainsCoffeeByCountry(country);
        return new ResponseEntity<>(sumOutputWeight, HttpStatus.OK);
    }

    @GetMapping("/sumRestByTypes")
    public ResponseEntity<Integer> getRestCoffeeByTypes(@RequestBody List<String> typeGrain) {
        Integer sumOutputWeight = infoCoffeeService.remainsCoffeeByTypes(typeGrain);
        return new ResponseEntity<>(sumOutputWeight, HttpStatus.OK);
    }

    @GetMapping("/infoBrigade")
    public ResponseEntity<List<BrigadeDto>> getInfoBrigades() {
        List<BrigadeDto> brigades = infoCoffeeService.getBrigadeLosses();
        return new ResponseEntity<>(brigades, HttpStatus.OK);
    }

    @GetMapping("/infoCountry")
    public ResponseEntity<List<CountryDto>> getInfoCountries() {
        List<CountryDto> countries = infoCoffeeService.getCountryLosses();
        return new ResponseEntity<>(countries, HttpStatus.OK);
    }


}
