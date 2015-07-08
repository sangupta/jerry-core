/**
 *
 * jerry-web - Common Java Functionality
 * Copyright (c) 2012-2014, Sandeep Gupta
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

package com.sangupta.jerry.jersey;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.sangupta.jerry.util.GsonUtils;
import com.sun.jersey.core.provider.AbstractMessageReaderWriterProvider;

/**
 * JSON serialization mechanism for Jersey. We use Google GSON library
 * for serialization for its better performance and support.
 * 
 * @author sangupta
 * @since 2.0
 */
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
@Provider
public class GsonJsonJerseyProvider extends AbstractMessageReaderWriterProvider<Object> {

    private static final String DEFAULT_ENCODING = "utf-8";
    
    private static final Gson GSON = GsonUtils.getGson(FieldNamingPolicy.IDENTITY);
    
    @Override
    public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType arg3) {
        return true;
    }

    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType arg3) {
    	return true;
    }

    /**
	 * Returns the charset as a string depending on the encoding specified, or
	 * the usual default of <code>UTF-8</code>.
	 * 
	 * @param m
	 *            the {@link MediaType} provided
	 * 
	 * @return the {@link Charset} value as string
	 */
    protected static String getCharsetAsString(MediaType m) {
        if (m == null) {
            return DEFAULT_ENCODING;
        }
        String result = m.getParameters().get("charset");
        return (result == null) ? DEFAULT_ENCODING : result;
    }

    public Object readFrom(Class<Object> aClass, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> map, InputStream stream) throws IOException, WebApplicationException  {
        String encoding = getCharsetAsString(mediaType);
        return GSON.fromJson(new InputStreamReader(stream, encoding), aClass);
    }

    public void writeTo(Object o, Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> map, OutputStream stream) throws IOException, WebApplicationException {
        String encoding = getCharsetAsString(mediaType);
        String json = GSON.toJson(o, type);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(stream, encoding);
        outputStreamWriter.write(json);
        outputStreamWriter.flush();
    }

}
