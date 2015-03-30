/**
 *
 * jerry - Common Java Functionality
 * Copyright (c) 2012-2015, Sandeep Gupta
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
 
package com.sangupta.jerry.ds;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.sangupta.jerry.util.AssertUtils;

/**
 * A simple n-ary Tree data-structure useful to keep arbitrary data
 * in a tree-shape form.
 * 
 * This class is NOT thread-safe.
 * 
 * @author sangupta
 *
 * @param <T>
 */
public class SimpleTree<T> implements Iterable<SimpleTree<T>> {

	/**
	 * The data that this node keeps
	 */
	private T data;
	
	/**
	 * The parent node to this node
	 * 
	 */
	private SimpleTree<T> parent;
	
	/**
	 * List of all child nodes
	 * 
	 */
	private List<SimpleTree<T>> children;
	
	/**
	 * Convenience constructor
	 * 
	 * @param data
	 */
	public SimpleTree(T data) {
		this.data = data;
	}
	
	// Common methods

	/**
	 * Check if this node is the root of the tree.
	 * 
	 * @return
	 */
	public boolean isRoot() {
		return this.parent == null;
	}
	
	/**
	 * Check if this node is a leaf node, one without any
	 * children.
	 * 
	 * @return
	 */
	public boolean isLeaf() {
		if(this.children == null) {
			return true;
		}
		
		return this.children.isEmpty();
	}
	
	/**
	 * Check if this node has child elements or not.
	 * 
	 * @return
	 */
	public boolean hasChildren() {
		if(this.children == null) {
			return false;
		}
		
		return !this.children.isEmpty();
	}
	
	/**
	 * Return the level of this node in the tree.
	 * 
	 * @return
	 */
	public int getLevel() {
		if(this.isRoot()) {
			return 0;
		}
		
		return this.parent.getLevel() + 1;
	}
	
	/**
	 * Add a new node as a child node to this node and return
	 * the newly added node back.
	 * 
	 * @param data
	 * @return
	 */
	public SimpleTree<T> addChild(T data) {
		if(this.children == null) {
			this.children = new ArrayList<SimpleTree<T>>();
		}
		
		SimpleTree<T> child = new SimpleTree<T>(data);
		child.parent = this;
		this.children.add(child);
		
		return child;
	}
	
	/**
	 * Remove this very node from the tree.
	 * 
	 * @return
	 */
	boolean removeSelf() {
		if(this.isRoot()) {
			return true;
		}
		
		boolean removed = this.parent.removeChild(this);
		if(!removed) {
			return false;
		}
		
		this.parent = null;
		return true;
	}
	
	/**
	 * Remove the given child from the list of children for this
	 * node.
	 * 
	 * @param node
	 * @return
	 */
	boolean removeChild(SimpleTree<T> node) {
		if(node == null) {
			return false;
		}
		
		if(AssertUtils.isEmpty(this.children)) {
			return false;
		}
		
		Iterator<SimpleTree<T>> childIterator = this.children.iterator();
		while(childIterator.hasNext()) {
			SimpleTree<T> child = childIterator.next();
			if(node == child) {
				childIterator.remove();
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Get the child node at the given index
	 *  
	 * @param index
	 * @return
	 */
	SimpleTree<T> getChild(int index) {
		if(index < 0) {
			throw new IllegalArgumentException("Index cannot be negative");
		}
		
		if(this.children == null) {
			throw new IndexOutOfBoundsException("No child present");
		}
		
		int size = this.children.size();
		if(index >= size) {
			throw new IndexOutOfBoundsException("Index cannot be greater than size of children");
		}
		
		return this.children.get(index);
	}
	
	/**
	 * Get the next sibling for this node.
	 * 
	 * @return
	 */
	public SimpleTree<T> getSibling() {
		if(this.parent == null) {
			return null;
		}
		
		return this.parent.nextChild(this);
	}
	
	/**
	 * Get the next child node from this node where the node being
	 * searched is provided.
	 * 
	 * @param node
	 * @return
	 */
	public SimpleTree<T> nextChild(SimpleTree<T> node) {
		if(this.isLeaf()) {
			return null;
		}
		
		for(int index = 0; index < this.children.size(); index++) {
			// the following comparison is == intentionally because we are looking for exact node
			// reference and not two nodes that have same data
			if(this.children.get(index) == node) {
				// found return next
				if(index >= this.children.size() - 1) {
					return null;
				}
				
				return this.children.get(index + 1);
			}
		}
		
		return null;
	}
	
	@Override
	public Iterator<SimpleTree<T>> iterator() {
		return new SimpleTreeIterator<T>(this);
	}
	
	@Override
	public String toString() {
		if(this.data != null) {
			return this.data.toString();
		}
		
		return "SimpleTreeNode(null)";
	}
	
	// Usual accessors follow
	
	public SimpleTree<T> getParent() {
		return this.parent;
	}
	
	/**
	 * Read the data stored in this node.
	 * 
	 * @return
	 */
	public T getData() {
		return this.data;
	}
	
	/**
	 * Change the data stored in this node.
	 * 
	 * @param data
	 */
	public void setData(T data) {
		this.data = data;
	}

	// STATIC CLASS FOR ITERATOR follows
	
	/**
	 * This is a depth-first iterator for the tree.
	 * 
	 * @author sangupta
	 *
	 * @param <T>
	 */
	public static class SimpleTreeIterator<T> implements Iterator<SimpleTree<T>> {
		
		/**
		 * The node that will be returned next
		 */
		private SimpleTree<T> nextNode = null;
		
		/**
		 * Whether the root node has been consumed by the iterator or not
		 * 
		 */
		private boolean rootConsumed = false; 

		/**
		 * The constructor to generate the iterator
		 * 
		 * @param rootNode
		 */
		public SimpleTreeIterator(SimpleTree<T> rootNode) {
			if(rootNode == null) {
				throw new IllegalArgumentException("Rootnode to iterate on cannot be null");
			}
			
			this.nextNode = rootNode;
		}

		@Override
		public boolean hasNext() {
			// send the root first
			if(!rootConsumed) {
				this.rootConsumed = true;
				return true;
			}
			
			// find the next node 
			if(this.nextNode.hasChildren()) {
				this.nextNode = this.nextNode.getChild(0);
				return true;
			}
			
			do {
				SimpleTree<T> sibling = this.nextNode.getSibling();
				if(sibling != null) {
					this.nextNode = sibling;
					return true;
				}
				
				if(this.nextNode.isRoot()) {
					return false;
				}
				
				// if sibling is null, go back to its parent
				this.nextNode = this.nextNode.getParent();
			} while(true);
		}

		@Override
		public SimpleTree<T> next() {
			return this.nextNode;
		}

		@Override
		public void remove() {
			if(this.nextNode == null) {
				return;
			}

			this.nextNode.removeSelf();
		}
		
	}
}
