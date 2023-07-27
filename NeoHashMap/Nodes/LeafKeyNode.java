package NeoHashMap.Nodes;

import NeoHashMap.NeoHashMapSettings;

import java.util.ArrayList;

/**
 * Leaf Key Node class for the hashmap
 * Contains a valueNode compared to KeyNode which contain a list of AbstractKeyNode as children
 * @param <K> Type of the keys
 * @param <V> Type of the Values
 */
public class LeafKeyNode<K, V> extends AbstractKeyNode<K, V> {
    public SingleChainedValueNode<V> valueNode;

    /**
     * Constructor
     * Sets valueNode & parent to null
     * @param hash hash value
     */
    public LeafKeyNode(int hash, NeoHashMapSettings settings) {
        super(hash, settings);
        valueNode = null;
    }

    /**
     * Constructor
     * Sets the valueNode to null
     * @param hash hash value
     * @param parent parent node (can be null)
     */
    public LeafKeyNode(int hash, AbstractKeyNode parent, NeoHashMapSettings settings) {
        super(hash, parent, settings);
    }

    /**
     * Constructor
     * @param hash hash value
     * @param parent parent node (can be null)
     * @param valueNode value node (can be null)
     */
    public LeafKeyNode(int hash, AbstractKeyNode parent, SingleChainedValueNode valueNode, NeoHashMapSettings settings){
        super(hash, parent, settings);
        this.valueNode = valueNode;
    }

    /**
     * Return all the values in the node tree left first.
     * @return all the values in the value node (can be multiple if there is overflow)
     */
    @Override
    ArrayList<V> getValues() {
        return valueNode.getValues();
    }
}
