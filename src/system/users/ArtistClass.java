/**
 * @author Joao Ventura 65590 jm.ventura@campus.fct.unl.pt
 * @author Vicente Ribeiro 67241 vg.ribeiro@campus.fct.unl.pt
 */

package system.users;

import dataStructures.*;
import system.artWork.Work;

import java.io.Serializable;

/**
 * Artist Class
 */
public class ArtistClass extends UserClass implements Artist, Serializable {
    /**
     * Serializable constant
     */
    private static final long serialVersionUID = 0L;
    /**
     * Artistic name of the artist
     */
    private String artistName;
    /**
     * OrderedDictionary with the works of the artist authorship
     * The key represents the name of the work
     * The Value represents the work itself as an object
     */
    private OrderedDictionary<String, Work> works;
    /**
     * Number of pending works that are being sold of the artist
     */
    private int pendingSellings;

    /**
     * ArtistClass constructor
     * @param login login of the artist
     * @param name name of the artist
     * @param artistName artistic name of the artist
     * @param age age of the artist
     * @param email email of the artist
     */
    public ArtistClass(String login, String name, String artistName, int age, String email){
        super(login, name, age, email);
        this.artistName = artistName;
        works = new BinarySearchTree<>();
        pendingSellings=0;
    }

    @Override
    public boolean hasWorks(){
        return !works.isEmpty();
    }

    @Override
    public String getArtistName(){
        return artistName;
    }

    @Override
    public void addSelling(){
        pendingSellings++;
    }

    @Override
    public void removeSelling() {
        pendingSellings--;

    }

    @Override
    public Iterator<Entry<String, Work>> listWorks(){
        return works.iterator();
    }

    @Override
    public void addWork(Work work){
        works.insert(work.getName().toLowerCase(), work);
    }

    @Override
    public boolean hasWorksSelling(){return pendingSellings!=0;}

}
