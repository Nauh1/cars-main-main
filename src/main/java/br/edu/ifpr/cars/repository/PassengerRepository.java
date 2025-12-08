package br.edu.ifpr.cars.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifpr.cars.domain.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {

}
