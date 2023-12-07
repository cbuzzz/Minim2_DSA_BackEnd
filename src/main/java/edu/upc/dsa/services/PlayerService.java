package edu.upc.dsa.services;

import edu.upc.dsa.CRUD.DAO.PlayerManagerImpl;
import edu.upc.dsa.CRUD.DAO.PlayerManager;
import edu.upc.dsa.exceptions.*;
import edu.upc.dsa.models.Player;
import edu.upc.dsa.models.Login;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Api(value = "/player", description = "Endpoint to Player Service")
@Path("/player")
public class PlayerService{
        private PlayerManager pm;

        public PlayerService() throws UsernameInUseException {
            this.pm = PlayerManagerImpl.getInstance();
            if (pm.getPlayers().size() == 0) {
                //pm.registerPlayer(new Player("Andrei", "1234", "680739345", "andrei@upc.edu"));
                //pm.registerPlayer(new Player("Pau", "3334", "680739346", "pau@gmail.com"));
                //pm.registerPlayer(new Player("Marc", "4444", "680739347", "Marc@yahoo.es"));
            }
        }

        //Post: Register a new player
        @POST
        @ApiOperation(value = "Register a new player", notes = "asdasd")
        @ApiResponses(value = {
                @ApiResponse(code = 201, message = "Successful", response = Player.class),
                @ApiResponse(code = 404, message = "Username in use"),
        })
        @Path("/register")
        @Consumes(MediaType.APPLICATION_JSON)
        public Response registerPlayer(Player player) throws UsernameInUseException{
            try {
                this.pm.registerPlayer(new Player(player.getUsername(), player.getPassword(), player.getTelephoneNumber(), player.getEmail()));
                return Response.status(201).entity(player).build();
            } catch (UsernameInUseException e) {
                return Response.status(404).build();
            }
        }

        //Post: Login a player
        @POST
        @ApiOperation(value = "Login a player", notes = "asdasd")
        @ApiResponses(value = {
                @ApiResponse(code = 201, message = "Successful", response = Player.class),
                @ApiResponse(code = 404, message = "Player not found"),
                @ApiResponse(code = 405, message = "Password not match"),
        })
        @Path("/login")
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
}

