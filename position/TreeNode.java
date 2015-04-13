package position;

import nodeList.PositionList;

/**
 * The TreeNode class it's a node that can be used for tree.
 * 
 * @author SuperJulietta
 * @version 1.0
 * @param <E> the element type.
 * 
 */

public class TreeNode<E> implements TreePosition<E> {
	//Instance Variables
		private E el;
		private TreePosition<E> parent;
		private PositionList<Position <E>> children;
	
	//Constructors
		
		/**
		 * Constructs an empty TreeNode.
		 */
		public TreeNode(){
		}
	
		/**
		 * Constructs a TreeNode with a given element, 
		 * parent and children.
		 * @param el the element to save in the TreeNode.
		 * @param parent the reference to the parent.
		 * @param children the list of children.
		 */
		public TreeNode(E el, TreePosition<E> parent,PositionList<Position <E>> children){
			this.el = el;
			this.parent = parent;
			this.children = children;
		}
	//Methods
		
		/**
		 * Returns the list of children.
		 * @return the list of children.
		 */
		public PositionList<Position<E>> getChildren() {
			return children;
		}
		
		/**
		 * Returns the Position of the parent.
		 * @return the Position of the parent.
		 */
		public TreePosition<E> getParent() {
			return parent;
		}
		
		/**
		 * Sets the current list of children with another list 
		 * given in input.
		 * @param c the new list of children. 
		 */
		public void setChildren(PositionList<Position<E>> c) {
			children = c;		
		}

		/**
		 * Sets the element stored in this TreeNode with another element.
		 * @param el the new element.
		 */
		public void setElement(E el) {
			this.el = el;
		}

		/**
		 * Sets the parent Position with another position given in input.
		 * @param p the new position.
		 */
		public void setParent(TreePosition<E> p) {
			parent = p;
		}

		/**
		 * Returns the element stored in this TreeNode.
		 * @return the element stored in this TreeNode.
		 */
		public E element() {
			return el;
		}

		
		@Override
		public String toString(){
			return el.toString();
		}
		

		 
		/**
		 * Returns true if the element stored in the given TreeNode is equals
		 * to the element stored in this TreeNode
		 */
		@SuppressWarnings("unchecked")
		@Override
		public boolean equals(Object otherObject){
			if (otherObject == null) return false;
			if (getClass() != otherObject.getClass())
				return false;
			TreeNode<E> other = (TreeNode<E>)otherObject;
			return el.equals(other.el);
		}
}
