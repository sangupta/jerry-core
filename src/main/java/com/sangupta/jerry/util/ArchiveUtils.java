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

package com.sangupta.jerry.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;
import org.apache.commons.compress.compressors.gzip.GzipUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility classes to pack/unpack popular compressed files
 * like ZIP, RAR, TAR, GZ etc.
 *
 * @author sangupta
 *
 */
public abstract class ArchiveUtils {

	/**
	 * Logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(ArchiveUtils.class);

	/**
	 * Unpack the TAR file into the given output directory.
	 *
	 * @param tarFile
	 *            the tar file that needs to be unpacked
	 *
	 * @param outputDir
	 *            the directory in which the entire file is unpacked
	 *
	 * @throws ArchiveException
	 *             if the TAR file is corrupt
	 *
	 * @throws IOException
	 *             if error occurs reading TAR file
	 *
	 * @return a list of {@link File} objects representing the files unpacked
	 *         from the TAR file
	 *
	 */
	public static List<File> unpackTAR(final File tarFile, final File outputDir) throws ArchiveException, IOException {
		LOGGER.info("Untaring {} to dir {}", tarFile.getAbsolutePath(), outputDir.getAbsolutePath());

	    final List<File> untaredFiles = new LinkedList<File>();

	    InputStream fileInputStream = null;
	    TarArchiveInputStream tarInputStream = null;
        OutputStream outputFileStream = null;

	    try {
			fileInputStream = new FileInputStream(tarFile);
			tarInputStream = (TarArchiveInputStream) new ArchiveStreamFactory().createArchiveInputStream("tar", fileInputStream);

		    TarArchiveEntry entry = null;
		    while ((entry = (TarArchiveEntry) tarInputStream.getNextEntry()) != null) {
		        final File outputFile = new File(outputDir, entry.getName());
		        if (entry.isDirectory()) {
		        	LOGGER.debug("Attempting to write output directory {}", outputFile.getAbsolutePath());

		            if (!outputFile.exists()) {
		            	LOGGER.debug("Attempting to create output directory {}", outputFile.getAbsolutePath());

		            	if (!outputFile.mkdirs()) {
		                    throw new IllegalStateException("Couldn't create directory: " + outputFile.getAbsolutePath());
		                }
		            }

		            // next file
		            continue;
		        }

		        // write the plain file
	        	LOGGER.debug("Creating output file {}", outputFile.getAbsolutePath());

				outputFileStream = new FileOutputStream(outputFile);
	            org.apache.commons.io.IOUtils.copy(tarInputStream, outputFileStream);
	            outputFileStream.close();

	            // add to the list of written files
		        untaredFiles.add(outputFile);
		    }
	    } finally {
	    	IOUtils.closeQuietly(outputFileStream, tarInputStream, fileInputStream);
	    }

	    return untaredFiles;
	}

	/**
	 * Unpack the Gzip GZ file into the given directory.
	 *
	 * @param gzipFile
	 *            the file that needs to be unpacked
	 *
	 * @param outputDir
	 *            the directory in which the archive is unpacked
	 *
	 * @throws FileNotFoundException
	 *             if the GZ file is not found
	 *
	 * @return a list of {@link File} objects representing the files unpacked
	 *         from the TAR file
	 */
	public static List<File> unpackGZIP(final File gzipFile, final File outputDir) throws IOException {
		String outputFileName = GzipUtils.getUncompressedFilename(gzipFile.getName());
		File outputFile = new File(outputDir, outputFileName);

		FileInputStream fin = null;
		BufferedInputStream in = null;
		FileOutputStream out = null;
		GzipCompressorInputStream gzIn = null;

		try {
			fin = new FileInputStream(gzipFile);
			in = new BufferedInputStream(fin);
			out = new FileOutputStream(outputFile);
			gzIn = new GzipCompressorInputStream(in);

			org.apache.commons.io.IOUtils.copy(gzIn, out);
		} finally {
			IOUtils.closeQuietly(gzIn, out, in, fin);
		}

		List<File> files = new ArrayList<File>();
		files.add(outputFile);

		return files;
	}

}
