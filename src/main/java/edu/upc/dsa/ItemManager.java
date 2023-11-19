package edu.upc.dsa;

import edu.upc.dsa.models.Item;
import java.util.List;
import edu.upc.dsa.exceptions.ProductoNoExisteException;

public interface ItemManager {
    int ItemNumber();
    List<Item> ShopItems();
}
