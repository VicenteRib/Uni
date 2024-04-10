/**
 * @author Vicente Ribeiro 67241 vg.ribeiro@campus.fct.unl.pt
 */

import java.util.Scanner;

import java.io.*;
public class Main {

    private static final String QUIT_MESSAGE = "Bye.";

    public static void main(String[] args){

        Scanner in = new Scanner(System.in);
        Game game = new gameClass();
        selectCommand(game, in);
        in.close();
    }

    /**
     * Enumerates all the different commands available
     */
    private enum Command{
    quit, help, game, status, map, bunkers, players, create, move, attack, unkown
    }

    /**
     * Reads the command input and returns the chosen command
     * @param in: Scanner object
     * @return value of the chosen command
     */
    private static Command getCommand(Scanner in){
        try {
            String command = in.next();
            return Command.valueOf(command);
        } catch (IllegalArgumentException e) {
            // in case the command is not recognized
            return Command.UNKNOWN;
        }
    }

    /**
     * Selects the command to be executed
     * @param artAuction:  object
     * @param in: Scanner object
     */
    private static void selectCommand(Game game, Scanner in){
        Command command;
        do{
            command = getCommand(in);
            processCommand(gmae, command, in);
        }while (!command.equals(Command.QUIT));
    }

    /**
     * Evaluates which command to execute
     * @param artAuction:  System object
     * @param in: Scanner object
     * @param command: Command we want to execute
     */
    private static void processCommand (Game game,Command command, Scanner in){
        switch (command) {
            case game -> processGame(in, game);
            case help -> processHelp(in, game);
            case status -> processStatus(in, game);
            case map -> processMap(in, game);
            case bunkers -> processBunkers(in, game);
            case players -> processPlayers(in, game);
            case create -> processCreate(in, game);
            case move -> processMove(in, game);
            case attack -> processAttack(in, game);
            case quit -> processQuit();
            case unkown -> System.out.printf(Outputs.UNKNOWN);
        }
        System.out.println();
    }

    /**
     * Method to add user to artAuction
     * @param in Scanner object
     * @param artAuction System object to place the user in
     */
    private static void processGame(Scanner in, Game game){

    }

    /**
     * Method to add user of type artist to artAuction
     * @param in Scanner Object
     * @param artAuction System object to place the artist in
     */
    private static void processHelp(Scanner in, Game game){
        if(gameIsinSession)
            System.out.printf(Outputs.HELP_TEAM);
        else
            System.out.printf(Outputs.HELP);
    }

    /**
     * Method to remover user from artAuction
     * @param in Scanner Object
     * @param artAuction System object to remove the user from
     */
    private static void processStatus(Scanner in, Game game){

    }

    /**
     * Add an art work to a specific artist inside artAuction
     * @param in Scanner Object
     * @param artAuction System object where the artist, the author of the work is in.
     */
    private static void processMap(Scanner in, Game game){

    }

    /**
     * Gives the info of a specific user inside artAuction
     * @param in Scanner Object
     * @param artAuction System object where the user is located
     */
    private static void processBunkers(Scanner in, Game game){

    }
    /**
     * Gives the info of a specific user of type artist inside artAuction
     * @param in Scanner Object
     * @param artAuction System object where the artist is located
     */
    private static void processPlayers(Scanner in, Game game){

    }
    /**
     * Gives the info of a specific work of an artist inside artAuction
     * @param in Scanner Object
     * @param artAuction System object where the artist and the work is located
     */
    private static void processCreate(Scanner in, Game game){

    }

    /**
     * Creates an auction inside artAuction
     * @param in Scanner object
     * @param artAuction System object where the auction will be placed inside
     */
    private static void processMove(Scanner in, Game game){

    }

    /**
     * Adds a work to a specific auction inside artAuction
     * @param in Scanner Object
     * @param artAuction System object where the auction of which the work will be placed is located.
     */
    private static void processAttack(Scanner in, Game game){

    }

    /**
     * Writes the QUIT output and saves the program
     * @param artAuction the content to be saved
     */
    private static void processQuit(){
        System.out.printf(Outputs.QUIT);
        System.exit(0);
    }

}