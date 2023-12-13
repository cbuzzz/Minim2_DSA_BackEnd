package edu.upc.dsa.CRUD.DAO;

import edu.upc.dsa.exceptions.NoExistenItemException;
import edu.upc.dsa.models.Item;
import edu.upc.dsa.models.Player;
import java.util.List;
import java.sql.SQLException;
import java.util.List;

public interface ItemManager {
    public List<Item> getItems();
    Item getItemById(String idItem) throws NoExistenItemException, SQLException;
}
