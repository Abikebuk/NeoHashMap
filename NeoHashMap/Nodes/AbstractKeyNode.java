package NeoHashMap.Nodes;

import NeoHashMap.NeoHashMapSettings;
import java.util.ArrayList;
import static java.lang.Integer.parseInt;

/**
 * Node class for the hashmap
 * Contains links to the parents & children on top of the hash value and the data it stores.
 * @param <K> Type of the keys
 * @param <V> Type of the Values
 */
public abstract class AbstractKeyNode<K, V>{
    /**
     * Parent node. If null, it is a root node.
     */
    protected AbstractKeyNode parent;

    /**
     * Hash of the node.
     * Is a subHash of the parent node
     */
    protected String hash;

    /**
     * index of the node (int value of the hash)
     */
    protected int index;

    /**
     * Settings of the NeoHashMap
     */
    protected NeoHashMapSettings settings;

    /**
     * Constructor
     * Sets the children to an empty list
     * @param hash hash value
     * @param settings settings of the NeoHashMap
     */
    public AbstractKeyNode(String hash, NeoHashMapSettings settings) {
        this(hash, null, settings);
    }

    /**
     * Constructor
     * @param index index value
     * @param settings settings of the NeoHashMap
     */
    public AbstractKeyNode(int index, NeoHashMapSettings settings) {
        this(index, null, settings);
    }
    /**
     * Constructor
     * @param hash hash value
     * @param parent parent node (can be null)
     * @param settings settings of the NeoHashMap
     */
    public AbstractKeyNode(String hash, AbstractKeyNode parent, NeoHashMapSettings settings){
        this.hash = hash;
        this.index = parseInt(hash, 2);
        this.parent = parent;
        this.settings = settings;
    }

    /**
     * Constructor
     * @param index index value
     * @param parent parent node (can be null)
     * @param settings settings of the NeoHashMap
     */
    public AbstractKeyNode(int index, AbstractKeyNode parent, NeoHashMapSettings settings){
        this.index = index;
        this.hash = Integer.toBinaryString(index);
        this.parent = parent;
        this.settings = settings;
    }

    /**
     * Common Setter & Getters
     */

    /**
     * Returns the hash value
     * @return hash value
     */
    public String getHash() {
        return hash;
    }

    /**
     * Return the index of the node
     * @return index value
     */
    public int getIndex(){
        return index;
    }
    /**
     * Returns the parent node
     * @return parent
     */
    public AbstractKeyNode getParent() {
        return parent;
    }

    /**
     * Sets the parent node
     * @param parent
     */
    public void setParent(AbstractKeyNode parent) {
        this.parent = parent;
    }

    public String getFullHash(){
        if(parent == null) return hash;
        else return parent.getFullHash() + hash;
    }
    /**
     * Abstract methods
     */

    /**
     * Return all the values in the node tree left first.
     * @return all the values in the value node (can be multiple if there is overflow)
     */
    public abstract ArrayList<V> getValues();

    /**
     * Adds an element to the hasmap
     * @param hashRemainder Contrary to NeoHashMap, it takes a portion of the key translated in binary through HashCode
     * @param value
     */
    public abstract void add(String hashRemainder, V value);

    /**
     * Prints node content
     * @param prefix string appended to the start of each lines
     */
    public abstract void print(String prefix);

    /**
     * Returns the value associated with the key
     * Returns only the first one in the hashmap cannot overflow
     * Returns null if no element is found
     * @param remainderKey
     * @return
     */
    public abstract V getValue(String remainderKey);

    /**
     * Returns the values associated with the keys
     * Returns a empty list if no element is found
     * @param remainderKey
     * @return
     */
    public abstract ArrayList<V> getValues(String remainderKey);

    /**
     * Remove the first element associated with the keys
     * Does nothing if there is nothing to remove
     * @param remainderKey
     */
    public abstract void removeFirst(String remainderKey);

    /**
     * Remove all the elements associated with the keys
     * Does nothing if there is nothing to remove
     * @param remainderKey
     */
    public abstract void removeAll(String remainderKey);
}