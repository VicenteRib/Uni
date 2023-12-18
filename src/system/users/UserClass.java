/**
 * @author Joao Ventura 65590 jm.ventura@campus.fct.unl.pt
 * @author Vicente Ribeiro 67241 vg.ribeiro@campus.fct.unl.pt
 */

package system.users;

import java.io.Serializable;

/**
 * User Class
 */
public class UserClass implements User, Serializable {
    /**
     * Serializable constant
     */
    private static final long serialVersionUID = 0L;
    /**
     * Constant to check if the user has no bids
     */
    private static final int HAS_NO_BIDS = 0;
    /**
     * Login, name and email of the user
     */
    private String login, name, email;
    /**
     * Age and number of active bids of the user
     */
    private int age, numberBidsPending;

    /**
     * UserClass constructor
     * @param login login of the user
     * @param name name of the user
     * @param age age of the user
     * @param email email of the user
     */
    public UserClass(String login, String name, int age, String email) {
        this.login = login;
        this.name = name;
        this.age = age;
        this.email = email;
    }

    @Override
    public String getLogin(){
        return login;
    }

    @Override
    public String getName(){
        return name;
    }

    @Override
    public int getAge(){
        return age;
    }

    @Override
    public String getEmail(){
        return email;
    }

    @Override
    public void addBid(){numberBidsPending++;}

    @Override
    public void reduceBid(){
        numberBidsPending--;
    }

    @Override
    public boolean hasPendingBids(){
        return numberBidsPending!=HAS_NO_BIDS;
    }

}
