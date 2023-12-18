/**
 * @author Joao Ventura 65590 jm.ventura@campus.fct.unl.pt
 * @author Vicente Ribeiro 67241 vg.ribeiro@campus.fct.unl.pt
 */

package dataStructures;

/**
 * HashTableIterator implementation
 * @param <K> Generic Key
 * @param <V> Generic Value
 */
public interface HashTableIterator<K, V> extends Iterator<Entry<K, V>> {

    /**
     * Returns the position of the next list in the table that isn't empty
     * @return the position of the next list in the table that isn't empty'
     */
    int nextUsedList();

    /**
     * Returns true if the iterator has a next Value to return or if the
     * table has more values to iterate from
     * @return true if the table has more values to return. False otherwise
     */
    @Override
    boolean hasNext();

    /**
     * Returns the next value of the iterator
     * @return the next value of the iterator
     * @throws NoSuchElementException when there is no next value
     */
    @Override
    Entry<K, V> next() throws NoSuchElementException;

    /**
     * Resets the Iterator
     */
    @Override
    void rewind();
}
