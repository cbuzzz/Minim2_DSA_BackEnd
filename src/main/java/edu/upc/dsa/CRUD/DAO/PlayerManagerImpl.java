/*
package edu.upc.dsa.CRUD.DAO;

import edu.upc.dsa.exceptions.*;


import java.util.List;
import java.util.LinkedList;
import java.util.HashMap;

import edu.upc.dsa.models.Login;
import edu.upc.dsa.models.Player;
import org.apache.log4j.Logger;


public class PlayerManagerImpl implements PlayerManager {

    private static PlayerManager instance;
    final static Logger logger = Logger.getLogger(PlayerManagerImpl.class);

    protected List<Player> players;
    protected List<Player> logins;
    private HashMap<String, Player> playerHashMap;


    public static PlayerManager getInstance() {
        if (instance==null)
            instance = new PlayerManagerImpl();
        return instance;
    }

    public int size() {
        int ret = this.players.size();
        logger.info("size " + ret);
        return ret;
    }

    public PlayerManagerImpl() {
        this.players = new LinkedList<>();
        this.logins = new LinkedList<>();
        this.playerHashMap = new HashMap<>();
    }

    //Give all the players
    @Override
    public List<Player> getPlayers() {
        return this.players;
    }

    //Search a player by username and password
    @Override
    public Player searchPlayerUsernameAndPassword(String username, String password) {
         for (Player p : this.players) {
            if (p.getUsername().equals(username) && p.getPassword().equals(password)) {
                return p;
            }
        }
        return null;
    }

    //Register a player
    @Override
    public Player registerPlayer(Player p) throws UsernameInUseException{
        Player player = playerHashMap.get(p.getUsername());
        if(player != null){
            logger.warn("Username already in use");
            throw new UsernameInUseException();
        }
        else{
            this.players.add(p);
            this.playerHashMap.put(p.getUsername(), p);
            logger.info("New player registered");
            return p;
        }
    }

    //Login a player
    @Override
    public Player loginPlayer(Login login) throws PlayerNotResgisteredException, PasswordNotMatchException {
        String username = login.getUsername();
        Player player = playerHashMap.get(username);
        if(player == null){
            logger.warn("Player not registered");
            throw new PlayerNotResgisteredException();
        }
        else{
            if(player.getPassword().equals(login.getPassword())){
                this.logins.add(player);
                logger.info("Player logged in");
                return player;
            }
            else{
                logger.warn("Password not match");
                throw new PasswordNotMatchException();
            }
        }
    }

    //Player number
    @Override
    public int playerNumber() {
        return this.players.size();
    }

    //Log Number
    @Override
    public int logNumber() {
        return this.logins.size();
    }
}


 */