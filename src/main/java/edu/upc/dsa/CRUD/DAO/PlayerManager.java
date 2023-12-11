package edu.upc.dsa.CRUD.DAO;

import edu.upc.dsa.exceptions.*;
import edu.upc.dsa.models.Player;
import edu.upc.dsa.models.Item;
import edu.upc.dsa.models.Login;

import java.sql.SQLException;
import java.util.List;

public interface PlayerManager {

    public int addPlayer(Player p);
    public Player getPlayer(String name);

    public List<Player> getPlayers();

    Player registerPlayer(Player p) throws UsernameInUseException;

    Player loginPlayer(Login l) throws PlayerNotResgisteredException, PasswordNotMatchException;

    Player searchPlayerUsernameAndPassword(String username, String password);

    public List<Item> getItems();

   int size();

   int logNumber();

   int playerNumber();
}
