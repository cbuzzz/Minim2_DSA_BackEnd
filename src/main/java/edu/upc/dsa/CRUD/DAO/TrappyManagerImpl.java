package edu.upc.dsa.CRUD.DAO;

import org.apache.log4j.Logger;
import edu.upc.dsa.CRUD.MYSQL.FactorySession;
import edu.upc.dsa.CRUD.MYSQL.Session;
import edu.upc.dsa.models.Player;

import java.util.List;


public class TrappyManagerImpl implements TrappyManager {
    Session session;
    private static TrappyManager instance;
    final static Logger logger = Logger.getLogger(TrappyManagerImpl.class);

    public TrappyManagerImpl() {
        this.session = FactorySession.openSession();
    }

    public static TrappyManager getInstance() {
        if (instance==null) instance = new TrappyManagerImpl();
        return instance;
    }

    @Override
    public int numPlayers(){
        int ret = this.session.count(Player.class);
        logger.info("numPlayers "+ret);
        return ret;
    }







}
