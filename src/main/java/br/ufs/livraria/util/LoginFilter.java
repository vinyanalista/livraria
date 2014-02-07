package br.ufs.livraria.util;

import java.io.IOException;

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

@WebFilter(urlPatterns = {"/*"})
public class LoginFilter implements Filter {

	@Inject
	private LoginInfo loginInfo;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		if (false && !isLoginUri(httpRequest.getRequestURI()) && isPaginaFuncionario(httpRequest.getRequestURI()) && !loginInfo.isLoggedIn()) {
			HttpServletResponse httpResponse = (HttpServletResponse) response;
			StringBuilder uriLogin = new StringBuilder(httpRequest.getContextPath());
			uriLogin.append("/funcionario/login.jsf?faces-redirect=true");
			uriLogin.append("&urlRetorno=");
			uriLogin.append(httpResponse.encodeURL(httpRequest.getRequestURI().substring(9)));
			httpResponse.sendRedirect(httpResponse.encodeRedirectURL(uriLogin.toString()));
		}
		chain.doFilter(httpRequest, response);
	}

	@Override
	public void destroy() {
	}
	
	private boolean isPaginaFuncionario(String uri) {
		return uri.startsWith("/livraria/funcionario/");
	}
	
	private boolean isLoginUri(String uri) {
		return uri.equals("/livraria/funcionario/login.jsf");
	}
}
