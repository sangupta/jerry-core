package com.sangupta.jerry.http.filter;

import java.io.IOException;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sangupta.jerry.constants.HttpHeaderName;
import com.sangupta.jerry.constants.HttpStatusCode;
import com.sangupta.jerry.entity.UserAwarePrincipal;
import com.sangupta.jerry.security.SecurityContext;
import com.sangupta.jerry.util.AssertUtils;
import com.sangupta.jerry.util.RequestUtils;
import com.sangupta.jerry.util.StringUtils;

public abstract class AbstractAuthTokenSecurityFilter implements Filter {

	/**
     * My personal instance logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractAuthTokenSecurityFilter.class);

    /**
     * Urls that need no security check
     */
    protected Set<String> passThroughUrls;
    
    protected boolean filterEnabled = true;
    
    protected String tokenPrefix;
    
    protected boolean useWildCardMatch = false;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (!this.filterEnabled) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        
        final String path = RequestUtils.extractUri(request);
        LOGGER.trace("Request received for path: {}", path);

        // clear any principal in request as of now
        SecurityContext.clearPrincipal();

        // try and read authorization header
        String authToken = request.getHeader(HttpHeaderName.AUTHORIZATION);
        final boolean isPassThroughUrl = this.isPassThroughUrl(path);
        
        // check if we have the token or not
        if (AssertUtils.isEmpty(authToken)) {
            if(isPassThroughUrl) {
                // run the filter chain
                filterChain.doFilter(request, response);
                return;
            }
            
            response.sendError(HttpStatusCode.UNAUTHORIZED, "An authenticated user is required");
            return;
        }

        // we have the token - let's massage as needed
        if (AssertUtils.isNotEmpty(this.tokenPrefix) && authToken.startsWith(this.tokenPrefix)) {
            authToken = authToken.substring(this.tokenPrefix.length());
        }

        // set it in request
        SecurityContext.setUserToken(authToken);

        // fetch the profile - this will also validate the token
        UserAwarePrincipal principal = this.getUserProfileForToken(authToken);

        if (principal == null) {
            // token must be invalid
            response.sendError(HttpStatusCode.UNAUTHORIZED, "A valid authentication token is required.");
            return;
        }
        
        // every thing is good to go
        LOGGER.debug("User found in auth header: {}", principal);
        SecurityContext.setPrincipal(principal);

        // run the filter chain
        filterChain.doFilter(request, response);

        // clear principal
        SecurityContext.clearPrincipal();
    }
    
    protected boolean isPassThroughUrl(String pathPrefix) {
    	if(!this.useWildCardMatch) {
    		return this.passThroughUrls == null ? false : this.passThroughUrls.contains(pathPrefix);
    	}
    	
    	for(String urlPattern : this.passThroughUrls) {
    		if(StringUtils.wildcardMatch(pathPrefix, urlPattern)) {
    			return true;
    		}
    	}
    	
    	return false;
    }
    
    protected void setFilterEnabled(boolean enabled) {
    	this.filterEnabled = enabled;
    }
    
    protected void setTokenPrefix(String prefix) {
    	this.tokenPrefix = prefix;
    }
    
    protected void setUseWildCardMatch(boolean useWildCardMatch) {
    	this.useWildCardMatch = useWildCardMatch;
    }
    
    protected void setPassThroughUrls(Set<String> passThroughUrls) {
    	this.passThroughUrls = passThroughUrls;
    }

    protected abstract UserAwarePrincipal getUserProfileForToken(String authToken);
    
}
