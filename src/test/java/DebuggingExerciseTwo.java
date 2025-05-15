import java.util.ArrayList;
import java.util.Arrays;

public class DebuggingExerciseTwo {
    private static String encode(String text, String key) {
        ArrayList cipher = makeCipher(key);

        ArrayList<String> ciphertextChars = new ArrayList<>();
        for (int i = 0; i < text.length(); i++) {
            Integer v = cipher.indexOf(Character.toString(text.charAt(i)));
            ciphertextChars.add(Character.toString(65 + v));
        }
        return String.join("", ciphertextChars);
    }
    private static String decode(String encrypted, String key) {
        ArrayList cipher = makeCipher(key);

        ArrayList<String> plaintextChars = new ArrayList<>();
        for (int i = 0; i < encrypted.length(); i++) {
            String letter = cipher.get(65 - (int) encrypted.charAt(i)).toString();
            plaintextChars.add(letter);
        }
        return String.join("", plaintextChars);
    }
    private static ArrayList makeCipher(String key) {
        ArrayList<String> cipherWithDuplicates = new ArrayList<String>(Arrays.asList(key.split("")));
        for (int i = 1; i < 26; i++) {
            cipherWithDuplicates.add(Character.toString((char) i + 98));
        }

        ArrayList<String> cipher = new ArrayList<>();
        for (int i = 0; i < cipherWithDuplicates.size(); i++) {
            String current = cipherWithDuplicates.get(i);
            if ( !cipherWithDuplicates.subList(0, i).contains(current) ) {
                cipher.add(cipherWithDuplicates.get(i));
            }
        }
        return cipher;
    }
    public static void main(String[] args) {
        // When you run this file, these next lines will show you the expected
        // and actual outputs of the functions above.
        System.out.println(" Running: encode(\"theswiftfoxjumpedoverthelazydog\", \"secretkey\")");
        System.out.println("Expected: EMBAXNKEKSYOVQTBJSWBDEMBPHZGJSL");
        System.out.println("  Actual: " + encode("theswiftfoxjumpedoverthelazydog", "secretkey"));
        System.out.println("");
        System.out.println(" Running: decode(\"EMBAXNKEKSYOVQTBJSWBDEMBPHZGJSL\", \"secretkey\")");
        System.out.println("Expected: theswiftfoxjumpedoverthelazydog");
        System.out.println("  Actual: " + decode("EMBAXNKEKSYOVQTBJSWBDEMBPHZGJSL", "secretkey"));
    }
}
