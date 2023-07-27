package NeoHashMap;

/**
 * Settings class for NeoHashMap.NeoHashMap
 * Permits more flexibility by using
 */
public class NeoHashMapSettings {
    private int hashMapMaxLayers = 1;

    private int hashCodeSize = 1;
    private boolean overflow = true;

    /**
     * Constructor
     * @param hashMapMaxLayers max number of layer in the hashmap
     * @param hashCodeSize size of hashcode
     */
    public NeoHashMapSettings(int hashMapMaxLayers, int hashCodeSize){
        this.hashMapMaxLayers = hashMapMaxLayers;
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
    public int getHashMapMaxLayers() {
        return hashMapMaxLayers;
    }

    public void setHashMapMaxLayers(int hashMapMaxLayers) {
        this.hashMapMaxLayers = hashMapMaxLayers;
    }

    public int getHashCodeSize() {
        return hashCodeSize;
    }

    public void setHashCodeSize(int hashCodeSize) {
        this.hashCodeSize = hashCodeSize;
    }

    public boolean canOverflow() {
        return overflow;
    }

    public void setOverflow(boolean overflow) {
        this.overflow = overflow;
    }

}
