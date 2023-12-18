/**
 * @author Joao Ventura 65590 jm.ventura@campus.fct.unl.pt
 * @author Vicente Ribeiro 67241 vg.ribeiro@campus.fct.unl.pt
 */

package system;

import dataStructures.HashTable;
import dataStructures.BinarySearchTree;
import dataStructures.SepChainHashTable;
import dataStructures.OrderedDictionary;
import dataStructures.Iterator;
import dataStructures.Entry;
import dataStructures.List;
import dataStructures.DoubleList;
import system.auction.*;
import system.artWork.*;
import system.users.*;

import java.io.Serializable;

/**
 * ArtAuction Class
 */
public class ArtAuctionClass implements ArtAuction, Serializable {
    /**
     * Serializable constant
     */
    private static final long serialVersionUID = 0L;
    /**
     * Minimum age for a user
     */
    private static final int MIN_AGE=18;

    /**
     * Constant to check if a given login doesn't exist on the system
     */
    private static final User UNKNOWN_USER=null;
    private static final Work UNKNOWN_WORK=null;
    private static final Auction UNKNOWN_AUCTION=null;

    /**
     * HashTable with the active auctionings
     * The Key represents the auction ID
     * The Value represents the auction itself as an object
     */
    private HashTable<String, Auction> auctionings;
    /**
     * HashTable with the active users
     * The Key represents the user login
     * The Value represents the user itself as an object
     */
    private HashTable<String, User> users;
    /**
     * HashTable with the active works
     * The Key represents the ID of the work
     * The Value represents the work itself as an object
     */
    private HashTable<String, Work> works;
    /**
     * Variable with the number of works sold
     */
    private int sold;

    /**
     * ArtAuctionClass constructor
     */
    public ArtAuctionClass(){
        auctionings = new SepChainHashTable<>();
        users = new SepChainHashTable<>();
        works = new SepChainHashTable<>();
        sold = 0;
    }

    @Override
    public void addUser(String userLogin, String name, int age, String email)
            throws NonAdultUserException, AlreadyExistantUserException{
        if(age<MIN_AGE) {
            throw new NonAdultUserException();
        }if(users.find(toLowerCase(userLogin))!=UNKNOWN_USER){
            throw new AlreadyExistantUserException();
        }
        users.insert(toLowerCase(userLogin), new UserClass(userLogin, name, age, email));
    }

    @Override
    public void addArtist(String artistLogin, String name, String artistName, int age, String email)
            throws NonAdultUserException, AlreadyExistantUserException{
        if(age<MIN_AGE) {
            throw new NonAdultUserException();
        }if(users.find(toLowerCase(artistLogin))!=UNKNOWN_USER){
            throw new AlreadyExistantUserException();
        }
        users.insert(toLowerCase(artistLogin), new ArtistClass(artistLogin, name, artistName, age, email));
    }

    @Override
    public void removeUser(String userLogin) throws NonexistantUserException,
            ArtWorkInAuctionException, OffersRemainingException{
        User user= users.find(toLowerCase(userLogin));
        if(user==UNKNOWN_USER) {
            throw new NonexistantUserException();
        }
        if(user.hasPendingBids()){
            throw new OffersRemainingException();
        }
        if(user instanceof Artist artist) {
            if (artist.hasWorksSelling()) {
                throw new ArtWorkInAuctionException();
            }
            Iterator<Entry<String, Work>> it = artist.listWorks();
            while(it.hasNext()) {
                Work work = it.next().getValue();
                works.remove(toLowerCase(work.getID()));
                if(work.getHighestEvaluation()!=0){
                    sold--;
                }
            }
        }
        users.remove(toLowerCase(user.getLogin()));

    }

    @Override
    public void addWork(String workID, String artistLogin, int yearOfRelease, String name)
            throws AlreadyExistantArtWorkException, NonexistantUserException, NonexistantArtistException{
        User user = users.find(toLowerCase(artistLogin));
        if(works.find(toLowerCase(workID))!=UNKNOWN_WORK){
            throw new AlreadyExistantArtWorkException();
        }if(user==UNKNOWN_USER){
            throw new NonexistantUserException();
        }if(!(user instanceof Artist artist)) throw new NonexistantArtistException();
        Work work =new WorkClass(workID, artist, yearOfRelease, name);
        works.insert(toLowerCase(workID), work);
        artist.addWork(work);
    }

    @Override
    public Iterator<User> infoUser(String userLogin) throws NonexistantUserException {
        User aux = users.find(toLowerCase(userLogin));
        if(aux==UNKNOWN_USER){
            throw new NonexistantUserException();
        }
        List<User> user = new DoubleList<>();
        user.addFirst(aux);
        return user.iterator();
    }

    @Override
    public Iterator<Artist> infoArtist(String artistLogin)
            throws NonexistantUserException, NonexistantArtistException {
        User user = users.find(toLowerCase(artistLogin));
        if(user==UNKNOWN_USER){
            throw new NonexistantUserException();
        }if(!(user instanceof Artist aux)){
            throw new NonexistantArtistException();
        }
        List<Artist> artist = new DoubleList<>();
        artist.addFirst(aux);
        return artist.iterator();
    }

    @Override
    public Iterator<Work> infoWork(String workID) throws NonexistantArtWorkException{
        Work aux = works.find(toLowerCase(workID));
        if(aux==UNKNOWN_WORK){
            throw new NonexistantArtWorkException();
        }
        List<Work> work = new DoubleList<>();
        work.addFirst(aux);
        return work.iterator();
    }

    @Override
    public void createAuction(String auctionID) throws AlreadyExistantAuctionException{
        if(auctionings.find(toLowerCase(auctionID))!=UNKNOWN_AUCTION){
            throw new AlreadyExistantAuctionException();
        }
        auctionings.insert(toLowerCase(auctionID), new AuctionClass(auctionID));
    }

    @Override
    //TODO not sure if in here the addWorkAuction fucks up cuz of some reference to work
    //      since you set a new min Bid price
    public void addWorkAuction(String auctionID, String workID, int minValue)
            throws NonexistantAuctionException, NonexistantArtWorkException{
        Auction auction = auctionings.find(toLowerCase(auctionID));
        Work work = works.find(toLowerCase(workID));
        if(auction==UNKNOWN_AUCTION){
            throw new NonexistantAuctionException();
        }if(work==UNKNOWN_WORK){
            throw new NonexistantArtWorkException();
        }if(!auction.hasWork(work)){
            work.getArtist().addSelling();
            auction.addWorkAuction(work, minValue);
        }
    }

    @Override
    public void bid(String auctionID, String workID, String userLogin, int bidValue)
            throws NonexistantUserException, NonexistantAuctionException, ArtWorkNotInAuctionException, BidLowerMinimumException{
        Auction auction = auctionings.find(toLowerCase(auctionID));
        User user = users.find(toLowerCase(userLogin));
        if(user==UNKNOWN_USER){
            throw new NonexistantUserException();
        }if(auction==UNKNOWN_AUCTION){
            throw new NonexistantAuctionException();
        }
        Work work = auction.getWork(toLowerCase(workID));
        if(work==UNKNOWN_WORK){
            throw new ArtWorkNotInAuctionException();
        }if((bidValue< work.getMinPrice())){
            throw new BidLowerMinimumException();
        }
        work.addBid(new BidClass(bidValue, user, auctionID));
        user.addBid();
    }

    @Override
    public Iterator<Work> closeAuction(String auctionID) throws NonexistantAuctionException{
        Auction auction = auctionings.find(toLowerCase(auctionID));
        if(auction==UNKNOWN_AUCTION){
            throw new NonexistantAuctionException();
        }
        Iterator<Work> it = auction.listAuctionWorks();
        while(it.hasNext()){
            Work work = it.next();
            work.getArtist().removeSelling();
            sold++;
        }
        auctionings.remove(toLowerCase(auctionID));
        it.rewind();
        return it;
    }

    @Override
    public Iterator<Work> listAuctionWorks(String auctionID)
            throws NonexistantAuctionException, NoArtWorkAuctionException{
        Auction auction = auctionings.find(toLowerCase(auctionID));
        if(auction==UNKNOWN_AUCTION){
            throw new NonexistantAuctionException();
        }if(auction.isEmpty()){
            throw new NoArtWorkAuctionException();
        }
        return auction.listAuctionWorks();
    }

    @Override
    public Iterator<Entry<String, Work>> listArtistWork(String artistLogin)
            throws NonexistantUserException, NonexistantArtistException, NoArtWorkArtistException{
        User user = users.find(toLowerCase(artistLogin));
        if(user==UNKNOWN_USER){
            throw new NonexistantUserException();
        }if(!(user instanceof Artist artist)){
            throw new NonexistantArtistException();
        }if(!artist.hasWorks()){
            throw new NoArtWorkArtistException();
        }
        return artist.listWorks();
    }

    @Override
    public Iterator<Bid> listBidsWork(String auctionID, String workID)
            throws NonexistantAuctionException, ArtWorkNotInAuctionException, NoBidsArtWorkException{
        Auction auction = auctionings.find(toLowerCase(auctionID));
        if(auction==UNKNOWN_AUCTION){
            throw new NonexistantAuctionException();
        }if(!auction.hasWork(works.find(toLowerCase(workID)))){
            throw new ArtWorkNotInAuctionException();
        }Work work = auction.getWork(toLowerCase(workID));
        if(!(work.hasBids(auctionID))){
            throw new NoBidsArtWorkException();
        }
        return work.listBids();
    }

    @Override
    public Iterator<Entry<Work, Work>> listWorksByValue() throws NoArtWorkSoldException {
        if (sold==0) {
            throw new NoArtWorkSoldException();
        }
        OrderedDictionary<Work,Work> aux = new BinarySearchTree<>();
        Iterator<Entry<String, Work>> it = works.iterator();
        while (it.hasNext()){
            Work work = it.next().getValue();
            if (work.getHighestEvaluation()!=0){
                aux.insert(work, work);
            }
        }
        return aux.iterator();
    }

    /**
     * Turns a given string into a lowe case one
     * @param ID string to convert
     * @return a lower case string
     */
    protected String toLowerCase(String ID){
        return ID.toLowerCase();
    }

}
