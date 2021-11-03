
/**
 * A class implementing a stack ADT. It uses a linked node chain
 * to keep the stack items in order. You can add an item to the stack
 * with push, remove and return an item with pop, clear the stack, or
 * look at the top item in the stack. 
 */
public class LinkedStack<T> implements Stack<T> {
    Node topNode = null;
    /**
    * A class implementing a linked node. The node contains a piece of
    * data and also points to the data in another node. 
    */
    private class Node {
        private T    data; 
        private Node next;
    
        private Node(T dataPortion) {
            this(dataPortion, null);
        } // end constructor

        private Node(T dataPortion, Node nextNode) {
            data = dataPortion;
            next = nextNode;
        } // end constructor
    } // end Node

    /**
     * Adds an item to the top of this stack.
     * @param item The item to add.
     */
    public void push(T item) {
        Node testNode = new Node(item);
        testNode.data = item;
        testNode.next = topNode;
        topNode = testNode;
    }
    
    /**
     * Removes and returns the item from the top of this stack.
     * @return the item at the top of the stack. Returns null if empty.
     */
    public T pop() {
        if (topNode == null){
            return null;
        } 
        else{
            T currentTopdata = topNode.data;
            topNode = topNode.next;
            return currentTopdata;
        }
    }
    
    /**
     * Returns the item on top of the stack, without removing it.
     * @return the item at the top of the stack. Returns null if empty.
     */
    public T peek() {
        if (!isEmpty()){
            return topNode.data;
        } 
        else{
            return null;
        }
    }
    
    /** 
     * Returns whether the stack is empty. 
     * @return true if the stack is empty; false otherwise
     */
    public boolean isEmpty() {
        if (topNode == null){
            return true;
        } 
        else{
            return false;
        }
    }
    
    /** 
     * Removes all items from the stack. 
     */
    public void clear() {
        while (topNode != null){
            topNode = topNode.next;
        }
    }
}
