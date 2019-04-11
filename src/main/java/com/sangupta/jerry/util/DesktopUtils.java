/**
 *
 * jerry - Common Java Functionality
 * Copyright (c) 2012-present, Sandeep Gupta
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

import java.awt.Desktop;
import java.awt.Desktop.Action;
import java.io.IOException;
import java.net.URI;

/**
 * Utility methods around the {@link Desktop} class like
 * open a URL in the system browser etc.
 *
 * @author sangupta
 *
 */
public abstract class DesktopUtils {

	/**
	 * Open the given URL in the system web browser.
	 *
	 * @param uri
	 *            the {@link URI} to be opened
	 *
	 * @return <code>true</code> if call to open was successfully made,
	 *         <code>false</code> otherwise. A value of <code>true</code> DOES
	 *         NOT guarantee that the {@link URI} was opened, but only that the
	 *         call was successfully made.
	 */
	public static boolean openURL(URI uri) {
		if(!Desktop.isDesktopSupported()) {
			return false;
		}

		Desktop d = Desktop.getDesktop();
		if(!d.isSupported(Action.BROWSE)) {
			return false;
		}

		try {
			d.browse(uri);
			return true;
		} catch (IOException e) {
			return false;
		}
	}

}
