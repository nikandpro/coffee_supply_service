package github.nikandpro.coffeesupplyservice.controller;

import github.nikandpro.coffeesupplyservice.dto.CoffeeDto;
import github.nikandpro.coffeesupplyservice.service.InfoCoffeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class InfoCoffeeController {

    private final InfoCoffeeService infoCoffeeService;

    @GetMapping()
    public ResponseEntity<List<CoffeeDto>> getAllCoffeeBeans(
            @RequestParam(required = false) String country,
            @RequestParam(required = false) String variety) {
        List<CoffeeDto> coffeeBeans = infoCoffeeService.infoCoffee(country, variety);
        return new ResponseEntity<>(coffeeBeans, HttpStatus.OK);
    }

}
