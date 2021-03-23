
package com.mathias.Blockchain;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.math.BigInteger;
import java.security.MessageDigest;

public class Blockchain {

	/// Variables///
	String transaction = "";
	String previousHash = "";
	String BLOCK = "";

	/// Calculated Variables///
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
		getTime();
		transaction = trans;
		previousHash = pH;

	}

	public void setValid(boolean b) {
		isValid = b;
	}

	public String getText() {
		return blockHash;

	}

	public String getTime() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();

		timestamp = dtf.format(now);
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

			output += "Transaction: " + transaction + "\n" + "\n";

			output += "BlockHash: " + blockHash + "\n";
		} else {
			output += "Block: " + BLOCK + "\n";
			output += "THIS BLOCK IS INVALID" + "\n";
			if (previousHashValid == false) {
				output += "previous Hash is not valid" + "\n";
			} else if (isValid == false) {
				output += "No valid Nonce" + "\n";
			}
		}
		output += "--------------------" + "\n";

		return output;
	}

	public String saveBlock() {
		String output = "";
		output += BLOCK + "\n";
		output += header + "\n";
		output += previousHash + "\n";
		output += timestamp + "\n";
		output += topRoot + "\n";
		output += nonce + "\n";
		output += difficulty + "\n";
		output += blockHash + "\n";
		output += isValid + "\n";
		output += previousHashValid + "\n";
		output += transaction + "\n";
		output += "--------------------" + "\n";
		return output;
	}

	public void imprt(String input) {
		String[] atributes = input.split("\n");

		BLOCK = atributes[0];
		header = atributes[1];
		previousHash = atributes[2];
		timestamp = atributes[3];
		topRoot = atributes[4];
		nonce = Integer.parseInt(atributes[5]);
		difficulty = atributes[6];
		blockHash = atributes[7];
		isValid = Boolean.parseBoolean(atributes[8]);
		previousHashValid = Boolean.parseBoolean(atributes[9]);
		transaction = atributes[10];

		if (getBlockHash(header(), nonce) == null) {
			isValid = false;
			blockHash = getBlockHash(header(), nonce);
		}

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