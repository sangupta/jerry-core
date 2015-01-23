/**
 *
 * jerry - Common Java Functionality
 * Copyright (c) 2012-2015, Sandeep Gupta
 * 
 * http://sangupta.com/projects/jerry
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

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author sangupta
 *
 */
public class MimeUtils {

	/**
	 * Mappings between extension and MIME type
	 */
	private static final Map<String, String> EXTENSION_MIME_MAP = new HashMap<String, String>();
	
	static {
		// custom
		EXTENSION_MIME_MAP.put("pdf", "application/pdf");
		EXTENSION_MIME_MAP.put("ps", "application/postscript");
		EXTENSION_MIME_MAP.put("xls", "application/vnd.ms-excel");
		EXTENSION_MIME_MAP.put("xlsx", "application/vnd.ms-excel");
		EXTENSION_MIME_MAP.put("ppt", "application/vnd.ms-powerpoint");
		EXTENSION_MIME_MAP.put("pptx", "application/vnd.ms-powerpoint");
		EXTENSION_MIME_MAP.put("xps", "application/vnd.ms-xpsdocument");
		
		// rss and all
		EXTENSION_MIME_MAP.put("atom", "application/atom+xml");
		EXTENSION_MIME_MAP.put("rdf", "application/rdf+xml");
		EXTENSION_MIME_MAP.put("rss", "application/rss+xml");
		
		// fonts
		EXTENSION_MIME_MAP.put("woff", "aplication/font-woff");
		EXTENSION_MIME_MAP.put("ttf", "application/x-font-ttf");
		
		// text data files
		EXTENSION_MIME_MAP.put("json", "application/json");
		EXTENSION_MIME_MAP.put("xml", "application/xml");
		EXTENSION_MIME_MAP.put("csv", "text/csv");
		EXTENSION_MIME_MAP.put("latex", "application/x-latex");
		EXTENSION_MIME_MAP.put("markdown", "text/x-markdown");
		EXTENSION_MIME_MAP.put("md", "text/x-markdown");
		EXTENSION_MIME_MAP.put("kml", "application/vnd.google-earth.kml+xml");
		EXTENSION_MIME_MAP.put("xul", "application/vnd.mozilla.xul+xml");
		
		// compressed files
		EXTENSION_MIME_MAP.put("zip", "application/zip");
		EXTENSION_MIME_MAP.put("gz", "application/gzip");
		EXTENSION_MIME_MAP.put("7z", "application/x-7z-compressed");
		EXTENSION_MIME_MAP.put("crx", "application/x-chrome-extension");
		EXTENSION_MIME_MAP.put("rar", "application/x-rar-compressed");
		EXTENSION_MIME_MAP.put("swf", "application/x-shockwave-flash");
		EXTENSION_MIME_MAP.put("tar", "application/x-tar");
		EXTENSION_MIME_MAP.put("apk", "application/vnd.android.package-archive");
		
		// audio
		EXTENSION_MIME_MAP.put("ogg", "application/ogg");
		EXTENSION_MIME_MAP.put("mp4", "audio/mp4");
		EXTENSION_MIME_MAP.put("mp3", "audio/mpeg");
		EXTENSION_MIME_MAP.put("vorbis", "audio/vorbis");
		EXTENSION_MIME_MAP.put("webm", "audio/webm");
		EXTENSION_MIME_MAP.put("aac", "audio/x-aac");
		EXTENSION_MIME_MAP.put("caf", "audio/x-caf");
		
		// image
		EXTENSION_MIME_MAP.put("gif", "image/gif");
		EXTENSION_MIME_MAP.put("jpeg", "image/jpeg");
		EXTENSION_MIME_MAP.put("jpg", "image/jpg");
		EXTENSION_MIME_MAP.put("png", "image/png");
		EXTENSION_MIME_MAP.put("svg", "image/svg+xml");
		EXTENSION_MIME_MAP.put("tiff", "image/tiff");
		
		// text
		EXTENSION_MIME_MAP.put("dart", "application/dart");
		EXTENSION_MIME_MAP.put("js", "application/javascript");
		EXTENSION_MIME_MAP.put("css", "text/css");
		EXTENSION_MIME_MAP.put("htm", "text/html");
		EXTENSION_MIME_MAP.put("html", "text/html");
		EXTENSION_MIME_MAP.put("rtf", "text/rtf");
		EXTENSION_MIME_MAP.put("txt", "text/plain");
		EXTENSION_MIME_MAP.put("text", "text/plain");
		
		// video
		EXTENSION_MIME_MAP.put("avi", "video/avi");
		EXTENSION_MIME_MAP.put("mpeg", "video/mpeg");
		EXTENSION_MIME_MAP.put("mpg", "video/mpeg");
		EXTENSION_MIME_MAP.put("quicktime", "video/quicktime");
		EXTENSION_MIME_MAP.put("qt", "video/quicktime");
		EXTENSION_MIME_MAP.put("flv", "video/x-flv");
		
		// code files - should be text/plain
		EXTENSION_MIME_MAP.put("java", "text/plain");
		EXTENSION_MIME_MAP.put("h", "text/plain");
		EXTENSION_MIME_MAP.put("c", "text/plain");
		EXTENSION_MIME_MAP.put("cpp", "text/plain");
		EXTENSION_MIME_MAP.put("m", "text/plain");
		EXTENSION_MIME_MAP.put("py", "text/plain");
		EXTENSION_MIME_MAP.put("pl", "text/plain");
		EXTENSION_MIME_MAP.put("rb", "text/plain");
		EXTENSION_MIME_MAP.put("asp", "text/plain");
		EXTENSION_MIME_MAP.put("vb", "text/plain");
		EXTENSION_MIME_MAP.put("bas", "text/plain");
		EXTENSION_MIME_MAP.put("cs", "text/plain");
		EXTENSION_MIME_MAP.put("pas", "text/plain");
		EXTENSION_MIME_MAP.put("go", "text/plain");
		EXTENSION_MIME_MAP.put("php", "text/plain");
		EXTENSION_MIME_MAP.put("scala", "text/plain");
		EXTENSION_MIME_MAP.put("asm", "text/plain");
		EXTENSION_MIME_MAP.put("lsp", "text/plain");
		
		// shell scripts
		EXTENSION_MIME_MAP.put("sh", "text/plain");
		EXTENSION_MIME_MAP.put("zsh", "text/plain");
		EXTENSION_MIME_MAP.put("bat", "text/plain");
	}

	public static String getMimeTypeForFileExtension(String extension) {
		if(AssertUtils.isEmpty(extension)) {
			return "application/octet-stream";
		}
		
		// convert to lower case
		extension = extension.toLowerCase();
		
		// not found - we use default of octet/stream
		if(!EXTENSION_MIME_MAP.containsKey(extension)) {
			return "application/octet-stream";
		}
		
		return EXTENSION_MIME_MAP.get(extension);
	}

}
