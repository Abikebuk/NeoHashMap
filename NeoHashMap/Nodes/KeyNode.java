package NeoHashMap.Nodes;

import NeoHashMap.Exception.InvalidSettingsValue;
import NeoHashMap.HashCode;
import NeoHashMap.NeoHashMapSettings;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Key Node class for the hashmap
 * Contains a list of AbstractKeyNode compared to the LeafKeyNode which contains a valueNode
 * @param <K> Type of the keys
 * @param <V> Type of the Values
 */
public class KeyNode<K, V> extends AbstractKeyNode<K, V> {
    /**
     * Value node which contains data
     */
    public SingleChainedDataNode<V> valueNode;

    /**
     * List of children which are also HashMapNodes with the same type arguments
     */
    private ArrayList<AbstractKeyNode<K, V>> children;

    /**
     * Constructor
     * Sets the parent to null
     * @param hash hash value
     * @param parent parent node
     */
    public KeyNode(String hash, AbstractKeyNode<K, V> parent, NeoHashMapSettings settings) throws InvalidSettingsValue {
        this(hash, parent, null, settings); // children is null there but set below
        this.children = populatedNodes();
    }

    /**
     * Constructor
     * Sets the parent to null
     * @param index index value
     * @param parent parent node
     */
    public KeyNode(int index, AbstractKeyNode<K, V> parent, NeoHashMapSettings settings) throws InvalidSettingsValue {
        this(index, parent, null, settings); // children is null there but set below
        this.children = populatedNodes();
    }

    /**
     * Constructor
     * Sets parent to null and children to an empty list
     * @param hash hash value
     * @param settings settings of the hashmap
     */
    public KeyNode(String hash, NeoHashMapSettings settings) throws InvalidSettingsValue {
        this(
                hash,
                null,
                null, // children is null there but set below
                settings);
        children = populatedNodes();
    }

    /**
     * Constructor
     * Sets parent to null and children to an empty list
     * @param index index value
     * @param settings settings of the hashmap
     */
    public KeyNode(int index, NeoHashMapSettings settings) throws InvalidSettingsValue {
        this(
                index,
                null,
                null, // children is null there but set below
                settings
        );
        children = populatedNodes();
    }

    /**
     * Constructor
     * @param hash hash value
     * @param parent parent node (can be null)
     * @param settings settings of the hashmap
     */
    public KeyNode(String hash, AbstractKeyNode<K, V> parent, ArrayList<AbstractKeyNode<K, V>> children, NeoHashMapSettings settings){
        super(hash, parent, settings);
        this.children = children;
        this.valueNode = null;
    }

    /**
     * Constructor
     * @param index index value
     * @param parent parent node (can be null)
     * @param settings settings of the hashmap
     */
    public KeyNode(int index, AbstractKeyNode<K, V> parent, ArrayList<AbstractKeyNode<K, V>> children, NeoHashMapSettings settings){
        super(index, parent, settings);
        this.children = children;
        this.valueNode = null;
    }

    /**
     * Create the nodes required as children (if this node is not a leaf)
     * @return a list of nodes required as children
     * @throws InvalidSettingsValue
     */
    private ArrayList<AbstractKeyNode<K, V>> populatedNodes() throws InvalidSettingsValue {
        if(settings.getHashMapLayers() == 1) return null;
        ArrayList<AbstractKeyNode<K, V>> result = new ArrayList<AbstractKeyNode<K, V>>(settings.getHashMapSize());
        for(int i = 0; i < settings.getHashMapSize(); i++){
            result.add(i, new KeyNode<K, V>(i, this,settings.withDecrementedLayer()));
        }
        return result;
    }

    /**
     * Returns the list of children nodes
     * @return children
     */
    public ArrayList<AbstractKeyNode<K, V>> getChildren() {
        return children;
    }

    /**
     * Overrides
     */

    /**
     * Common Getters & Setters
     */
    /**
     * Return all the values in the node tree left first.
     * @return all the values in the node tree (including this node)
     */
    @Override
    public ArrayList<V> getValues() {
        ArrayList<V> result = new ArrayList<V>();
        for(AbstractKeyNode child : children){
            result.addAll(child.getValues());
        }
        return result;
    }

    /**
     * Adds an element to the hashmap
     * @param hashRemainder Contrary to NeoHashMap, it takes a portion of the key translated in binary through HashCode
     * @param value
     */
    @Override
    public void add(String hashRemainder, V value) {
        HashCode hashCode = HashCode.fromRemainder(hashRemainder, settings.getHashCodeSize());
        if(settings.getHashMapLayers() <= 1 || hashCode.getRemainder() == null){
            if(valueNode == null){
                valueNode = new SingleChainedDataNode<V>(value, settings.canOverflow());
            }
            else
                valueNode.add(value);
        }else{
            children.get(hashCode.getValue()).add(hashCode.getRemainder(), value);
        }
    }

    /**
     * Prints node content
     * @param prefix string appended to the start of each lines
     */
    @Override
    public void print(String prefix){
        String newPrefix = prefix + "  |  ";
        DecimalFormat hashFormat = new DecimalFormat("0".repeat(settings.getHashCodeSize()));
        System.out.format("%s [# Node #] Index : %d | Hash %s | Full Hash %s\n",
                prefix,
                index,
                hashFormat.format(Integer.parseInt(hash)),
                getFullHash()
        );

        if(valueNode != null) valueNode.print(prefix);
        if(children != null){
            for(AbstractKeyNode<K, V> child : children){
                child.print(newPrefix);
            }
        }
    }

    /**
     * Returns the value associated with the key
     * Returns only the first one in the hashmap cannot overflow
     * Returns null if no element is found
     * @param remainderKey remainder of the key
     * @return
     */
    @Override
    public V getValue(String remainderKey) {
        if(settings.getHashMapLayers() == 1){
            if(valueNode == null) return null;
            return valueNode.getData();
        }
        if(remainderKey == "" || remainderKey == null){
            if(valueNode == null) return null;
            else return valueNode.getData();
        }else {
            HashCode hashCode = HashCode.fromRemainder(remainderKey, settings.getHashCodeSize());
            return children.get(hashCode.getValue()).getValue(hashCode.getRemainder());
        }
    }

    /**
     * Returns the values associated with the keys
     * Returns a empty list if no element is found
     * @param remainderKey remainder of the key
     * @return
     */
    @Override
    public ArrayList<V> getValues(String remainderKey) {
        if(settings.getHashMapLayers() == 1){
            if(valueNode == null) return null;
            return valueNode.getValues();
        }
        if(remainderKey == "" || remainderKey == null){
            if(valueNode == null) return new ArrayList<V>();
            else return valueNode.getValues();
        }else {
            HashCode hashCode = HashCode.fromRemainder(remainderKey, settings.getHashCodeSize());
            return children.get(hashCode.getValue()).getValues(hashCode.getRemainder());
        }
    }

    /**
     * Remove the first element associated with the keys
     * Does nothing if there is nothing to remove
     * Remark : Old node goes to the garbage collector but there is surely a better
     * way to destroy the old node efficiently
     * @param remainderKey
     */
    @Override
    public void removeFirst(String remainderKey) {
        if(settings.getHashMapLayers() == 1){
            if(valueNode == null) return;
            SingleChainedDataNode newValueNode = valueNode.getNext(); // can be null
            valueNode = newValueNode;
            return;
        }
        if(remainderKey == "" || remainderKey == null){
            if(valueNode == null) return;
            else {
                SingleChainedDataNode newValueNode = valueNode.getNext(); // can be null
                valueNode = newValueNode;
            }
        }else {
            HashCode hashCode = HashCode.fromRemainder(remainderKey, settings.getHashCodeSize());
            children.get(hashCode.getValue()).removeFirst(hashCode.getRemainder());
        }
    }

    /**
     * Remove all the elements associated with the keys
     * Does nothing if there is nothing to remove
     * Remark : Old nodes goes to the garbage collector but there is surely a better
     * way to destroy the old nodes efficiently
     * @param remainderKey
     */
    @Override
    public void removeAll(String remainderKey) {
        if(settings.getHashMapLayers() == 1){
            if(valueNode == null) return;
            valueNode = null;
            return;
        }
        if(remainderKey == "" || remainderKey == null){
            if(valueNode == null) return;
            else valueNode = null;
        }else {
            HashCode hashCode = HashCode.fromRemainder(remainderKey, settings.getHashCodeSize());
            children.get(hashCode.getValue()).removeAll(hashCode.getRemainder());
        }
    }


}
