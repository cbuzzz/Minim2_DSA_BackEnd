# Frontend i Backend - Juego del grupo 2

Este repositorio contiene el código del frontend y backend del juego del grupo 2.

## Integrantes

- Andrei Piposi - andrei.piposi@estudiantat.upc.edu
- Mario Martínez - mario.martinez.caraballo@estudiantat.upc.edu
- Carles Martín - carles.martin.sepulveda@estudiantat.upc.edu
- Sebastián Pineda - sebastian.pineda@estudiantat.upc.edu

## Features

- [X] Registro de players
- [X] Login de players
- [X] Creación Shop
- [X] Items en Shop

## Tecnologías

- Frontend: HTML, CSS, JavaScript.
- Backend: Java, JQuery.
- Base de datos: MariaDB.

## Cómo funciona el código

### Frontend

El frontend está hecho con HTML, CSS y JavaScript. El código está estructurado de la siguiente manera en la carpeta `public`:

- `login.html`: Es la página de login.
- `register.html`: Es la página de registro.
- `shop.html`: Es la página de la tienda.
- `styleShop.css`: Es el archivo que contiene el estilo del tienda.

### Backend

El backend está hecho con Java. El código está estructurado de la siguiente manera:

En la carpeta `models`:
- `Player.java`: Es la clase que contiene los datos de los jugadores. Se utiliza para el registro.
- `Login.java`: Es la clase que contiene los getters y setters de los jugadores para el login.
- `Item.java`: Es la clase que contiene los datos de los items.

En la carpeta `services`:
- `PlayerService.java`: Los POST para el registro y login de los jugadores. Son los servicios de la API.
- `ItemService.java`: El GET para conseguir todos los items. Son los servicios de la API.

En la carpeta `exceptions`:
- `PlayerNotRegisteredException.java`: Es la excepción que se lanza cuando el jugador no está registrado.
- `EmailInUseException.java`: Es la excepción que se lanza cuando el email ya está en uso.
- `PasswordNotMatchException.java`: Es la excepción que se lanza cuando la contraseña no coincide.
- `UsernameInUseException.java`: Es la excepción que se lanza cuando el nombre de usuario ya está en uso.
- `NoExistenItemsException.java`: Es la excepción que se lanza cuando el item no está en la tienda.

En la carpeta edu.upc.dsa:
- `PlayerManager.java`: Es la interficie que contiene los métodos de los players.
- `PlayerManagerImpl.java`: Es la clase que implementa los métodos de los players.
- `ItemManager.java`: Es la interficie que contiene los métodos de los items.
- `ItemManagerImpl.java`: Es la clase que implementa los métodos de los items.
- `Main.java`: Es la clase que contiene el main del backend.

En la parte de `test`:
- `PlayerManagerTest.java`: Es la clase que contiene los tests de los métodos de los players.
- `ItemManagerTest.java`: Es la clase que contiene los tests de los métodos de los items.


## Próximas features a implementar en las siguientes versiones

- [ ] Nuevos servicios de la API
- [ ] Incluir nuevos métodos en el backend
- [ ] Incluir Base de datos
- [ ] Mejorar el diseño de la tienda
- [ ] Mejorar el diseño de la página de login
- [ ] Empezar a implementar el juego




