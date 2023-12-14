package edu.upc.dsa;

import edu.upc.dsa.CRUD.DAO.PlayerManager;
import edu.upc.dsa.CRUD.DAO.TrappyManager;
import edu.upc.dsa.CRUD.DAO.TrappyManagerImpl;
import edu.upc.dsa.exceptions.*;
import edu.upc.dsa.models.*;

import junit.framework.Assert;
import org.apache.log4j.Logger;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TrappyManagerTest {

    private static Logger logger = Logger.getLogger(TrappyManagerTest.class);
    TrappyManager tm;
    PlayerManager pm;

    @Before
    public void setUp() throws EmailInUseException, UsernameInUseException, PasswordNotMatchException, PlayerNotResgisteredException {
        this.tm = new TrappyManagerImpl();
        this.tm.registerPlayer(new Player("00","chls","1234","62234356","hello@gmail.com",400));
        this.tm.registerPlayer(new Player("01","andrew","1234","62234356","hello@gmail.com",400));
        //this.tm.loginPlayer(new Login("javi","12345"));
    }

    @After
    public void tearDown()  {
        this.tm = null;
    }
    @Test
    public void registerUserTest() throws UsernameInUseException, EmailInUseException {
        this.tm.registerPlayer(new Player("03","nom","12345","62234356","hello@gmail.com",400));
        //Assert.assertEquals(0,tm.);;
    }
    @Test
    public void loginUserTest() throws PasswordNotMatchException, PlayerNotResgisteredException {
        this.tm.loginPlayer(new Login("chls","1234"));
        Assert.assertEquals(2,tm.PlayersLoginSize());;
    }
}