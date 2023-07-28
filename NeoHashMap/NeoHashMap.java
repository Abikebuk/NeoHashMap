package NeoHashMap;

import NeoHashMap.Exception.IncompatibleKeyTypeException;
import NeoHashMap.Exception.InvalidKeyException;
import NeoHashMap.Exception.InvalidSettingsValue;
import NeoHashMap.Nodes.AbstractKeyNode;
import NeoHashMap.Nodes.KeyNode;

import java.util.ArrayList;

/**
 * Hashmap class
 * The hash function is like a dictionary and can be destructured with multiple arguments
 * Even though the K type can be anything, it is only capable of processing string K type
 * for testing purpose but could be completed by using object.getHash() in the hash function.
 * @param <K> type of the keys
 * @param <V> type of the values
 */
public class NeoHashMap<K, V> {
    /**
     * Keys of the hashmap
     */
    private ArrayList<K> keys;

    /**
     * Values of the hashmap
     */
    private ArrayList<AbstractKeyNode<K, V>> nodes;

    private NeoHashMapSettings settings;
    /**
     * Constructors
     */

    /**
     * Constructor
     */
    public NeoHashMap() throws InvalidSettingsValue {
        this(new NeoHashMapSettings());
    }

    /**
     * Constructor
     * @param settings custom settings
     */
    public NeoHashMap(NeoHashMapSettings settings) throws InvalidSettingsValue {
        this.settings = settings;
        keys = new ArrayList<K>();
        nodes = populatedNodes();
    }

    /**
     * Getters & Setters
     */

    /**
     * Add a value in the hashmap
     * @param key
     * @param value
     * @throws Exception
     */
    public void add(K key, V value) throws IncompatibleKeyTypeException, InvalidKeyException, InvalidSettingsValue {
        if(key == null) throw new InvalidKeyException("Key value is empty.");
        HashCode hashCode = new HashCode(key, settings);
        int hashValue = hashCode.getValue();
        nodes.get(hashValue).add(hashCode.getRemainder(), value);
    }

    /**
     * Methods
     */
    public void print(){
        String prefix = " ";
        System.out.println("= NeoHashMap =");
        System.out.println("");
        System.out.format("Settings : \n");
        System.out.format(" |         Layers : %s\n", settings.getHashMapLayers());
        System.out.format(" |       NHM Size : %s\n", settings.getHashMapSize());
        System.out.format(" |  HashCode Size : %s\n", settings.getHashCodeSize());
        System.out.format(" |       Overflow : %s\n", settings.canOverflow());
        System.out.format(" |_______________________________\n", settings.canOverflow());
        System.out.println("");
        System.out.println();
        for(AbstractKeyNode<K, V> n : nodes ){
            n.print(prefix);
        }
    }
    /**
     * Creates the nodes required for the hashmap
     * @return a list of required nodes
     * @throws InvalidSettingsValue
     */
    private ArrayList<AbstractKeyNode<K, V>> populatedNodes() throws InvalidSettingsValue {
        ArrayList<AbstractKeyNode<K, V>> result = new ArrayList<AbstractKeyNode<K, V>>(settings.getHashMapSize());
        for(int i = 0; i < settings.getHashMapSize(); i++){
             result.add(i, new KeyNode<K, V>(i, settings));
        }
        return result;
    }

    /**
     * Getter & Setters
     */

    /**
     * Return all the values in the node tree left first.
     * @return all the values in the node tree (including this node)
     */
    public ArrayList<V> getValues() {
        ArrayList<V> result = new ArrayList<V>();
        for (AbstractKeyNode<K, V> node : nodes) {
            result.addAll(node.getValues());
        }
        return result;
    }

    /**
     * Returns node list
     * @return
     */
    public ArrayList<AbstractKeyNode<K, V>> getNodes() {
        return nodes;
    }

    /**
     * Returns the value associated with the key
     * Returns only the first one in the hashmap cannot overflow
     * Returns null if no element is found
     * @param key key
     * @return
     */
    public V getValue(K key){
        HashCode hashCode = new HashCode(key, settings.getHashCodeSize());
        return nodes.get(hashCode.getValue()).getValue(hashCode.getRemainder());
    }

    /**
     * Returns the values associated with the keys
     * Returns a empty list if no element is found
     * @param key key
     * @return
     */
    public ArrayList<V> getValues(K key){
        HashCode hashCode = new HashCode(key, settings.getHashCodeSize());
        return nodes.get(hashCode.getValue()).getValues(hashCode.getRemainder());
    }


    /**
     * Removes the first element associated with the key
     * Doesn't do anything if there is nothing to remove
     * @param key
     */
    public void removeFirst(String key){
        HashCode hashCode = new HashCode(key, settings.getHashCodeSize());
        nodes.get(hashCode.getValue()).removeFirst(hashCode.getRemainder());
    }

    /**
     * Removes all the elements associated with the key
     * Doesn't do anything if there is nothing to remove
     * @param key
     */
    public void removeAll(String key){
        HashCode hashCode = new HashCode(key, settings.getHashCodeSize());
        nodes.get(hashCode.getValue()).removeAll(hashCode.getRemainder());
    }
}

