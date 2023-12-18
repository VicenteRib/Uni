/**
 * @author Joao Ventura 65590 jm.ventura@campus.fct.unl.pt
 * @author Vicente Ribeiro 67241 vg.ribeiro@campus.fct.unl.pt
 */

package dataStructures;

/**
 * HashTableIteratorClass implementation
 * @param <K> Generic Key
 * @param <V> Generic Value
 */
public class HashTableIteratorClass<K,V>  implements HashTableIterator<K, V> {

    /**
     * Current position on the table
     */
    protected int pos;
    /**
     * Hash table
     */
    protected Dictionary<K, V>[] table;
    /**
     * Iterator of the current position on the table
     */
    protected Iterator<Entry<K, V>> current;

    /**
     * HashTableIterator constructor
     * @param newTable the new HashTable
     */
    public HashTableIteratorClass (Dictionary<K, V>[] newTable){
        pos=-1;
        table= newTable;
        if(nextUsedList()==-1) {
            current=null;
            return;
        }pos = nextUsedList();
        current=table[pos].iterator();
    }

    @Override
    public int nextUsedList(){
        int auxPos=pos+1;
        while(auxPos<table.length) {
            if (!table[auxPos].isEmpty()){
                return auxPos;
            }auxPos++;
        }
        return -1;
    }

    @Override
    public boolean hasNext() {
        return (current!=null && current.hasNext()) || nextUsedList() != -1;
    }

    @Override
    public Entry<K, V> next() throws NoSuchElementException {
        if(current.hasNext()){
            return current.next();
        }
        pos=nextUsedList();
        current = table[pos].iterator();
        if(current.hasNext()){
            return current.next();
        }
        pos=nextUsedList();
        current = table[pos].iterator();
        return current.next();
    }

    @Override
    public void rewind() {
        pos=-1;
        if(nextUsedList() != -1) {
            pos=nextUsedList();
            current = table[pos].iterator();
        } else {
            current = null;
        }
    }
}
