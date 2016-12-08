/**
 *
 * jerry - Common Java Functionality
 * Copyright (c) 2012-2016, Sandeep Gupta
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


package com.sangupta.jerry.ds;

import java.util.Iterator;

import org.junit.Assert;
import org.junit.Test;

public class TestSimpleTree {

	@Test
	public void testIterator() {
		// we have 3 levels with each node containing 4 child
		Tree<Integer> tree = new Tree<Integer>(1);

		Tree<Integer> nodeLevel1Child1 = tree.addChild(2);
		Tree<Integer> nodeLevel1Child2 = tree.addChild(3);
		Tree<Integer> nodeLevel1Child3 = tree.addChild(4);
		Tree<Integer> nodeLevel1Child4 = tree.addChild(5);

		Tree<Integer> n6 = nodeLevel1Child1.addChild(6);
		nodeLevel1Child1.addChild(7);
		nodeLevel1Child1.addChild(8);
		nodeLevel1Child1.addChild(9);

		n6.addChild(61).addChild(62).addChild(63);

		nodeLevel1Child2.addChild(10);
		nodeLevel1Child2.addChild(11);
		nodeLevel1Child2.addChild(12);
		nodeLevel1Child2.addChild(13);

		nodeLevel1Child3.addChild(14);
		nodeLevel1Child3.addChild(15);
		nodeLevel1Child3.addChild(16);
		nodeLevel1Child3.addChild(17);

		nodeLevel1Child4.addChild(18);
		nodeLevel1Child4.addChild(19);
		nodeLevel1Child4.addChild(20);
		nodeLevel1Child4.addChild(21);

		Iterator<Tree<Integer>> iterator = tree.iterator();

		Tree<Integer> node = iterator.next();
		Assert.assertTrue(node.isRoot());
		Assert.assertFalse(node.isLeaf());
		Assert.assertTrue(node.hasChildren());
		Assert.assertEquals(1, (int) node.getData());
		Assert.assertEquals(String.valueOf(node.getData()), node.toString());
		
		node = iterator.next();
		Assert.assertFalse(node.isRoot());
		Assert.assertFalse(node.isLeaf());
		Assert.assertTrue(node.hasChildren());
		Assert.assertEquals(2, (int) node.getData());
		Assert.assertEquals(String.valueOf(node.getData()), node.toString());
		
		node = iterator.next();
		Assert.assertFalse(node.isRoot());
		Assert.assertFalse(node.isLeaf());
		Assert.assertTrue(node.hasChildren());
		Assert.assertEquals(6, (int) node.getData());
		Assert.assertEquals(String.valueOf(node.getData()), node.toString());
		
		node = iterator.next();
		Assert.assertFalse(node.isRoot());
		Assert.assertFalse(node.isLeaf());
		Assert.assertTrue(node.hasChildren());
		Assert.assertEquals(61, (int) node.getData());
		Assert.assertEquals(String.valueOf(node.getData()), node.toString());
		
		node = iterator.next();
		Assert.assertFalse(node.isRoot());
		Assert.assertFalse(node.isLeaf());
		Assert.assertTrue(node.hasChildren());
		Assert.assertEquals(62, (int) node.getData());
		Assert.assertEquals(String.valueOf(node.getData()), node.toString());
		
		node = iterator.next();
		Assert.assertFalse(node.isRoot());
		Assert.assertTrue(node.isLeaf());
		Assert.assertFalse(node.hasChildren());
		Assert.assertEquals(63, (int) node.getData());
		Assert.assertEquals(String.valueOf(node.getData()), node.toString());
		
		node = iterator.next();
		Assert.assertFalse(node.isRoot());
		Assert.assertTrue(node.isLeaf());
		Assert.assertFalse(node.hasChildren());
		Assert.assertEquals(7, (int) node.getData());
		Assert.assertEquals(String.valueOf(node.getData()), node.toString());
		
		node = iterator.next();
		Assert.assertFalse(node.isRoot());
		Assert.assertTrue(node.isLeaf());
		Assert.assertFalse(node.hasChildren());
		Assert.assertEquals(8, (int) node.getData());
		Assert.assertEquals(String.valueOf(node.getData()), node.toString());
		
		node = iterator.next();
		Assert.assertFalse(node.isRoot());
		Assert.assertTrue(node.isLeaf());
		Assert.assertFalse(node.hasChildren());
		Assert.assertEquals(9, (int) node.getData());
		Assert.assertEquals(String.valueOf(node.getData()), node.toString());
		
		node = iterator.next();
		Assert.assertFalse(node.isRoot());
		Assert.assertFalse(node.isLeaf());
		Assert.assertTrue(node.hasChildren());
		Assert.assertEquals(3, (int) node.getData());
		Assert.assertEquals(String.valueOf(node.getData()), node.toString());
		
		node = iterator.next();
		Assert.assertFalse(node.isRoot());
		Assert.assertTrue(node.isLeaf());
		Assert.assertFalse(node.hasChildren());
		Assert.assertEquals(10, (int) node.getData());
		Assert.assertEquals(String.valueOf(node.getData()), node.toString());
		
		node = iterator.next();
		Assert.assertFalse(node.isRoot());
		Assert.assertTrue(node.isLeaf());
		Assert.assertFalse(node.hasChildren());
		Assert.assertEquals(11, (int) node.getData());
		Assert.assertEquals(String.valueOf(node.getData()), node.toString());
		
		node = iterator.next();
		Assert.assertFalse(node.isRoot());
		Assert.assertTrue(node.isLeaf());
		Assert.assertFalse(node.hasChildren());
		Assert.assertEquals(12, (int) node.getData());
		Assert.assertEquals(String.valueOf(node.getData()), node.toString());
		
		node = iterator.next();
		Assert.assertFalse(node.isRoot());
		Assert.assertTrue(node.isLeaf());
		Assert.assertFalse(node.hasChildren());
		Assert.assertEquals(13, (int) node.getData());
		Assert.assertEquals(String.valueOf(node.getData()), node.toString());
		
		node = iterator.next();
		Assert.assertFalse(node.isRoot());
		Assert.assertFalse(node.isLeaf());
		Assert.assertTrue(node.hasChildren());
		Assert.assertEquals(4, (int) node.getData());
		Assert.assertEquals(String.valueOf(node.getData()), node.toString());
		
		node = iterator.next();
		Assert.assertFalse(node.isRoot());
		Assert.assertTrue(node.isLeaf());
		Assert.assertFalse(node.hasChildren());
		Assert.assertEquals(14, (int) node.getData());
		Assert.assertEquals(String.valueOf(node.getData()), node.toString());
		
		node = iterator.next();
		Assert.assertFalse(node.isRoot());
		Assert.assertTrue(node.isLeaf());
		Assert.assertFalse(node.hasChildren());
		Assert.assertEquals(15, (int) node.getData());
		Assert.assertEquals(String.valueOf(node.getData()), node.toString());
		
		node = iterator.next();
		Assert.assertFalse(node.isRoot());
		Assert.assertTrue(node.isLeaf());
		Assert.assertFalse(node.hasChildren());
		Assert.assertEquals(16, (int) node.getData());
		Assert.assertEquals(String.valueOf(node.getData()), node.toString());
		
		node = iterator.next();
		Assert.assertFalse(node.isRoot());
		Assert.assertTrue(node.isLeaf());
		Assert.assertFalse(node.hasChildren());
		Assert.assertEquals(17, (int) node.getData());
		Assert.assertEquals(String.valueOf(node.getData()), node.toString());
		
		node = iterator.next();
		Assert.assertFalse(node.isRoot());
		Assert.assertFalse(node.isLeaf());
		Assert.assertTrue(node.hasChildren());
		Assert.assertEquals(5, (int) node.getData());
		Assert.assertEquals(String.valueOf(node.getData()), node.toString());
		
		node = iterator.next();
		Assert.assertFalse(node.isRoot());
		Assert.assertTrue(node.isLeaf());
		Assert.assertFalse(node.hasChildren());
		Assert.assertEquals(18, (int) node.getData());
		Assert.assertEquals(String.valueOf(node.getData()), node.toString());
		
		node = iterator.next();
		Assert.assertFalse(node.isRoot());
		Assert.assertTrue(node.isLeaf());
		Assert.assertFalse(node.hasChildren());
		Assert.assertEquals(19, (int) node.getData());
		Assert.assertEquals(String.valueOf(node.getData()), node.toString());
		
		node = iterator.next();
		Assert.assertFalse(node.isRoot());
		Assert.assertTrue(node.isLeaf());
		Assert.assertFalse(node.hasChildren());
		Assert.assertEquals(20, (int) node.getData());
		Assert.assertEquals(String.valueOf(node.getData()), node.toString());
		
		node = iterator.next();
		Assert.assertFalse(node.isRoot());
		Assert.assertTrue(node.isLeaf());
		Assert.assertFalse(node.hasChildren());
		Assert.assertEquals(21, (int) node.getData());
		Assert.assertEquals(String.valueOf(node.getData()), node.toString());
	}

}
