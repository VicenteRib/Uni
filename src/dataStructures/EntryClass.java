package dataStructures;

/**
 * Entry Class Data Type
 * Describes each method implemented by the interface
 * @param <K> Generic Key
 * @param <V> Generic Value
 */
public class EntryClass<K,V> implements Entry<K,V>{

    private K key;
    private V value;

    public EntryClass(K key, V value){
        this.key=key;
        this.value=value;
    }

    public K getKey() {
        return key;
    }

    public V getValue(){
        return value;
    }

    public void setKey(K key){
        this.key=key;
    }

    public void setValue(V value){
        this.value=value;
    }

}
