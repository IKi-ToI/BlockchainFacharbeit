package com.mathias.Blockchain;

public class Padding {
	String input;
	String output = "";
	String length;

	public Padding(String inpt) {
		input = inpt;
	}

	public String toBin() {
		String out = "";
		int ascii[] = new int[input.length()];

		for (int i = 0; i < input.length(); i++) {
			out += (int) input.charAt(i) + ", ";
			ascii[i] = (int) input.charAt(i);
		}
		out += "\n";

		for (int i = 0; i < ascii.length; i++) {
			String s = Integer.toBinaryString(ascii[i]);
			s = "00000000".substring(0, 8 - s.length()) + s;
			out += s + ", ";
			output += s;

		}
		length = output;
		return out;
	}

	public String pad() {
		String out = output;
		while (out.length() < (512 - 64)) {
			out += "0";
		}
		output = out;
		return out;
	}

	public String addLen() {
		int len = length.length();
		String lenBin = Integer.toBinaryString(len);
		String zeros = "";
		for (int i = 0; i < 64; i++) {
			zeros += "0";
		}
		lenBin = zeros.substring(0, 64 - lenBin.length()) + lenBin;
		output += lenBin;
		return lenBin + "\n" + output;
	}

}