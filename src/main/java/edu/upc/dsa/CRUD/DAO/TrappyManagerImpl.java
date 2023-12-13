package edu.upc.dsa.CRUD.DAO;

import edu.upc.dsa.CRUD.DAO.ItemManager;
import edu.upc.dsa.CRUD.DAO.PlayerManager;
import edu.upc.dsa.CRUD.DAO.PlayerManagerImpl;
import edu.upc.dsa.CRUD.DAO.ItemManagerImpl;
import edu.upc.dsa.CRUD.MYSQL.FactorySession;
import edu.upc.dsa.CRUD.MYSQL.Session;
import edu.upc.dsa.exceptions.*;
import edu.upc.dsa.models.*;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.List;
import java.util.LinkedList;
import java.util.HashMap;


public class TrappyManagerImpl implements TrappyManager {

    private static TrappyManager instance;
    final static Logger logger = Logger.getLogger(TrappyManagerImpl.class);

    private HashMap<String, Player> trappyplayersMap;
    protected List<Item> items;
    protected List<Player> players;
    protected List<Player> playerslogged;

    private TrappyManagerImpl() {
        this.trappyplayersMap = new HashMap<>();
        this.items = new LinkedList<>();
        this.players = new LinkedList<>();
        this.playerslogged = new LinkedList<>();
    }

    public static TrappyManager getInstance() {
        if (instance == null) instance = new TrappyManagerImpl();
        return instance;
    }

    public List<Player> getPlayers(){
        return players;
    }

    public int size(){
        int ret = this.players.size();
        logger.info("size " + ret);
        return ret;
    }

    @Override
    public int PlayerSize(){
        int ret = this.players.size();
        return ret;
    }

    @Override
    public int PlayersLoginSize(){
        int ret = this.playerslogged.size();
        return ret;
    }

    @Override
    public int ItemSize(){
        int ret = this.items.size();
        return ret;
    }

    @Override
    public Player registerPlayer(Player p) throws UsernameInUseException, EmailInUseException {
        Player player = trappyplayersMap.get(p.getEmail());
        if(player == null){
            String idPlayer = p.getId();
            String username = p.getUsername();
            String password = p.getPassword();
            String telephoneNumber = p.getTelephoneNumber();
            String email = p.getEmail();
            PlayerManager pm = new PlayerManagerImpl();
            pm.addPlayer(idPlayer, username, password, telephoneNumber, email);
            logger.info("Player registered");
            return p;
        }else{
            logger.info("Username or email already in use");
            throw new UsernameInUseException();
        }
    }

    @Override
    public Player loginPlayer(Login login) throws PlayerNotResgisteredException, PasswordNotMatchException {
        try{
            PlayerManager pm = new PlayerManagerImpl();
            HashMap<String, String> Login = new HashMap<>();
            Login.put("username", login.getUsername());
            Login.put("password", login.getPassword());
            Player player = pm.getPlayerByUsername(login.getUsername());
            if(player.getPassword().equals(login.getPassword())){
                logger.info("Player logged");
                return player;
            }else if(player.getUsername() == null) {
                logger.info("Player not registered");
                throw new PlayerNotResgisteredException();
            }
        } catch (Exception e){
            logger.info("Password not match");
            throw new PasswordNotMatchException();
        }
        return null;
    }

    @Override
    public List<Item> ShopTrappy(){
        ItemManager im = new ItemManagerImpl();
        List<Item> items = im.getItems();
        return items;
    }
}
