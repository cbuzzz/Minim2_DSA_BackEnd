package edu.upc.dsa.services;

import edu.upc.dsa.ItemManager;
import edu.upc.dsa.ItemManagerImpl;
import edu.upc.dsa.models.Item;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Api(value = "/items", description = "Endpoint to Text Service")
@Path("/items")
public class ItemService {
    private ItemManager tm;
    public ItemService() {
        this.tm = ItemManagerImpl.getInstance();
        if (tm.ItemNumber()==0) {
            this.tm.ShopItems();
        }
    }
    @GET
    @ApiOperation(value = "get all Items", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Item.class, responseContainer="List"),
    })
    @Path("/shop")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItems() {

        List<Item> items = this.tm.ShopItems();
        GenericEntity<List<Item>> entity = new GenericEntity<List<Item>>(items) {};
        return Response.status(201).entity(entity).build()  ;

    }
}
