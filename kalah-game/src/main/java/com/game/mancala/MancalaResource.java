package com.game.mancala;

import java.io.IOException;
import java.util.Arrays;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;

/**
 * REST Web Service
 *
 *
 */
//This class is responsible for the rest services
//which are used to communicate with the front end
@Path("service")
public class MancalaResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of MancalaResource
     */
    public MancalaResource() {
    }

    /**
     * Retrieves representation of an instance of
     * com.game.mancala.MancalaResource
     *
     */
    @GET
    @Produces("text/html")
    public String getHtml() {

        return "service";
    }

    /**
     * This service is called to initiate the game
     * Returns the game's board
     */

    @GET
    @Path("/mancala")
    @Produces("text/html")
    public String startGame() throws IOException {
        return Arrays.toString(Mancala.init());
    }

    /**
     *
     * @param pit
     * @return
     * @throws IOException
     *
     * This service is called to make a move
     * Returns the game board
     */

    @POST
    @Path("/play")
    public String playGame(int pit) throws IOException {

        if (Mancala.flag == Mancala.playerOne.id) {
            Mancala.gameBoard[14] = Mancala.play(Mancala.playerOne, pit);
        } else {
            Mancala.gameBoard[14] = Mancala.play(Mancala.playerTwo, pit);
        }

        return Arrays.toString(Mancala.gameBoard);

    }

}
