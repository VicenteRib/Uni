/**
 * @author Joao Ventura 65590 jm.ventura@campus.fct.unl.pt
 * @author Vicente Ribeiro 67241 vg.ribeiro@campus.fct.unl.pt
 */

package system;

import dataStructures.Entry;
import dataStructures.Iterator;
import system.artWork.Work;
import system.auction.Bid;
import system.users.Artist;
import system.users.User;

import java.io.Serializable;

/**
 * ArtAuction Interface
 */
public interface ArtAuction extends Serializable {
    /**
     * Adds a new User to the program
     * @param userLogin login of the user
     * @param name name of the user
     * @param age age of the user
     * @param email email of the user
     * @throws NonAdultUserException if the user age is below 18
     * @throws AlreadyExistantUserException if the user login already exists in the system
     */
    void addUser(String userLogin, String name, int age, String email)
            throws NonAdultUserException, AlreadyExistantUserException;

    /**
     * Adds a new Artist to the program
     * @param artistLogin login of teh artist
     * @param name name of the artist
     * @param artistName artist name
     * @param age age of the artist
     * @param email email of the artist
     * @throws NonAdultUserException  if the age is below 18
     * @throws AlreadyExistantUserException if the artistLogin already exists in the system
     */
    void addArtist(String artistLogin, String name, String artistName, int age, String email)
            throws NonAdultUserException, AlreadyExistantUserException;

    /**
     * Removes a User or Artist from the system
     * @param userLogin login of the user or artist
     * @throws NonexistantUserException if the login doesn't exist on the system
     */
    void removeUser(String userLogin) throws NonexistantUserException, ArtWorkInAuctionException, OffersRemainingException;

    /**
     * Adds a Work to a given artist
     * @param workID work ID
     * @param artistLogin login of the artist
     * @param yearOfRelease year of work release
     * @param name name of the work
     * @throws AlreadyExistantArtWorkException if the work ID already exists on the system
     * @throws NonexistantUserException if the artistLogin doesn't exist on the system
     * @throws NonexistantArtistException if the artistLogin belongs to a user instead of an artist
     */
    void addWork(String workID, String artistLogin, int yearOfRelease, String name)
            throws AlreadyExistantArtWorkException, NonexistantUserException, NonexistantArtistException;

    /**
     * Returns an Iterator of a List with the given user if it exists
     * @param userLogin login of the user to give info about
     * @return an Iterator of a List with the given user
     * @throws NonexistantUserException if the user login doesn't exist on the system
     */
    Iterator<User> infoUser(String userLogin) throws NonexistantUserException;

    /**
     * Returns an Iterator of a List with the given artist if it exists
     * @param artistLogin login of the artist
     * @return an Iterator of a List with the given artist
     * @throws NonexistantUserException if the artist doesn't exist on the system
     * @throws NonexistantArtistException if the artistLogin doesn't belong to an artist
     */
    Iterator<Artist> infoArtist(String artistLogin)
            throws NonexistantUserException, NonexistantArtistException;

    /**
     * Returns an Iterator of a List with the given work if it exists
     * @param workID ID fo the work
     * @return an Iterator of a List with the given work
     * @throws NonexistantArtWorkException if the workID isn't in the system
     */
    Iterator<Work> infoWork(String workID) throws NonexistantArtWorkException;

    /**
     * Creates an auction to the program
     * @param auctionID ID of the auction
     * @throws AlreadyExistantAuctionException if the auctionID is already on the system
     */
    void createAuction(String auctionID) throws AlreadyExistantAuctionException;

    /**
     * Adds a work to the auction if both of them exist
     * @param auctionID  ID of the auction
     * @param workID ID of the work
     * @param minValue min bid value
     * @throws NonexistantAuctionException if the auctionID doesn't exist on the system
     * @throws NonexistantArtWorkException if the workID doesn't exist on the system
     */
    void addWorkAuction(String auctionID, String workID, int minValue)
            throws NonexistantAuctionException, NonexistantArtWorkException;

    /**
     * Adds a bid to a given work on a given auction by a given user
     * @param auctionID ID of the auction
     * @param workID ID of the work
     * @param userLogin login of the user
     * @param bidValue value of the bid
     * @throws NonexistantUserException if the userLogin doesn't exist on the system
     * @throws NonexistantAuctionException if the auctionID doesn't exist on the system
     * @throws NonexistantArtWorkException if the workID doesn't exist on the system
     * @throws BidLowerMinimumException if the bidValue is lower than the minimum bid value of the work
     */
    void bid(String auctionID, String workID, String userLogin, int bidValue)
            throws NonexistantUserException, NonexistantAuctionException, ArtWorkNotInAuctionException, BidLowerMinimumException;

    /**
     * Closes a given auction and gives an iterator with the works on the given auction if teh auction exists
     * @param auctionID ID of the auction
     * @return an Iterator of the works in the given auction
     * @throws NonexistantAuctionException if the auction doesn't exist on the system
     */
    Iterator<Work> closeAuction(String auctionID) throws NonexistantAuctionException;

    /**
     * Returns an Iterator with the works on an auction if the auction exists, and it has artWorks on it
     * @param auctionID ID of the auction
     * @return an Iterator of the works in the given auction
     * @throws NonexistantAuctionException if the auctionID doesn't exist on the system
     * @throws NoArtWorkAuctionException if the auction has no works on it
     */
    Iterator<Work> listAuctionWorks(String auctionID)
            throws NonexistantAuctionException, NoArtWorkAuctionException;

    /**
     * Returns an Iterator with the works of a given artist if it exists, and it has works of their credit
     * @param artistLogin login of the artist
     * @return an Iterator with the works of the artist
     * @throws NonexistantUserException if the artistLogin doesn't exist on the system
     * @throws NonexistantArtistException if the artistLogin belongs to a user instead of an artist
     * @throws NoArtWorkArtistException if the artist has no works of their credit
     */
    Iterator<Entry<String, Work>> listArtistWork(String artistLogin)
            throws NonexistantUserException, NonexistantArtistException, NoArtWorkArtistException;

    /**
     * Returns an Iterator with the bids of a given work in a given auction if both exist and the work has bids
     * @param auctionID ID of the auction
     * @param workID ID of the work
     * @return an Iterator with the bids of a given on work in a given auction
     * @throws NonexistantAuctionException if the auctionID doesn't exist
     * @throws NoArtWorkAuctionException if the auction has no works on it
     * @throws NoBidsArtWorkException if the work has no bids
     */
    Iterator<Bid> listBidsWork(String auctionID, String workID)
            throws NonexistantAuctionException, ArtWorkNotInAuctionException, NoBidsArtWorkException;

    /**
     * Returns a sorted Iterator with the works on the system sorted by their bought value if there already has been a work sold
     * @return a sorted Iterator of the works on the system
     * @throws NoArtWorkSoldException if no works were sold
     */
    Iterator<Entry<Work, Work>> listWorksByValue() throws NoArtWorkSoldException;
}
