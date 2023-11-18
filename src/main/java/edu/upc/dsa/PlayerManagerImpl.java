package edu.upc.dsa;

import edu.upc.dsa.exceptions.*;

import java.util.List;
import java.util.LinkedList;

import edu.upc.dsa.models.Item;
import edu.upc.dsa.models.Login;
import edu.upc.dsa.models.Register;
import edu.upc.dsa.models.Player;
import edu.upc.dsa.PlayerManager;
import org.apache.log4j.Logger;

public class PlayerManagerImpl implements PlayerManager {

    private static PlayerManager instance;
    final static Logger logger = Logger.getLogger(PlayerManagerImpl.class);
    protected List<Player> players;

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
    }

    //Search a player by username and password
    @Override
    public Player searchPlayerUsernameAndPassword(String username, String password) {
        logger.info("getPlayer("+username+","+password+")");
        for (Player p: this.players) {
            if (p.getUsername().equals(username) && p.getPassword().equals(password)) {
                logger.info("getPlayer("+username+","+password+"): "+p);
                return p;
            }
        }
        logger.warn("not found " + username + " " + password);
        return null;
    }

    //Find all the players
    @Override
    public List<Player> findAll() {
        return this.players;
    }

    //Get a player by id
    @Override
    public Player getPlayer(String id) throws PlayerNoEncontrado {
        logger.info("getPlayer("+id+")");
        for (Player p: this.players) {
            if (p.getId().equals(id)) {
                logger.info("getPlayer("+id+"): "+p);
                return p;
            }
        }
        logger.warn("not found " + id);
        throw new PlayerNoEncontrado();
    }

    //Add a player to the list
    @Override
    public Player addPlayer(String username, String password, String telephone, String email) throws PlayerNoEncontrado {
        Player p = searchPlayerUsernameAndPassword(username, password);
        if (p==null) {
            logger.info("new player " + username + " " + password);
            p = new Player(username, password, telephone, email);
            this.players.add ( p );
            return p;
        }
        else {
            logger.warn("Player already exists");
            return null;
        }
    }

    //Put a player
    @Override
    public Player putPlayer(Player p) throws PlayerNoEncontrado {
        Player p1 = this.getPlayer(p.getId());
        if (p1!=null) {
            logger.info(p + " received!");
            p1.setUsername(p.getUsername());
            p1.setPassword(p.getPassword());
            logger.info(p1 + " updated!");
        }
        else {
            logger.warn("not found " + p.getId());
            throw new PlayerNoEncontrado();
        }
        return p1;
    }

    //Register a player
    @Override
    public Player registerPlayer(Register r) {
        Player p = searchPlayerUsernameAndPassword(r.getUsername(), r.getPassword());
        if (p==null) {
            logger.info("new player " + r.getUsername() + " " + r.getPassword());
            p = new Player(r.getUsername(), r.getPassword(), r.getTelephoneNumber(), r.getEmail());
            this.players.add ( p );
            return p;
        }
        else {
            logger.warn("Player already exists");
            return null;
        }
    }

    //Login a player
    @Override
    public Player loginPlayer(Login l)  {
        logger.info("loginPlayer("+l.getUsername()+","+l.getPassword()+")");
        Player p = searchPlayerUsernameAndPassword(l.getUsername(), l.getPassword());
        if (p!=null) {
            logger.info("loginPlayer("+l.getUsername()+","+l.getPassword()+"): "+p);
            return p;
        }
        else {
            logger.warn("not found " + l.getUsername() + " " + l.getPassword());
            return null;
        }
    }
}
