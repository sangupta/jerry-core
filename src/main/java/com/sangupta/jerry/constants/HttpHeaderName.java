/**
 *
 * jerry - Common Java Functionality
 * Copyright (c) 2012-present, Sandeep Gupta
 *
 * https://sangupta.com/projects/jerry-core
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 		http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.sangupta.jerry.constants;

/**
 * Constants regarding various HTTP Header names. The values have been
 * taken from the following resources:
 *
 * <ul>
 * <li>http://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html</li>
 * <li>https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers</li>
 * <li>https://en.wikipedia.org/wiki/List_of_HTTP_header_fields</li>
 * </ul>
 *
 * @author sangupta
 *
 */
public interface HttpHeaderName {

    /**
     * Refer section 14.1 at <code>RFC-2616</code>
     */
    public static final String ACCEPT = "Accept";

    /**
     * Refer section 14.2 at <code>RFC-2616</code>
     */
    public static final String ACCEPT_CHARSET = "Accept-Charset";

    /**
     * Refer section 14.3 at <code>RFC-2616</code>
     */
    public static final String ACCEPT_ENCODING = "Accept-Encoding";

    /**
     * Refer section 14.4 at <code>RFC-2616</code>
     */
    public static final String ACCEPT_LANGUAGE = "Accept-Language";

    /**
     * Refer section 14.5 at <code>RFC-2616</code>
     */
    public static final String ACCEPT_RANGES = "Accept-Ranges";

    /**
     * Refer section 14.6 at <code>RFC-2616</code>
     */
    public static final String AGE = "Age";

    /**
     * Refer section 14.7 at <code>RFC-2616</code>
     */
    public static final String ALLOW = "Allow";

    /**
     * Refer section 14.8 at <code>RFC-2616</code>
     */
    public static final String AUTHORIZATION = "Authorization";

    /**
     * Refer section 14.9 at <code>RFC-2616</code>
     */
    public static final String CACHE_CONTROL = "Cache-Control";

    /**
     * Refer section 14.10 at <code>RFC-2616</code>
     */
    public static final String CONNECTION = "Connection";

    /**
     * Refer section 14.11 at <code>RFC-2616</code>
     */
    public static final String CONTENT_ENCODING = "Content-Encoding";

    /**
     * Refer section 14.12 at <code>RFC-2616</code>
     */
    public static final String CONTENT_LANGUAGE = "Content-Language";

    /**
     * Refer section 14.13 at <code>RFC-2616</code>
     */
    public static final String CONTENT_LENGTH = "Content-Length";

    /**
     * Refer section 14.14 at <code>RFC-2616</code>
     */
    public static final String CONTENT_LOCATION = "Content-Location";

    /**
     * Refer section 14.15 at <code>RFC-2616</code>
     */
    public static final String CONTENT_MD5 = "Content-MD5";

    /**
     * Refer section 14.16 at <code>RFC-2616</code>
     */
    public static final String CONTENT_RANGE = "Content-Range";

    /**
     * Refer section 14.17 at <code>RFC-2616</code>
     */
    public static final String CONTENT_TYPE = "Content-Type";

    /**
     * Refer section 14.18 at <code>RFC-2616</code>
     */
    public static final String DATE = "Date";

    /**
     * Refer section 14.19 at <code>RFC-2616</code>
     */
    public static final String ETAG = "ETag";

    /**
     * Refer section 14.20 at <code>RFC-2616</code>
     */
    public static final String EXPECT = "Expect";

    /**
     * Refer section 14.21 at <code>RFC-2616</code>
     */
    public static final String EXPIRES = "Expires";

    /**
     * Refer section 14.22 at <code>RFC-2616</code>
     */
    public static final String FROM = "From";

    /**
     * Refer section 14.23 at <code>RFC-2616</code>
     */
    public static final String HOST = "Host";

    /**
     * Refer section 14.24 at <code>RFC-2616</code>
     */
    public static final String IF_MATCH = "If-Match";

    /**
     * Refer section 14.25 at <code>RFC-2616</code>
     */
    public static final String IF_MODIFIED_SINCE = "If-Modified-Since";

    /**
     * Refer section 14.26 at <code>RFC-2616</code>
     */
    public static final String IF_NONE_MATCH = "If-None-Match";

    /**
     * Refer section 14.27 at <code>RFC-2616</code>
     */
    public static final String IF_RANGE = "If-Range";

    /**
     * Refer section 14.28 at <code>RFC-2616</code>
     */
    public static final String IF_UNMODIFIED_SINCE = "If-Unmodified-Since";

    /**
     * Refer section 14.29 at <code>RFC-2616</code>
     */
    public static final String LAST_MODIFIED = "Last-Modified";

    /**
     * Refer section 14.30 at <code>RFC-2616</code>
     */
    public static final String LOCATION = "Location";

    /**
     * Refer section 14.31 at <code>RFC-2616</code>
     */
    public static final String MAX_FORWARDS = "Max-Forwards";

    /**
     * Refer section 14.32 at <code>RFC-2616</code>
     */
    public static final String PRAGMA = "Pragma";

    public static final String P3P = "P3P";

    /**
     * Refer section 14.33 at <code>RFC-2616</code>
     */
    public static final String PROXY_AUTHENTICATE = "Proxy-Authenticate";

    /**
     * Refer section 14.34 at <code>RFC-2616</code>
     */
    public static final String PROXY_AUTHORIZATION = "Proxy-Authorization";

    /**
     * Refer section 14.35 at <code>RFC-2616</code>
     */
    public static final String RANGE = "Range";

    /**
     * Refer section 14.36 at <code>RFC-2616</code>
     */
    public static final String REFERER = "Referer";

    /**
     * Refer section 14.37 at <code>RFC-2616</code>
     */
    public static final String RETRY_AFTER = "Retry-After";

    /**
     * Refer section 14.38 at <code>RFC-2616</code>
     */
    public static final String SERVER = "Server";

    /**
     * Refer section 14.39 at <code>RFC-2616</code>
     */
    public static final String TE_TRANSFER_ENCODING = "TE";

    /**
     * Refer section 14.40 at <code>RFC-2616</code>
     */
    public static final String TRAILER = "Trailer";

    /**
     * Refer section 14.41 at <code>RFC-2616</code>
     */
    public static final String TRANSFER_ENCODING = "TE";

    /**
     * Refer https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/Set-Cookie
     */
    public static final String SET_COOKIE = "Set-Cookie";

    /**
     * Refer section 14.42 at <code>RFC-2616</code>
     */
    public static final String UPGRADE = "Upgrade";

    /**
     * Refer section 14.43 at <code>RFC-2616</code>
     */
    public static final String USER_AGENT = "User-Agent";

    /**
     * Refer section 14.44 at <code>RFC-2616</code>
     */
    public static final String VARY = "Vary";

    /**
     * Refer section 14.45 at <code>RFC-2616</code>
     */
    public static final String VIA = "Via";

    /**
     * Refer section 14.46 at <code>RFC-2616</code>
     */
    public static final String WARNING = "Warning";

    /**
     * Refer section 14.47 at <code>RFC-2616</code>
     */
    public static final String WWW_AUTHENTICATE = "WWW-Authenticate";

    /**
     * Refer https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/Clear-Site-Data
     * 
     * @since 4.0.0
     */
    public static final String CLEAR_SITE_DATA = "Clear-Site-Data";

    /**
     * Refer https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/Access-Control-Allow-Origin
     * 
     * @since 4.0.0
     */
    public static final String ACCESS_CONTROL_ALLOW_ORIGIN = "Access-Control-Allow-Origin";

    /**
     * Refer https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/Access-Control-Allow-Headers
     * 
     * @since 4.0.0
     */
    public static final String ACCESS_CONTROL_ALLOW_HEADERS = "Access-Control-Allow-Headers";

    /**
     * Refer https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/Access-Control-Allow-Methods
     * 
     * @since 4.0.0
     */
    public static final String ACCESS_CONTROL_ALLOW_METHODS = "Access-Control-Allow-Methods";

    /**
     * Refer https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/Access-Control-Expose-Headers
     * 
     * @since 4.0.0
     */
    public static final String ACCESS_CONTROL_EXPOSE_HEADERS = "Access-Control-Expose-Headers";

    /**
     * Refer https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/Access-Control-Max-Age
     * 
     * @since 4.0.0
     */
    public static final String ACCESS_CONTROL_MAX_AGE = "Access-Control-Max-Age";

    /**
     * Refer https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/Access-Control-Request-Headers
     * 
     * @since 4.0.0
     */
    public static final String ACCESS_CONTROL_REQUEST_HEADERS = "Access-Control-Request-Headers";

    /**
     * Refer https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/Access-Control-Request-Method
     * 
     * @since 4.0.0
     */
    public static final String ACCESS_CONTROL_REQUEST_METHOD = "Access-Control-Request-Method";

    /**
     * Refer https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/Origin
     * 
     * @since 4.0.0
     */
    public static final String ORIGIN = "Origin";
    
    /**
     * Refer https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/Timing-Allow-Origin
     * 
     * @since 4.0.0
     */
    public static final String TIMING_ALLOW_ORIGIN = "Timing-Allow-Origin";

    /**
     * Refer https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/Content-Disposition
     * 
     * @since 4.0.0
     */
    public static final String CONTENT_DISPOSITION = "Content-Disposition";
    
    /**
     * Refer https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/DNT
     * 
     * @since 4.0.0
     */
    public static final String DO_NOT_TRACK = "DNT";
    
    /**
     * Refer https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/Tk
     * 
     * @since 4.0.0
     */
    public static final String TRACKING_STATUS = "Tk";
    
    /**
     * Refer https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/Content-Security-Policy
     */
    public static final String CONTENT_SECURITY_POLICY = "Content-Security-Policy";
    
    /**
     * Refer https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/Expect-CT
     */
    public static final String EXPECT_CT = "Expect-CT";
    
    /**
     * Refer https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/Feature-Policy
     */
    public static final String FEATURE_POLICY = "Feature-Policy";
    
    /**
     * Refer https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/Strict-Transport-Security
     */
    public static final String STRICT_TRANSPORT_SECURITY = "Strict-Transport-Security";
    
    /**
     * Refer https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/Upgrade-Insecure-Requests
     */
    public static final String UPGRADE_INSECURE_REQUESTS = "Upgrade-Insecure-Requests";
    
    /**
     * Refer https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/X-Frame-Options
     */
    public static final String X_FRAME_OPTIONS = "X-Frame-Options";
    
    /**
     * Refer https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/X-XSS-Protection
     */
    public static final String X_XSS_PROTECTION = "X-XSS-Protection";
    
    /**
     * Refer https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/X-Content-Type-Options
     */
    public static final String X_CONTENT_TYPE_OPTIONS = "X-Content-Type-Options";
    
    /**
     * Refer https://en.wikipedia.org/wiki/List_of_HTTP_header_fields
     */
    public static final String X_FORWARDED_FOR = "X-Forwarded-For";
    
    /**
     * Refer https://en.wikipedia.org/wiki/List_of_HTTP_header_fields
     */
    public static final String X_FORWARDED_HOST = "X-Forwarded-Host";
    
    /**
     * Refer https://en.wikipedia.org/wiki/List_of_HTTP_header_fields
     */
    public static final String X_FORWARDED_PROTO = "X-Forwarded-Proto";
    
    /**
     * Refer https://en.wikipedia.org/wiki/List_of_HTTP_header_fields
     */
    public static final String X_CSRF_TOKEN = "X-Csrf-Token";
    
    /**
     * Refer https://en.wikipedia.org/wiki/List_of_HTTP_header_fields
     */
    public static final String X_REQUEST_ID = "X-Request-ID";
    
    /**
     * Refer https://en.wikipedia.org/wiki/List_of_HTTP_header_fields
     */
    public static final String X_CORRELATION_ID = "X-Correlation-ID";
    
    /**
     * Refer https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/Sec-Fetch-Site
     */
    public static final String SEC_FETCH_SITE = "Sec-Fetch-Site";
    
    /**
     * Refer https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/Sec-Fetch-Mode
     */
    public static final String SEC_FETCH_MODE = "Sec-Fetch-Mode";
    
    /**
     * Refer https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/Sec-Fetch-User
     */
    public static final String SEC_FETCH_USER = "Sec-Fetch-User";
    
    /**
     * Refer https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/Sec-Fetch-Dest
     */
    public static final String SEC_FETCH_DEST = "Sec-Fetch-Dest";
    
    /**
     * Refer https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/Link
     */
    public static final String LINK = "Link";
    
    /**
     * Refer https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/Server-Timing
     */
    public static final String SERVER_TIMING = "Server-Timing";
    
}
