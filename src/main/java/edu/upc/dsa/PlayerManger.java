package edu.upc.dsa;

import edu.upc.dsa.exceptions.*;
import edu.upc.dsa.models.Player;

public interface PlayerManger {
    void register(String username, String password, String email) throws PlayerYaExiste;

    void login(String username, String password) throws PlayerNoEncontrado, ContraseñaIncorrecta;

    void comprarItem(String idProducto, int idUsuario) throws ProductoNoExisteException, DineroInsuficienteException,PlayerNoEncontrado;

    Player searchPlayerUsername(String username) throws PlayerNoEncontrado;

    Player searchPlayerId(int idUsuario) throws PlayerNoEncontrado;
}
