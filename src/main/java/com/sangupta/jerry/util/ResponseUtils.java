/**
 *
 * jerry - Common Java Functionality
 * Copyright (c) 2012-2017, Sandeep Gupta
 *
 * http://sangupta.com/projects/jerry-core
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


package com.sangupta.jerry.util;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sangupta.jerry.constants.HttpStatusCode;

/**
 * Utility methods to work with {@link HttpServletResponse} object.
 *
 * @author sangupta
 *
 */
public abstract class ResponseUtils {

	/**
	 * Write the given response in UTF-8 encoding to the given
	 * {@link HttpServletResponse} object.
	 *
	 * @param response
	 *            the {@link HttpServletResponse} to write data to
	 *
	 * @param data
	 *            the data that needs to be written to the stream
	 *
	 * @param mimeType
	 *            the MIME type of the data being written
	 *
	 * @throws IOException
	 *             if something fails while writing to
	 *             {@link HttpServletResponse}.
	 *
	 * @throws IllegalArgumentException
	 *             if {@link HttpServletResponse} object is <code>null</code>,
	 *             or if <code>data</code> is <code>null</code>, or if
	 *             <code>mimeType</code> is <code>null</code>
	 *
	 */
	public static void sendResponse(HttpServletResponse response, String data, String mimeType) throws IOException {
		if(response == null) {
			throw new IllegalArgumentException("HttpServletResponse cannot be null");
		}
		
		if(data == null) {
			throw new IllegalArgumentException("data cannot be null");
		}
		
		if(mimeType == null) {
			throw new IllegalArgumentException("mimeType cannot be null");
		}
		
		byte[] bytes = data.getBytes("UTF-8");
		response.setContentType(mimeType + "; charset=UTF-8");
		response.setStatus(HttpStatusCode.OK);
		response.setContentLength(bytes.length);
		response.setCharacterEncoding("UTF-8");
		response.getOutputStream().write(bytes);
	}

	/**
	 * Write the given byte array as response to the given
	 * {@link HttpServletResponse} object
	 *
	 * @param response
	 *            the {@link HttpServletResponse} to write data to
	 *
	 * @param bytes
	 *            the bytes that need to be sent
	 *
	 * @param mimeType
	 *            the MIME type of the data being written
	 *
	 * @throws IOException
	 *             if something fails while writing to
	 *             {@link HttpServletResponse}.
	 *
	 * @throws IllegalArgumentException
	 *             if {@link HttpServletResponse} object is <code>null</code>,
	 *             or if <code>bytes</code> is <code>null</code>, or if
	 *             <code>mimeType</code> is <code>null</code>
	 */
	public static void sendResponse(HttpServletResponse response, byte[] bytes, String mimeType) throws IOException {
		if(response == null) {
			throw new IllegalArgumentException("HttpServletResponse cannot be null");
		}
		
		if(bytes == null) {
			throw new IllegalArgumentException("bytes cannot be null");
		}
		
		if(mimeType == null) {
			throw new IllegalArgumentException("mimeType cannot be null");
		}
		
		response.setContentType(mimeType + "; charset=UTF-8");
		response.setStatus(HttpStatusCode.OK);
		response.setContentLength(bytes.length);
		response.setCharacterEncoding("UTF-8");
		response.getOutputStream().write(bytes);
	}

	/**
	 * Push the given data as a file to the client browser for downloading. The
	 * response is written in such a way so that the client browser displays a
	 * dailog for downloading the file.
	 *
	 * @param response
	 *            the {@link HttpServletResponse} object to write to
	 *
	 * @param data
	 *            the data that needs to be sent to client
	 *
	 * @param fileName
	 *            the filename to be used by browsers when displaying 'Save As'
	 *            dialog
	 *
	 * @param mimeType
	 *            the MIME type of the data being written
	 *
	 * @throws IOException
	 *             if something fails when writing to
	 *             {@link HttpServletResponse} object
	 *
	 * @throws IllegalArgumentException
	 *             if {@link HttpServletResponse} object is <code>null</code>,
	 *             or if <code>data</code> is <code>null</code>, or if
	 *             <code>mimeType</code> is <code>null</code>, or if
	 *             <code>fileName</code> is <code>null</code>
	 *
	 */
	public static void pushForUserDownload(HttpServletResponse response, String data, String fileName, String mimeType) throws IOException {
		if(response == null) {
			throw new IllegalArgumentException("HttpServletResponse cannot be null");
		}
		
		if(data == null) {
			throw new IllegalArgumentException("data cannot be null");
		}
		
		if(fileName == null) {
			throw new IllegalArgumentException("fileName cannot be null");
		}
		
		if(mimeType == null) {
			throw new IllegalArgumentException("mimeType cannot be null");
		}
		
		setOnlyDownload(response, fileName);

		response.setContentType(mimeType + "; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setStatus(HttpStatusCode.OK);

		ServletOutputStream stream = response.getOutputStream();
		byte[] bytes = data.getBytes("UTF-8");
		response.setContentLength(bytes.length);
		stream.write(bytes);
		stream.flush();
		stream.close();
	}

	/**
	 * Write the given byte array to the {@link HttpServletResponse} object
	 * asking the client browser to present a dialog to save the file. The MIME
	 * type used is <code>application/octet-stream</code> to allow for binary
	 * file downloads.
	 *
	 * @param response
	 *            the {@link HttpServletResponse} object to write to
	 *
	 * @param bytes
	 *            the bytes to write
	 *
	 * @param fileName
	 *            the file name to be presented to client browser
	 *
	 * @throws IOException
	 *             if something fails when writing to
	 *             {@link HttpServletResponse} object
	 *
	 * @throws IllegalArgumentException
	 *             if {@link HttpServletResponse} object is <code>null</code>,
	 *             or if <code>bytes</code> is <code>null</code>, or if
	 *             <code>fileName</code> is <code>null</code>
	 *
	 */
	public static void pushForUserDownload(HttpServletResponse response, byte[] bytes, String fileName) throws IOException {
		if(response == null) {
			throw new IllegalArgumentException("HttpServletResponse cannot be null");
		}
		
		if(bytes == null) {
			throw new IllegalArgumentException("data cannot be null");
		}
		
		if(fileName == null) {
			throw new IllegalArgumentException("fileName cannot be null");
		}
		
		setOnlyDownload(response, fileName);

		response.setContentType("application/octet-stream");
		response.setCharacterEncoding("UTF-8");
		response.setStatus(HttpStatusCode.OK);

		ServletOutputStream stream = response.getOutputStream();
		response.setContentLength(bytes.length);
		stream.write(bytes);
		stream.flush();
		stream.close();
	}

	/**
	 * Make sure that IE8+ do not sniff for MIME when already specified in
	 * response.
	 *
	 * Refer to
	 * http://blogs.msdn.com/b/ie/archive/2008/07/02/ie8-security-part-vcomprehensive-protection.aspx for more details.
	 *
	 * @param repsonse
	 *            the {@link HttpServletResponse} object to use
	 *
	 * @throws NullPointerException
	 *             if {@link HttpServletResponse} object is <code>null</code>
	 *
	 */
	public static void sendNoSniff(HttpServletResponse repsonse) {
		repsonse.addHeader("X-Content-Type-Options", "nosniff");
	}

	/**
	 * Instruct the response to only allow file download, and not let it open
	 * directly in the client browser.
	 *
	 * Refer to
	 * http://blogs.msdn.com/b/ie/archive/2008/07/02/ie8-security-part-vcomprehensive-protection.aspx for more details.
	 *
	 * @param response
	 *            the {@link HttpServletResponse} object to use
	 *
	 * @param fileName
	 *            the fileName to present to client browser for allowing
	 *            download of file
	 *
	 * @throws NullPointerException
	 *             if {@link HttpServletResponse} object is <code>null</code>
	 *
	 */
	public static void setOnlyDownload(HttpServletResponse response, String fileName) {
		response.addHeader("X-Download-Options", "noopen");
		response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
	}

	/**
	 * Set cache headers to one month from today.
	 *
	 * @param response
	 *            the {@link HttpServletResponse} object
	 */
	public static void setCacheHeaders(HttpServletResponse response) {
		setCacheHeaders(response, DateUtils.ONE_MONTH);
	}

	/**
	 * Set cache headers to given time from today.
	 *
	 * @param response
	 *            the {@link HttpServletResponse} object
	 *
	 * @param ageInMillis
	 *            the age in milliseconds
	 */
	public static void setCacheHeaders(HttpServletResponse response, long ageInMillis) {
		response.addDateHeader("Expires", System.currentTimeMillis() + ageInMillis);
		response.addHeader("Cache-Control", "public, max-age=" + (ageInMillis / DateUtils.ONE_SECOND));
	}

	/**
	 * Construct the URL prefixed with the context path in which the app is
	 * deployed
	 *
	 * @param request
	 *            the {@link HttpServletRequest} object
	 *
	 * @param url
	 *            the URL to be prefixed
	 *
	 * @return the constructed URL
	 * 
	 * @throws NullPointerException
	 *             if <code>request</code> is <code>null</code>
	 */
	public static String getUrlWithContext(HttpServletRequest request, String url) {
		return UriUtils.addWebPaths(request.getContextPath(), url);
	}

}
