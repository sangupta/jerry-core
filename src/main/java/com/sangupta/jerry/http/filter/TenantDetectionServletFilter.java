package com.sangupta.jerry.http.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.sangupta.jerry.http.TenantDetectionStrategy;
import com.sangupta.jerry.security.SecurityContext;
import com.sangupta.jerry.util.AssertUtils;

/**
 * Servlet filter that helps identify the incoming tenant.
 * 
 * @author sangupta
 *
 */
public class TenantDetectionServletFilter implements Filter {

	protected TenantDetectionStrategy tenantDetectionStrategy = TenantDetectionStrategy.REQUEST_PARAM;

	protected String tenancyRequestParam = "tenantID";

	protected String tenancyHttpHeader = "X-Tenant-ID";

	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest hsr = (HttpServletRequest) request;

		// clear existing tenant
		SecurityContext.clear();

		// check if we have a strategy to find out tenant
		if (this.tenantDetectionStrategy == null) {
			// nothing to do
			chain.doFilter(request, response);
			return;
		}

		// detect tenant based on strategy
		switch (this.tenantDetectionStrategy) {
		case PATH_VARIABLE:
			break;

		case REQUEST_HTTP_HEADER:
			this.setTenantFromHttpHeader(hsr);
			break;

		case REQUEST_PARAM:
			this.setTenantFromRequestParam(hsr);
			break;

		case USER_AUTH:
			break;

		default:
			break;
		}

		// complete the request
		try {
			chain.doFilter(request, response);
		} finally {
			// clean the tenant again
			SecurityContext.clear();
		}
	}

	/**
	 * Find the tenant using chosen request http header.
	 * 
	 * @param hsr the {@link HttpServletRequest}
	 */
	protected void setTenantFromHttpHeader(HttpServletRequest hsr) {
		String value = hsr.getHeader(this.tenancyHttpHeader);
		if (AssertUtils.isEmpty(value)) {
			// do nothing
			return;
		}

		SecurityContext.setTenant(value);
	}

	/**
	 * Find the tenant using chosen request parameter.
	 * 
	 * @param hsr the {@link HttpServletRequest}
	 */
	protected void setTenantFromRequestParam(HttpServletRequest hsr) {
		String value = hsr.getParameter(this.tenancyRequestParam);
		if (AssertUtils.isEmpty(value)) {
			// do nothing
			return;
		}

		SecurityContext.setTenant(value);
	}

	@Override
	public void destroy() {

	}
	
	/**
	 * 
	 * @param tenantDetectionStrategy
	 */
	public void setTenantDetectionStrategy(TenantDetectionStrategy tenantDetectionStrategy) {
		this.tenantDetectionStrategy = tenantDetectionStrategy;
	}
	
	/**
	 * 
	 * @param tenancyHttpHeader
	 */
	public void setTenancyHttpHeader(String tenancyHttpHeader) {
		this.tenancyHttpHeader = tenancyHttpHeader;
	}
	
	/**
	 * 
	 * @param tenancyRequestParam
	 */
	public void setTenancyRequestParam(String tenancyRequestParam) {
		this.tenancyRequestParam = tenancyRequestParam;
	}

}
