package edu.upc.dsa;


import java.util.LinkedList;
import java.util.List;
import edu.upc.dsa.models.Item;
import org.apache.log4j.Logger;

public class ItemManagerImpl implements ItemManager {

        private static ItemManager instance;
        protected List<Item> items;
        final static Logger logger = Logger.getLogger(ItemManagerImpl.class);

        private ItemManagerImpl() {
            this.items = new LinkedList<>();
        }
        public static ItemManager getInstance() {
            if (instance == null) instance = new ItemManagerImpl();
            return instance;
        }
        public int ItemNumber() {
            return this.items.size();
        }
        public List<Item> ShopItems() {
            items.add(new Item("Magnesi", "+20faster", "Potion", 10));
            items.add(new Item("Escudo", "Escudo de madera", "Arma", 10));
            logger.info("Items added to the shop");
            return items;
        }
}
