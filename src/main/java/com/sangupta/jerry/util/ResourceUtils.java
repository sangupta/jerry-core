package com.sangupta.jerry.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.apache.commons.io.IOUtils;

/**
 * Utility methods to read resources from the classpath, or from the JAR.
 * 
 * @author sangupta
 *
 */
public class ResourceUtils {
	
	/**
	 * Read the file at given {@link Path} as a {@link String} in default
	 * encoding.
	 * 
	 * @param path
	 *            the {@link Path} to read file from
	 * 
	 * @return file contents as {@link String} or <code>null</code> if the file
	 *         does not exist, or results in error during read operation.
	 * 
	 */
	public static String getResourceAsString(Path path) {
		byte[] bytes = getResourceQuietly(path);
		if(bytes == null) {
			return null;
		}
		
		return new String(bytes);
	}
	
	public static String getResourceAsString(Path path, Charset charset) {
		byte[] bytes = getResourceQuietly(path);
		if(bytes == null) {
			return null;
		}
		
		return new String(bytes, charset);
	}
	
	/**
	 * Read the file at given path as a {@link String} in default encoding. The
	 * path is specified from the root of the application, for example: to read
	 * a file from <code>src/main/resources/sangupta/bio.txt</code> call this
	 * method using
	 * <code>ResourceUtils.getResourceAsString("sangupta/bio.txt")</code>
	 * 
	 * @param path
	 *            the file path to read from
	 * 
	 * @return file contents as {@link String} or <code>null</code> if the file
	 *         does not exist, or results in error during read operation.
	 */
	public static String getResourceAsString(String path) {
		byte[] bytes = getResourceQuietly(path);
		if(bytes == null) {
			return null;
		}
		
		return new String(bytes);
	}
	
	public static String getResourceAsString(String path, Charset charset) {
		byte[] bytes = getResourceQuietly(path);
		if(bytes == null) {
			return null;
		}
		
		return new String(bytes, charset);
	}
	
	public static byte[] getResourceQuietly(String path) {
		try {
			return ResourceUtils.getResource(path);
		} catch(IOException e) {
			return null;
		}
	}
	
	public static byte[] getResourceQuietly(Path path) {
		try {
			return ResourceUtils.getResource(path);
		} catch(IOException e) {
			return null;
		}
	}

	public static byte[] getResource(String path) throws IOException {
		if(AssertUtils.isEmpty(path)) {
			return null;
		}
		
		InputStream stream = ResourceUtils.class.getResource(path).openStream();
		return IOUtils.toByteArray(stream);
	}
	
	public static byte[] getResource(Path path) throws IOException {
		if(path == null) {
			return null;
		}
		
		InputStream stream = Files.newInputStream(path);
		return IOUtils.toByteArray(stream);
	}
	
	/**
	 * Return a list of all files contained in the folder specified as relative
	 * path here, as a {@link List} of {@link Path} elements. These can then be
	 * read using <code>ResourceUtils.getResource(path)</code>.
	 * 
	 * @param path
	 *            the relative path
	 * 
	 * @return the list of child {@link Path} elements, or <code>null</code> if
	 *         something fails or path is not valid.
	 */
	public static List<Path> getResourcesInFolder(String path) {
		if(AssertUtils.isEmpty(path)) {
			return null;
		}
		
		URL url = Thread.currentThread().getContextClassLoader().getResource(path);
		if(url == null) {
			return null;
		}
		
		URI uri = null;
		try {
			uri = url.toURI();
		} catch (URISyntaxException e) {
			// eat up - usually won't happen
		}
		
		List<Path> paths = new ArrayList<>();
		
		Stream<Path> stream = null;
		try {
			// check if we need to mount the JAR file as a filesystem
			if(uri.getScheme().equals("jar")) {
				Map<String, String> env = new HashMap<>();
		        env.put("create", "true");
		        FileSystems.newFileSystem(uri, env);
			}
			
			// start reading the files
			Path filePath = Paths.get(uri);
			
			// convert to stream
			stream = Files.list(filePath);
			Iterator<Path> iterator = stream.iterator();
			while(iterator.hasNext()) {
				paths.add(iterator.next());
			}
			
			return paths;
		} catch(IOException e) {
			return null;
		} finally {
			if(stream != null) {
				stream.close();
			}
		}
	}
}
