package edu.upc.dsa;


import org.apache.log4j.Logger;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class ItemManagerTest {
    public static Logger log = Logger.getLogger(ItemManagerTest.class);
    ItemManager im;

    @Before
    public void setUp(){
        im = ItemManagerImpl.getInstance();
    }

    @After
    public void tearDown(){
        im = null;
    }

    @Test
    public void addItemsTest() {
        this.im.ShopItems();
        Assert.assertEquals(2, im.ItemNumber());
        log.info("Item added to the shop");
    }
}
