
/**
 * Created by lewis on 9/25/2017.
 */


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

    /**
     * A utility class that encrypts or decrypts a file.
     * @author www.codejava.net
     *
     */
    public class CryptoUtils {
        private static final String ALGORITHM = "AES";
        private static final String TRANSFORMATION = "AES";
        private static boolean reply = true;

        public static boolean encrypt(String key, File inputFile, File outputFile)
                throws CryptoException {
            doCrypto(Cipher.ENCRYPT_MODE, key, inputFile, outputFile);
            return  reply;
        }

        public static boolean decrypt(String key, File inputFile, File outputFile)
                throws CryptoException {
            doCrypto(Cipher.DECRYPT_MODE, key, inputFile, outputFile);
            return  reply;
        }

        private static boolean doCrypto(int cipherMode, String key, File inputFile,
                                     File outputFile) throws CryptoException {
            try {
                Key secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
                Cipher cipher = Cipher.getInstance(TRANSFORMATION);
                cipher.init(cipherMode, secretKey);

                FileInputStream inputStream = new FileInputStream(inputFile);
                byte[] inputBytes = new byte[(int) inputFile.length()];
                inputStream.read(inputBytes);

                byte[] outputBytes = cipher.doFinal(inputBytes);

                FileOutputStream outputStream = new FileOutputStream(outputFile);
                outputStream.write(outputBytes);

                inputStream.close();
                outputStream.close();

            } catch (IOException ex) {
                reply = false;
                throw new CryptoException("Error I/O encrypting/decrypting file", ex);
            } catch (NoSuchPaddingException ex) {
                reply = false;
                throw new CryptoException("Error Padding encrypting/decrypting file", ex);
            } catch (NoSuchAlgorithmException ex) {
                reply = false;
                throw new CryptoException("Error NO Algor encrypting/decrypting file", ex);
            } catch (InvalidKeyException ex) {
                reply = false;
                throw new CryptoException("Error Invalid key encrypting/decrypting file", ex);
            } catch (BadPaddingException ex) {
                reply = false;
                throw new CryptoException("Error Badd padding encrypting/decrypting file", ex);
            } catch (IllegalBlockSizeException ex) {
                reply = false;
                throw new CryptoException("Error Illegal block size encrypting/decrypting file", ex);
            }
            return reply;
        }

    }