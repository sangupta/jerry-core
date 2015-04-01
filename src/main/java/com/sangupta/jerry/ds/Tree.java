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
import java.util.LinkedList;
import java.util.List;

import com.sangupta.jerry.transform.Transformer;
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
	 */
	public Tree(T data) {
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
	 * @return
	 */
	public List<Tree<T>> getChildren() {
		return this.children;
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
	 * @return
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
	 * @return
	 */
	public Tree<T> getSibling() {
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

	/**
	 * Render the tree into a {@link String}.
	 * 
	 * @param transformer
	 * @return
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
		 * The constructor to generate the iterator
		 * 
		 * @param rootNode
		 */
		public SimpleTreeIterator(Tree<T> rootNode) {
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
