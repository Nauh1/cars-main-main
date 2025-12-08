package br.edu.ifpr.cars.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifpr.cars.domain.Driver;
import br.edu.ifpr.cars.service.DriverService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/drivers")
public class DriverController {

    private final DriverService driverService;

    @Autowired
    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @GetMapping
    public List<Driver> listDrivers() {
        return driverService.listDrivers();
    }

    @GetMapping("/{id}")
    public Driver findDriver(@PathVariable Long id) {
        return driverService.findDriver(id);
    }

    @PostMapping
    public Driver createDriver(@Valid @RequestBody Driver driver) {
        return driverService.createDriver(driver);
    }

    @PutMapping("/{id}")
    public Driver fullUpdate(
            @PathVariable Long id,
            @Valid @RequestBody Driver driver) {

        return driverService.fullUpdateDriver(id, driver);
    }

    @PatchMapping("/{id}")
    public Driver incrementalUpdate(
            @PathVariable Long id,
            @RequestBody Driver driver) {

        return driverService.incrementalUpdateDriver(id, driver);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        driverService.deleteDriver(id);
    }
}

