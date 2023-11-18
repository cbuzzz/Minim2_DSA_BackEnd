package edu.upc.dsa;

import edu.upc.dsa.models.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.LinkedList;

import org.apache.log4j.Logger;

public class PlayerManagerImpl implements PlayerManager {
    private static PlayerManager instance;
    protected List<Player> players;
    final static Logger logger = Logger.getLogger(PlayerManagerImpl.class);

    public static PlayerManager getInstance(){
        //logger.info(instance);
        if(instance == null)
            instance = new PlayerManagerImpl();
        //logger.info(instance);
        return instance;
    }

    public int size() {
        int ret = this.players.size();
        logger.info("size " + ret);
        return ret;
    }


    //LOGIN PLAYER
    @Override
    public Player loginPlayer(Login login){
        logger.info("loginPlayer("+login.getUsername()+","+login.getPassword()+")");
        Player player = searchPlayer(loginPlayer.getNombre(), logInParams.getPassword());
        if(player != null){
            logger.info("player found");
            return player;
        }
        else{
            logger.info("player not found");
            return null;
        }
    }

}
