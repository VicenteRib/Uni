package dataStructures;

import dataStructures.DoubleList.DoubleListNode;

/**
 * Doubly linked list Implementation 
 * @author AED  Team
 * @version 1.0
 * @param <K, V> Generics - K extends Comparable
 * 
 */
class OrderedDoubleList<K extends Comparable<K>, V> 
	implements OrderedDictionary<K , V> {

    /**
	 * Serial Version UID of the Class
	 */
    static final long serialVersionUID = 0L;
    
	/**
     *  Node at the head of the list.
     */
	protected DoubleListNode<Entry<K,V>> head;

    /**
     * Node at the tail of the list.
     */
	protected DoubleListNode<Entry<K,V>> tail;

    /**
     * Number of elements in the list.
     */
	protected int currentSize;
	
    /**
     * Constructor of an empty ordered double linked list.
     * head and tail are initialized as null.
     * currentSize is initialized as 0.
     */
	public OrderedDoubleList() {
		head=null;
		tail=null;
		currentSize=0;
	}

    /**
     * Inserts the Entry element before node after.
     * Precondition: after is not the head of the ordered double list.
     * @param element - Entry to be inserted
     * @param after - Node to be next to the new node  
     */
	protected void addBeforeNode(Entry<K,V> element, DoubleListNode<Entry<K,V>> after){
        DoubleListNode<Entry<K,V>> newNode = new DoubleListNode<> (element, after.getPrevious(), after);
        if(after.hasPrevious()) {
            after.getPrevious().setNext(newNode);
            after.setPrevious(newNode);
        }else{
            this.addFirst(newNode.getElement());
        }
        currentSize++;
	}
	
    /**
     * Inserts the Entry element at the first position in the list.
     * @param element - Entry to be inserted
     */
    protected void addFirst( Entry<K,V> element )
    {
        DoubleListNode<Entry<K,V>> newNode = new DoubleListNode<Entry<K,V>>(element, null, head);
        if ( this.isEmpty() )
            tail = newNode;
        else
            head.setPrevious(newNode);
        head = newNode;
        currentSize++;
    }


    /**
     * Inserts the Entry element at the last position in the list.
     * @param element - Entry to be inserted
     */
    protected void addLast( Entry<K,V> element )
    {
        DoubleListNode<Entry<K,V>> newNode = new DoubleListNode<>(element, tail,null);
        if( this.isEmpty())
            head=newNode;
        else
            tail.setNext(newNode);
        tail = newNode;
        currentSize++;
    }

	@Override
    public Entry<K, V> maxEntry() throws EmptyDictionaryException {
        Entry<K, V> current;
        Entry<K, V> max = new EntryClass<>(null, null);
        Iterator<Entry<K,V>> it = this.iterator();
        while(it.hasNext()){
            current = it.next();
            if(max.getKey().compareTo(current.getKey())<0) {
                max = new EntryClass<>(current.getKey(), current.getValue());
            }
        }
        if (max.getKey() != null && max.getValue() != null)
            return max;
        return null;
	}

    @Override
	public Entry<K, V> minEntry() throws EmptyDictionaryException {
        if(this.isEmpty()) throw new EmptyDictionaryException();
        Entry<K, V> current;
        Entry<K, V> min = new EntryClass<>(null, null);
        Iterator<Entry<K, V>> it = this.iterator();
        while(it.hasNext()){
            current = it.next();
            if(min.getKey().compareTo(current.getKey())>0) {
                min = new EntryClass<>(current.getKey(), current.getValue());
            }
        }
        if (min.getKey() != null && min.getValue() != null)
            return min;
        return null;
	}

    /**
     * Returns the node with the Entry with Key
     * in the list, if the list contains this entry.
     * Otherwise, returns null.
     * @param key - Key of type K to be searched
     * @return DoubleListNode<E> where the Entry with key was found, or the one with the key immmediately after 
     */
	protected DoubleListNode<Entry<K,V>> findNode (K key){
        DoubleListNode<Entry<K,V>> it = this.head;
        while(it!=null){
            if(key.compareTo(it.getElement().getKey())==0){
                return it;
            }it = it.getNext();
        }
        return null;
	}
	
    @Override
	public V find(K key) {
		DoubleListNode<Entry<K,V>> node = findNode(key);
        if(node!=null && node.getElement()!=null)
            return node.getElement().getValue();
        return null;
	}


	@Override
	public V insert(K key, V value) {
		DoubleListNode<Entry<K,V>> node = findNode(key);
		if ((node!=null) && (node.getElement().getKey().compareTo(key)==0)){
            V toReturn = node.getElement().getValue();
            node.setElement(new EntryClass<K, V>(key, value));
            return toReturn;
		}
		else {
		  Entry<K,V> newNode=new EntryClass<K,V> (key, value);
          if (this.isEmpty()) { addFirst(newNode);}
          else{
              Iterator<Entry<K, V>> it = this.iterator();
              while (it.hasNext()) {
                  Entry<K,V> comp = it.next();
                  if(key.compareTo(comp.getKey())<0) {
                      addBeforeNode(newNode, findNode(comp.getKey()));
                      return null;
                  }
              }if(findNode(newNode.getKey())==null){
                  addLast(newNode);
              }
          }
		}
        return null;
    }
	
	@Override
    public boolean isEmpty() {
	
		return currentSize==0;
	}

    @Override
	public Iterator<Entry<K, V>> iterator() {
		return new DoubleListIterator<Entry<K,V>>(head,tail);
	}

    /**
     * Removes the first node in the list.
     * Pre-condition: the list is not empty.
     */
    protected void removeFirstNode( )
    {
        head = head.getNext();
        if ( head == null )
            tail = null;
        else
            head.setPrevious(null);
        if (head!=null && head.getNext()==null)
            tail.setPrevious(null);
        currentSize--;
    }


    /**
     * Removes and returns the value at the first entry in the list.
     */
    protected V removeFirst( ) throws EmptyDictionaryException
    {
        if(this.isEmpty()) {throw new EmptyDictionaryException();}

        V value = head.getElement().getValue();
        this.removeFirstNode();
        return value;
    }


    /**
     * Removes the last node in the list.
     * Pre-condition: the list is not empty.
     */
    protected void removeLastNode( )
    {
        tail = tail.getPrevious();
        if ( tail == null )
            head = null;
        else
            tail.setNext(null);
        if(tail!=null && tail.getPrevious()==null)
            head.setNext(null);
        currentSize--;
    }


    /**
     * Removes and returns the value at the last entry in the list.
     */
    protected V removeLast( ) throws EmptyDictionaryException
    {
        if ( this.isEmpty() )
            throw new EmptyDictionaryException();

        V value = tail.getElement().getValue();
        this.removeLastNode();
        return value;
    }

    /**
     * Removes the specified node from the list.
     * Pre-condition: the node is neither the head nor the tail of the list.
     * @param node - middle node to be removed
     */
    protected void removeMiddleNode( DoubleListNode<Entry<K,V>> node )
    {
        node.getPrevious().setNext(node.getNext());
        node.getNext().setPrevious(node.getPrevious());
        currentSize--;

    }

    @Override
    public V remove(K key) {
		DoubleListNode<Entry<K,V>> node = findNode(key);
		if (node == null)
			return null;
		else {
            if(node.getNext()==null){
                return removeLast();
            }
            if(!node.hasPrevious()){
                return removeFirst();
            }
            removeMiddleNode(node);
            return node.getElement().getValue();
		}
	}

    @Override
	public int size() {
		return currentSize;
	}
	
	
}
