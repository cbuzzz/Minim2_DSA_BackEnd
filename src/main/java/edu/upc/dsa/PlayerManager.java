package edu.upc.dsa;

import edu.upc.dsa.models.Player;
import edu.upc.dsa.models.Login;
import edu.upc.dsa.models.Register;

import java.util.List;

public interface PlayerManager {
    public Player loginPlayer(Login login);
    public Player registerPlayer(Register register);
    public Player addPlayer(String username, String password, String confirm_password,String email, int coins);
    public Player getPlayerById(int idPlayer);
    public List<Player> findAll();

    public int size();
}
