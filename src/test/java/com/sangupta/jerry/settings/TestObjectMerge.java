package com.sangupta.jerry.settings;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class TestObjectMerge {

	@Test
	public void test() throws InstantiationException, IllegalAccessException {
		TestSettingsObject highest = new TestSettingsObject(100);
		highest.x1 = 100;
		highest.x2 = 100;
		highest.x3 = 100;
		highest.x4 = 100;
		highest.x5 = 100;
		highest.x6 = 100;
		highest.x7 = true;

		TestSettingsObject medium = new TestSettingsObject(50);
		medium.x1 = 50;
		medium.x2 = 50;
		medium.x3 = 50;
		medium.x4 = 50;
		medium.x5 = 50;
		medium.x6 = 50;
		medium.x7 = false;

		TestSettingsObject lowest = new TestSettingsObject(10);
		lowest.x1 = 500;
		lowest.x2 = 500;
		lowest.x3 = 500;
		lowest.x4 = 5;
		lowest.x5 = 500;
		lowest.x6 = 500;
		lowest.x7 = false;

		// where everything is copied
		TestSettingsObject destination = new TestSettingsObject(0);

		List<TestSettingsObject> list = new ArrayList<>();
		list.add(lowest);
		list.add(highest);
		list.add(medium);

		ObjectMerge.merge(TestSettingsObject.class, destination, list);

		Assert.assertEquals(100, destination.x1);
		Assert.assertEquals(100, destination.x2);
		Assert.assertEquals(100, destination.x3);
		Assert.assertEquals(100, destination.x4);
		Assert.assertEquals(100, destination.x5, 0.01f);
		Assert.assertEquals(100, destination.x6, 0.01d);
		Assert.assertTrue(destination.x7);

	}

	private static class TestSettingsObject implements MergeComparable {

		final int weight;

		TestSettingsObject(int weight) {
			this.weight = weight;
		}

		@ConflictResolution(policy = ConflictResolutionPolicy.MIN)
		int x1;

		@ConflictResolution(policy = ConflictResolutionPolicy.MAX)
		long x2;

		@ConflictResolution(policy = ConflictResolutionPolicy.BLIND_COPY)
		char x3;

		@ConflictResolution(policy = ConflictResolutionPolicy.NON_ZERO)
		byte x4;

		float x5;

		double x6;

		boolean x7;

		@Override
		public int compareForMerge(MergeComparable other) {
			if (!(other instanceof TestSettingsObject)) {
				throw new RuntimeException("Invalid object to compare");
			}

			TestSettingsObject tso = (TestSettingsObject) other;
			return Integer.compare(this.weight, tso.weight);
		}

		@Override
		public String toString() {
			return "Priority: " + this.weight;
		}

	}
}
