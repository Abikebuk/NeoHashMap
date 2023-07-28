import NeoHashMap.Exception.IncompatibleKeyTypeException;
import NeoHashMap.Exception.InvalidKeyException;
import NeoHashMap.Exception.InvalidSettingsValue;
import NeoHashMap.NeoHashMap;
import NeoHashMap.NeoHashMapSettings;


public class Main {
    public static void main(String[] args) throws InvalidSettingsValue, IncompatibleKeyTypeException, InvalidKeyException {
        // Initialization
        NeoHashMapSettings settings = new NeoHashMapSettings(2,3, true);
        NeoHashMap<String, String> hm = new NeoHashMap<String, String>(settings);
        hm.add("k1", "a");
        hm.add("k1", "b");
        hm.add("k1", "c");
        hm.add("k1", "d");
        hm.add("k2", "e");
        hm.add("k2", "f");
        hm.add("k3", "g");
        hm.add("k3", "h");
        hm.add("k4", "i");
        hm.add("k5", "j");
        hm.add("k6", "k");
        hm.add("k7", "l");
        hm.add("k8", "m");
        hm.add("k9", "o");
        hm.add("k9", "p");
        hm.add("k9", "q");
        hm.add("k10", "r");
        hm.add("k10", "s");
        hm.add("k10", "t");
        hm.add("k11", "w");
        hm.add("k11", "x");
        hm.add("k12", "y");
        hm.add("k12", "z");
        // Print of the full hashmap
        hm.print();

        // Lot of differents tests on access
        System.out.println("");
        System.out.println("Get :");
        System.out.format("k1 first value : %s\n", hm.getValue("k1"));
        System.out.format("k2 first value : %s\n", hm.getValue("k2"));
        System.out.format("k3 first value : %s\n", hm.getValue("k3"));
        System.out.format("k4 first value : %s\n", hm.getValue("k4"));
        System.out.format("NotAKey first value : %s\n", hm.getValue("NotAKey"));
        System.out.format("k11 values : [ %s ]\n", String.join("," , hm.getValues("k11")));
        System.out.format("k12 values : [ %s ]\n", String.join("," , hm.getValues("k12")));
        String k13 = hm.getValue("k13") == null ?
                "" :
                String.join("," , hm.getValues("k13"));
        System.out.format("k13 values : [ %s ]\n", k13);
        System.out.format(" ^ k13 doesn't exists \n");

        // Remove things
        System.out.println("");
        System.out.println("Remove :");
        System.out.format("k9 values before remove  : [ %s ]\n", String.join("," , hm.getValues("k9")));
        hm.removeFirst("k9");
        System.out.format("k9 values after 1 remove : [ %s ]\n", String.join("," , hm.getValues("k9")));
        hm.removeFirst("k9");
        System.out.format("k9 values after 1 remove : [ %s ]\n", String.join("," , hm.getValues("k9")));
        hm.removeFirst("k9");
        String k9 = hm.getValue("k9") == null ?
                "" :
                String.join("," , hm.getValues("k9"));
        System.out.format("k9 values after 1 remove : [ %s ]\n", String.join("," , k9));
        System.out.format("k10 values before remove all : [ %s ]\n", String.join("," , hm.getValues("k10")));
        hm.removeAll("k10");
        String k10 = hm.getValue("k10") == null ?
                "" :
                String.join("," , hm.getValues("k10"));
        System.out.format("k10 values after remove all  : [ %s ]\n", String.join("," , k10));

    }
}
