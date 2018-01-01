package com.max.login.Utils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.lang3.StringUtils;

import com.max.login.Configurations.ApplicationProperties;
import com.max.login.Utils.Constants.AppConstants;
import com.max.login.Utils.Constants.PropertiesConstants;

/**
 * @author Vivek
 */
public class AESEncryption {

	private static ApplicationProperties applicationProperties = ApplicationProperties.getInstance();
	private static String secret = applicationProperties.getValue(PropertiesConstants.AES_ENCRYPTION_SECRET_KEY, null);

	/**
	 * gets the AES encryption key. In your actual programs, this should be
	 * safely stored.
	 * 
	 * @return
	 * @throws Exception
	 */
	public static SecretKey getSecretEncryptionKey(String secret) throws Exception {
		byte[] key = secret.getBytes(StandardCharsets.UTF_8);
		SecretKey secKey = new SecretKeySpec(key, AppConstants.ENCRYPTION_TYPE_AES);
		return secKey;
	}

	/**
	 * Encrypts plainText in AES using the secret key
	 * 
	 * @param strToEncrypt
	 * @return
	 * @throws Exception
	 */
	public static String encryptText(String strToEncrypt) throws Exception {
		SecretKey secKey = getSecretEncryptionKey(secret);
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, secKey);

		if (StringUtils.isNotBlank(strToEncrypt)) {
			return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
		} else {
			return null;
		}
	}

	/**
	 * Decrypts encrypted String using the key used for encryption.
	 * 
	 * @param strToDecrypt
	 * @return
	 * @throws Exception
	 */
	public static String decryptText(String strToDecrypt) throws Exception {
		SecretKey secKey = getSecretEncryptionKey(secret);
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
		cipher.init(Cipher.DECRYPT_MODE, secKey);
		if (StringUtils.isNotBlank(strToDecrypt)) {
			return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
		} else {
			return null;
		}
	}

	/**
	 * 1. Generate a plain text for encryption 
	 * 2. Get a secret key (printed in hexadecimal form). 
	 * In actual use this must by encrypted and kept safe.
	 * The same key is required for decryption. 3.
	 */
/*	public static void main(String[] args) throws Exception {

		String plainText = "P@rrot";
		System.out.println("Original Text:" + plainText);
		String cipherText = encryptText(plainText);
		System.out.println("Encrypted Text (Hex Form):" + cipherText);
		String decryptedText = decryptText(cipherText);
		System.out.println("Decrypted Text:" + decryptedText);
	}*/
}