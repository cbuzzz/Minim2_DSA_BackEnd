package edu.upc.dsa;

import edu.upc.dsa.exceptions.*;
import edu.upc.dsa.models.Player;
import edu.upc.dsa.models.Login;
import edu.upc.dsa.models.Register;
import java.util.List;

public interface PlayerManager {

    List<Player> findAll();

   Player getPlayer(String id) throws PlayerNoEncontrado;

    Player registerPlayer(Register r) throws PlayerYaExiste;

     Player loginPlayer(Login l) throws PlayerNoEncontrado;

    Player addPlayer(String username, String password, String telephone, String email) throws PlayerYaExiste, PlayerNoEncontrado;

    Player updatePlayer(Player p) throws PlayerNoEncontrado;

    Player searchPlayerUsernameAndPassword(String username, String password) throws PlayerNoEncontrado;

   int size();
}
