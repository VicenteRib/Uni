/**
 * @author Joao Ventura 65590 jm.ventura@campus.fct.unl.pt
 * @author Vicente Ribeiro 67241 vg.ribeiro@campus.fct.unl.pt
 */

import dataStructures.*;

import java.util.Scanner;

import java.io.*;
import system.*;
import system.artWork.Work;
import system.users.*;
import system.auction.*;

public class Main {

    /**
     * Name of the file where the data will be stored
     */
    private static final String ART_AUCTION_FILE = "storedArtAuction.dat";

    public static void main(String[] args){

        Scanner in = new Scanner(System.in);
        ArtAuction artAuction = load();
        selectCommand(artAuction,in);
        in.close();
    }

    /**
     * Enumerates all the different commands available
     */
    private enum Command{
        ADDUSER,ADDARTIST, REMOVEUSER, ADDWORK, INFOUSER, INFOARTIST, INFOWORK, CREATEAUCTION, ADDWORKAUCTION,
        BID, CLOSEAUCTION, LISTAUCTIONWORKS, LISTARTISTWORKS, LISTBIDSWORK, LISTWORKSBYVALUE, QUIT, UNKNOWN;
    }

    /**
     * Reads the command input and returns the chosen command
     * @param in: Scanner object
     * @return value of the chosen command
     */
    private static Command getCommand(Scanner in){
        try {
            String command = in.next().toUpperCase();
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
    private static void selectCommand(ArtAuction artAuction, Scanner in){
        Command command;
        do{
            command = getCommand(in);
            processCommand(artAuction, command, in);
        }while (!command.equals(Command.QUIT));
    }

    /**
     * Evaluates which command to execute
     * @param artAuction:  System object
     * @param in: Scanner object
     * @param command: Command we want to execute
     */
    private static void processCommand (ArtAuction artAuction , Command command, Scanner in){
        switch (command) {
            case ADDUSER -> processAddUser(in, artAuction);
            case ADDARTIST -> processAddArtist(in, artAuction);
            case REMOVEUSER -> processRemoveUser(in, artAuction);
            case ADDWORK -> processAddWork(in, artAuction);
            case INFOUSER -> processInfoUser(in, artAuction);
            case INFOARTIST -> processInfoArtist(in, artAuction);
            case INFOWORK -> processInfoWork(in, artAuction);
            case CREATEAUCTION -> processCreateAuction(in, artAuction);
            case ADDWORKAUCTION -> processAddWorkAuction(in, artAuction);
            case BID -> processBid(in, artAuction);
            case CLOSEAUCTION -> processCloseAuction(in, artAuction);
            case LISTAUCTIONWORKS -> processListAuctionWorks(in, artAuction);
            case LISTARTISTWORKS -> processListArtistWorks(in, artAuction);
            case LISTBIDSWORK -> processListBidsWork(in, artAuction);
            case LISTWORKSBYVALUE ->processListWorksByValue(artAuction);
            case QUIT -> processQuit(artAuction);
            case UNKNOWN -> System.out.printf(Outputs.UNKNOWN);
        }
        System.out.println();
    }

    /**
     * Method to add user to artAuction
     * @param in Scanner object
     * @param artAuction System object to place the user in
     */
    private static void processAddUser(Scanner in, ArtAuction artAuction){
        String login = in.next();
        String name = in.nextLine();
        name=name.trim();
        int age = in.nextInt();
        String email = in.next();
        try{
            artAuction.addUser(login, name, age, email);
            System.out.printf(Outputs.USER_WAS_REGISTERED);
        }catch(NonAdultUserException e){
            System.out.printf(Outputs.NON_ADULT_USER);
        }catch(AlreadyExistantUserException e) {
            System.out.printf(Outputs.ALREADY_EXISTANT_USER);
        }
    }

    /**
     * Method to add user of type artist to artAuction
     * @param in Scanner Object
     * @param artAuction System object to place the artist in
     */
    private static void processAddArtist(Scanner in, ArtAuction artAuction){
        String login = in.next();
        String name = in.nextLine();
        name=name.trim();
        String artisticName = in.nextLine();
        int age = in.nextInt();
        String email = in.next();
        try{
            artAuction.addArtist(login, name, artisticName, age, email);
            System.out.printf(Outputs.ARTIST_WAS_REGISTERED);
        }catch(NonAdultUserException e){
            System.out.printf(Outputs.NON_ADULT_USER);
        }catch(AlreadyExistantUserException e) {
            System.out.printf(Outputs.ALREADY_EXISTANT_USER);
        }
    }

    /**
     * Method to remover user from artAuction
     * @param in Scanner Object
     * @param artAuction System object to remove the user from
     */
    private static void processRemoveUser(Scanner in, ArtAuction artAuction){
        String login = in.next();
        try {
            artAuction.removeUser(login);
            System.out.printf(Outputs.USER_REMOVED);
        } catch(NonexistantUserException e){
            System.out.printf(Outputs.NON_EXISTANT_USER);
        } catch(ArtWorkInAuctionException e){
            System.out.printf(Outputs.ARTWORK_IN_AUCTION);
        } catch(OffersRemainingException e){
            System.out.printf(Outputs.OFFERS_REMAINING);
        }

    }

    /**
     * Add an art work to a specific artist inside artAuction
     * @param in Scanner Object
     * @param artAuction System object where the artist, the author of the work is in.
     */
    private static void processAddWork(Scanner in, ArtAuction artAuction){
        String id = in.next().trim();
        String login = in.next();
        int year = in.nextInt();
        String name = in.nextLine().trim();
        try{
            artAuction.addWork(id, login, year, name);
            System.out.printf(Outputs.ARTWORK_WAS_REGISTERED);
        }catch(AlreadyExistantArtWorkException e){
            System.out.printf(Outputs.ALREADY_EXISTANT_ARTWORK);
        } catch(NonexistantUserException e){
            System.out.printf(Outputs.NON_EXISTANT_USER);
        } catch(NonexistantArtistException e){
            System.out.printf(Outputs.NON_EXISTANT_ARTIST);
        }
    }

    /**
     * Gives the info of a specific user inside artAuction
     * @param in Scanner Object
     * @param artAuction System object where the user is located
     */
    private static void processInfoUser(Scanner in, ArtAuction artAuction){
        String login = in.next();
        try{
            User user = artAuction.infoUser(login).next();
            System.out.printf(Outputs.INFO_USER, login, user.getName(), user.getAge(), user.getEmail());
        }catch(NonexistantUserException e){
            System.out.printf(Outputs.NON_EXISTANT_USER);
        }
    }
    /**
     * Gives the info of a specific user of type artist inside artAuction
     * @param in Scanner Object
     * @param artAuction System object where the artist is located
     */
    private static void processInfoArtist(Scanner in, ArtAuction artAuction){
        String login = in.next();
        try{
            Artist artist = artAuction.infoArtist(login).next();
            System.out.printf(Outputs.INFO_ARTIST, login, artist.getName(), artist.getArtistName(), artist.getAge(), artist.getEmail());
        }catch(NonexistantUserException e){
            System.out.printf(Outputs.NON_EXISTANT_USER);
        } catch(NonexistantArtistException e){
            System.out.printf(Outputs.NON_EXISTANT_ARTIST);
        }
    }
    /**
     * Gives the info of a specific work of an artist inside artAuction
     * @param in Scanner Object
     * @param artAuction System object where the artist and the work is located
     */
    private static void processInfoWork(Scanner in, ArtAuction artAuction){
        String workID = in.next();
        try{
            Work work = artAuction.infoWork(workID).next();
            Artist artist = work.getArtist();
            System.out.printf(Outputs.INFO_WORK, workID, work.getName(), work.getYearOfRelease(), work.getHighestEvaluation(), artist.getLogin(), artist.getName());
        }catch(NonexistantArtWorkException e){
            System.out.printf(Outputs.NON_EXISTANT_ARTOWRK);
        }
    }

    /**
     * Creates an auction inside artAuction
     * @param in Scanner object
     * @param artAuction System object where the auction will be placed inside
     */
    private static void processCreateAuction(Scanner in, ArtAuction artAuction){
        String auctionID = in.next();
        try{
            artAuction.createAuction(auctionID);
            System.out.printf(Outputs.AUCTION_WAS_REGISTERED);
        }catch(AlreadyExistantAuctionException e){
            System.out.printf(Outputs.ALREADY_EXISTANT_AUCTION);
        }
    }

    /**
     * Adds a work to a specific auction inside artAuction
     * @param in Scanner Object
     * @param artAuction System object where the auction of which the work will be placed is located.
     */
    private static void processAddWorkAuction(Scanner in, ArtAuction artAuction){
        String auctionID = in.next().trim();
        String workID = in.next().trim();
        int minValue = in.nextInt();
        try{
            artAuction.addWorkAuction(auctionID,workID, minValue);
            System.out.printf(Outputs.ARTWORK_ADDED_AUCTION);
        }catch(NonexistantAuctionException e){
            System.out.printf(Outputs.NON_EXISTANT_AUCTION);
        } catch(NonexistantArtWorkException e){
            System.out.printf(Outputs.NON_EXISTANT_ARTOWRK);
        }
    }

    /**
     * Adds a bid to a specific work inside a unique auction if the parameters are met
     * @param in Scanner object
     * @param artAuction System object where the auction and the specific art work is placed inside.
     */
    private static void processBid(Scanner in, ArtAuction artAuction){
        String auctionID = in.next();
        String workID = in.next();
        String login = in.next();
        int value = in.nextInt();
        try{
            artAuction.bid(auctionID, workID, login, value);
            System.out.printf(Outputs.BID_ACCEPTED);
        }catch(NonexistantUserException e){
            System.out.printf(Outputs.NON_EXISTANT_USER);
        } catch(NonexistantAuctionException e){
            System.out.printf(Outputs.NON_EXISTANT_AUCTION);
        } catch(ArtWorkNotInAuctionException e){
            System.out.printf(Outputs.ARTWORK_NOT_IN_AUCTION);
        } catch(BidLowerMinimumException e){
            System.out.printf(Outputs.BID_LOWER_MINIMUM);
        }
    }

    /**
     * Closes a specific auction and its works with their bidding
     * @param in Scanner object
     * @param artAuction System object where the auction is located
     */
    private static void processCloseAuction(Scanner in, ArtAuction artAuction){
        String auctionID = in.next();
        try{
            Iterator<Work> it = artAuction.closeAuction(auctionID);
            System.out.printf(Outputs.CLOSED_AUCTION);
            while(it.hasNext()){
                Work work = it.next();
                if(work.hasBids(auctionID)) {
                    work.removeBids(auctionID);
                    User owner = work.getOwner();
                    System.out.printf(Outputs.CLOSING_AUCTION_LIST_SOLD, work.getID(), work.getName(), owner.getLogin(), owner.getName(), work.getSoldPrice());
                }else {
                    System.out.printf(Outputs.CLOSING_AUCTION_LIST_NOT_SOLD, work.getID(), work.getName());
                }
            }
        }catch(NonexistantAuctionException e){
            System.out.printf(Outputs.NON_EXISTANT_AUCTION);
        }
    }

    /**
     * Lists all works of a specific auction by placement order
     * @param in Scanner object
     * @param artAuction System object where the auction is placed inside
     */
    private static void processListAuctionWorks(Scanner in, ArtAuction artAuction){
        String auctionID = in.next();
        try{
            Iterator<Work> it = artAuction.listAuctionWorks(auctionID);
            System.out.println();
            while(it.hasNext()){
                Work work= it.next();
                User artist = work.getArtist();
                    System.out.printf(Outputs.LIST_AUCTION_WORKS, work.getID(), work.getName(), work.getYearOfRelease(), work.getHighestEvaluation(), artist.getLogin(), artist.getName());
            }
        }catch(NonexistantAuctionException e){
            System.out.printf(Outputs.NON_EXISTANT_AUCTION);
        } catch(NoArtWorkAuctionException e) {
            System.out.printf(Outputs.NO_ARTWORK_AUCTION);
        }
    }

    /**
     * List all the works created by an artist
     * @param in Scanner object
     * @param artAuction System object where the artist is located
     */
    private static void processListArtistWorks(Scanner in, ArtAuction artAuction){
        String login = in.next();
        try{
            Iterator<Entry<String, Work>> it = artAuction.listArtistWork(login);
            System.out.println();
            while(it.hasNext()){
                Work work= it.next().getValue();

                System.out.printf(Outputs.LIST_ARTIST_WORKS, work.getID(), work.getName(),
                        work.getYearOfRelease(), work.getHighestEvaluation());
            }
        }catch(NonexistantUserException e){
            System.out.printf(Outputs.NON_EXISTANT_USER);
        } catch(NonexistantArtistException e){
            System.out.printf(Outputs.NON_EXISTANT_ARTIST);
        } catch(NoArtWorkArtistException e){
            System.out.printf(Outputs.NO_ARTWORK_ARTIST);
        }
    }

    /**
     * Lists all the bids of a work in a given auction
     * @param in Scanner object
     * @param artAuction System object where the auction and the work are located
     */
    private static void processListBidsWork(Scanner in, ArtAuction artAuction){
        String auctionID = in.next();
        String workID = in.next();
        try{
            Iterator<Bid> it = artAuction.listBidsWork(auctionID, workID);
            System.out.println();
            while(it.hasNext()) {
                Bid bid = it.next();
                if(bid.getAuction().equals(auctionID)) {
                    User user = bid.getUser();
                    System.out.printf(Outputs.LIST_BIDS_WORK, user.getLogin(), user.getName(), bid.getBidValue());
                }
            }
        }catch(NonexistantAuctionException e){
            System.out.printf(Outputs.NON_EXISTANT_AUCTION);
        } catch(ArtWorkNotInAuctionException e){
            System.out.printf(Outputs.ARTWORK_NOT_IN_AUCTION);
        } catch(NoBidsArtWorkException e){
            System.out.printf(Outputs.NO_BIDS_ARTWORK);
        }
    }

    /**
     * list all the works of the system, sorted by their value they were sold
     * @param artAuction System object where the works are located
     */
    private static void processListWorksByValue(ArtAuction artAuction){
        try{
            Iterator<Entry<Work, Work>> it =artAuction.listWorksByValue();
            System.out.println();
            while(it.hasNext()) {
                Work work = it.next().getKey();
                User artist = work.getArtist();
                System.out.printf(Outputs.LIST_WORKS_VALUE, work.getID(), work.getName(), work.getYearOfRelease(),
                        work.getHighestEvaluation(), artist.getLogin(), artist.getName());
            }
        }catch(NoArtWorkSoldException e){
            System.out.printf(Outputs.NO_ARTWORK_SOLD);
        }
    }

    /**
     * Writes the QUIT output and saves the program
     * @param artAuction the content to be saved
     */
    private static void processQuit(ArtAuction artAuction){
        System.out.printf(Outputs.QUIT);
        save(artAuction);
    }

    /**
     * Returns the data from the previous time the code was run. If it's the first time running the code, returns a new object instance
     * @return the data from the previous time the code was run. If it's the first time running the code, returns a new object instance
     */
    @SuppressWarnings("unchecked")
    private static ArtAuction load(){
        try{
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ART_AUCTION_FILE));
            ArtAuction artAuction = (ArtAuctionClass) ois.readObject();
            ois.close();
            return artAuction;
        }catch(IOException | ClassNotFoundException e){
            return new ArtAuctionClass();
        }
    }

    /**
     * Saves the data of the system before closing the program
     * @param artAuction the content to be saved
     */
    private static void save(ArtAuction artAuction){
        try{
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ART_AUCTION_FILE));
            oos.writeObject(artAuction);
            oos.flush();
            oos.close();
        }catch(IOException e){
            throw new RuntimeException();
        }
    }

}