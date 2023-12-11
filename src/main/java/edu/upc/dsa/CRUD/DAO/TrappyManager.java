package edu.upc.dsa.CRUD.DAO;

import edu.upc.dsa.exceptions.NoCoinsForBuyException;
import edu.upc.dsa.exceptions.NoExistenItemException;
import edu.upc.dsa.models.Item;
import edu.upc.dsa.models.Player;
import edu.upc.dsa.models.Partida;

import java.sql.SQLException;
import java.util.List;
public interface TrappyManager {

    /**Player**/
    public int numPlayers();
    public void registerPlayer(Player p);
    public Player loginPlayer(String username, String password);
    public List<Player> getPlayers();
    public int getPlayerCoins(String username);
    public void buyItem(String username, String item) throws NoCoinsForBuyException;
    public List<String> getItemsByPlayer(String username);
    public void updatePlayer(Player p);
    public List<Player> getPlayersByCoins();
    public Player getPlayerByEmail(String email);

    /**Item**/
    public void addItem(Item i);
    public int numItems();
    public List<Item> getShop();
    public Item getItem(String itemId);
    public void deleteItem(String itemId);
    public int getItemPrice(String itemId);
    public List<Item> purchaseItems(String idPlayer) throws SQLException, NoExistenItemException;

    /**Partida**/
    public void createPartida(Partida p);
    public void updatePartida(Partida p);
    public int numPartidas();
    public List<Partida> getAllPartidas();

}
