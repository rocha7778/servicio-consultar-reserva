# servicio-consultar-reserva
Este microservicio se encarga de consultar una reserva,registrada en un base de datos MySQL

Estos microservicos conforman la arquitectura para el maneno de  una rerserva en un hotel.

- servicio-regitrar-reserva
- servicio-eureka-server
- servicio-zuul-server
- proyecto libreria commons



# servicio-consultar-reserva
Este servicio esta escuchando una cola SQS de AWS y guarda la informacion en una bd MySQL y envia una notificacion al usario que su reserva fue realizada con exito.  
Una vez registrada la reserva ese ofrece un punto de acceso para consultar la reserva por id.  

# Proyecto libreria commons  
Este poryecto se debe compilar y generar el jar con el comando mvnw.cmd install para window, y para linux mvnw install

## MySQl
La tabla base para  registrar los datos es la tabla reserva, la cual presenta la siguiente estructura :  
```sh
CREATE TABLE `reserva` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `fecha_ingreso` date DEFAULT NULL,
  `fecha_salida` date DEFAULT NULL,
  `no_personas` tinyint DEFAULT NULL,
  `titular_reserva` varchar(50) DEFAULT NULL,
  `no_habitaciones` tinyint DEFAULT NULL,
  `no_menores` tinyint DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
)

esta tabla debe de crearse dentro de una base de datos llamada db_springboot_cloud, para su correcto funcionamiento  
De igual forma si desea cambiar el nombre de la base de datos puede hacer en el archivo application.yml en la propidad database.
