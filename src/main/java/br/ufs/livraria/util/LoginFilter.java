package br.ufs.livraria.util;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Enumeration;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufs.livraria.bean.LoginInfo;
import br.ufs.livraria.modelo.Cliente;
import br.ufs.livraria.modelo.Funcionario;
import br.ufs.livraria.modelo.Usuario;

@WebFilter(urlPatterns = {
		"/*",
})
public class LoginFilter implements Filter {

	@Inject
	private LoginInfo loginInfo;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		Usuario usuarioLogado = loginInfo.getUsuarioLogado();
		boolean isResourceUrl = httpRequest.getRequestURI().startsWith(httpRequest.getContextPath() + "/javax.faces.resource");
		boolean isUriFuncionario = httpRequest.getRequestURI().startsWith(httpRequest.getContextPath() + "/funcionario");
		if (!isResourceUrl && ((isUriFuncionario && usuarioLogado instanceof Cliente) || (!isUriFuncionario && usuarioLogado instanceof Funcionario))) {
			loginInfo.setUsuarioLogado(null);
			httpRequest.getSession().invalidate();
			if (!isUriFuncionario) {
				chain.doFilter(httpRequest, response);
				return;
			}
		}
		if (!isLoginUri(httpRequest.getRequestURI()) && !isResourceUrl && isUriFuncionario
				&& !(loginInfo.isLoggedIn() || usuarioLogado instanceof Funcionario)) {
			HttpServletResponse httpResponse = (HttpServletResponse) response;
			StringBuilder uriLogin = new StringBuilder(httpRequest.getContextPath());
			uriLogin.append("/funcionario/login.jsf?faces-redirect=true");
			uriLogin.append("&urlRetorno=");
			StringBuilder uriRetorno = new StringBuilder();
			if (httpRequest.getRequestURI().substring(9).endsWith(".jsf")) {
				uriRetorno.append(httpRequest.getRequestURI().substring(9));
			} else {
				uriRetorno.append("index.jsf");
			}
			uriRetorno.append("?faces-redirect=true");
			
			Enumeration<String> parameterNames = httpRequest.getParameterNames();
			if (parameterNames.hasMoreElements()) {
				while (parameterNames.hasMoreElements()) {
					String parameter = parameterNames.nextElement();
					uriRetorno.append("&");
					uriRetorno.append(parameter);
					uriRetorno.append("=");
					uriRetorno.append(httpRequest.getParameter(parameter));
				}
			}
			
			uriLogin.append(URLEncoder.encode(uriRetorno.toString(), "UTF-8"));
			httpResponse.sendRedirect(httpResponse.encodeRedirectURL(uriLogin.toString()));
		} else {
			chain.doFilter(httpRequest, response);
		}
	}

	@Override
	public void destroy() {
	}
	
	private boolean isLoginUri(String uri) {
		return uri.contains("login.jsf");
	}
}
