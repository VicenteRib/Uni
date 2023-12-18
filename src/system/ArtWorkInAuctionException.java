/**
 * @author Joao Ventura 65590 jm.ventura@campus.fct.unl.pt
 * @author Vicente Ribeiro 67241 vg.ribeiro@campus.fct.unl.pt
 */

package system;

/**
 * Exception thrown when the user tries to remove an artist, but they still have active works being auctioned off
 */
public class ArtWorkInAuctionException extends Exception{

    /**
     * Serializable constant
     */
    private static final long serialVersionUID = 1L;

    /**
     * Exception constructor
     */
    public ArtWorkInAuctionException() {
        super();
    }

}
