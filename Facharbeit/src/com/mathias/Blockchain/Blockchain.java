
package com.mathias.Blockchain;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.math.BigInteger;
import java.security.MessageDigest;

public class Blockchain {

	///Variables///
	String transaction = "";
	String previousHash = "";
	String BLOCK = "";
	
	///Calculated Variables///
	String blockHash = "NOT DIFINED";
	String header = "";
	String timestamp = "";
	String topRoot = "";
	
	/// BITCCOIN//
	long nonce;
	String difficulty = "0000";
	boolean isValid = false;
	boolean previousHashValid = false;

	/// Initialize///
	public Blockchain(String pH, String name, String trans) {
		BLOCK = name;
		if (!pH.startsWith(difficulty)) {
			System.out.println(pH + " <-- is not Valid. Current difficulty: " + difficulty);
			return;
		} else {
			previousHashValid = true;
			System.out.println("Previous Block Valid");
		}

		transaction = trans;
		previousHash = pH;

	}

	public void setNonce(int n) {
		nonce = n;
	}

	public String getText() {
		return blockHash;

	}

	public String getTime() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();

		// timestamp = dtf.format(now);
		return dtf.format(now);
	}

	public String getBlock() {
		String output = "--------------------" + "\n";
		if (isValid == true && previousHashValid == true) {
			output += "Block: " + BLOCK + "\n";
			output += "Header: " + header + "\n" + "\n";

			output += "Previous-Hash: " + previousHash + "\n";
			output += "Time: " + timestamp + "\n";
			output += "Top-Root: " + topRoot + "\n";
			output += "Nonce: " + nonce + "\n";
			output += "isValid: " + isValid + "\n" + "\n";

			output += "BlockHash: " + blockHash + "\n";
		} else {
			output += "Block: " + BLOCK + "\n";
			output += "THIS BLOCK IS INVALID" + "\n";
			if (previousHashValid==false) {
				output += "previous Hash is not valid" + "\n";
			} else if(isValid == false) {
				output += "No valid Nonce" + "\n";
			}
		}
		output += "--------------------" + "\n";

		return output;
	}

	public String header() {

		String timeH = getHash(timestamp);
		String th = getHash(transaction);
		topRoot = th;
		String temp = th + previousHash + timeH;

		header = getHash(temp);
		return getHash(temp);
	}

	public String getBlockHash(String hdr, long nnce) {
		String s = hdr + nnce + "";

		if (previousHashValid == false)
			return "ERROR";

		if (getHash(s).startsWith(difficulty)) {
			blockHash = getHash(s);
			nonce = nnce;
			isValid = true;
			return getHash(s);

		} else {
			return null;
		}
	}

	public String getHash(String input) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-1");
			digest.reset();
			digest.update(input.getBytes("utf8"));
			return String.format("%040x", new BigInteger(1, digest.digest()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
