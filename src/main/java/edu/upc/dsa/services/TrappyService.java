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

    public TrappyService() throws SQLException, UsernameInUseException, EmailInUseException, NoExistenItemException{
        this.tm = TrappyManagerImpl.getInstance();
        logger.info("TrappyService Started");
        if (tm.numPlayers()==0) {
            this.tm.registerPlayer("Andrei", "1234", "680739345", "andrei@yahoo.es");
            this.tm.registerPlayer("Pau", "3334", "680739346", "paulinho@upc.edu");
            this.tm.registerPlayer("Marc", "4444", "680739347", "marcus@upc.edu");
        }
        if (tm.numItems()==0) {
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
            @ApiResponse(code = 404, message = "Username in use"),
    })
    @Path("/player/register")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registerPlayer(Player player) throws UsernameInUseException, SQLException, EmailInUseException {
        if(player.getUsername()==null || player.getPassword()==null || player.getTelephoneNumber()==null || player.getEmail()==null){

        return null;
    }

        //Post: Login a player
        @POST
        @ApiOperation(value = "Login a player", notes = "asdasd")
        @ApiResponses(value = {
                @ApiResponse(code = 201, message = "Successful", response = Player.class),
                @ApiResponse(code = 404, message = "Player not found"),
                @ApiResponse(code = 405, message = "Password not match"),
        })
        @Path("/player/login")
        @Consumes(MediaType.APPLICATION_JSON)
        public Response loginPlayer(Login login) throws PlayerNotResgisteredException, PasswordNotMatchException{
            try {
                Player player = this.pm.loginPlayer(new Login(login.getUsername(), login.getPassword()));
                return Response.status(201).entity(player).build();
            } catch (PlayerNotResgisteredException e) {
                return Response.status(404).build();
            } catch (PasswordNotMatchException e) {
                return Response.status(405).build();
            }
        }

        @PUT
        @ApiOperation(value = "Buy an item from the shop", notes = "Buy items")
        @ApiResponses(value = {
                @ApiResponse(code = 201, message = "Successful"),
                @ApiResponse(code = 403, message = "Not enough money"),
                @ApiResponse(code = 409, message = "Player not found")
        })
        @Path("/items/purchase/{idItem}/{idPlayer}")
        public Response purchaseItem(@PathParam("idItem")String ,@PathParam("idPlayer") String idPlayer) {
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
