package br.edu.ifpr.cars.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.edu.ifpr.cars.domain.TravelRequest;
import br.edu.ifpr.cars.service.TravelService;

@RestController
@RequestMapping("/api/travel")
public class TravelController {

    @Autowired
    private TravelService travelService;

    @GetMapping
    public List<TravelRequest> listTravels() {
        return travelService.listTravels();
    }

    @GetMapping("/{travelId}")
    public TravelRequest listTravel(@PathVariable Long travelId) {
        return travelService.findTravel(travelId);
    }

    @PostMapping
    public TravelRequest createTravel(@RequestBody TravelRequest travelRequest) {
        return travelService.createTravel(travelRequest.getOrigin(), travelRequest.getDestination(),
                travelRequest.getPassenger().getId());
    }

    @PutMapping("/{travelId}/accept")
    public TravelRequest acceptTravel(@PathVariable Long travelId,
            @RequestParam Long driverId) {
        return travelService.acceptTravel(travelId, driverId);
    }

    @PutMapping("/{travelId}/refuse")
    public TravelRequest refuseTravel(@PathVariable Long travelId) {
        return travelService.refuseTravel(travelId);
    }

    @DeleteMapping("/{travelId}")
    public ResponseEntity<Void> deleteTravel(@PathVariable Long travelId) {
        travelService.deleteTravel(travelId);
        return ResponseEntity.noContent().build();
    }
}
