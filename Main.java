import NeoHashMap.Exception.InvalidSettingsValue;
import NeoHashMap.NeoHashMap;
import NeoHashMap.NeoHashMapSettings;

public class Main {
    public static void main(String[] args) throws InvalidSettingsValue {
        System.out.println("HashMap test : ");
        NeoHashMapSettings settings = new NeoHashMapSettings(2,2);
        NeoHashMap<String, String> hm = new NeoHashMap<String, String>();
    }
}
