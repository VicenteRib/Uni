/**
 * @author Joao Ventura 65590 jm.ventura@campus.fct.unl.pt
 * @author Vicente Ribeiro 67241 vg.ribeiro@campus.fct.unl.pt
 */

package system;

/**
 * Exception thrown when a new user is going to be added but there's already a user with the same ID on the system
 */
public class AlreadyExistantUserException extends Exception{
    /**
     * Serializable constant
     */
    private static final long serialVersionUID = 1L;

    /**
     * Exception constructor
     */
    public AlreadyExistantUserException() {
        super();
    }
}