package com.bank.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;

public class JwtFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String authHeader = req.getHeader("authorisation");

		if ("OPTION".equals(req.getMethod())) {
			res.setStatus(200);
			chain.doFilter(request, response);
		}

		else {

			if (authHeader == null || !authHeader.startsWith("Bearer ")) {
				throw new ServletException("Missing or invalid header");
			}

			final String token = authHeader.substring(7);

			try {

				Claims claim = Jwts.parser().setSigningKey("venom").parseClaimsJws(token).getBody();
				req.setAttribute(authHeader, token);
			} catch (SignatureException e) {
				throw new ServletException("Invalid token");
			}

			chain.doFilter(request, response);

		}

	}

}
