package edu.upc.dsa;

import edu.upc.dsa.exceptions.*;
import edu.upc.dsa.models.Player;
import edu.upc.dsa.models.Login;

import java.util.List;

public interface PlayerManager {

    List<Player> getPlayers();

    Player registerPlayer(Player p) throws UsernameInUseException;

    Player loginPlayer(Login l) throws PlayerNotResgisteredException, PasswordNotMatchException;

    Player searchPlayerUsernameAndPassword(String username, String password);

   int size();

   int logNumber();

   int playerNumber();
}
