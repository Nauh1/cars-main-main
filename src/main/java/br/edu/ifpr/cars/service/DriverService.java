package br.edu.ifpr.cars.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.edu.ifpr.cars.domain.Driver;
import br.edu.ifpr.cars.repository.DriverRepository;

@Service
public class DriverService {

    private final DriverRepository driverRepository;

    @Autowired
    public DriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    public List<Driver> listDrivers() {
        return driverRepository.findAll();
    }

    public Driver findDriver(Long id) {
        return driverRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Driver createDriver(Driver driver) {
        return driverRepository.save(driver);
    }

    public Driver fullUpdateDriver(Long id, Driver driver) {
        Driver found = findDriver(id);

        found.setName(driver.getName());
        found.setEmail(driver.getEmail());
        found.setCpf(driver.getCpf());
        found.setBirthDate(driver.getBirthDate());
        found.setPlaca(driver.getPlaca());
        found.setCnh(driver.getCnh());
        found.setCarro(driver.getCarro());
        found.setComentario(driver.getComentario());

        return driverRepository.save(found);
    }

    public Driver incrementalUpdateDriver(Long id, Driver driver) {
        Driver found = findDriver(id);

        if (driver.getName() != null) found.setName(driver.getName());
        if (driver.getEmail() != null) found.setEmail(driver.getEmail());
        if (driver.getCpf() != null) found.setCpf(driver.getCpf());
        if (driver.getBirthDate() != null) found.setBirthDate(driver.getBirthDate());
        if (driver.getPlaca() != null) found.setPlaca(driver.getPlaca());
        if (driver.getCnh() != null) found.setCnh(driver.getCnh());
        if (driver.getCarro() != 0) found.setCarro(driver.getCarro());
        if (driver.getComentario() != null) found.setComentario(driver.getComentario());

        return driverRepository.save(found);
    }

    public void deleteDriver(Long id) {
        if (!driverRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        driverRepository.deleteById(id);
    }
}
