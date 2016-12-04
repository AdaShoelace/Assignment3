/**
 * Created by Pierre Lejdbring on 12/4/16.
 */
public class Encrypter {

    /**
     * Method to reverse given string
     * @param toEncrypt
     * @return the input string in reversed order
     */
    public String encrypt(String toEncrypt) {
        String result = new StringBuffer(toEncrypt).reverse().toString();
        return result;
    }

}
