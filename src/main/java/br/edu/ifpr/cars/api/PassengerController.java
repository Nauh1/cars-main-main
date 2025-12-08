package br.edu.ifpr.cars.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.edu.ifpr.cars.domain.Passenger;
import br.edu.ifpr.cars.repository.PassengerRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/passenger", produces = MediaType.APPLICATION_JSON_VALUE)
public class PassengerController {

    @Autowired
    PassengerRepository passengerRepository;

    @GetMapping
    public List<Passenger> listPassengers() {
        return passengerRepository.findAll();
    }

    @GetMapping("/{id}")
    public Passenger findPassenger(@PathVariable("id") Long id) {
        return passengerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public Passenger createPassenger(@RequestBody @Valid Passenger passenger) {
        return passengerRepository.save(passenger);
    }

    @PutMapping("/{id}")
    public Passenger fullUpdatePassenger(@PathVariable("id") Long id, @RequestBody @Valid Passenger passenger) {
        Passenger found = findPassenger(id);
        found.setName(passenger.getName());
        found.setCpf(passenger.getCpf());
        found.setEmail(passenger.getEmail());
        found.setBirthDate(passenger.getBirthDate());
        return passengerRepository.save(found);
    }

    @PatchMapping("/{id}")
    public Passenger incrementalUpdatePassenger(@PathVariable("id") Long id, @RequestBody Passenger passenger) {
        Passenger found = findPassenger(id);
        if (passenger.getName() != null) found.setName(passenger.getName());
        if (passenger.getEmail() != null) found.setEmail(passenger.getEmail());
        if (passenger.getCpf() != null) found.setCpf(passenger.getCpf());
        if (passenger.getBirthDate() != null) found.setBirthDate(passenger.getBirthDate());
        return passengerRepository.save(found);
    }

    @DeleteMapping("/{id}")
    public void deletePassenger(@PathVariable("id") Long id) {
        passengerRepository.deleteById(id);
    }
}

