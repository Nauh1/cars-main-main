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
import br.edu.ifpr.cars.domain.PassagerRepository;

@Service
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class PassagerController {
    @Autowired
    PassagerRepository passagerRepository;

    @GetMapping("/passager")
    public List<Passager> listPassagers() {
        return passagerRepository.findAll();
    }

     // definir uma Exception personalizada
    @GetMapping("/passager/{id}")
    public Passager findPassager(@PathVariable("id") Long id) {
        return passagerRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/passager")
    public Passager createPassager(@RequestBody Passager passager) {
        return passagerRepository.save(passager);
    }

    // update
    @PutMapping("/passager/{id}")
    public Passager fullUpdatePassager(@PathVariable("id") Long id,
            @RequestBody Passager passager) {
                Passager foundPassager = findPassager(id);
        foundPassager.setName(passager.getName());
        foundPassager.setBirthDate(passager.getBirthDate());
        return passagerRepository.save(foundPassager);
    }

    @PatchMapping("/passager/{id}")
    public Passager incrementalUpdatePassager(@PathVariable("id") Long id,
            @RequestBody Passager passager){
                Passager foundPassager = findPassager(id);
            
                foundPassager.setName(Optional.ofNullable(passager.getName())
            .orElse(foundPassager.getName()));

            foundPassager.setBirthDate(Optional.ofNullable(passager.getBirthDate())
            .orElse(foundPassager.getBirthDate()));

            return passagerRepository.save(foundPassager);
    }

    @DeleteMapping("/passager/{id}")
    public void deletePassager(@PathVariable("id") Long id){
        passagerRepository.deleteById(id);
    }

}
