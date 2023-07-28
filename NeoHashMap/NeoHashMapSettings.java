package NeoHashMap;

import NeoHashMap.Exception.InvalidSettingsValue;

/**
 * Settings class for NeoHashMap
 */
public class NeoHashMapSettings {
    /**
     * Default values
     */
    public static final int DEFAULT_HASHMAP_LAYERS = 1;
    public static final int DEFAULT_HASHMAP_SIZE = 2;
    public static final boolean DEFAULT_HASHMAP_OVERFLOW = true;

    /**
     * Number of layer in NeoHashMap
     * By setting it to 1, it is a hashmap
     * By setting it to a greater number, it is a hash tree
     */
    private int hashMapLayers;

    /**
     * HashMapSize ( = 2 ^ hashCodeSize )
     */
    private int hashMapSize;

    /**
     * HashCode size
     * as an example, if the key is "ABCD",
     * With a hashCodeSize = 1 : hashCode is "A"
     * With a hashCodeSize = 3 : hashCode is "ABC"
     */
    private int hashCodeSize;

    /**
     * Defines if the hashmap can overflow.
     * If false, it will overwrite the value in the hashmap.
     */
    private boolean overflow = true;

    /**
     * Constructor
     * Takes default values.
     */
    public NeoHashMapSettings() throws InvalidSettingsValue {
        this(DEFAULT_HASHMAP_LAYERS, DEFAULT_HASHMAP_SIZE, DEFAULT_HASHMAP_OVERFLOW);
    }
    /**
     * Constructor
     * @param hashMapLayers max number of layer in the hashmap
     * @param hashCodeSize size of hashcode
     */
    public NeoHashMapSettings(int hashMapLayers, int hashCodeSize) throws InvalidSettingsValue {
        this(hashMapLayers, hashCodeSize, DEFAULT_HASHMAP_OVERFLOW);
    }

    /**
     * Constructor
     * @param hashMapLayers max number of layer in the hashmap
     * @param hashCodeSize size of hashcode
     * @param overflow overflow possibility  for the hashmap
     * @throws InvalidSettingsValue
     */
    public NeoHashMapSettings(int hashMapLayers, int hashCodeSize, boolean overflow) throws InvalidSettingsValue {
        if(hashMapLayers < 1 || hashCodeSize < 1){
            throw new InvalidSettingsValue("Invalid Setting value");
        }
        this.hashMapLayers = hashMapLayers;
        this.hashCodeSize = hashCodeSize;
        this.overflow = overflow;
        this.hashMapSize = (int) Math.pow(2, hashCodeSize);
    }

    /**
     * Common Getter & Setters
     */

    /**
     * Returns the number of layer in the hashmap
     * @return the number of layer in the hashmap
     */
    public int getHashMapLayers() {
        return hashMapLayers;
    }

    /**
     * Sets the number of layer in the hashmap
     * @param hashMapLayers
     */
    public void setHashMapLayers(int hashMapLayers) {
        this.hashMapLayers = hashMapLayers;
    }

    /**
     * Returns the hash code size
     * @return the hash code size
     */
    public int getHashCodeSize() {
        return hashCodeSize;
    }

    /**
     * Sets the hash code size
     * @param hashCodeSize
     */
    public void setHashCodeSize(int hashCodeSize) {
        this.hashCodeSize = hashCodeSize;
    }

    /**
     * returns the possibility for the hashmap to overflow
     * @return true if the hashmap can overflow, else false
     */
    public boolean canOverflow() {
        return overflow;
    }

    /**
     * Defines the possibility for the hashmap to overflow
     * @param overflow
     */
    public void setOverflow(boolean overflow) {
        this.overflow = overflow;
    }

    public int getHashMapSize(){
        return hashMapSize;
    }

    /**
     * Static methods
     */
    public NeoHashMapSettings withDecrementedLayer() throws InvalidSettingsValue {
        return new NeoHashMapSettings(
                hashMapLayers - 1,
                hashCodeSize,
                overflow
        );
    }
}
