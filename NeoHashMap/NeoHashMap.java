package NeoHashMap;

import NeoHashMap.Exception.IncompatibleKeyTypeException;
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
    private ArrayList<KeyNode<K, V>> nodes;

    private NeoHashMapSettings settings;
    /**
     * Constructors
     */

    /**
     * Constructor
     */
    public NeoHashMap(){
        this(new NeoHashMapSettings());
        keys = new ArrayList<K>();
        nodes = new ArrayList<KeyNode<K, V>>();
    }

    /**
     * Constructor
     * @param settings custom settings
     */
    NeoHashMap(NeoHashMapSettings settings){
        this.settings = settings;
    }


    /**
     * Getters & Setters
     */

    /**
     * Add a value in the hashmap
     * I might be wrong but it felt right to me to put the class
     * @param key
     * @param value
     * @throws Exception
     */
    public void addValue(K key, V value) throws IncompatibleKeyTypeException {
        // Switch case doesn't work with
        if(key instanceof String){
            addValueWithStringKey((String) key, value);
        }else{
            throw new IncompatibleKeyTypeException("Invalid type (only String is available for key type).");
        }

    }

    private void addValueWithStringKey(String key, V value){

    }

    /**
     * Common Getter & Setters
     */

    /**
     * Returns the list of keys
     * @return list of keys
     */
    public ArrayList<K> getKeys() { return keys; }


    /**
     * Return all the values in the node tree left first.
     * @return all the values in the node tree (including this node)
     */
    public ArrayList<V> getValues() {
        ArrayList<V> result = new ArrayList<V>();
        for (KeyNode<K, V> node : nodes) {
            // @TODO result.addAll(node.getValues());
        }
        return result;
    }

}

