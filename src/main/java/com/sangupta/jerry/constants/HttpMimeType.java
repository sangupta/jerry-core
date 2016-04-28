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


package com.sangupta.jerry.constants;

/**
 * Constants regarding various HTTP MIME codes. The basic values have been copied from
 * http://en.wikipedia.org/wiki/Internet_media_type
 *
 * Additional codes have been copied from the Wikipedia page at,
 * http://www.iana.org/assignments/media-types/media-types.xhtml
 *
 * @author sangupta
 *
 */
public interface HttpMimeType {

	// Image

	public static final String GIF_IMAGE = "image/gif";

	public static final String PNG_IMAGE = "image/png";

	public static final String JPEG_IMAGE = "image/jpeg";

	public static final String JPG_IMAGE = "image/jpg";

	public static final String SVG_IMAGE = "image/svg+xml";

	public static final String TIFF_IMAGE = "image/tiff";

	// text formats

	public static final String TEXT_PLAIN = "text/plain";

	public static final String CSV = "text/csv";

	public static final String CSS = "text/css";

	public static final String HTML = "text/html";

	public static final String JAVASCRIPT = "text/javascript";

	public static final String RTF = "text/rtf";

	public static final String XML = "text/xml";

	// document formats

	public static final String JSON = "application/json";

	public static final String ATOM = "application/atom+xml";

	public static final String PDF = "application/pdf";

	public static final String POSTSCRIPT = "application/postscript";

	public static final String XLS = "application/vnd.ms-excel";

	public static final String XLSX = "application/vnd.ms-excel";

	public static final String PPT = "application/vnd.ms-powerpoint";

	public static final String PPTX = "application/vnd.ms-powerpoint";

	public static final String XPS = "application/vnd.ms-xpsdocument";

	// binary

	public static final String BINARY = "application/octet-stream";

	// music ones

	public static final String MP4_AUDIO = "audio/mp4";

	public static final String MP3_AUDIO = "audio/mpeg";

	public static final String OGG_VORBIS_AUDIO = "audio/ogg";

	public static final String OPUS_AUDIO = "audio/opus";

	public static final String VORBIS_AUDIO = "audio/vorbis";

	public static final String WEBM_AUDIO = "audio/webm";

	// video

	public static final String MPEG_VIDEO = "video/mpeg";

	public static final String MP4_VIDEO = "video/mp4";

	public static final String OGG_THEORA_VIDEO = "video/ogg";

	public static final String QUICKTIME_VIDEO = "video/quicktime";

	public static final String WEBM_VIDEO = "video/webm";

	// feeds

	public static final String RDF = "application/rdf+xml";

	public static final String RSS = "application/rss+xml";

}
