package edu.upc.dsa.CRUD.DAO;

import edu.upc.dsa.exceptions.NoExistenItemException;
import edu.upc.dsa.models.Item;
import java.util.List;

public interface ItemManager {
    public List<Item> getItems();
    Item getItemByName(String nameItem) throws NoExistenItemException;
}
