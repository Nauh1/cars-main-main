package br.edu.ifpr.cars.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.edu.ifpr.cars.domain.TravelRequestStatus;
import br.edu.ifpr.cars.repository.TravelRepository;
import br.edu.ifpr.cars.domain.TravelRequest;

@Service
public class TravelService {

    @Autowired
    private TravelRepository travelRepository;

    public TravelRequest createTravel(TravelRequest travel) {
        travel.setStatus(TravelRequestStatus.CREATED);
        return travelRepository.save(travel);
    }

    public TravelRequest acceptTravel(Long id) {
        //procura a viagem pelo id caso não exista lança um erro 404
        TravelRequest travel = travelRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        //validaçãos de estado da viagem, lança erro 400
        if (travel.getStatus() == TravelRequestStatus.ACCEPTED ||
            travel.getStatus() == TravelRequestStatus.FINISHED) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Viagem já aceita ou finalizada");
        }

        //verifica se o estado é CREATED para aceitar, retorna erro 400
        if (travel.getStatus() != TravelRequestStatus.CREATED) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Só é possível aceitar viagens em estado CREATED");
        }

        travel.setStatus(TravelRequestStatus.ACCEPTED);
        return travelRepository.save(travel);
    }

    public TravelRequest refuseTravel(Long id) {
         //procura a viagem e lança erro 404 caso não exista
        TravelRequest travel = travelRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        //atualiza o estado da viagem para REFUSED
        travel.setStatus(TravelRequestStatus.REFUSED);
        return travelRepository.save(travel);
    }
}

