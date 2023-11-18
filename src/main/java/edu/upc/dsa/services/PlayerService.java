package edu.upc.dsa.services;

import edu.upc.dsa.PlayerManagerImpl;
import edu.upc.dsa.PlayerManager;
import edu.upc.dsa.exceptions.PlayerNoEncontrado;
import edu.upc.dsa.exceptions.PlayerYaExiste;
import edu.upc.dsa.models.Player;
import edu.upc.dsa.models.Login;
import edu.upc.dsa.models.Register;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Api(value = "/player", description = "Endpoint to Player Service")
@Path("/player")

public class PlayerService{

    private PlayerManager pm;

    final static Logger logger = Logger.getLogger(PlayerService.class);

    public PlayerService() throws PlayerNoEncontrado, PlayerYaExiste {
        this.pm = PlayerManagerImpl.getInstance();
        if (pm.size()==0) {
            pm.addPlayer("Pablito","2345","680739345","pab@gmail.com");
            pm.addPlayer("Juanito","1234","680156796","juas@gmail.com");
            pm.addPlayer("Pepito","3456","611212117","pep@yahoo.es");
        }
    }

    //Get all the players
    @GET
    @ApiOperation(value = "get all players", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Player.class, responseContainer="List"),
    })
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlayers() {
        List<Player> players = this.pm.findAll();
        GenericEntity<List<Player>> entity = new GenericEntity<List<Player>>(players) {};
        return Response.status(201).entity(entity).build();
    }

    //Get a player by id
    @GET
    @ApiOperation(value = "get player", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Player.class),
            @ApiResponse(code = 404, message = "Player not found")
    })
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlayer(@PathParam("id") String id) throws PlayerNoEncontrado {
        Player p = this.pm.getPlayer(id);
        if (p == null) return Response.status(404).build();
        else  return Response.status(201).entity(p).build();
    }

    //Update a player using putPlayer method
    @PUT
    @ApiOperation(value = "update player", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Player.class),
            @ApiResponse(code = 404, message = "Player not found")
    })
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePlayer(Player p) throws PlayerNoEncontrado {
        Player p1 = this.pm.putPlayer(p);
        if (p1 == null) return Response.status(404).build();
        else  return Response.status(201).entity(p1).build();
    }

    //Register a player
    @POST
    @ApiOperation(value = "register a new player", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=Player.class),
            @ApiResponse(code = 500, message = "Validation Error")
    })
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newPlayer(Register r) {
       if(r.getUsername()==null || r.getPassword()==null || r.getTelephoneNumber()==null || r.getEmail()==null) {
           return Response.status(500).build();
       }
       else {
           try {
               Player p = this.pm.registerPlayer(r);
               return Response.status(201).entity(p).build();
           } catch (PlayerYaExiste playerYaExiste) {
               return Response.status(500).build();
           }
       }
    }

    //Login a player
    @POST
    @ApiOperation(value = "login a player", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=Player.class),
            @ApiResponse(code = 404, message = "Player not found"),
            @ApiResponse(code = 500, message = "Validation Error")
    })
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response loginPlayer(Login l) throws PlayerNoEncontrado {
        Player p = this.pm.loginPlayer(l);
        if (p == null) return Response.status(404).build();
        else  return Response.status(201).entity(p).build();
    }
}

