
# Passenger:

#### POST: http://localhost:8080/api/passenger
  Body JSON:
  ```json
{
    "name": "user",
    "email": "user@gmail.com",
    "cpf": "123.456.789-09",
    "birthDate": "01/01/2001"
}
```

#### GET: http://localhost:8080/api/passenger

#### GET: http://localhost:8080/api/passenger/{id}

#### DELETE: http://localhost:8080/api/passenger/{id}

# Driver:

#### POST: http://localhost:8080/api/drivers
  Body JSON:
  ```json
{
    "name": "Carlos",
    "email": "carlos@gmail.com",
    "cpf": "11144477735",
    "birthDate": "10/04/1990",
    "placa": "BRA2E19",
    "cnh": "98765432101",
    "carro": 2018,
    "comentario": "muitolegal"
}
```

#### GET: http://localhost:8080/api/drivers

#### GET: http://localhost:8080/api/drivers/{id}

#### DELETE: http://localhost:8080/api/drivers/{id}

# Travel:

#### POST: http://localhost:8080/api/travel
  Body JSON:
  ```json
{
    "origin": "centro",
    "destination": "Instituto Federal",
    "passenger": {
        "id": 1
    }
}
```

#### PUT http://localhost:8080/api/travel/{id}/accept?driverId=1

#### PUT http://localhost:8080/api/travel/{id}/refuse

#### GET: http://localhost:8080/api/travel

#### GET: http://localhost:8080/api/travel/{id}

#### DELETE http://localhost:8080/api/travel/{id}


> **Nota:** É preciso criar um driver e passageiro antes de criar uma viagem.
