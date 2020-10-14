# Ejecutar BirrasApi

#### Pre-Requisitos:<br />
    Java8, Maven, Postman (o alguna herramienta parecida), Mongobd (de querer consumir local)

#### Pasos para la ejecución:
  1 - Descargar el proyecto BirrasApi.

  2 - Abrir un CMD.

  3 - Ingresar la siguiente línea "cd <PATH_DE_DESCARGA>\BirrasApi" para ubicarse en la carpeta del proyecto.

  4 - Ingresar la siguiente línea "mvn spring-boot:run" para ejecutar la aplicación.

  5 - Esperar a que el servicio levante.

  6 - Abrir Postman e ingrese las siguientes urls:
  
  <details>
    <summary>(GET) http://localhost:8080/api/birra/temperature-per-meetup?meetupID=${id} </summary>

    curl --location --request GET 'http://localhost:8080/api/birra/temperature-per-meetup?meetupID=5f84b6441c7e2c4bb59a372b' \
    --header 'username: camoreno' \
    --header 'token: asd12312312as123123'
    
  </details>
  
  <details>
    <summary>(GET) http://localhost:8080/api/birra/number-of-boxes-to-buy?meetupID=${id} </summary>
    
    curl --location --request GET 'http://localhost:8080/api/birra/number-of-boxes-to-buy?meetupID=5f84d87523f93e35a4cf79b2' \
    --header 'username: camoreno' \
    --header 'token: asd12312312as123123'
    
  </details>
  
  <details>
    <summary>(POST) http://localhost:8080/api/meet/check-in-meetup </summary>
    
    curl --location --request POST 'http://localhost:8080/api/meet/check-in-meetup' \
    --header 'username: litorres' \
    --header 'token: 345345fetrtedgdg' \
    --header 'Content-Type: application/json' \
    --data-raw '{
        "id":"5f84b6021c7e2c4bb59a3729"
    }'
    
  </details>
  
  <details>
    <summary>(POST) http://localhost:8080/api/meet/register-meetup </summary>
    
    curl --location --request POST 'localhost:8080/api/meet/register-meetup' \
    --header 'username: litorres' \
    --header 'token: 345345fetrtedgdg' \
    --header 'Content-Type: application/json' \
    --data-raw '{
        "id":"5f84b6021c7e2c4bb59a3729"
    }'
    
  </details>
  
  <details>
    <summary>(POST) http://localhost:8080/api/meet/create-meetup </summary>
    
    curl --location --request POST 'http://localhost:8080/api/meet/create-meetup' \
    --header 'username: camoreno' \
    --header 'token: asd12312312as123123' \
    --header 'Content-Type: application/json' \
    --data-raw '{
        "nameOfMeet": "Prueba1",
        "dateOfMeet": "04/11/2020",
        "hourOfMeet": "10:10",
        "address": "Av Del Libertador"
    }'
    
  </details>
  
  <details>
    <summary>(GET) http://localhost:8080/api/meet/get-all-meetups </summary>
    
    curl --location --request GET 'http://localhost:8080/api/meet/get-all-meetups' \
    --header 'username: camoreno' \
    --header 'token: asd12312312as123123'
    
  </details>

##### NOTA: Hay validaciones si se ingresa alguna parametro no correcto.

  8 - Dar Send en el Postman, posibles respuestas:<br />
  
  <details>
    <summary>(GET) http://localhost:8080/api/birra/temperature-per-meetup?meetupID=${id} </summary>

    {
        "date": "Tue Oct 20 10:15:00 ART 2020",
        "temperature": 21.03,
        "feels_like": 17.76
    }
    
  </details>
  
  <details>
    <summary>(GET) http://localhost:8080/api/birra/number-of-boxes-to-buy?meetupID=${id} </summary>
    
    {
        "numberOfBoxes": 2,
        "numberOfGuests": 9,
        "dateOfMeet": "Mon Oct 12 11:00:00 ART 2020",
        "temperature": {
            "date": "Mon Oct 12 11:00:00 ART 2020",
            "temperature": 16.98,
            "feels_like": 13.78
        }
    }
    
  </details>
  
  <details>
    <summary>(POST) http://localhost:8080/api/meet/check-in-meetup </summary>
    
    {
        "id": "5f87013b71ae7c1f51d86971",
        "nameOfMeet": "Reunion",
        "dateOfMeet": "2020-11-01T15:00:00.000+00:00",
        "numberOfGuests": 1,
        "guests": [
            "camoreno"
        ],
        "checkIn": [
            "litorres"
        ],
        "owner": "camoreno",
        "address": "Cafe"
    }
    
  </details>
  
  <details>
    <summary>(POST) http://localhost:8080/api/meet/register-meetup </summary>
    
    {
        "id": "5f87013b71ae7c1f51d86971",
        "nameOfMeet": "Reunion",
        "dateOfMeet": "2020-11-01T15:00:00.000+00:00",
        "numberOfGuests": 2,
        "guests": [
            "camoreno",
            "litorres"
        ],
        "checkIn": [
            "litorres"
        ],
        "owner": "camoreno",
        "address": "Cafe"
    }
    
  </details>
  
  <details>
    <summary>(POST) http://localhost:8080/api/meet/create-meetup </summary>
    
    {
        "id": "5f87013b71ae7c1f51d86971",
        "nameOfMeet": "Prueba1",
        "dateOfMeet": "2020-11-04T13:10:00.000+00:00",
        "numberOfGuests": 0,
        "owner": "camoreno",
        "address": "Av Del Libertador"
    }
    
  </details>
  
  <details>
    <summary>(GET) http://localhost:8080/api/meet/get-all-meetups </summary>
    
    [
        {
            "id": "5f84b5dc1c7e2c4bb59a3728",
            "nameOfMeet": "Navidad",
            "dateOfMeet": "2020-12-24T23:30:00.000+00:00",
            "numberOfGuests": 2,
            "guests": [
                "litorres",
                "camoreno"
            ],
            "owner": "camoreno",
            "address": "Casa"
        },
        {
            "id": "5f84b6021c7e2c4bb59a3729",
            "nameOfMeet": "Reunion",
            "dateOfMeet": "2020-11-01T15:00:00.000+00:00",
            "numberOfGuests": 1,
            "guests": [
                "camoreno"
            ],
            "owner": "camoreno",
            "address": "Cafe"
        },
        {
            "id": "5f84b6441c7e2c4bb59a372b",
            "nameOfMeet": "Pole",
            "dateOfMeet": "2020-10-20T13:15:00.000+00:00",
            "numberOfGuests": 1,
            "guests": [
                "litorres"
            ],
            "owner": "camoreno",
            "address": "Academia"
        },
    ]
    
  </details>
  
# Ejecucion de Prueba

#### Pasos para la ejecución:<br />
  1 - Debe estar corriendo el proyecto.
  2 - Ubicarse en cd <PATH_DE_DESCARGA>\BirrasApi"  y ejecutar el siguiente comando "mvn clean test".

# DATABASE MONGODB

#### Pasos para conectar una DB local<br />
La aplicación actualmente usa MongoDB Atlas . Si se desea cambiar por una DB Mongodb local seguir estos pasos:<br />
  1 - Abrir el archivo "<PATH_DE_DESCARGA>\BirrasApi\src\main\resources\application.properties".<br />

  2 - Comentar toda la línea correspondiente a el placeholder "spring.data.mongodb.uri".<br />

  3 - Agregar las siguientes líneas:<br />
        spring.data.mongodb.host=localhost<br />
        spring.data.mongodb.port=27017

  4 - Volver a ejecutar la aplicación.

##### NOTA 1: Si se usa local no debería tener Autenticación, de ser así hay de agregar más configuraciones.
##### NOTA 2: No es necesario importar alguna DB, la aplicación creara la DB y las collections cuando lo necesite.
##### NOTA 3: EL nombre de la DB es "magneto" y las collectiones son "User" y "Meetup".
