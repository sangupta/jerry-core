package com.sangupta.jerry.jersey2;

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

import org.glassfish.jersey.message.internal.AbstractMessageReaderWriterProvider;

import com.sangupta.jerry.util.GsonUtils;

/**
 * JSON serialization mechanism for Jersey. We use Google GSON library
 * for serialization for its better performance and support.
 * 
 * @author sangupta
 *
 */
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
@Provider
public class GsonJsonProvider extends AbstractMessageReaderWriterProvider<Object> {

    private static final String DEFAULT_ENCODING = "utf-8";
    
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
        return GsonUtils.getGson().fromJson(new InputStreamReader(stream, encoding), aClass);
    }

    public void writeTo(Object o, Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> map, OutputStream stream) throws IOException, WebApplicationException {
        String encoding = getCharsetAsString(mediaType);
        String json = GsonUtils.getGson().toJson(o, type);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(stream, encoding);
        outputStreamWriter.write(json);
        outputStreamWriter.flush();
        
    }

}
