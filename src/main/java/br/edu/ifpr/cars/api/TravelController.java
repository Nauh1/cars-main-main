package br.edu.ifpr.cars.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.edu.ifpr.cars.domain.TravelRequest;
import br.edu.ifpr.cars.service.TravelService;

@RestController
@RequestMapping("/api/travel")
public class TravelController {

    private final TravelService service;

    public TravelController(TravelService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<TravelRequest> create(@RequestBody TravelRequest travel) {
        //define a resposta http com status 201 e o body da resposta o objeto travel criado
        return ResponseEntity.status(201).body(service.createTravel(travel));
    }

    @PutMapping("/{id}/accept")
    public ResponseEntity<TravelRequest> accept(@PathVariable Long id) {
        return ResponseEntity.status(200).body(service.acceptTravel(id));
    }

    @PutMapping("/{id}/refuse")
    public ResponseEntity<TravelRequest> refuse(@PathVariable Long id) {
        return ResponseEntity.status(200).body(service.refuseTravel(id));
    }
}

