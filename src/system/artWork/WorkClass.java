/**
 * @author Joao Ventura 65590 jm.ventura@campus.fct.unl.pt
 * @author Vicente Ribeiro 67241 vg.ribeiro@campus.fct.unl.pt
 */

package system.artWork;

import dataStructures.List;
import dataStructures.DoubleList;
import dataStructures.Iterator;
import system.auction.Bid;
import system.users.*;


import java.io.Serializable;

/**
 * Work Class
 */
public class WorkClass implements Work, Serializable {

    /**
     * Serializable constant
     */
    private static final long serialVersionUID = 0L;

    /**
     * ID and name of the work
     */
    private String ID, name;
    /**
     * Author of the work
     */
    private Artist artist;
    /**
     * Owner of the work
     */
    private User owner;
    /**
     * Year of release, minimum price, the highest evaluation and sold price of the work at a auction
     */
    private int yearOfRelease, minPrice, highestEvaluation, soldPrice;
    /**
     * List of bids the work has
     */
    private List<Bid> bids;

    /**
     * WorkClass constructor
     * @param ID ID of the work
     * @param artist name of the author
     * @param yearOfRelease work year of release
     * @param name name of the work
     */
    public WorkClass(String ID, Artist artist, int yearOfRelease, String name) {
        this.ID = ID;
        this.name = name;
        this.artist = artist;
        this.yearOfRelease = yearOfRelease;
        bids = new DoubleList<>();
        minPrice=0;
        soldPrice=0;
        highestEvaluation=0;
    }

    @Override
    public String getID(){
        return ID;
    }

    @Override
    public String getName(){
        return name;
    }

    @Override
    public Artist getArtist(){
        return artist;
    }

    @Override
    public User getOwner(){
        return owner;
    }

    @Override
    public int getYearOfRelease(){
        return yearOfRelease;
    }

    @Override
    public int getMinPrice(){
        return minPrice;
    }

    @Override
    public boolean hasBids(String auctionID){
        Iterator<Bid> it = bids.iterator();
        boolean found = false;
        while(it.hasNext() && !found) {
            Bid bid = it.next();
            if(bid.getAuction().equalsIgnoreCase(auctionID)){
                found = true;
            }
        }
        return found;
    }

    @Override
    public void addBid(Bid bid){
        bids.addLast(bid);
    }

    @Override
    public void setMinimumPrice(int minPrice){
        this.minPrice = minPrice;
    }

    @Override
    public Iterator<Bid> listBids(){
        return bids.iterator();
    }

    @Override
    public int getHighestEvaluation(){
        return highestEvaluation;
    }

    @Override
    public int getSoldPrice() { return soldPrice; }

    @Override
    public void removeBids(String auctionID){
        soldPrice = 0;
        Iterator<Bid> bidIterator = bids.iterator();
        while(bidIterator.hasNext()) {
            Bid bid = bidIterator.next();
            if (bid.getAuction().toLowerCase().equals(auctionID)) {
                bids.remove(bid);
                bid.getUser().reduceBid();
                workSold(bid, auctionID);
            }
        }
    }

    @Override
    public int compareTo(Work o) {
        if(this.getHighestEvaluation()>o.getHighestEvaluation()) {
            return -1;
        }else if(this.getHighestEvaluation()<o.getHighestEvaluation()) {
            return 1;
        }else{
            return this.getName().compareTo(o.getName());
        }
    }

    /**
     * Finds the highest bidValue on the given auction
     * @param bid the bid
     * @param auctionID ID of the auction where to sell the work
     */
    private void workSold(Bid bid, String auctionID){
        if(bid.getAuction().toLowerCase().equals(auctionID)){
            if(bid.getBidValue()>soldPrice) {
                soldPrice = bid.getBidValue();
                owner = bid.getUser();
            }
            if(bid.getBidValue()>highestEvaluation){
                highestEvaluation = bid.getBidValue();
            }
        }
    }
}
