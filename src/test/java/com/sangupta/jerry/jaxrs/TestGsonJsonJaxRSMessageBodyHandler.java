package com.sangupta.jerry.jaxrs;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for {@link GsonJsonJaxRSMessageBodyHandler}.
 * 
 * @author sangupta
 *
 */
public class TestGsonJsonJaxRSMessageBodyHandler {

	@Test
	public void testClass() throws WebApplicationException, IOException {
		GsonJsonJaxRSMessageBodyHandler handler = new GsonJsonJaxRSMessageBodyHandler();
		
		// the basic ones
		Assert.assertTrue(handler.isReadable(null, null, null, null));
		Assert.assertTrue(handler.isWriteable(null, null, null, null));
		
		// the main conversion test
		String[] strings = new String[] { "Hello", "World" };
		
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		handler.writeTo(strings, String[].class, String[].class, null, MediaType.APPLICATION_JSON_TYPE, null, stream);
		String json = new String(stream.toByteArray());
		
		Assert.assertNotNull(json);
		
		ByteArrayInputStream in = new ByteArrayInputStream(json.getBytes());
		Object reconstructed = handler.readFrom(null, String[].class, null, MediaType.APPLICATION_JSON_TYPE, null, in);
		
		Assert.assertNotNull(reconstructed);
		Assert.assertTrue(reconstructed instanceof String[]);
		
		String[] array = (String[]) reconstructed;
		Assert.assertArrayEquals(strings, array);
	}
}
