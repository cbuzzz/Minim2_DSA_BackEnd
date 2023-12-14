package edu.upc.dsa.services;

import edu.upc.dsa.CRUD.DAO.PlayerManager;
import edu.upc.dsa.CRUD.DAO.PlayerManagerImpl;
import edu.upc.dsa.CRUD.DAO.TrappyManager;
import edu.upc.dsa.CRUD.DAO.TrappyManagerImpl;

import edu.upc.dsa.exceptions.*;
import edu.upc.dsa.models.*;
import io.swagger.annotations.*;
import org.apache.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;



@Api(value = "/trappy", description = "Endpoint to Trappy Service")
@Path("/trappy")

public class TrappyService {
    final static Logger logger = Logger.getLogger(TrappyService.class);
    private TrappyManager tm;
    private PlayerManager pm;

    public TrappyService(){
        this.tm = TrappyManagerImpl.getInstance();
        this.pm = PlayerManagerImpl.getInstance();
    }

    @POST
    @ApiOperation(value = "Player registration", notes = "Register a new player")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Player.class),
            @ApiResponse(code = 500, message = "Validation Error")
    })
    @Path("/player/register")
    @Produces(MediaType.APPLICATION_JSON)
    public Response registerPlayer(Player player) throws UsernameInUseException{
        try{
            this.tm.registerPlayer(player);
            logger.info("Player added");
            return Response.status(201).entity(player).build();
        } catch (Exception e){
            logger.error("Error");
            return Response.status(500).build();
        }
    }

    @POST
    @ApiOperation(value = "Player login", notes = "Login a player")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Player.class),
            @ApiResponse(code = 404, message = "Player not registered"),
            @ApiResponse(code = 401, message = "Password not match")
    })
    @Path("/player/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response loginPlayer(Login login) throws PlayerNotResgisteredException, PasswordNotMatchException{
        try{
            Player player = this.tm.loginPlayer(new Login(login.getUsername(), login.getPassword()));
            return Response.status(201).entity(player).build();
        } catch (PlayerNotResgisteredException e) {
            return Response.status(404).build();
        } catch (PasswordNotMatchException e) {
            return Response.status(405).build();
        }
    }

    @GET
    @ApiOperation(value = "Get all items", notes = "Get all items from the shop")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Item.class, responseContainer="List"),
            @ApiResponse(code = 404, message = "No items found")
    })
    @Path("/items/shop")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItems(){
        try{
            List<Item> items = this.tm.ShopTrappy();
            GenericEntity<List<Item>> entity = new GenericEntity<List<Item>>(items) {};
            return Response.status(201).entity(entity).build();
        } catch (Exception e){
            return Response.status(404).build();
        }
    }
    @PUT
    @ApiOperation(value = "Buy an item", notes = "Buy an item from the shop")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Player.class),
            @ApiResponse(code = 404, message = "Player not found"),
            @ApiResponse(code = 401, message = "No coins for buy"),
            @ApiResponse(code = 500, message = "Validation Error")
    })
    @Path("/buyItems/{idPlayer}/{idItem}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buyItem(@PathParam("idPlayer") String idPlayer, @PathParam("idItem") String idItem) throws NoCoinsForBuyException, SQLException, NoExistenItemException{
        try{
            this.pm.buyItem(idPlayer, idItem);
            logger.info("Item bought");
            return Response.status(201).build();
        } catch (NoCoinsForBuyException e){
            logger.error("No coins for buy");
            return Response.status(401).build();
        } catch (SQLException e){
            logger.error("SQL Exception");
            return Response.status(500).build();
        }
    }
}





