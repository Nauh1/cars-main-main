package br.edu.ifpr.cars.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifpr.cars.domain.Driver;

public interface DriverRepository extends JpaRepository<Driver, Long> {

}
