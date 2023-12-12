package edu.upc.dsa.CRUD.DAO;

import edu.upc.dsa.exceptions.*;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import edu.upc.dsa.CRUD.MYSQL.FactorySession;
import edu.upc.dsa.CRUD.MYSQL.Session;
import edu.upc.dsa.models.*;
import java.util.HashMap;
import java.sql.SQLException;
import java.util.List;


public class TrappyManagerImpl implements TrappyManager {
    Session session;
    private static TrappyManager instance;
    final static Logger logger = Logger.getLogger(TrappyManagerImpl.class);
    protected List<Player> players;
    protected List<Player> logins;
    private HashMap<String,Player> playerHashMap;
    public TrappyManagerImpl() {
        this.session = FactorySession.openSession();
    }

    public static TrappyManager getInstance() {
        if (instance==null) instance = new TrappyManagerImpl();
        return instance;
    }

    @Override
    public int numPlayers(){
        logger.info("Number of players");
        return this.session.findAll(Player.class).size();
    }

    @Override
    public void registerPlayer(String username, String password, String telephoneNumber, String email) throws EmailInUseException,SQLException {
        logger.info("Register a player");
        Player player = new Player(username, password, telephoneNumber, email);
        try{
            player = (Player) this.session.get(Player.class,"email",email);
        } catch(SQLException e){
            this.session.save(player);
            logger.info("Player has been added correctly"+player.getUsername());
        }
        logger.info("User not registered, email already in use");
        throw new EmailInUseException();
    }

    @Override
    public String loginPlayer(Login credentials) throws IncorrectCredentialsException, SQLException {
        logger.info("Login a player");
        HashMap<String, String> player = new HashMap<>();
        player.put("username", credentials.getUsername());
        player.put("password", credentials.getPassword());
        List<Object> playerMatch = this.session.findAll(Player.class, player);
        if (playerMatch.size()!=0){
            logger.info("Login was correct!");
            Player player1 = (Player) playerMatch.get(0);
            return player1.getUsername();
        }
        logger.info("Login was incorrect!");
        throw new IncorrectCredentialsException();
    }

    @Override
    public void updatePlayer(UserInformation newuser, String idUser) throws SQLException {
        Player player = new Player();
        try {
            player = (Player) this.session.get(Player.class, "idUser", idUser);
            logger.info("UPDATING THE USER");
        } catch(SQLException e) {
            logger.info("User does not exist EXCEPTION");
        }
        try {
            logger.info("UPDATING THE USER:");
            player.setUsername(newuser.getUsername());
            player.setPassword(newuser.getPassword());
            player.setTelephoneNumber(newuser.getTelephoneNumber());
            player.setEmail(newuser.getEmail());
            logger.info(player);
            this.session.update(player);
        } catch(SQLException e) {
            logger.warn("Invalid Email");
        }
    }
    @Override
    public void purchaseItem(String idItem, String idPlayer){
        logger.info("Starting purchaseItem("+idItem+", "+idPlayer+")");

        Item item = getItem(idItem);
        Player player = getPlayer(idPlayer);

        try {
            player.purchaseItem(item);
        } catch (NoCoinsForBuyException e) {
            logger.warn("Not enough coins for buy");
        }
        logger.info("Item bought");
        this.session.update(player);

        Purchase purchase = new Purchase(idPlayer,idItem);
        this.session.save(purchase);
    }

    @Override
    public void addItem(String id, String name, String description, String type, double price) {
        logger.info("Adding item");
        Item item = new Item(id, name, description, type, price);
        try{
            item = (Item) this.session.get(Item.class,"id",id);
        } catch(SQLException e) {
            this.session.save(item);
            logger.info("Item has been added correctly" + item.getName());
            return;
        }
    }


    /*
    @Override
    public List<Item> purchasedItems(String idUser) throws SQLException, NoExistenItemException {
        logger.info("Looking for gadgets purchased by user with id: " + idUser);
        HashMap<String, String> user = new HashMap<>();
        user.put("idUser", idUser);

        List<Object> purchaseMatch = this.session.findAll(Purchase.class, user);
        List<Item> gadgetsOfUser=new ArrayList<>();

        if (purchaseMatch.size()!=0){
            logger.info("Purchase were found correctly for given user id!");
            for(Object object : purchaseMatch) {
                Purchase purchase = (Purchase) object;
                try{
                    gadgetsOfUser.add(this.getItem(purchase.getidItem()));
                }
                catch(Exception e){
                    throw new NoExistenItemException();
                }
            }
            return gadgetsOfUser;
        }
        logger.info("No purchase was found for given user id");
        throw new NoPurchaseWasFoundForIdUser();
    }

     */



}
