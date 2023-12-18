/**
 * @author Joao Ventura 65590 jm.ventura@campus.fct.unl.pt
 * @author Vicente Ribeiro 67241 vg.ribeiro@campus.fct.unl.pt
 */

package system;

/**
 * Exception thrown when the user tries to list the works from an artist, but they have no works of their authorship
 */
public class NoArtWorkArtistException extends Exception{
    /**
     * Serializable constant
     */
    private static final long serialVersionUID = 1L;

    /**
     * Exception constructor
     */
    public NoArtWorkArtistException() {
        super();
    }

}
