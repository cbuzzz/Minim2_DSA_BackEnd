package edu.upc.dsa.CRUD.DAO;

import edu.upc.dsa.CRUD.MYSQL.FactorySession;
import edu.upc.dsa.CRUD.MYSQL.Session;
import edu.upc.dsa.models.Item;
import org.apache.log4j.Logger;
import java.util.List;
import java.util.LinkedList;
import java.sql.SQLException;
import edu.upc.dsa.exceptions.NoExistenItemException;



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

    final static Logger logger = Logger.getLogger(ItemManagerImpl.class);
    public List<Item> getItems() {
        Session session = null;
        List<Item> items = null;
        try{
            session = FactorySession.openSession();
           // items = session.findAll(Item.class);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return items;
    }

    public Item getItemById(String idItem) throws NoExistenItemException, SQLException {
        Session session = null;
        Item item = null;
        try {
            session = FactorySession.openSession();
            item = (Item) session.get(Item.class, "idItem", idItem);
        } finally {
            session.close();
        }
        return item;
    }
}
