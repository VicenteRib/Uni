/**
 * @author Joao Ventura 65590 jm.ventura@campus.fct.unl.pt
 * @author Vicente Ribeiro 67241 vg.ribeiro@campus.fct.unl.pt
 */

package system;

/**
 * Exception thrown when the user tries to access or add a work to an auction, but the ID it doesn't exist on the system
 */
public class NonexistantArtWorkException extends Exception {
    /**
     * Serializable constant
     */
    private static final long serialVersionUID = 1L;

    /**
     * Exception constructor
     */
    public NonexistantArtWorkException() {
        super();
    }

}