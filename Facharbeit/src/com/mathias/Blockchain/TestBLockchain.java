package com.mathias.Blockchain;

public class TestBLockchain {
	public static void main(String[] args) {
		Padding p = new Padding("Hello World");
		System.out.println("Hello World");
		System.out.println(p.toBin());
		System.out.println(p.pad());
		System.out.println(p.addLen());
		Blockchain b1 = new Blockchain("0000000000000000000000000000000000000000","b1", "Trans5a5ction #01 \\n Transaction #02\\\\n ... Transaction #n \\\\n ");
		System.out.println("\n" + getNonce(b1, 1000000));
		
		
		Blockchain b2 = new Blockchain(b1.blockHash, "b2", "Transaction #01 \n Transaction #02\\n ... Transaction #n \\n ");
		System.out.println("\n" + getNonce(b2, 1000000));
		Blockchain b3 = new Blockchain(b2.blockHash,"b3", "fd3306fcca2c3372");
		System.out.println("\n" + getNonce(b3, 1000000));
		Blockchain b4 = new Blockchain(b3.blockHash,"b4", "fd3306fcca2c3372");
		System.out.println("\n" + getNonce(b4, 1000000));
		Blockchain b5 = new Blockchain(b4.blockHash,"b5", "fd3306fcca2c3372");
		System.out.println("\n" + getNonce(b5, 1000000));
		Blockchain b6 = new Blockchain(b5.blockHash,"b6", "fd3306fcca2c3372");
		System.out.println("\n" + getNonce(b6, 1000000));
		Blockchain b7 = new Blockchain(b6.blockHash,"b7", "fd3306fcca2c3372");
		System.out.println("\n" + getNonce(b7, 1000000));
		Blockchain b8 = new Blockchain(b7.blockHash,"b8", "fd3306fcca2c3372");
		System.out.println("\n" + getNonce(b8, 1000000));
		Blockchain b9 = new Blockchain(b8.blockHash,"b9", "fd330645456456456456a2c3372");
		System.out.println("\n" + getNonce(b9, 100000));
		Blockchain b10 = new Blockchain(b9.blockHash,"b10", "fd3306fcca2c3372");
		System.out.println("\n" + getNonce(b10, 1000000));
		

		System.out.println(b1.getBlock());
		System.out.println(b2.getBlock());
		System.out.println(b3.getBlock());
		System.out.println(b4.getBlock());
		System.out.println(b5.getBlock());
		System.out.println(b6.getBlock());
		System.out.println(b7.getBlock());
		System.out.println(b8.getBlock());
		System.out.println(b9.getBlock());
		System.out.println(b10.getBlock());


		
	}
	
	public static String getNonce(Blockchain b, long length) {
		boolean fond= false;
		for (long i = 0; i < length; i++) {
			if (b.getBlockHash(b.header(), i)!=null) {
				System.out.println(b.blockHash + " - " + i);
				fond = true;
				break;
			}
		}
		if(fond ==false)System.out.println("Nonce not found");
		return "";
	}


}
