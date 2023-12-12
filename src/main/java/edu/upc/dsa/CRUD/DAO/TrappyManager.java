package edu.upc.dsa.CRUD.DAO;

import edu.upc.dsa.exceptions.*;
import edu.upc.dsa.models.*;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface TrappyManager {

    /** Player **/
    public int numPlayers();

    public void registerPlayer(String username, String password, String telephoneNumber, String email) throws EmailInUseException,UsernameInUseException, SQLException;

    public String loginPlayer(Login credentials) throws IncorrectCredentialsException, SQLException;

    public List<Player> getPlayers();

    public Player getPlayer(String idPlayer);

    public int getPlayerCoins(String username);

    public void buyItem(String username, String item) throws NoCoinsForBuyException;

    public List<String> getItemsByPlayer(String username);

    public void updatePlayer(UserInformation newuser, String idUser) throws SQLException;

    public List<Player> getPlayersByCoins();

    public Player getPlayerByEmail(String email);


    /** Item **/
    public void addItem(String id, String name, String description, String type, double price) throws SQLException,ItemWithSameIdAlreadyExists ;

    public int numItems();

    public List<Item> getShop();

    public Item getItem(String itemId) throws ItemDoesNotExist;

    public Item deleteItem(String itemId) throws ItemDoesNotExist;

    public int getItemPrice(String itemId);

    public void purchaseItem(String idItem, String idPlayer) throws ItemDoesNotExist,NoCoinsForBuyException, PlayerNotResgisteredException,SQLException;


    /**Partida**/
    public void createPartida(Partida p);
    public void updatePartida(Partida p);
    public int numPartidas();
    public List<Partida> getAllPartidas();

}
