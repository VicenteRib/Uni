/**
 * @author Joao Ventura 65590 jm.ventura@campus.fct.unl.pt
 * @author Vicente Ribeiro 67241 vg.ribeiro@campus.fct.unl.pt
 */

package system;

/**
 * Exception thrown when the user tries to place a bid or list the bids of a work whose ID doesn't exist on the system
 */
public class ArtWorkNotInAuctionException extends Exception{

    /**
     * Serializable constant
     */
    private static final long serialVersionUID = 1L;

    /**
     * Exception constructor
     */
    public ArtWorkNotInAuctionException() {
        super();
    }

}
