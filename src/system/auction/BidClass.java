/**
 * @author Joao Ventura 65590 jm.ventura@campus.fct.unl.pt
 * @author Vicente Ribeiro 67241 vg.ribeiro@campus.fct.unl.pt
 */

package system.auction;

import system.users.User;

import java.io.Serializable;

/**
 * Bid Class
 */
public class BidClass implements Bid, Serializable {
    /**
     * Serializable constant
     */
    private static final long serialVersionUID = 0L;

    /**
     * Value of the bid
     */
    private int value;
    /**
     * User that placed the bid
     */
    private User user;
    /**
     * ID from the auction
     */
    private String auctionID;

    /**
     * BidClass constructor
     * @param value value of the bid
     * @param user user that placed the bid
     * @param auctionID ID of the auction where the bid was placed
     */
    public BidClass(int value, User user, String auctionID) {
        this.value = value;
        this.user=user;
        this.auctionID = auctionID;
    }

    @Override
    public int getBidValue(){
        return value;
    }

    @Override
    public User getUser(){
        return user;
    }

    @Override
    public String getAuction() {return this.auctionID;}
}
