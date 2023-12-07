package edu.upc.dsa;

import edu.upc.dsa.CRUD.DAO.PlayerManager;
import edu.upc.dsa.CRUD.DAO.PlayerManagerImpl;
import edu.upc.dsa.exceptions.*;
import edu.upc.dsa.models.Player;
import edu.upc.dsa.models.Login;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.apache.log4j.Logger;

import static org.junit.Assert.assertEquals;


public class PlayerManagerTest {

    PlayerManager instance;
    final static Logger logger = Logger.getLogger(PlayerManagerImpl.class);

    @Before
    public void setUp() throws UsernameInUseException, PasswordNotMatchException, PlayerNotResgisteredException {
        this.instance = new PlayerManagerImpl();
        this.instance.registerPlayer(new Player("Andrei", "1234", "680739345", "andreipiposi@gmail.com"));
        this.instance.registerPlayer(new Player("Pau", "3334", "680739346", "pauquintano@gmail.com"));
        this.instance.loginPlayer(new Login("Andrei", "1234"));
    }

    @After
    public void tearDown() {
        this.instance = null;
    }

    @Test
    public void testRegisterPlayer() throws UsernameInUseException {
        Player p = new Player("Marc", "3455", "680739348", "marcq@gmail.com");
        this.instance.registerPlayer(p);
        Assert.assertEquals(3, this.instance.playerNumber());
        Assert.assertEquals(3,this.instance.size());
    }

    @Test
    public void testLoginPlayer() throws PlayerNotResgisteredException, PasswordNotMatchException {
        Player p = this.instance.loginPlayer(new Login("Pau", "3334"));
        Assert.assertEquals(2, this.instance.logNumber());
    }
}
