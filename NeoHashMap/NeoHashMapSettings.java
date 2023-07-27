package NeoHashMap;

import NeoHashMap.Exception.InvalidSettingsValue;

/**
 * Settings class for NeoHashMap
 */
public class NeoHashMapSettings {
    /**
     * Number of layer in NeoHashMap
     * By setting it to 1, it is a hashmap
     * By setting it to a greater number, it is a hash tree
     */
    private int hashMapLayers = 1;

    /**
     * HashCode size
     * as an example, if the key is "ABCD",
     * With a hashCodeSize = 1 : hashCode is "A"
     * With a hashCodeSize = 3 : hashCode is "ABC"
     */
    private int hashCodeSize = 1;

    /**
     * Defines if the hashmap can overflow.
     * If false, it will overwrite the value in the hashmap.
     */
    private boolean overflow = true;

    /**
     * Constructor
     * @param hashMapLayers max number of layer in the hashmap
     * @param hashCodeSize size of hashcode
     */
    public NeoHashMapSettings(int hashMapLayers, int hashCodeSize) throws InvalidSettingsValue {
        if(hashMapLayers < 1 || hashCodeSize < 1){
            throw new InvalidSettingsValue("Invalid Setting value");
        }
        this.hashMapLayers = hashMapLayers;
        this.hashCodeSize = hashCodeSize;
    }

    /**
     * Constructor
     * Takes default values.
     */
    public NeoHashMapSettings(){}

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
}
