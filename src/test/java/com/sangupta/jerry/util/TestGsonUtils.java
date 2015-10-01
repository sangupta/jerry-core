package com.sangupta.jerry.util;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;

/**
 * Unit tests for {@link GsonUtils} class
 * 
 * @author sangupta
 *
 */
public class TestGsonUtils {

	@Test
	public void test() {
		Assert.assertNotNull(GsonUtils.getGson());
		
		Gson gson = GsonUtils.getGson();
		Assert.assertTrue(gson == GsonUtils.getGson());
		Assert.assertTrue(gson == GsonUtils.getGson(FieldNamingPolicy.IDENTITY));
		
		Assert.assertTrue(gson != GsonUtils.getGson(FieldNamingPolicy.LOWER_CASE_WITH_DASHES));
		Assert.assertTrue(gson != GsonUtils.getGson(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES));
		Assert.assertTrue(gson != GsonUtils.getGson(FieldNamingPolicy.UPPER_CAMEL_CASE));
		Assert.assertTrue(gson != GsonUtils.getGson(FieldNamingPolicy.UPPER_CAMEL_CASE_WITH_SPACES));
	}
	
}
