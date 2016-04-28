/**
 *
 * jerry - Common Java Functionality
 * Copyright (c) 2012-2016, Sandeep Gupta
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

import java.util.HashMap;
import java.util.Map;

import com.sangupta.jerry.constants.HttpMimeType;

/**
 *
 * @author sangupta
 *
 */
public abstract class MimeUtils implements HttpMimeType {

	/**
	 * Mappings between extension and MIME type
	 */
	private static final Map<String, String> EXTENSION_MIME_MAP = new HashMap<String, String>();

	static {
		// custom
		EXTENSION_MIME_MAP.put("pdf", PDF);
		EXTENSION_MIME_MAP.put("ps", POSTSCRIPT);
		EXTENSION_MIME_MAP.put("xls", XLS);
		EXTENSION_MIME_MAP.put("xlsx", XLSX);
		EXTENSION_MIME_MAP.put("ppt", PPT);
		EXTENSION_MIME_MAP.put("pptx", PPTX);
		EXTENSION_MIME_MAP.put("xps", XPS);

		// rss and all
		EXTENSION_MIME_MAP.put("atom", ATOM);
		EXTENSION_MIME_MAP.put("rdf", RDF);
		EXTENSION_MIME_MAP.put("rss", RSS);

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
		EXTENSION_MIME_MAP.put("ogg", OGG_VORBIS_AUDIO);
		EXTENSION_MIME_MAP.put("mp4", MP4_AUDIO);
		EXTENSION_MIME_MAP.put("mp3", MP3_AUDIO);
		EXTENSION_MIME_MAP.put("vorbis", VORBIS_AUDIO);
		EXTENSION_MIME_MAP.put("webm", WEBM_AUDIO);
		EXTENSION_MIME_MAP.put("aac", "audio/x-aac");
		EXTENSION_MIME_MAP.put("caf", "audio/x-caf");

		// image
		EXTENSION_MIME_MAP.put("gif", GIF_IMAGE);
		EXTENSION_MIME_MAP.put("jpeg", JPEG_IMAGE);
		EXTENSION_MIME_MAP.put("jpg", JPG_IMAGE);
		EXTENSION_MIME_MAP.put("png", PNG_IMAGE);
		EXTENSION_MIME_MAP.put("svg", SVG_IMAGE);
		EXTENSION_MIME_MAP.put("tiff", TIFF_IMAGE);

		// text
		EXTENSION_MIME_MAP.put("dart", "application/dart");
		EXTENSION_MIME_MAP.put("js", "application/javascript");
		EXTENSION_MIME_MAP.put("css", "text/css");
		EXTENSION_MIME_MAP.put("htm", "text/html");
		EXTENSION_MIME_MAP.put("html", "text/html");
		EXTENSION_MIME_MAP.put("rtf", "text/rtf");
		EXTENSION_MIME_MAP.put("txt", TEXT_PLAIN);
		EXTENSION_MIME_MAP.put("text", TEXT_PLAIN);

		// video
		EXTENSION_MIME_MAP.put("avi", "video/avi");
		EXTENSION_MIME_MAP.put("mpeg", "video/mpeg");
		EXTENSION_MIME_MAP.put("mpg", "video/mpeg");
		EXTENSION_MIME_MAP.put("quicktime", "video/quicktime");
		EXTENSION_MIME_MAP.put("qt", "video/quicktime");
		EXTENSION_MIME_MAP.put("flv", "video/x-flv");

		// code files - should be text/plain
		EXTENSION_MIME_MAP.put("java", TEXT_PLAIN);
		EXTENSION_MIME_MAP.put("h", TEXT_PLAIN);
		EXTENSION_MIME_MAP.put("c", TEXT_PLAIN);
		EXTENSION_MIME_MAP.put("cpp", TEXT_PLAIN);
		EXTENSION_MIME_MAP.put("m", TEXT_PLAIN);
		EXTENSION_MIME_MAP.put("py", TEXT_PLAIN);
		EXTENSION_MIME_MAP.put("pl", TEXT_PLAIN);
		EXTENSION_MIME_MAP.put("rb", TEXT_PLAIN);
		EXTENSION_MIME_MAP.put("asp", TEXT_PLAIN);
		EXTENSION_MIME_MAP.put("vb", TEXT_PLAIN);
		EXTENSION_MIME_MAP.put("bas", TEXT_PLAIN);
		EXTENSION_MIME_MAP.put("cs", TEXT_PLAIN);
		EXTENSION_MIME_MAP.put("pas", TEXT_PLAIN);
		EXTENSION_MIME_MAP.put("go", TEXT_PLAIN);
		EXTENSION_MIME_MAP.put("php", TEXT_PLAIN);
		EXTENSION_MIME_MAP.put("scala", TEXT_PLAIN);
		EXTENSION_MIME_MAP.put("asm", TEXT_PLAIN);
		EXTENSION_MIME_MAP.put("lsp", TEXT_PLAIN);

		// shell scripts
		EXTENSION_MIME_MAP.put("sh", TEXT_PLAIN);
		EXTENSION_MIME_MAP.put("zsh", TEXT_PLAIN);
		EXTENSION_MIME_MAP.put("bat", TEXT_PLAIN);
	}

	public static String getMimeTypeForFileExtension(String extension) {
		if(AssertUtils.isEmpty(extension)) {
			return BINARY;
		}

		// convert to lower case
		extension = extension.toLowerCase();

		// not found - we use default of octet/stream
		if(!EXTENSION_MIME_MAP.containsKey(extension)) {
			return BINARY;
		}

		return EXTENSION_MIME_MAP.get(extension);
	}

}
