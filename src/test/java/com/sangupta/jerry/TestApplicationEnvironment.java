package com.sangupta.jerry;

import org.junit.Assert;
import org.junit.Test;

public class TestApplicationEnvironment {

	@Test
	public void testApplicationEnvironment() {
		Assert.assertEquals(ApplicationEnvironment.DEVELOPMENT, ApplicationEnvironment.fromString("dev"));
		Assert.assertEquals(ApplicationEnvironment.DEVELOPMENT, ApplicationEnvironment.fromString("development"));
		Assert.assertEquals(ApplicationEnvironment.DEVELOPMENT, ApplicationEnvironment.fromString("DEv"));
		Assert.assertEquals(ApplicationEnvironment.DEVELOPMENT, ApplicationEnvironment.fromString("deVElopment"));
		
		Assert.assertEquals(ApplicationEnvironment.TEST, ApplicationEnvironment.fromString("qa"));
		Assert.assertEquals(ApplicationEnvironment.TEST, ApplicationEnvironment.fromString("qe"));
		Assert.assertEquals(ApplicationEnvironment.TEST, ApplicationEnvironment.fromString("test"));
		
		Assert.assertEquals(ApplicationEnvironment.PRE_STAGE, ApplicationEnvironment.fromString("prestage"));
		Assert.assertEquals(ApplicationEnvironment.PRE_STAGE, ApplicationEnvironment.fromString("pre-stage"));
		
		Assert.assertEquals(ApplicationEnvironment.STAGE, ApplicationEnvironment.fromString("stg"));
		Assert.assertEquals(ApplicationEnvironment.STAGE, ApplicationEnvironment.fromString("stage"));
		
		Assert.assertEquals(ApplicationEnvironment.PRE_PRODUCTION, ApplicationEnvironment.fromString("preprod"));
		Assert.assertEquals(ApplicationEnvironment.PRE_PRODUCTION, ApplicationEnvironment.fromString("pre-prod"));
		
		Assert.assertEquals(ApplicationEnvironment.PRODUCTION, ApplicationEnvironment.fromString("prod"));
		Assert.assertEquals(ApplicationEnvironment.PRODUCTION, ApplicationEnvironment.fromString("production"));
		
		Assert.assertNull(ApplicationEnvironment.fromString(null));
		Assert.assertNull(ApplicationEnvironment.fromString(""));
		
		try {
			ApplicationEnvironment.fromString("hello-world");
			Assert.assertTrue(false);
		} catch(IllegalArgumentException e) {
			Assert.assertTrue(true);
		}
	}
	
}
