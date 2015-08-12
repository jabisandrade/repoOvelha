 
package br.org.ovelha.acesso;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.gov.frameworkdemoiselle.internal.producer.HttpServletRequestProducer;
import br.gov.frameworkdemoiselle.internal.producer.HttpServletResponseProducer;
import br.gov.frameworkdemoiselle.util.Beans;
import br.org.ovelha.constant.PAGES;

public class FiltroAcesso implements Filter {

	@Override
	public void init(FilterConfig config) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		setDelegate(request, response);
		
		// Get the information from session attribute of user logado
		String logado = (String) ((HttpServletRequest)request).getSession().getAttribute("logado");
         
        // For the first application request there is no loginBean in the session so user needs to log in
        // For other requests loginBean is present but we need to check if user has logged in successfully		
		if (logado == null||!logado.equals("true")){
			
            String contextPath = ((HttpServletRequest)request).getContextPath();
            ((HttpServletResponse)response).sendRedirect(contextPath + PAGES.LOGIN);
		}

        chain.doFilter(request, response);
	}

	private void setDelegate(ServletRequest request, ServletResponse response) {
		if (request instanceof HttpServletRequest) {
			Beans.getReference(HttpServletRequestProducer.class).setDelegate((HttpServletRequest) request);
		}

		if (response instanceof HttpServletResponse) {
			Beans.getReference(HttpServletResponseProducer.class).setDelegate((HttpServletResponse) response);
		}
	}

	@Override
	public void destroy() {
	}
}
