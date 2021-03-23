package com.mathias.Blockchain;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TestBLockchain {
	static ArrayList<Blockchain> blck = new ArrayList<Blockchain>();

	public static void main(String[] args) {

		try (Scanner scn = new Scanner(System.in)) {
			while (1 < 2) {
				System.out.println("for Help enter Help");
				System.out.print("Enter Command: ");
				String input = scn.next();

				if (input.toUpperCase().substring(0, 2).equals("NE")) {
					System.out.print("Blockchain length (>=2): ");
					int quantity = scn.nextInt();
					newBlock(quantity);
				} else if (input.toUpperCase().substring(0, 2).equals("IM")) {
					System.out.print("File Name: ");
					String fileName = scn.next();
					imprt(fileName);
				} else if (input.toUpperCase().substring(0, 2).equals("PA")) {
					padding();
				} else if (input.toUpperCase().substring(0, 2).equals("GE")) {
					getText();
				} else if (input.toUpperCase().substring(0, 2).equals("AD")) {
					System.out.print("Block Name: ");
					String blockName = scn.next();
					System.out.println("Transactions: ");
					String transact = scn.next();
					transact += scn.nextLine();
					addBlock(blockName, transact);
				} else if (input.toUpperCase().substring(0, 2).equals("HE")) {
					System.out.println(
							"Possible Commands: NewBlockchain, AddBlock, GetBlock, Import, Export, Padding, Exit");
				} else if (input.toUpperCase().substring(0, 2).equals("CL")) {
					clear();
				} else if (input.toUpperCase().substring(0, 3).equals("EXP")) {
					System.out.print("File Name: ");
					String fileName = scn.next();
					exprt(fileName);
				} else if (input.toUpperCase().substring(0, 3).equals("EXI")) {
					System.exit(0);
				}
			}
		}
	}

	public static void newBlock(int len) {

		Blockchain b1 = new Blockchain("0000000000000000000000000000000000000000", "b1",
				"Trans5a5ction #01 \\n Transaction #02\\\\n ... Transaction #n \\\\n ");
		System.out.println("\n" + getNonce(b1, 1000000));

		Blockchain b2 = new Blockchain(b1.blockHash, "b2",
				"Transaction #01 \n Transaction #02\\n ... Transaction #n \\n ");
		System.out.println("\n" + getNonce(b2, 1000000));

		// Automatsierte List Version
		blck.add(new Blockchain(b2.blockHash, "b3", randomString()));
		System.out.println("\n" + getNonce(blck.get(0), 1000000));
		for (int i = 0; i < len - 2; i++) {
			blck.add(new Blockchain(blck.get(i).blockHash, "b" + (i + 4), randomString()));
			System.out.println("\n" + getNonce(blck.get(i + 1), 1000000));
		}

		/*System.out.println(b1.getBlock());
		System.out.println(b2.getBlock());

		// Automatisierte List Version
		for (int i = 0; i < len - 2; i++) {
			System.out.println(blck.get(i).getBlock());
		}*/

	}

	public static void padding() {
		Padding p = new Padding("Hello World");
		System.out.println("Hello World");
		System.out.println(p.toBin());
		System.out.println(p.pad());
		System.out.println(p.addLen() + "\n");
	}

	public static void clear() {
		blck.clear();
	}

	public static void imprt(String fileName) {
		try {
			FileReader fr = new FileReader(fileName + ".txt");
			BufferedReader br = new BufferedReader(fr);
			String line;
			int t = 0;
			while ((line = br.readLine()) != null) {
				String b = line + "\n";
				for (int i = 0; i < 10; i++) {
					b += br.readLine() + "\n";
				}
				blck.add(new Blockchain("0000", "", ""));
				blck.get(blck.size() - 1).imprt(b);

				if (t == 1) {
					if (blck.get(blck.size() - 2).isValid == false) {
						blck.get(blck.size() - 1).setValid(false);
					}
				}
				t = 1;
				line = br.readLine();
			}

			br.close();
		} catch (IOException e) {
			System.out.println("No File With This Name");
		}
	}

	public static void exprt(String fileName) {
		try {
			FileWriter fw = new FileWriter(fileName + ".txt");
			BufferedWriter bw = new BufferedWriter(fw);
			String out = "";
			System.out.println(blck.size());
			for (int i = 0; i < blck.size(); i++) {
				out += blck.get(i).saveBlock();
			}

			bw.write(out);
			bw.close();
		} catch (IOException e) {
			System.out.println("please make sure that your file name does only contain numbers or letters");
		}
	}

	public static void addBlock(String nam, String tras) {
		blck.add(new Blockchain(blck.get(blck.size() - 2).blockHash, nam, tras));
		System.out.println("\n" + getNonce(blck.get(blck.size() - 1), 1000000));
	}

	public static void getText() {
		for (int i = 0; i < blck.size(); i++) {
			System.out.println(blck.get(i).getBlock());
		}
	}

	public static String getNonce(Blockchain b, long length) {
		boolean fond = false;
		for (long i = 0; i < length; i++) {
			if (b.getBlockHash(b.header(), i) != null) {
				System.out.println(b.blockHash + " - " + i);
				fond = true;
				break;
			}
		}
		if (fond == false)
			System.out.println("Nonce not found");
		return "";
	}

	public static String randomString() {
		String s = "";

		for (int i = 0; i < 256; i++) {
			int temp = (int) (Math.random() * 128);
			if (temp < 31) {
				temp += 31;
			}
			s += (char) (temp);
		}

		return s;
	}

}