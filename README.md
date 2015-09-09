jerry-core
==========

[![Build Status](https://travis-ci.org/sangupta/jerry-core.svg?branch=master)](https://travis-ci.org/sangupta/jerry-core)
[![Coverage Status](https://coveralls.io/repos/sangupta/jerry-core/badge.png)](https://coveralls.io/r/sangupta/jerry-core)
[![Maven Version](https://maven-badges.herokuapp.com/maven-central/com.sangupta/jerry-core/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.sangupta/jerry-core)

Common Java functionality for core functionality.

`jerry-core` is a module library for the uber `jerry` library project. This module provides core functionality 
as static helper utility classes that can help reduce the boiler plate code when making even a simple Java
application. 

The core idea of the project is to include all basic functionality that is always used in a normal project. The project
does not aim to replace the `Apache Commons` framework but only extend it to include the otherwise useful functions and
methods that are missing in there.

_History:_ Ages ago, the project forked from the `https://github.com/sangupta/jerry` project, when all utility classes were extracted
out into the `jerry-core` project.

The library is tested on the following JDK versions:

* Oracle JDK 8
* Oracle JDK 7
* Open JDK 7

**Change Log**

[Release Notes](https://github.com/sangupta/jerry-core/blob/master/RELEASE-NOTES.md) are available here.

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

The latest released library can be downloaded from Maven Central using:

```xml
<dependency>
    <groupId>com.sangupta</groupId>
    <artifactId>jerry-core</artifactId>
    <version>2.2.2</version>
</dependency>
```

The current development snapshot `JAR` can be obtained using `JitPack.io` as:

Add the following `repository` to Maven,

```xml
<repository>
	<id>jitpack.io</id>
	<url>https://jitpack.io</url>
</repository>
```

Then add the dependency as,

```xml
<dependency>
    <groupId>com.github.sangupta</groupId>
    <artifactId>jerry-core</artifactId>
    <version>-SNAPSHOT</version>
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
Copyright (c) 2012-2015, Sandeep Gupta

http://sangupta.com/projects/jerry-core

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
