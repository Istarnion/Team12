package no.hist.aitel.team12.app;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 * Class for handling passwords using the PBKDF2WithHmacSHA1 algorithm
 * 
 * @author Hallgeir
 * @version 1.0
 */
public class PasswordManager {

	/**
	 * Generates a temporary password based on a username.
	 * @param username
	 * @return A temporary password.
	 */
	public static String generatePassword(String username) {
		List<String> characters = Arrays.asList(username.split(""));
		
		Collections.shuffle(characters);
		StringBuilder sb = new StringBuilder();
		for(String s : characters) {
			sb.append(s);
		}
		
		return sb.toString();
	}
	
	/**
	 * This methods take a password inputed from a user and generates a secure hash, ready to store in the database.
	 * 
	 * @param password
	 * @return A secure hash of a password.
 	 */
	public static String generatePasswordHash(String password) {
		int iterations = 1000;
		char[] carray = password.toCharArray();
		byte[] salt = getSalt().getBytes();
		
		PBEKeySpec spec = new PBEKeySpec(carray, salt, iterations, 64 * 8);
		SecretKeyFactory skFactory;
		try {
			skFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			byte[] hash = skFactory.generateSecret(spec).getEncoded();
			return iterations+":"+toHex(salt)+":"+toHex(hash);
		}
		catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		catch (InvalidKeySpecException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * This method takes a non-hashed password and a hashed password, and compares them
	 * 
	 * @param inputPwd		An inputed password from the user
	 * @param storedPwd		A retrieved password from the database
	 * @return true if the inputed password is valid, false if not.
	 */
	public static boolean validatePasswordMatch(String inputPwd, String storedPwd) {
		String[] parts = storedPwd.split(":");
		int iterations = Integer.parseInt(parts[0]);
		byte[] salt = fromHex(parts[1]);
		byte[] hash = fromHex(parts[2]);
		
		PBEKeySpec spec = new PBEKeySpec(inputPwd.toCharArray(), salt, iterations, hash.length * 8);
		SecretKeyFactory skFactory;
		try {
			skFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		
			byte[] testHash = skFactory.generateSecret(spec).getEncoded();
			
			int diff = hash.length ^ testHash.length;
			for(int i=0; i<hash.length && i<testHash.length; i++) {
				diff |= hash[i] ^ testHash[i];
			}
			return diff == 0;
		}
		catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		catch (InvalidKeySpecException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	private static String getSalt() {
		try {
			SecureRandom sRandom = SecureRandom.getInstance("SHA1PRNG");
			byte[] salt = new byte[16];
			sRandom.nextBytes(salt);
			return salt.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	private static String toHex(byte[] array) {
		BigInteger bInt = new BigInteger(1, array);
		String hex = bInt.toString(16);
		int paddingLength = (array.length * 2) - hex.length();
		if(paddingLength > 0) {
			return String.format("%0"+paddingLength+"d", 0) + hex;
		}
		else {
			return hex;
		}
	}
	
	private static byte[] fromHex(String hex) {
		byte[] bytes = new byte[hex.length()/2];
		for(int i=0; i<bytes.length; i++) {
			bytes[i] = (byte)Integer.parseInt(hex.substring(2*i, 2*i+2), 16);
		}
		return bytes;
	}
}
