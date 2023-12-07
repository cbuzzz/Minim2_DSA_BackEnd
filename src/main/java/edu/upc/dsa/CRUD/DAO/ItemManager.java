package edu.upc.dsa.CRUD.DAO;

import edu.upc.dsa.models.Item;
import java.util.List;

public interface ItemManager {
    int ItemNumber();
    List<Item> ShopItems();
}
