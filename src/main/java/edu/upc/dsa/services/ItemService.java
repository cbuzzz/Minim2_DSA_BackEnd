package edu.upc.dsa.services;

import edu.upc.dsa.ItemManager;
import edu.upc.dsa.ItemManagerImpl;
import edu.upc.dsa.exceptions.NoCoinsForBuyException;
import edu.upc.dsa.exceptions.NoExistenItemException;
import edu.upc.dsa.exceptions.PlayerNotResgisteredException;
import edu.upc.dsa.models.Item;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;


@Api(value = "/items", description = "Endpoint to Text Service")
@Path("/items")
public class ItemService {
    private ItemManager tm;
    public ItemService() {
        this.tm = ItemManagerImpl.getInstance();
        if (tm.ItemNumber()==0) {
            this.tm.shopItems();
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

        List<Item> items = this.tm.shopItems();
        GenericEntity<List<Item>> entity = new GenericEntity<List<Item>>(items) {};
        return Response.status(201).entity(entity).build()  ;

    }
    @POST
    @ApiOperation(value = "Buy an item from the shop", notes = "Buy items")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 403, message = "Not enough money"),
            @ApiResponse(code = 409, message = "Player not found")
    })
    @Path("/items/purchase/{idItem}/{idPlayer}")
    public Response purchaseItem(@PathParam("idItem")String idItem,@PathParam("idPlayer") String idPlayer) {
        try{
            this.tm.purchaseItem(idItem,idPlayer);
            return Response.status(201).build();
        }
        catch (NoCoinsForBuyException e){
            return Response.status(403).build();
        }
        catch (NoExistenItemException e) {
            return Response.status(401).build();
        }
        catch (PlayerNotResgisteredException | SQLException e) {
            return Response.status(409).build();
        }
    }
    }
}
