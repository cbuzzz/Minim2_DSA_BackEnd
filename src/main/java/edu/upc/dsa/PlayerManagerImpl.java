package edu.upc.dsa;

import edu.upc.dsa.exceptions.*;

import java.util.List;

import edu.upc.dsa.models.Item;
import edu.upc.dsa.models.Player;
import org.apache.log4j.Logger;

public class PlayerManagerImpl implements PlayerManger {
    final static Logger logger = Logger.getLogger(PlayerManagerImpl.class);

    protected List<Player> players;

    @Override
    public void register(String username, String password, String email) throws PlayerYaExiste {
        Player player = new Player(username, password, email, 0);
        if (players.contains(player)) {
            logger.error("Ya hay un jugador con este nombre: " + player.getUsername());
            throw new PlayerYaExiste();
        } else {
            players.add(player);
            logger.info("Jugador añadido correctamente");
        }

    }

    @Override
    public void login(String username, String password) throws PlayerNoEncontrado, ContraseñaIncorrecta {
        //Función para buscar si hay un usuario con el mismo nombre registrado
        Player player = searchPlayerUsername(username);
        if (player.getUsername().equals(username)) {
            if (player.getPassword().equals(password)) {
                //Inicio de sesión correcto
                logger.info("Inicio de sesión exitoso para el jugador: " + player.getUsername());
            }else {
                // Contraseña incorrecta
                logger.error("Contraseña incorrecta para el jugador: " + player.getUsername());
                throw new ContraseñaIncorrecta();
            }
        }else {
            // No se encontró al jugador
            logger.error("Jugador no encontrado: " + username);
            throw new PlayerNoEncontrado();
        }
    }

    @Override
    public void comprarItem(String idProducto, int idUsuario) throws ProductoNoExisteException, DineroInsuficienteException,PlayerNoEncontrado {
        logger.info("Comprobamos si el producto con id " + idProducto + " y el usuario con id: " + idUsuario + " existen");
        if (Item.Tienda.equals(idProducto)) {
            //Función para buscar si existe el player con el mismo id
            Player player = searchPlayerId(idUsuario);
            Item item = Item.Tienda.get(idProducto);
            if (player.getCoins() > item.getPrice()) {
                player.getInventario().add(item);
                double monedero = player.getCoins();
                player.setCoins(monedero - item.getPrice());
                logger.info("El jugador con id " + idUsuario + " ha comprado el producto con id " + idProducto);
            } else {
                logger.info("El usuario no tiene suficientes DSA_coins");
                throw new DineroInsuficienteException();
            }
        } else {
            logger.info("El producto no existe");
            throw new ProductoNoExisteException();
        }
    }
    @Override
    public Player searchPlayerUsername(String username) throws PlayerNoEncontrado{
        for (Player player : players) {
            if (player.getUsername().equals(username)) {
                return player;
            }
        }
        throw new PlayerNoEncontrado();
    }
    @Override
    public Player searchPlayerId(int idUsuario) throws PlayerNoEncontrado{
        for (Player player : players) {
            if (player.getId() == idUsuario) {
                return player;
            }
        }
        throw new PlayerNoEncontrado();
    }
}
