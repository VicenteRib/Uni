/**
 * @author Joao Ventura 65590 jm.ventura@campus.fct.unl.pt
 * @author Vicente Ribeiro 67241 vg.ribeiro@campus.fct.unl.pt
 */

package system.users;

import java.io.Serializable;

/**
 * User inetrface
 */
public interface User extends Serializable {
    /**
     * Returns the login of the user
     * @return the login of the user
     */
    String getLogin();

    /**
     * Returns the name of the user
     * @return the name of the user
     */
    String getName();

    /**
     * Returns the age of the user
     * @return the age of the user
     */
    int getAge();

    /**
     * Returns the email of the user
     * @return the email of the user
     */
    String getEmail();

    /**
     * Increases the number of bids the user has
     */
    void addBid();

    /**
     * Decreases the number of bids the user has
     */
    void reduceBid();

    /**
     * Returns true if the user has pending bids. In other words, if the number of bids from the user are different from 0
     * @return true if the user has pending bids. False otherwise
     */
    boolean hasPendingBids();

}
