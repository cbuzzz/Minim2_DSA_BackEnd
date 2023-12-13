package edu.upc.dsa.CRUD.DAO;

import edu.upc.dsa.exceptions.*;
import edu.upc.dsa.models.Player;
import edu.upc.dsa.models.Item;
import edu.upc.dsa.models.Login;


import java.sql.SQLException;
import java.util.List;

public interface PlayerManager {
    public int addPlayer(String idPlayer, String username, String password, String telephoneNumber, String email);
    public Player getPlayer(String idPlayer);
    public void deletePlayer(String idPlayer);
    public List<Player> getPlayers();
    public List<Item> getItemsFromPlayer();
    public void buyItem(String idPlayer, String idItem) throws NoCoinsForBuyException, SQLException, NoExistenItemException;
    Player getPlayerByUsername(String username);
}
