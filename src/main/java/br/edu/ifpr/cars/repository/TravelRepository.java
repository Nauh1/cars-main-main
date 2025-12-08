package br.edu.ifpr.cars.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifpr.cars.domain.TravelRequest;

public interface TravelRepository extends JpaRepository<TravelRequest, Long> {

}
