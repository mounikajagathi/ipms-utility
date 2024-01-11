package in.ults.gisurvey.utils;

import android.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by anuag on 21-09-2020
 **/
public class CryptoUtils {
    private static final int pswdIterations = 10;
    private static final int keySize = 128;
    private static final String secretKeyInstance = "PBKDF2WithHmacSHA1";
    private static final String plainText = "sampleText";
    private static final String AESSalt = "exampleSalt";
    private static final String cypherInstance = "AES/CBC/PKCS5Padding";
    private static final String initializationVector = "8119745113154120";
    private static final String secretKey = "c88ba867994f440963f55b727cdd3cb7";


    public static String encrypt(String textToEncrypt) throws Exception {

        SecretKeySpec skeySpec = new SecretKeySpec(secretKey.getBytes("UTF-8"), "AES");
        Cipher cipher = Cipher.getInstance(cypherInstance);
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, new IvParameterSpec(initializationVector.getBytes()));
        byte[] encrypted = cipher.doFinal(textToEncrypt.getBytes());
        return Base64.encodeToString(encrypted, Base64.DEFAULT);
    }

    public static String decrypt(String textToDecrypt) throws Exception {

        byte[] encryted_bytes = Base64.decode(textToDecrypt, Base64.DEFAULT);
        SecretKeySpec skeySpec = new SecretKeySpec(secretKey.getBytes("UTF-8"), "AES");
        Cipher cipher = Cipher.getInstance(cypherInstance);
        cipher.init(Cipher.DECRYPT_MODE, skeySpec, new IvParameterSpec(initializationVector.getBytes()));
        byte[] decrypted = cipher.doFinal(encryted_bytes);
        return new String(decrypted, "UTF-8");
    }

//    private static byte[] getRaw(String plainText, String salt) {
//        try {
//            SecretKeyFactory factory = SecretKeyFactory.getInstance(secretKeyInstance);
//            KeySpec spec = new PBEKeySpec(plainText.toCharArray(), salt.getBytes(), pswdIterations, keySize);
//            String keyString = new String(factory.generateSecret(spec).getEncoded(), "UTF8");
//            Log.i("KEYVAL_CHECK", "-" + keyString);
////            Log.i("shgdhsgdhga","-"+ Arrays.toString(factory.generateSecret(spec).getEncoded()));
//            return factory.generateSecret(spec).getEncoded();
//        } catch (InvalidKeySpecException e) {
//            e.printStackTrace();
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        return new byte[0];
//    }


}
