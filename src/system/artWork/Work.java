/**
 * @author Joao Ventura 65590 jm.ventura@campus.fct.unl.pt
 * @author Vicente Ribeiro 67241 vg.ribeiro@campus.fct.unl.pt
 */

package system.artWork;

import dataStructures.Iterator;
import system.auction.Bid;
import system.users.Artist;
import system.users.User;

import java.io.Serializable;

/**
 * Work Interface
 */
public interface Work extends Serializable, Comparable<Work> {
    /**
     * Returns the work ID
     * @return the work ID
     */
    String getID();

    /**
     * Returns the name of the work
     * @return the name of the work
     */
    String getName();

    /**
     * Returns the author of the work
     * @return the author of the work
     */
    Artist getArtist();

    /**
     * Returns the owner of the work
     * @return the owner of the work
     */
    User getOwner();

    /**
     * Returns the year of release of the work
     * @return the year of release of the work
     */
    int getYearOfRelease();

    /**
     * Returns the minimum bid value
     * @return the minimum bid value
     */
    int getMinPrice();

    /**
     * Returns true if the work has bids on the given auction. False otherwise
     * @param auctionID ID where the work is being auctioned at
     * @return true if the work has bids on the given auction. False otherwise
     */
    boolean hasBids(String auctionID);

    /**
     * Adds a bid to the work
     * @param bid the bid that will be added
     */
    void addBid(Bid bid);

    /**
     * Sets the minimum bid value for the work
     * @param minPrice minimum bid value
     */
    void setMinimumPrice(int minPrice);

    /**
     * Returns an iterator with the bids the work has
     * @return an iterator with the bids the work has
     */
    Iterator<Bid> listBids();

    /**
     * Returns the value of the highest evaluation of the work
     * @return the value of the highest evaluation of the work
     */
    int getHighestEvaluation();

    /**
     * Returns the sold price of the work
     * @return the sold price of the work
     */
    int getSoldPrice();

    /**
     * Removes bids from the specific auction from the work
      * @param auctionID ID of the auction with the bids to be removed
     */
    void removeBids(String auctionID);

}
