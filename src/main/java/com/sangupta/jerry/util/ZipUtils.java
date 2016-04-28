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

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility functions to work around ZIP files.
 * 
 * @author sangupta
 *
 */
public class ZipUtils {
    
    /**
     * Private as static methods do not need instantiation
     */
    public ZipUtils() {
        // prevent instantiation for utility class
    }
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ZipUtils.class);

	/**
	 * Read a given file from the ZIP file and store it in a temporary file. The
	 * temporary file is set to be deleted on exit of application.
	 * 
	 * @param zipFile
	 *            the zip file from which the file needs to be read
	 * 
	 * @param fileName
	 *            the name of the file that needs to be extracted
	 * 
	 * @return the {@link File} handle for the extracted file in the temp
	 *         directory
	 *
	 * @throws FileNotFoundException
	 *             if the zip file does not exist
	 * 
	 * @throws IOException
	 *             if something fails while reading the ZIP file
	 * 
	 * @throws IllegalArgumentException
	 *             if the zipFile is <code>null</code> or the fileName is
	 *             <code>null</code> or empty.
	 */
	public static File readFileFromZip(File zipFile, String fileName) throws FileNotFoundException, IOException {
		if(zipFile == null) {
			throw new IllegalArgumentException("zip file to extract from cannot be null");
		}
		
		if(AssertUtils.isEmpty(fileName)) {
			throw new IllegalArgumentException("the filename to extract cannot be null/empty");
		}
		
		LOGGER.debug("Reading {} from {}", fileName, zipFile.getAbsolutePath());
		
		ZipInputStream stream = null;
		BufferedOutputStream outStream = null;
		File tempFile = null;
		
		try {
			byte[] buf = new byte[1024];
			stream = new ZipInputStream(new FileInputStream(zipFile));
			ZipEntry entry;
			while((entry = stream.getNextEntry()) != null) {
				String entryName = entry.getName();
				if(entryName.equals(fileName)) {
					tempFile = File.createTempFile(FilenameUtils.getName(entryName), FilenameUtils.getExtension(entryName));
					tempFile.deleteOnExit();
					
					outStream = new BufferedOutputStream(new FileOutputStream(tempFile));
					int readBytes;
					while ((readBytes = stream.read(buf, 0, 1024)) > -1) {
				        outStream.write(buf, 0, readBytes);
					}
					
					stream.close();
					outStream.close();
					
					return tempFile;
				}
			}
		} finally {
			IOUtils.closeQuietly(stream);
			IOUtils.closeQuietly(outStream);
		}
		
		return tempFile;
	}
	
	/**
	 * Compresses the provided file into ZIP format adding a '.ZIP' at the end
	 * of the filename.
	 * 
	 * @param filePath
	 *            the file path that needs to be compressed
	 * 
	 * @return returns the absolute path of the ZIP file.
	 */
	public String createZipFile(String filePath) {
        LOGGER.debug("Starting compression of " + filePath);
        
    	String zipFilename = filePath + ".zip";
    	LOGGER.debug("Creating zip file at " + zipFilename);
        
    	byte[] buf = new byte[1024];

    	ZipOutputStream stream = null;
        FileInputStream input = null;
        try {
            // Create the ZIP file
			stream = new ZipOutputStream(new FileOutputStream(zipFilename));

            // Compress the file
            File file = new File(filePath);
			input = new FileInputStream(file);
    
            // Add ZIP entry to output stream.
            stream.putNextEntry(new ZipEntry(file.getName()));
    
            // Transfer bytes from the file to the ZIP file
            int len;
            while ((len = input.read(buf)) > 0) {
                stream.write(buf, 0, len);
            }
    
            // Complete the entry
            stream.closeEntry();
        } catch (IOException e) {
            LOGGER.error("Unable to compress file " + filePath, e);
        } finally {
            IOUtils.closeQuietly(input);
            
            // Complete the ZIP file
            IOUtils.closeQuietly(stream);
        }
        
        return zipFilename;	
	}
	
	/**
	 * Extract the given ZIP file into the given destination folder.
	 * 
	 * @param zipFile
	 *            file to extract
	 *            
	 * @param baseFolder
	 *            destination folder to extract in
	 */
	public static void extractZipToFolder(File zipFile, File baseFolder) {
		try {
			byte[] buf = new byte[1024];
	        
			ZipInputStream zipinputstream = new ZipInputStream(new FileInputStream(zipFile));
	        ZipEntry zipentry = zipinputstream.getNextEntry();
	        
	        while (zipentry != null) {
	            // for each entry to be extracted
	            String entryName = zipentry.getName();
	            
	            entryName = entryName.replace('/', File.separatorChar);
	            entryName = entryName.replace('\\', File.separatorChar);
	            
	            int numBytes;
	            FileOutputStream fileoutputstream;
	            File newFile = new File(baseFolder, entryName);
	            if (zipentry.isDirectory()) {
	                if (!newFile.mkdirs()) {
	                    break;
	                }
	                zipentry = zipinputstream.getNextEntry();
	                continue;
	            }

	            fileoutputstream = new FileOutputStream(newFile);
	            while ((numBytes = zipinputstream.read(buf, 0, 1024)) > -1) {
	                fileoutputstream.write(buf, 0, numBytes);
	            }

	            fileoutputstream.close();
	            zipinputstream.closeEntry();
	            zipentry = zipinputstream.getNextEntry();
	        }

	        zipinputstream.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

}