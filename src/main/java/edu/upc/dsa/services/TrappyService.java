package edu.upc.dsa.services;

import edu.upc.dsa.CRUD.DAO.TrappyManager;
import edu.upc.dsa.CRUD.DAO.TrappyManagerImpl;

import edu.upc.dsa.exceptions.*;
import edu.upc.dsa.models.*;
import io.swagger.annotations.*;
import org.apache.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;



@Api(value = "/trappy", description = "Endpoint to Trappy Service")
@Path("/trappy")

public class TrappyService {
    private TrappyManager tm;
    final static Logger logger = Logger.getLogger(TrappyService.class);

    public TrappyService() throws SQLException, UsernameInUseException, EmailInUseException, NoExistenItemException, ItemWithSameIdAlreadyExists {
        this.tm = TrappyManagerImpl.getInstance();
        logger.info("TrappyService Started");
        if (tm.numPlayers() == 0) {
            this.tm.registerPlayer("Andrei", "1234", "680739345", "andrei@yahoo.es");
            this.tm.registerPlayer("Pau", "3334", "680739346", "paulinho@upc.edu");
            this.tm.registerPlayer("Marc", "4444", "680739347", "marcus@upc.edu");
        }
        if (tm.numItems() == 0) {
            this.tm.addItem("1", "Potion", "Heals 20 HP", "Consumable", 20);
            this.tm.addItem("2", "Sword", "Deals 20 damage", "Weapon", 20);
            this.tm.addItem("3", "Shield", "Blocks 20 damage", "Armor", 20);
        }
    }

    //Post: Register a new player
    @POST
    @ApiOperation(value = "Register a new player", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Player.class),
            @ApiResponse(code = 409, message = "Username in use"),
            @ApiResponse(code = 500, message = "Empty credentials")

    })
    @Path("/player/register")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response registerPlayer(Player player) throws UsernameInUseException, SQLException, EmailInUseException {
        if (player.getUsername() == null || player.getPassword() == null || player.getTelephoneNumber() == null || player.getEmail() == null )  return Response.status(500).entity(player).build();
        try {
            this.tm.registerPlayer(player.getUsername(),player.getPassword(),player.getTelephoneNumber(), player.getEmail());
            return Response.status(201).entity(player).build();
        }
        catch(UsernameInUseException | SQLException E){
            return Response.status(409).entity(player).build();
        }
    }

    //Post: Login a player
    @POST
    @ApiOperation(value = "Login a player", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Player.class),
            @ApiResponse(code = 404, message = "Player not found"),
            @ApiResponse(code = 405, message = "Password not match")
    })
    @Path("/player/login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response loginPlayer(Login login) throws PlayerNotResgisteredException, IncorrectCredentialsException {
        try{
            logger.info(login.getUsername());
            String nameplayer = this.tm.loginPlayer(login);

            return Response.status(201).entity(nameplayer).build();
        }
        catch (IncorrectCredentialsException | SQLException E){
            return Response.status(409).build();
        }
    }

    @PUT
    @ApiOperation(value = "Buy an item from the shop", notes = "Buy items")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 403, message = "Not enough money"),
            @ApiResponse(code = 409, message = "Player doesn't exist"),
            @ApiResponse(code = 401, message = "Item does not exist")
    })
    @Path("/items/purchase/{idItem}/{idPlayer}")
    public Response purchaseItem(@PathParam("idItem")String idItem, @PathParam("idPlayer") String idPlayer) {
        try {
            this.tm.purchaseItem(idItem, idPlayer);
            return Response.status(201).build();
        }
        catch (NoCoinsForBuyException e) {
            return Response.status(403).build();
        }
        catch (ItemDoesNotExist e) {
            return Response.status(401).build();
        }
        catch (PlayerNotResgisteredException | SQLException e) {
            return Response.status(409).build();
        }
    }
    //Add a item to the shop
    @POST
    @ApiOperation(value = "create a new Item", notes = "Do you want to create a new Item?")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response= Item.class),
            @ApiResponse(code = 500, message = "Some parameter is null or not valid")
    })
    @Path("/items/addItem")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response newItem(Item newItem){
        if (newItem.getId()==null || newItem.getPrice()<0 || newItem.getDescription()==null || newItem.getName()==null || newItem.getType()==null )  return Response.status(500).entity(newItem).build();
        try {
            this.tm.addItem(newItem.getId(),newItem.getDescription(),newItem.getName(),newItem.getType(),newItem.getPrice());
        } catch (SQLException | ItemWithSameIdAlreadyExists e) {
            throw new RuntimeException(e);
        }
        return Response.status(201).entity(newItem).build();
    }

    //Returns an Item by an ID
    @GET
    @ApiOperation(value = "Gives an Item by id", notes = "With an id")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Item.class),
            @ApiResponse(code = 404, message = "Gadget does not exist")
    })
    @Path("/items/{idItem}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGadget(@PathParam("idItem") String idItem) {
        try {
            Item item = (Item) this.tm.getItem(idItem);
            return Response.status(201).entity(item).build();
        }
        catch (ItemDoesNotExist E){
            return Response.status(404).build();
        }
    }

    //Deletes an Item by an ID
    @DELETE
    @ApiOperation(value = "Deletes a item", notes = "Deletes a item")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Item not found")
    })
    @Path("/items/delete/{idItem}")
    public Response deleteItem(@PathParam("idItem") String id) {
        try{
            this.tm.deleteItem(id);
            return Response.status(201).build();
        }
        catch (ItemDoesNotExist e) {
            return Response.status(401).build();
        }
    }
}





