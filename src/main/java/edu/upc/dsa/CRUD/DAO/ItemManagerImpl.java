package edu.upc.dsa.CRUD.DAO;

import edu.upc.dsa.CRUD.FactorySession;
import edu.upc.dsa.CRUD.Session;
import java.util.List;
import edu.upc.dsa.models.Item;



public class ItemManagerImpl implements ItemManager {

        /*
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
            items.add(new Item("Flashlight", "+20faster", "Potion", 10));
            items.add(new Item("Key", "Escudo de madera", "Arma", 10));
            items.add(new Item("The horn", "+20faster", "Potion", 10));
            items.add(new Item("Escudo","Escudo de madera", "Arma", 10));
            items.add(new Item("Magnesi", "+20faster", "Potion", 10));
            items.add(new Item("Escudo", "Escudo de madera", "Arma", 10));
            logger.info("Items added to the shop");
            return items;
        }
        */
    public List<Item> getItems() {
        Session session = null;
        List<Item> items = null;
        try {
            session = FactorySession.openSession();
            items = session.findAll(Item.class);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return items;
    }

    public Item getItemByName(String nameItem) {
        Session session = null;
        Item item = null;
        try {
            session = FactorySession.openSession();
            item = (Item) session.select(Item.class, "name", nameItem);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return item;
    }
}
