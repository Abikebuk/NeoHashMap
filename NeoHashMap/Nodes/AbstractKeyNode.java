package NeoHashMap.Nodes;

import NeoHashMap.NeoHashMapSettings;

import java.util.ArrayList;

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
    private AbstractKeyNode parent;

    /**
     * Hash of the node.
     * Is a subHash of the parent node
     */
    private int hash;

    /**
     * Settings of the NeoHashMap
     */
    private NeoHashMapSettings settings;

    /**
     * Constructor
     * Sets the children to an empty list
     * @param hash hash value
     * @param settings settings of the NeoHashMap
     */
    public AbstractKeyNode(int hash, NeoHashMapSettings settings) {
        this(hash, null, settings);
    }

    /**
     * Constructor
     * @param hash hash value
     * @param parent parent node (can be null)
     * @param settings settings of the NeoHashMap
     */
    public AbstractKeyNode(int hash, AbstractKeyNode parent, NeoHashMapSettings settings){
        this.hash = hash;
        this.parent = parent;
        this.settings = settings;
    }

    /**
     * Setters & Getters
     */

    /**
     * Common Setter & Getters
     */

    /**
     * Returns the hash value
     * @return hash value
     */
    public int getHash() {
        return hash;
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

    /**
     * Return all the values in the node tree left first.
     * @return all the values in the value node (can be multiple if there is overflow)
     */
    public abstract ArrayList<V> getValues();
}