package br.ufs.livraria.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import br.ufs.livraria.enumeration.AlgoritmoDeCriptografia;

public class Criptografia {

	// http://codare.net/2007/02/02/java-gerando-codigos-hash-md5-sha/

	private static String stringHexa(byte[] bytes) {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			int parteAlta = ((bytes[i] >> 4) & 0xf) << 4;
			int parteBaixa = bytes[i] & 0xf;
			if (parteAlta == 0)
				s.append('0');
			s.append(Integer.toHexString(parteAlta | parteBaixa));
		}
		return s.toString();
	}

	public static String gerarHash(String frase,
			AlgoritmoDeCriptografia algoritmo) {
		try {
			MessageDigest md = MessageDigest.getInstance(algoritmo.toString());
			md.update(frase.getBytes());
			return stringHexa(md.digest());
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}

}