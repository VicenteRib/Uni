/**
 * @author Joao Ventura 65590 jm.ventura@campus.fct.unl.pt
 * @author Vicente Ribeiro 67241 vg.ribeiro@campus.fct.unl.pt
 */

package dataStructures;

import java.io.Serializable;

/**
 * BSTKeyOrder Iterator implementation
 * @param <K> Generic Key
 * @param <V> Generic Value
 */
class BSTKeyOrderIterator<K,V> implements Serializable, Iterator<Entry<K,V>>{

    /**
     * Serial Version UID of Class
     */
    static final long serialVersionUID = 0L;

    /**
     * Root of the BST
     */
    protected BSTNode<K,V> root;
    /**
     * Stack with the Keys in order
     */
    protected Stack<Entry<K,V>> stack;

    /**
     * BSTKeyOrderIterator constructor
     * @param root root of the BST
     */
    public BSTKeyOrderIterator(BSTNode<K,V> root){
        this.root = root;
        stack = new StackInArray<>();
        inorder();
    }

    @Override
    public boolean hasNext(){
        return !stack.isEmpty();
    }

    @Override
    public Entry<K,V> next(){
        return stack.pop();
    }

    @Override
    public void rewind() {
        stack = new StackInArray<>();
        inorder();
    }

    /**
     * Performs an in-order traversal of the tree starting at the root
     */
    protected void inorder(){
        this.inorder(root);
    }

    /**
     * Performing an in-order traversal and adding the entry of the
     * node to the stack
     * @param node the node to traverse
     */
    protected void inorder(BSTNode<K,V> node){
        if(node!=null) {
            inorder(node.getRight());
            stack.push(node.getEntry());
            inorder(node.getLeft());
        }

    }

}
