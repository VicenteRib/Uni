/**
 * @author Joao Ventura 65590 jm.ventura@campus.fct.unl.pt
 * @author Vicente Ribeiro 67241 vg.ribeiro@campus.fct.unl.pt
 */

package system;

/**
 * Exception thrown when the user tries to add a work or list information about an artist or their work but their ID doesn't exist on the system
 */
public class NonexistantArtistException extends Exception{
    /**
     * Serializable constant
     */
    private static final long serialVersionUID = 1L;

    /**
     * Exception constructor
     */
    public NonexistantArtistException() {
        super();
    }

}
