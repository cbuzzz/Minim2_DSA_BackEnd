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
            items.add(new Item("Flashlight","https://png.pngtree.com/background/20230612/original/pngtree-wolf-animals-images-wallpaper-for-pc-384x480-picture-image_3180467.jpg", "+20faster", "Potion", 10));
            items.add(new Item("Key","https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcQl0BaK1HcOHmEYrdp_95-SiTv3WV8B2MnMKcVKGPrBh9aOjorN", "Escudo de madera", "Arma", 10));
            items.add(new Item("The horn","https://png.pngtree.com/background/20230612/original/pngtree-wolf-animals-images-wallpaper-for-pc-384x480-picture-image_3180467.jpg", "+20faster", "Potion", 10));
            items.add(new Item("Escudo","https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcQl0BaK1HcOHmEYrdp_95-SiTv3WV8B2MnMKcVKGPrBh9aOjorN", "Escudo de madera", "Arma", 10));
            items.add(new Item("Magnesi","https://png.pngtree.com/background/20230612/original/pngtree-wolf-animals-images-wallpaper-for-pc-384x480-picture-image_3180467.jpg", "+20faster", "Potion", 10));
            items.add(new Item("Escudo","https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcQl0BaK1HcOHmEYrdp_95-SiTv3WV8B2MnMKcVKGPrBh9aOjorN", "Escudo de madera", "Arma", 10));
            logger.info("Items added to the shop");
            return items;
        }
}
