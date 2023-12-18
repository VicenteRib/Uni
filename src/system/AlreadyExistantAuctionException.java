/**
 * @author Joao Ventura 65590 jm.ventura@campus.fct.unl.pt
 * @author Vicente Ribeiro 67241 vg.ribeiro@campus.fct.unl.pt
 */

package system;

/**
 * Exception thrown when a new auction is going to be added but there's already an auction with the same ID on the system
 */
public class AlreadyExistantAuctionException extends Exception {
    /**
     * Serializable constant
     */
    private static final long serialVersionUID = 1L;

    /**
     * Exception constructor
     */
    public AlreadyExistantAuctionException() {
        super();
    }
}
