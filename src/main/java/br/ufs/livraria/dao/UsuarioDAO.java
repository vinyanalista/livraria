package br.ufs.livraria.dao;

import javax.ejb.Stateless;

import org.apache.commons.codec.digest.DigestUtils;

import br.ufs.livraria.modelo.Usuario;

@Stateless
public class UsuarioDAO extends DAO<Usuario> {

	private static final long serialVersionUID = 1L;
	
	public UsuarioDAO() {
		super(Usuario.class);
	}
	
	public Usuario getByEmail(String email) {
		return entityManager.createQuery("select u from Usuario u where u.email = :email", Usuario.class)
				.setParameter("email", email)
				.getSingleResult();
	}
	
	public boolean verificarSenha(Usuario usuario, String senha) {
		String hash = DigestUtils.sha1Hex(senha);
		return hash.equals(usuario.getSenha());
	}
	
	public boolean verificarCredenciais(String email, String senha) {
		return verificarSenha(getByEmail(email), senha);
	}
}
