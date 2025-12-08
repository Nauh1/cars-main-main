package br.edu.ifpr.cars.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
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

import br.edu.ifpr.cars.domain.Passager;
import br.edu.ifpr.cars.repository.PassagerRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/passager", produces = MediaType.APPLICATION_JSON_VALUE)
public class PassagerController {

    @Autowired
    PassagerRepository passagerRepository;

    @GetMapping
    public List<Passager> listPassagers() {
        return passagerRepository.findAll();
    }

    @GetMapping("/{id}")
    public Passager findPassager(@PathVariable("id") Long id) {
        return passagerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public Passager createPassager(@RequestBody @Valid Passager passager) {
        return passagerRepository.save(passager);
    }

    @PutMapping("/{id}")
    public Passager fullUpdatePassager(@PathVariable("id") Long id, @RequestBody @Valid Passager passager) {
        Passager found = findPassager(id);
        found.setName(passager.getName());
        found.setCpf(passager.getCpf()); // corrigi aqui
        found.setEmail(passager.getEmail());
        found.setBirthDate(passager.getBirthDate());
        return passagerRepository.save(found);
    }

    @PatchMapping("/{id}")
    public Passager incrementalUpdatePassager(@PathVariable("id") Long id, @RequestBody Passager passager) {
        Passager found = findPassager(id);
        if (passager.getName() != null) found.setName(passager.getName());
        if (passager.getEmail() != null) found.setEmail(passager.getEmail());
        if (passager.getCpf() != null) found.setCpf(passager.getCpf());
        if (passager.getBirthDate() != null) found.setBirthDate(passager.getBirthDate());
        return passagerRepository.save(found);
    }

    @DeleteMapping("/{id}")
    public void deletePassager(@PathVariable("id") Long id) {
        passagerRepository.deleteById(id);
    }
}

