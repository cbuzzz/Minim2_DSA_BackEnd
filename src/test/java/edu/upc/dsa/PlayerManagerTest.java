package edu.upc.dsa;
import edu.upc.dsa.PlayerManager;
import edu.upc.dsa.PlayerManagerImpl;
import edu.upc.dsa.exceptions.PlayerNoEncontrado;
import edu.upc.dsa.exceptions.PlayerYaExiste;
import edu.upc.dsa.models.Player;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

public class PlayerManagerTest {
    PlayerManager pm;

    @Before
    public void setUp() throws PlayerNoEncontrado, PlayerYaExiste {
        pm = (PlayerManager) new PlayerManagerImpl();
        pm.addPlayer("Pablito","2345","680739345","pab@gmail.com");
        pm.addPlayer("Juanito","1234","680156796","juas@gmail.com");
        pm.addPlayer("Pepito","3456","611212117","pep@yahoo.es");
    }

    @After
    public void tearDown() {
        pm = null;
    }

    @Test
    public void testAddPlayer() throws PlayerYaExiste, PlayerNoEncontrado {
        List<Player> players = pm.findAll();


        Assert.assertEquals("Pablito", players.get(0).getUsername());
        Assert.assertEquals("2345", players.get(0).getPassword());

        Assert.assertEquals("Juanito", players.get(1).getUsername());
        Assert.assertEquals("1234", players.get(1).getPassword());

        Assert.assertEquals("Pepito", players.get(2).getUsername());
        Assert.assertEquals("3456", players.get(2).getPassword());
    }

    @Test
    public void testUpdatePlayer() throws PlayerNoEncontrado {
        List<Player> players = pm.findAll();
        Player p = players.get(1);
        p.setPassword("2333");
        Player p2 = pm.putPlayer(p);

        Assert.assertEquals(p2.getUsername(),pm.findAll().get(1).getUsername());
        Assert.assertEquals(p2.getPassword(),pm.findAll().get(1).getPassword());
    }
}
