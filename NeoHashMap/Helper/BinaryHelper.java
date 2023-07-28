package NeoHashMap.Helper;

/**
 * Binary helper class
 */
public class BinaryHelper {
    /**
     * Convert an object to a binary by taking its HashCode
     * @param o an object
     * @return a binary converted from his hashcode
     */
    public static String ObjectToBinary(Object o){
        int hashCode = o.hashCode();
        String binary = Integer.toBinaryString(hashCode);
        return binary;
    }
}
