package br.edu.ifpr.cars.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifpr.cars.domain.Passager;

public interface PassagerRepository extends JpaRepository<Passager, Long> {

}
