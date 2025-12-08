package br.edu.ifpr.cars.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.edu.ifpr.cars.domain.TravelRequestStatus;
import br.edu.ifpr.cars.repository.DriverRepository;
import br.edu.ifpr.cars.repository.PassengerRepository;
import br.edu.ifpr.cars.repository.TravelRepository;
import br.edu.ifpr.cars.domain.Driver;
import br.edu.ifpr.cars.domain.Passenger;
import br.edu.ifpr.cars.domain.TravelRequest;

@Service
public class TravelService {

    @Autowired
    private TravelRepository travelRepository;

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private DriverRepository driverRepository;


    //listar travels
    public List<TravelRequest> listTravels() {
        return travelRepository.findAll();
    }

    public TravelRequest findTravel(Long travelId) {
        return travelRepository.findById(travelId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Viagem não encontrada"));
    }

    // Criação da viagem
    public TravelRequest createTravel(String origin, String destination, Long passengerId) {
        Passenger passenger = passengerRepository.findById(passengerId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Passageiro não encontrado"));

        TravelRequest travel = new TravelRequest();
        travel.setOrigin(origin);
        travel.setDestination(destination);
        travel.setPassenger(passenger);
        travel.setStatus(TravelRequestStatus.CREATED);

        return travelRepository.save(travel);
    }

    // Aceitar viagem (atribui driver)
    public TravelRequest acceptTravel(Long travelId, Long driverId) {
        TravelRequest travel = travelRepository.findById(travelId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Viagem não encontrada"));

        if (travel.getStatus() != TravelRequestStatus.CREATED) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Só é possível aceitar viagens em estado CREATED");
        }

        Driver driver = driverRepository.findById(driverId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Motorista não encontrado"));

        travel.setDriver(driver);
        travel.setStatus(TravelRequestStatus.ACCEPTED);

        return travelRepository.save(travel);
    }

    public TravelRequest refuseTravel(Long travelId) {
        TravelRequest travel = travelRepository.findById(travelId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Viagem não encontrada"));

        travel.setStatus(TravelRequestStatus.REFUSED);
        return travelRepository.save(travel);
    }

    public void deleteTravel(Long travelId) {
        travelRepository.deleteById(travelId);
    }
}
