## Release Notes

**2.1.0**

* Udpated `BitArray` and its various implementation for many bug-fixes and enhancements
* Added JCIP thread-safety annotations to `BitArray` and its implementations
* Added `HttpHeaderName` constants from jerry-http project
* Renamed `GsonJsonProvider` to `GsonJsonJerseyProvider` for better clarity
* Added `Gson` based `JAX-RS` handler for use with RestEasy

**2.0.0**

* Moved `MutableInt`, `MutableLong`, `MutableFloat` and `MutableDouble` to a sub-package
* Moved `IntegerCounter` and `LongCounter` to a sub-package
* Added a `Tree` implementation that stores an n-ary tree and can render its flat view, allows for depth-first traversal
* Updated JavaDocs to remove all missing docs and removed all warnings
* Added `BitArray` and its multiple implementations: an in-memory `FastBitArray`, a simple `FileBackedBitArray`, an implementation using standard `JavaBitArray` and a fast memory-mapped file based `MMapFileBackedBitArray`
* Added Jersey 1.0 and 2.0 based `GsonJsonProvider` for building REST services
* Added `DumpUtils` to contruct a reflection-based string representation of an object useful for debugging purposes
* Added `CookieUtils`, `ResponseUtils`, and `RequestUtils` to work with Servlet specification based objects
* Added `PropertiesUtils` to work with `Properties` objects easily
* Added method to resolve a file path to `File` instance taking care of all `..`, and `~`
* Added `Transformer` construct to transform an object from one form to another
* Added constant to `SystemPropertyNames` for fetching java temporary folder
* Added `FileUtils.getExtension` method to extract file extension
* Added `FileUtils.hexDump` method to dump file contents in hex-notation
* Added `ReadableUtils` to parse number of bytes/time in milliseconds to human readable form
* Added `OSUtils` to retrieve operating-system based information
* Added Oracle JDK 8 as one of the test environments
* Updated copyright headers


**1.6.1**

* Fixed bug in `CheckUtils.directoryExists` where its existence as a `File` was being tested

**1.6.0**

* Renamed `SystemProperty` to `SystemPropertyNames` to indicate that this just contains names
* Added method to `FileUtils` to list files using wild-cards in a given path
* Added method to `FileUtils` to return the extension of file
* Added `MimeUtils` to guess the MIME type based on the extension
* Added methods to `CheckUtils` to see if file can be read, written and/or executed
* Added a `GenericConsumer` class that allows consuming an entity easily
* Added a `FileByteChunkConsumer` that allows consuming an entire file chunk-by-chunk of bytes
* Added methods to `FileUtils` to compute MD5 and SHA-256 of a file
* Added `BitUtils` utility class with basic bit operations 
* Added `EnvironmentUtils.dumpAllProperties()` to dump all system properties and evironment variables into a string for easier debugging
* Added `areEmpty()` and `areNotEmpty()` in `AssertUtils`

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
