/**
 * @author Joao Ventura 65590 jm.ventura@campus.fct.unl.pt
 * @author Vicente Ribeiro 67241 vg.ribeiro@campus.fct.unl.pt
 */

package system.auction;

import dataStructures.Iterator;
import system.artWork.Work;

import java.io.Serializable;

/**
 * Auction interface
 */
public interface Auction extends Serializable {
    /**
     * Returns the ID of the auction
     * @return the ID of the auction
     */
    String getID();

    /**
     * Returns true if the auction has no works being auctioned. False otherwise
     * @return true if the auction has no works being auctioned. False otherwise
     */
    boolean isEmpty();

    /**
     * Return true if the auction has a given work. False otherwise
     * @param work the work to be checked
     * @return true if the auction has a given work. False otherwise
     */
    boolean hasWork(Work work);

    /**
     * Returns the work with the given ID
     * @param workID ID of the work
     * @return the work with the given ID
     */
    Work getWork(String workID);

    /**
     * Adds a given work to the auction with a minimum price to be auctioned off
     */
    void addWorkAuction(Work book, int minPrice);

    /**
     * Returns an iterator with the works on the auction
     * @return an iterator with the works on the auction
     */
    Iterator<Work> listAuctionWorks();

}
