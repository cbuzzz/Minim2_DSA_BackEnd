package edu.upc.dsa.CRUD.DAO;

import edu.upc.dsa.exceptions.*;
import edu.upc.dsa.models.*;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface TrappyManager {

    int size();

    /** Player **/

    Player registerPlayer(Player player) throws EmailInUseException, UsernameInUseException;
    Player loginPlayer(Login login) throws PlayerNotResgisteredException, PasswordNotMatchException;
    List<Player> getPlayers();
    int PlayerSize();
    int PlayersLoginSize();

    /** Item **/

    int ItemSize();
    List<Item> ShopTrappy();


    /**Partida**/

    //public void createPartida(Partida p);
    //public void updatePartida(Partida p);
    //public int numPartidas();
    //public List<Partida> getAllPartidas();

}
