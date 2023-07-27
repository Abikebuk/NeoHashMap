package NeoHashMap.Nodes;

import NeoHashMap.NeoHashMapSettings;

import java.util.ArrayList;

/**
 * Key Node class for the hashmap
 * Contains a list of AbstractKeyNode compared to the LeafKeyNode which contains a valueNode
 * @param <K> Type of the keys
 * @param <V> Type of the Values
 */
public class KeyNode<K, V> extends AbstractKeyNode<K, V> {
    /**
     * List of children which are also HashMapNodes with the same type arguments
     */
    private ArrayList<AbstractKeyNode<K, V>> children;

    /**
     * Constructor
     * Sets the parent to null
     * @param hash hash value
     * @param children children nodes
     */
    public KeyNode(int hash, ArrayList<AbstractKeyNode<K, V>> children, NeoHashMapSettings settings){
        this(hash, null, children, settings);
    }

    /**
     * Constructor
     * Sets parent to null and children to an empty list
     * @param hash hash value
     */
    public KeyNode(int hash, NeoHashMapSettings settings) {
        this(hash, null, new ArrayList<AbstractKeyNode<K, V>>(), settings);
    }
    /**
     * Constructor
     * @param hash hash value
     * @param parent parent node (can be null)
     * @param children children nodes
     */
    public KeyNode(int hash, KeyNode<K, V> parent, ArrayList<AbstractKeyNode<K, V>> children, NeoHashMapSettings settings){
        super(hash, parent, settings);
        this.children = children;
    }

    /**
     * Common Getters & Setters
     */
    /**
     * @TODO
     * Return all the values in the node tree left first.
     * @return all the values in the node tree (including this node)
     */
    @Override
    ArrayList<V> getValues() {
        ArrayList<V> result = new ArrayList<V>();
        for(AbstractKeyNode child : children){
            result.addAll(child.getValues());
        }
        return result;
    }

    /**
     * Removes a child from the children list.
     * Doesn't do anything if the hash doesn't exist.
     * @param hash
     */
    public void removeChildrenByHash(int hash){
        // there is no foreach with tuple (index, value)
        // Because of other languages I'm quite scared to use remove(object o) which is inconsistent
        // This is why I use index "i" instead to remove element.
        int i = 0;
        for (AbstractKeyNode<K, V> child : children) {
            if(child.getHash() == hash){
                children.remove(i);
                return;
            }
            i++;
        }
    }
    /**
     * Returns the list of children nodes
     * @return children
     */
    public ArrayList<AbstractKeyNode<K, V>> getChildren() {
        return children;
    }

    /**
     * Add a child node to the children list
     * @param childNode
     */
    public void addChildren(AbstractKeyNode<K, V> childNode){
        children.add(childNode);
    }

    /**
     * Clear the children list
     */
    public void clearChildren(){
        this.children = new ArrayList<AbstractKeyNode<K, V>>();
    }
}
