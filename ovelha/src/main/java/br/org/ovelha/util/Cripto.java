package br.org.ovelha.util;

import java.security.MessageDigest;

public final class Cripto {

	public static String gerar(String senha) {

		try {
			MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
			byte messageDigest[] = algorithm.digest(senha.getBytes("UTF-8"));

			StringBuilder hexString = new StringBuilder();
			for (byte b : messageDigest) {
				hexString.append(String.format("%02X", 0xFF & b));
			}
			return hexString.toString();

		} catch (Exception e){
			System.out.println("Erro ao criptografar.");
			return "";
		}
	}
	
	public static void main(String[] args) {
		System.out.println("["+Cripto.gerar("JABIS.ANDRADE@GMAIL.COM")+"]");

	}

}
