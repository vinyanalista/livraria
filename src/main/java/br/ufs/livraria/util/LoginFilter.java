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

import br.ufs.livraria.bean.ControleAcessoFuncionario;
import br.ufs.livraria.bean.LoginInfo;
import br.ufs.livraria.modelo.Funcionario;

@WebFilter(urlPatterns = {
		"/funcionario/*",
})
public class LoginFilter implements Filter {

	@Inject
	private LoginInfo loginInfo;
	
	@Inject
	private ControleAcessoFuncionario controleAcessoFuncionario;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		if (!isLoginUri(httpRequest.getRequestURI()) && (!loginInfo.isLoggedIn() || !controleAcessoFuncionario.possuiPermissao((Funcionario) loginInfo.getUsuarioLogado(), httpRequest.getRequestURI()))) {
			HttpServletResponse httpResponse = (HttpServletResponse) response;
			StringBuilder uriLogin = new StringBuilder(httpRequest.getContextPath());
			uriLogin.append("/funcionario/login.jsf?faces-redirect=true");
			uriLogin.append("&urlRetorno=");
			StringBuilder uriRetorno = new StringBuilder(httpRequest.getRequestURI().substring(9));
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
		}
		chain.doFilter(httpRequest, response);
	}

	@Override
	public void destroy() {
	}
	
	private boolean isLoginUri(String uri) {
		return uri.equals("/livraria/funcionario/login.jsf");
	}
}
