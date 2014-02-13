package br.ufs.livraria.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import br.ufs.livraria.dao.LivroDAO;
import br.ufs.livraria.modelo.Livro;

public class LivroCapaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private LivroDAO livroDao;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			String idString = request.getParameter("id");
			int id;
			if (idString == null) {
				id = -1;
			} else {
				id = Integer.parseInt(request.getParameter("id"));
			}
			Livro livro = livroDao.buscar(id);
			OutputStream saida = response.getOutputStream();

			if ((livro == null) || (livro.getCapa() == null)) {
				String caminhoDaCapa;
				if ((livro != null) && (livro.getId() <= 16)) {
					caminhoDaCapa = "/resources/pt_BR/cliente/images/capas/" + livro.getId() + ".jpg";
				} else {
					caminhoDaCapa = "/resources/pt_BR/cliente/images/livro.jpg";
				}
				InputStream entrada = getServletContext().getResourceAsStream(
						caminhoDaCapa);
				IOUtils.copy(entrada, saida);
			} else {
				saida.write(livro.getCapa());
			}
			saida.close();
		} catch (Exception excecao) {
			excecao.printStackTrace();
			response.getWriter().close();
		}
	}

}