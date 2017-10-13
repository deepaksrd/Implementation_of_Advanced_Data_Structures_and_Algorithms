package cs6301.g12.Implementation_of_Advanced_Data_Structures_and_Algorithms.sp7_q2_AVLTree;

import cs6301.g12.Implementation_of_Advanced_Data_Structures_and_Algorithms.sp7_q1_BST.BinarySearchTree;

public class AVLTree<T extends Comparable<? super T>> extends BinarySearchTree<T> {

	static public class AVLNode<T> extends Entry<T>{
		/*public T element;
		public AVLNode<T> left;
		public AVLNode<T> right;*/
		int height;

		public AVLNode(T x, AVLNode<T> left, AVLNode<T> right) {
			super(x,left,right);
			/*element = x;
			this.left = left;
			this.right = right;*/
			height = -1;
		}
	}

	protected Entry<T> root;
	public int size;

	public AVLTree() {
		root = null;
		size = 0;
	}

	public boolean insert(T x) {
		insert(root, x);
		return true;
	}

	public void insert(Entry<T> root, T x) {
		if (add(x)) {
			Entry<T> entry = stack.pop();
			while (entry != null) {
				int balance = height(entry);
				// Left Left case
				if (balance > 1 && x.compareTo(entry.left.element) < 0)
					rightRotate(entry);

				// Right Right Case
				if (balance < -1 && x.compareTo(entry.right.element) > 0)
					leftRotate(entry);

				// Left Right Case
				if (balance > 1 && x.compareTo(entry.left.element) > 0) {
					doubleRightRotate(entry);
				}

				// Right Left Case
				if (balance < -1 && x.compareTo(entry.right.element) < 0) {
					doubleLeftRotate(entry);
				}
				entry = stack.pop();
			}
			/*
			 * AVLNode<T> newNode = new AVLNode<>(x, null, null); if (root == null) { root =
			 * newNode;
			 * 
			 * root.height = 0; return root;
			 * 
			 * } else if (x.compareTo(root.element) < 0) { root.left = insert(root.left, x);
			 * 
			 * if ((getHeight(root.left) - getHeight(root.right)) == 2) { if
			 * (x.compareTo(root.left.element) < 0) root = leftRotate(root); else root =
			 * doubleLeftRotate(root); } } else if (x.compareTo(root.element) > 0) {
			 * root.right = insert(root.right, x);
			 * 
			 * if ((getHeight(root.right) - getHeight(root.left)) == 2) { if
			 * (x.compareTo(root.right.element) > 0) root = rightRotate(root); else root =
			 * doubleRightRotate(root); } }
			 * 
			 * root.height = Math.max(getHeight(root.left), getHeight(root.right))+1; return
			 * root;
			 */
		}
	}

	public Entry<T> leftRotate(Entry<T> node) {
		Entry<T> otherNode = node.left;
		node.left = otherNode.right;
		otherNode.right = node;

		node.height = Math.max(getHeight(node.left), getHeight(node.left)) + 1;
		otherNode.height = Math.max(getHeight(otherNode.left), node.height) + 1;
		return otherNode;
	}

	public Entry<T> rightRotate(Entry<T> node) {
		Entry<T> otherNode = node.right;
		node.right = otherNode.left;
		otherNode.left = node;

		node.height = Math.max(getHeight(node.left), getHeight(node.left)) + 1;
		otherNode.height = Math.max(getHeight(otherNode.left), node.height) + 1;
		return otherNode;
	}

	public Entry<T> doubleLeftRotate(Entry<T> node) {
		node.left = rightRotate(node.left);
		return leftRotate(node);
	}

	public Entry<T> doubleRightRotate(Entry<T> node) {
		node.right = leftRotate(node.right);
		return rightRotate(node);
	}

	public int getHeight(Entry<T> node) {
		return node == null ? -1 : node.height;
	}

	public static void main(String args[]) {

		AVLTree<Integer> tree = new AVLTree<Integer>();

		tree.insert(new Integer(2));
		tree.insert(new Integer(1));
		tree.insert(new Integer(4));
		tree.insert(new Integer(5));
		tree.insert(new Integer(9));
		tree.insert(new Integer(3));
		tree.insert(new Integer(6));
		tree.insert(new Integer(7));

		// tree.inOrder(tree.root);

	}
}
