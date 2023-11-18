# Frontend i Backend - Juego del grupo 2

Este repositorio contiene el código del frontend y backend del juego del grupo 2.

## Integrantes

- Andrei Piposi - andrei.piposi@estudiantat.upc.edu
- Mario Martínez - 
- Carles Martín - 
- Sebastián Pineda - sebastian.pineda@estudiantat.upc.edu

## Features

- [X] Registro de players
- [X] Login de players
- [X] Update de players
- [ ] Delete de players
- [ ] Creación Shop
- [ ] Update Shop

## Tecnologías

- Frontend: HTML, CSS, JavaScript.
- Backend: Java, JQuery.
- Base de datos: MariaDB.

## Cómo funciona el código

### Frontend

El frontend está hecho con HTML, CSS y JavaScript. El código está estructurado de la siguiente manera en la carpeta `public`:

- `index.html`: Es la página principal del frontend.
- `login.html`: Es la página de login.
- `register.html`: Es la página de registro.
- `home.html`: Es la página principal del juego. Contiene el menú principal y el menú de la tienda.

### Backend

El backend está hecho con Java. El código está estructurado de la siguiente manera:

En la carpeta models:
- `Main.java`: Es la clase principal del backend.
- `Player.java`: Es la clase que contiene los datos de los jugadores.
- `Register.java`: Es la clase que contiene los getters y setters de los jugadores para el registro.
- `Login.java`: Es la clase que contiene los getters y setters de los jugadores para el login.
- `Item.java`: Es la clase que contiene los datos de los items.

En la carpeta services:

- `PlayerService.java`:

En la carpeta edu.upc.dsa:

- `PlayerManager.java`: Es la interficie que contiene los métodos de los players.
- `PlayerManagerImpl.java`: Es la clase que implementa los métodos de los players.

En la parte de test:

- `PlayerManagerTest.java`: Es la clase que contiene los tests de los métodos de los players.







