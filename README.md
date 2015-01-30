jerry-core
==========

[![Build Status](https://travis-ci.org/sangupta/jerry-core.svg?branch=master)](https://travis-ci.org/sangupta/jerry-core)
[![Coverage Status](https://coveralls.io/repos/sangupta/jerry-core/badge.png)](https://coveralls.io/r/sangupta/jerry-core)

Common Java functionality for core functionality.

`jerry-core` is a module library for the uber `jerry` library project. This module provides core functionality 
as static helper utility classes that can help reduce the boiler plate code when making even a simple Java
application. 

The core idea of the project is to include all basic functionality that is always used in a normal project. The project
does not aim to replace the `Apache Commons` framework but onyl extend it to include the otherwise useful functions and
methods that are missing in there.

_History:_ Ages ago, the project forked from the `https://github.com/sangupta/jerry` project, when all utility classes were extracted
out into the `jerry-core` project.

Changelog
---------

**Current Development**

* Renamed `SystemProperty` to `SystemPropertyNames` to indicate that this just contains names
* Added method to `FileUtils` to list files using wild-cards in a given path
* Added method to `FileUtils` to return the extension of file
* Added `MimeUtils` to guess the MIME type based on the extension
* Added methods to `CheckUtils` to see if file can be read, written and/or executed
* Added a `GenericConsumer` class that allows consuming an entity easily
* Added a `FileByteChunkConsumer` that allows consuming an entire file chunk-by-chunk of bytes
* Added methods to `FileUtils` to compute MD5 and SHA-256 of a file


**1.5.0**

* Updated `GsonUtils` to allow for registeration of custom type adapters, and clear previously generated `Gson`s and/or custom adapters
* Added `SimpleMultiMap` to store multiple values against a given key in a `HashMap`
* Added `ApplicationContext` class to hold static values for every application
* Added a `StringLineIterator` that iterates over a string line-by-line and holds start/end position of each line
* Added functionality to dump `ConsoleTable` into `CSV`, `JSON` and `XML` formats


**1.4.0**

* Fixed a critical bug in `UriUtils.extractHost` - when the URL starts with `//` the method was throwing an NPE - which is now fixed and it returns the correct hostname
* Added `StringUtils.repeat` to create a `String` of repeatable characters
* Added `MutableInt` to create an object instance whose value can be changed as desired
* Added `MutableDouble`, `MutableFloat` and `MutableLong` on the lines of `MutableInt`
* Added `ConsoleTable` class to output display to `System.out` or `Console` implementations in a proper tabular format. Can be used to output data to plain text files - three implementations are available, `full-width`, `width-curtail` and `multi-line`
* Updated `UserLocalStore` to fetch a list of all keys stored within the store

**1.3.0**

* Updated `UserLocalStore` to allow files to be created in user's home directory
* Added method to `StringUtils` to merge an array using a given delimiter
* Added `DesktopUtils` to open a URL in system browser if available
* Added `StringUtils.getShortValue()` method
* Updated `IntegerCounter` and `LongCounter` to reset its value, or create a new one with a given initial value
* Upgraded `Maven` dependencies to latest

**1.2.1**

* Added unit test cases for `UserLocalStore`, `Base62Encoder`
* Added method in `HashUtils` to hash using `PBKDF2-HMAC-SHA1`
* Added `CryptoUtils` to encrypt/decrypt text using `AES-256`
* Added `EnvironmentUtils` to read a given property first by reading Java property, and then using System properties
* Updated `GsonUtils` to serialize time as `long` epoch values to preserve milli-seconds

**1.2.0**

* Added `UserLocalStore` and corresponding implementations to maintain a user-specific data store
* Added constants interface for popular system properties in `SystemProperty`
* Added more utility methods to `AssertUtils` and corresponding unit test cases
* Added `CheckUtils` for checking assertions and throwing appropriate exceptions when they are not met
* Added code coverage using `cobertura` maven plugin

**1.1.0**

* Added `IntegerCounter` to keep track of multiple named counters with max integer capacity
* Added `LongCounter` to keep track of multiple named counters with max long capacity
* Added `HttpMimeType` constants for common response types
* Added numerous utility methods to `StringUtils`
* Removed dependency on `Character.isAlphabetic()` from JDK 7
* Fixed bug in `UrlManipulator` when URL path is not present
* Updated javadocs

**1.0.0**

* Initial release

Features
--------

* Base62 encoder to create short urls
* Base64 encoder
* HTTP status code constants
* Utility to work with com.sun.misc.Unsafe 
* **ArchiveUtils** - methods to unpack ZIP/TAR/GZ files
* **AssertUtils** - assertion methods
* **CompressionUtils** - compress/decompress byte arrays in memory
* **ConsoleUtils** - read stuff from Console or Standard input
* **DateUtils** - function to work with java.util.Date objects
* **DomUtils** - convenience functions to work with JDOM 
* **EqualUtils** - check whether objects are equal or not
* **GsonUtils** - static singleton Google Guava Gson objects 
* **HashUtils** - compute various hashes - MD5, SHA-*, HMAC-SHA etc
* **HtmlUtils** - convenience functions for working with HTML DOM
* **ReflectionUtils** - common functionality around Java reflection
* **StringUtils** - basic string functions
* **TimeDurationUtils** - compute string represented duration between different times
* **UriUtils** - function to work with URI - encodeUriComponent/decode etc
* **UrlCanonicalizer** - basic URL canonicalizer
* **UrlManipulator** - url builder/manipulator that helps in composing/decomposing/replacing various url pieces
* **XmlUtils** - functions to extract XML values without parsing
* **XStreamUtils** - provides singleton XStream instances
* **ZipUtils** - zip entire folder, extract given file from ZIP etc

Downloads
---------

The library can be downloaded from Maven Central using:

```xml
<dependency>
    <groupId>com.sangupta</groupId>
    <artifactId>jerry-core</artifactId>
    <version>1.4.0</version>
</dependency>
```

Versioning
----------

For transparency and insight into our release cycle, and for striving to maintain backward compatibility, 
`jerry-core` will be maintained under the Semantic Versioning guidelines as much as possible.

Releases will be numbered with the follow format:

`<major>.<minor>.<patch>`

And constructed with the following guidelines:

* Breaking backward compatibility bumps the major
* New additions without breaking backward compatibility bumps the minor
* Bug fixes and misc changes bump the patch

For more information on SemVer, please visit http://semver.org/.

License
-------
	
```
jerry - Common Java Functionality
Copyright (c) 2012-2014, Sandeep Gupta

http://sangupta.com/projects/jerry

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

	http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
