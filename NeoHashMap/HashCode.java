package NeoHashMap;

import NeoHashMap.Helper.BinaryHelper;
import com.sun.tools.jconsole.JConsoleContext;

/**
 * Class HaschCode
 * Is a tuple { HashCode, RemainingCode } which is used to hash and
 * to give the different data required for the NeoHashMap
 */
public class HashCode {
    /**
     * Actual hashed code translated to integer from its binary form
     */
    private int value;
    /**
     * Remainder of the hashcode in binary
     */
    private String remainder;

    /**
     * Constructor
     * @param key key to hash
     * @param settings NeoHashMap settings
     */
    public HashCode(Object key, NeoHashMapSettings settings){
        this(key, settings.getHashCodeSize());
    }

    /**
     * Constructor
     * @param key key to hash
     * @param hashSize size of the hash
     */
    public HashCode(Object key, int hashSize){
        Object[] hashResult = hash(key, hashSize);
        value = (int) hashResult[0];
        remainder = (String) hashResult[1];
    }

    /**
     * Constructor
     * Is private so it doesn't clash with other constructors
     * Is used exclusively with the method withRemainder()
     * @param binaryRemainder String binary remainder of hash process
     * @param hashSize size of the hash
     */
    private HashCode(String binaryRemainder, int hashSize){
        Object[] hashResult = hashFromBinaryString(binaryRemainder, hashSize);
        value = (int) hashResult[0];
        remainder = (String) hashResult[1];
    }

    /**
     * Hash a key by taking in account the hash size.
     * @param key key to hash
     * @param hashSize size of the hash
     * @return A list [HashResult, remainder]
     */
    private Object[] hash(Object key, int hashSize){
        String bin = BinaryHelper.ObjectToBinary(key);
        return hashFromBinaryString(bin, hashSize);
    }

    /**
     * Hash a key by taking in account the hash size.
     * @param key String binary key
     * @param hashSize size of the hash
     * @return
     */
    private Object[] hashFromBinaryString(String key, int hashSize){
        if(key.length() <= hashSize){
            return new Object[] {
                    (int) Integer.parseInt(key, 2),
                    null
            };
        }
        String remainder = key.substring(0, key.length() - hashSize);
        String toHashValue = key.substring(key.length() - hashSize);
        int hashedValue = Integer.parseInt(toHashValue, 2);
        return new Object[] {
                (int) hashedValue,
                (String) remainder
        };

    }

    /**
     * Returns the value of the hash
     *
     * @return
     */
    public int getValue(){
        return value;
    }

    /**
     * Returns the remainder of the hash
     * @return
     */
    public String getRemainder(){
        return remainder;
    }

    /**
     * Static methods
     */

    /**
     * Returns a hashcode from a String binary remainder
     * @param binaryRemainder String binary remainder of hash process
     * @param hashSize size of the hash
     * @return
     */
    public static HashCode fromRemainder(String binaryRemainder, int hashSize){
        return new HashCode(binaryRemainder, hashSize);
    }
}
