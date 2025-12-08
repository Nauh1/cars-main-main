package br.edu.ifpr.cars.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class TravelRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String origin;
    private String destination;

    @ManyToOne
    @JoinColumn(name = "passenger_id")
    @NotNull(message = "Passageiro é obrigatório")
    private Passenger passenger;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;

    @Enumerated(EnumType.STRING)
    private TravelRequestStatus status;
}

/*
 * {
 * "origin": "centro",
 * "destination": "Instituto Federal",
 * "passenger": {
 * "id": 1
 * }
 * }
 */