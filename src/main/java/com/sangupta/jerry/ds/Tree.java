/**
 *
 * jerry - Common Java Functionality
 * Copyright (c) 2012-2017, Sandeep Gupta
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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.sangupta.jerry.transform.Transformer;
import com.sangupta.jerry.util.AssertUtils;

/**
 * A simple n-ary Tree data-structure useful to keep arbitrary data in a
 * tree-shape form.
 *
 * This class is NOT thread-safe.
 *
 * @author sangupta
 *
 * @param <T>
 *            the type of instance, that nodes of this {@link Tree} will hold
 */
public class Tree<T> implements Iterable<Tree<T>> {

	/**
	 * The data that this node keeps
	 */
	private T data;

	/**
	 * The parent node to this node
	 *
	 */
	private Tree<T> parent;

	/**
	 * List of all child nodes
	 *
	 */
	private List<Tree<T>> children;

	/**
	 * Convenience constructor
	 *
	 * @param data
	 *            the data to hold in this node of {@link Tree}
	 */
	public Tree(T data) {
		this.data = data;
	}

	// Common methods

	/**
	 * Check if this node is the root of the tree.
	 *
	 * @return <code>true</code> if this node is root, <code>false</code>
	 *         otherwise
	 */
	public boolean isRoot() {
		return this.parent == null;
	}

	/**
	 * Check if this node is a leaf node, one without any children.
	 *
	 * @return <code>true</code> if this node is leaf, <code>false</code>
	 *         otherwise
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
	 * @return <code>true</code> if this node has atleast one child,
	 *         <code>false</code> otherwise
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
	 * @return the level for this node, where ROOT node is assigned a level of
	 *         zero
	 */
	public int getLevel() {
		if(this.isRoot()) {
			return 0;
		}

		return this.parent.getLevel() + 1;
	}

	/**
	 * Add a new node as a child node to this node and return the newly added
	 * node back.
	 *
	 * @param data
	 *            the data for the added child node
	 *
	 * @return the added child node
	 */
	public Tree<T> addChild(T data) {
		if(this.children == null) {
			this.children = new ArrayList<Tree<T>>();
		}

		Tree<T> child = new Tree<T>(data);
		child.parent = this;
		this.children.add(child);

		return child;
	}

	/**
	 * Return the children associated with this node.
	 *
	 * @return a list of all child {@link Tree} nodes
	 */
	public List<Tree<T>> getChildren() {
		return this.children;
	}

	/**
	 * Remove this very node from the tree.
	 *
	 * @return <code>true</code> if we could successfully remove the node from
	 *         its parent, <code>false</code> otherwise
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
	 * Remove the given child from the list of children for this node.
	 *
	 * @param node
	 *            the node to be removed from this node's children
	 *
	 * @return <code>true</code> if node was removed, <code>false</code>
	 *         otherwise
	 */
	boolean removeChild(Tree<T> node) {
		if(node == null) {
			return false;
		}

		if(AssertUtils.isEmpty(this.children)) {
			return false;
		}

		Iterator<Tree<T>> childIterator = this.children.iterator();
		while(childIterator.hasNext()) {
			Tree<T> child = childIterator.next();
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
	 *            the index for which the children is desired
	 *
	 * @return the child node at given index
	 *
	 * @throws IllegalArgumentException
	 *             if index is less than zero
	 *
	 * @throws IndexOutOfBoundsException
	 *             if index is not valid, or there are no children present
	 */
	Tree<T> getChild(int index) {
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
	 * @return the next sibling for this node, <code>null</code> otherwise
	 */
	public Tree<T> getSibling() {
		if(this.parent == null) {
			return null;
		}

		return this.parent.nextChild(this);
	}

	/**
	 * Get the next child node from this node where the node being searched is
	 * provided.
	 *
	 * @param node
	 *            the node whose next sibling is desired
	 *
	 * @return the next node {@link Tree} instance, <code>null</code> otherwise
	 */
	public Tree<T> nextChild(Tree<T> node) {
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
	public Iterator<Tree<T>> iterator() {
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

	public Tree<T> getParent() {
		return this.parent;
	}

	/**
	 * Read the data stored in this node.
	 *
	 * @return the data in this node
	 */
	public T getData() {
		return this.data;
	}

	/**
	 * Change the data stored in this node.
	 *
	 * @param data the data to be set
	 */
	public void setData(T data) {
		this.data = data;
	}

	/**
	 * Render the tree into a {@link String}.
	 *
	 * @param transformer
	 *            the {@link Transformer} instance to call on each node
	 *
	 * @return the {@link String} representation of the tree as generated using
	 *         the given {@link Transformer} instance
	 */
	public String renderTree(Transformer<T, String> transformer) {
	    List<StringBuilder> lines = renderTreeLines(this, transformer);
	    String newline = System.getProperty("line.separator");
	    StringBuilder sb = new StringBuilder(lines.size() * 20);
	    for (StringBuilder line : lines) {
	        sb.append(line);
	        sb.append(newline);
	    }
	    return sb.toString();
	}

	private List<StringBuilder> renderTreeLines(Tree<T> tree, Transformer<T, String> transformer) {
	    List<StringBuilder> result = new LinkedList<StringBuilder>();
	    String treeData = transformer.transform(tree.getData());
	    result.add(new StringBuilder().append(treeData));

	    if(tree.hasChildren()) {
		    Iterator<Tree<T>> iterator = tree.getChildren().iterator();
		    while (iterator.hasNext()) {
		        List<StringBuilder> subtree = renderTreeLines(iterator.next(), transformer);
		        if (iterator.hasNext()) {
		            addSubtree(result, subtree);
		        } else {
		            addLastSubtree(result, subtree);
		        }
		    }
	    }

	    return result;
	}

	private void addSubtree(List<StringBuilder> result, List<StringBuilder> subtree) {
	    Iterator<StringBuilder> iterator = subtree.iterator();
	    result.add(iterator.next().insert(0, "├── "));
	    while (iterator.hasNext()) {
	        result.add(iterator.next().insert(0, "│   "));
	    }
	}

	private void addLastSubtree(List<StringBuilder> result, List<StringBuilder> subtree) {
	    Iterator<StringBuilder> iterator = subtree.iterator();
	    result.add(iterator.next().insert(0, "└── "));
	    while (iterator.hasNext()) {
	        result.add(iterator.next().insert(0, "    "));
	    }
	}

	// STATIC CLASS FOR ITERATOR follows

	/**
	 * This is a depth-first iterator for the tree.
	 *
	 * @author sangupta
	 *
	 * @param <T>
	 *            the type of instance, the nodes of this {@link Iterator} holds
	 */
	public static class SimpleTreeIterator<T> implements Iterator<Tree<T>> {

		/**
		 * The node that will be returned next
		 */
		private Tree<T> nextNode = null;

		/**
		 * Whether the root node has been consumed by the iterator or not
		 *
		 */
		private boolean rootConsumed = false;

		/**
		 * Indicates if we have the next node available or not
		 */
		protected volatile boolean hasNextNode;

		/**
		 * The constructor to generate the iterator
		 *
		 * @param rootNode
		 *            the node that will act as the root
		 */
		public SimpleTreeIterator(Tree<T> rootNode) {
			if(rootNode == null) {
				throw new IllegalArgumentException("Rootnode to iterate on cannot be null");
			}

			this.nextNode = rootNode;
		}

		@Override
		public boolean hasNext() {
			return this.hasNextNode;
		}

		/**
		 * Internal method that fills in the next node that will be returned to
		 * the user.
		 *
		 * @return <code>true</code> if there is a next node present,
		 *         <code>false</code> otherwise
		 */
		private boolean hasNextInternal() {
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
				Tree<T> sibling = this.nextNode.getSibling();
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
		public Tree<T> next() {
			this.hasNextNode = hasNextInternal();
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
