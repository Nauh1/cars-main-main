package br.edu.ifpr.cars.domain;

import java.time.LocalDate;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.edu.ifpr.cars.validate.AnoFabricado;
import br.edu.ifpr.cars.validate.CNH;
import br.edu.ifpr.cars.validate.PalavrasOfensivas;
import br.edu.ifpr.cars.validate.Placa;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity
@Data
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Pattern(regexp = "^[^\\s]+$")
    @NotBlank(message = "O nome não pode ser vazio")
    @Size(min = 3, max = 50)
    String name;

    @Pattern(regexp = "^[^\\s]+$")
    @NotBlank(message = "O e-mail não pode ser vazio")
    @Email
    String email;

    @Pattern(regexp = "^[^\\s]+$")
    @NotBlank(message = "O cpf não pode ser vazio")
    @CPF
    String cpf;

    @NotNull(message = "A data de nascimento é obrigatória")
    @Past(message = "A data de nascimento deve estar no passado")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    LocalDate birthDate;

    @NotBlank(message = "A placa não pode ser vazio")
    @Placa
    String placa;

    @NotBlank(message = "A CNH não pode ser vazio")
    @CNH
    String cnh;

    @NotNull(message = "o ano do carro não pode ser vazio")
    @AnoFabricado
    Integer carro;

    @PalavrasOfensivas
    String comentario;

}


/*
{
  "name": "Carlos",
  "email": "carlos@test.com",
  "cpf": "11144477735",
  "birthDate": "10/04/1990",
  "placa": "BRA2E19",
  "cnh": "98765432101",
  "carro": 2018,
  "comentario": "muitolegal"
}
*/