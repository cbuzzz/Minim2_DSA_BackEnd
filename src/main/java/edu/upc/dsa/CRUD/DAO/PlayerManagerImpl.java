package edu.upc.dsa.CRUD.DAO;

import edu.upc.dsa.CRUD.*;
import edu.upc.dsa.CRUD.MYSQL.FactorySession;
import edu.upc.dsa.CRUD.MYSQL.Session;
import edu.upc.dsa.CRUD.DAO.*;
import edu.upc.dsa.exceptions.*;
import edu.upc.dsa.models.*;


import java.sql.SQLException;
import java.util.List;
import java.util.HashMap;
import org.apache.log4j.Logger;


public class PlayerManagerImpl implements PlayerManager{

    final static Logger logger = Logger.getLogger(PlayerManagerImpl.class);
    private static PlayerManagerImpl instance;

    public static PlayerManagerImpl getInstance() {
        if (instance == null) instance = new PlayerManagerImpl();
        return instance;
    }

    public int addPlayer(String idPlayer, String username, String password, String telephoneNumber, String email){
        Session session = null;
        int res = 0;
        try {
            session = FactorySession.openSession();
            Player player = new Player(idPlayer, username, password, telephoneNumber, email, 500);
            session.save(player);
            logger.info("Player added");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return res;
    }

    public Player getPlayer(String id){
        Session session = null;
        Player player = null;

        try {
            session = FactorySession.openSession();
            player = (Player) session.get(Player.class, "id", id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return player;
    }

    public void deletePlayer(String idPlayer){
        Session session = null;
        try {
            session = FactorySession.openSession();
            Player player = (Player) session.get(Player.class, "id", idPlayer);
            session.delete(player);
            logger.info("Player deleted");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public List<Player> getPlayers(){
        Session session = null;
        List<Player> players = null;
        try {
            session = FactorySession.openSession();
            players = session.findAll(Player.class);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return players;
    }

    public List<Item> getItemsFromPlayer(){
        Session session = null;
        List<Item> items = null;
        try {
            session = FactorySession.openSession();
            items = session.findAll(Item.class);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return items;
    }

    public void buyItem(String idPlayer, String idItem) throws NoCoinsForBuyException, SQLException, NoExistenItemException {
        logger.info("Buying item");
        Session session = null;
        ItemManager item = new ItemManagerImpl();
        Item item1 = item.getItemById(idItem);
        Player player = getPlayer(idPlayer);
        logger.info(item1.getPrice());
        try{
            session = FactorySession.openSession();
            player.purchaseItem(item1);
            session.update(player);
            logger.info("Item bought");
        } catch (NoCoinsForBuyException e) {
            logger.error("No coins for buy");
            throw new NoCoinsForBuyException();
        } catch (SQLException e) {
            logger.error("SQL Exception");
            throw new SQLException();
        } finally {
            session.close();
        }
    }

    public Player getPlayerByUsername(String username){
        Session session = null;
        Player player = null;
        try {
            session = FactorySession.openSession();
            player = (Player) session.get(Player.class, "username", username);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return player;
    }
}