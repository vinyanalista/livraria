package br.ufs.livraria.enumeration;

public enum AlgoritmoDeCriptografia {
	MD5 {
		@Override
		public String toString() {
			return "MD5";
		}
	},
	SHA1 {
		@Override
		public String toString() {
			return "SHA-1";
		}
	},
	SHA256 {
		@Override
		public String toString() {
			return "SHA-256";
		}
	}
}