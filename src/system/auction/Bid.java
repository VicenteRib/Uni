/**
 * @author Joao Ventura 65590 jm.ventura@campus.fct.unl.pt
 * @author Vicente Ribeiro 67241 vg.ribeiro@campus.fct.unl.pt
 */

package system.auction;

import system.users.User;

import java.io.Serializable;

/**
 * Bid interface
 */
public interface Bid extends Serializable {
    /**
     * Returns the value of the bid
     * @return the value of the bid
     */
    int getBidValue();

    /**
     * Returns the user that placed the bid
     */
    User getUser();

    /**
     * Returns the ID of the auction the bid has been placed
     * @return the ID of the auction the bid has been placed
     */
    String getAuction();
}
