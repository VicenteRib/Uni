/**
 * @author Joao Ventura 65590 jm.ventura@campus.fct.unl.pt
 * @author Vicente Ribeiro 67241 vg.ribeiro@campus.fct.unl.pt
 */

package system.users;

import dataStructures.Entry;
import dataStructures.Iterator;
import system.artWork.Work;

import java.io.Serializable;

/**
 * Artist Interface
 */
public interface Artist extends User, Serializable {
    /**
     * Returns true if the artist has books of their authorship. False otherwise
     * @return true if the artist has books of their authorship. False otherwise
     */
    boolean hasWorks();

    /**
     * Returns the artistic name of the artist
     * @return the artistic name of the artist
     */
    String getArtistName();

    /**
     * Increases the number of selling works of the artist
     */
    void addSelling();

    /**
     * Returns an iterator with all the work of the artist authorship
     * @return an iterator with all the work of the artist authorship
     */
    Iterator<Entry<String, Work>> listWorks();

    /**
     * Adds a work of the artist authorship to the work List
     * @param work work to be added
     */
    void addWork(Work work);

    /**
     * Reduces the number of works that the artist has selling at an auction
     */
    void removeSelling();

    /**
     * Returns true if the artist is selling any works of their authorship. False otherwise
     * @return true if the artist is selling any works of their authorship. False otherwise
     */
    boolean hasWorksSelling();
}
