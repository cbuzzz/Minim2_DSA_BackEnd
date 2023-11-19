package edu.upc.dsa;

import edu.upc.dsa.exceptions.*;
import edu.upc.dsa.models.Player;
import edu.upc.dsa.models.Login;
import edu.upc.dsa.models.Register;
import java.util.List;

public interface PlayerManager {

    public List<Player> findAll();

    public Player getPlayer(String id) throws PlayerNoEncontrado;

    public Player registerPlayer(Register r) throws PlayerYaExiste;

    public Player loginPlayer(Login l) throws PlayerNoEncontrado;

    public Player addPlayer(String username, String password, String telephone, String email) throws PlayerYaExiste, PlayerNoEncontrado;

    public Player updatePlayer(Player p) throws PlayerNoEncontrado;

    Player searchPlayerUsernameAndPassword(String username, String password) throws PlayerNoEncontrado;

    public int size();
}
