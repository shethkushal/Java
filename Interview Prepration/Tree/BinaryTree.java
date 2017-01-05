package GeeksForGeeks.Tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class Node {
	int key;
	Node left, right;

	public Node(int item) {
		key = item;
		left = null;
		right = null;
	}
}

public class BinaryTree {

	Node root;

	public BinaryTree(int item) {
		root = new Node(item);
	}

	public BinaryTree() {
		root = null;
	}

	public void inorder(Node node) {
		if (node == null) {
			return;
		}
		inorder(node.left);
		System.out.print(node.key + " ");
		inorder(node.right);
	}

	public void inorderWRS(Node node) {
		Stack<Node> stack = new Stack<Node>();
		if (node == null) {
			return;
		}
		Node current = node;
		while (current != null) {
			stack.push(current);
			current = current.left;
		}
		while (stack.size() > 0) {
			current = stack.pop();
			System.out.print(current.key + " ");
			if (current.right != null) {
				current = current.right;
				while (current != null) {
					stack.push(current);
					current = current.left;
				}
			}
		}
	}

	public void inorderMorris(Node node) {
		Node current = node;
		while (current != null) {
			if (current.left == null) {
				System.out.print(current.key + " ");
				current = current.right;
			} else {
				Node predecessor = current.left;
				while (predecessor.right != null
						&& predecessor.right != current) {
					predecessor = predecessor.right;
				}
				if (predecessor.right == null) {
					predecessor.right = current;
					current = current.left;
				} else {
					predecessor.right = null;
					System.out.print(current.key + " ");
					current = current.right;
				}
			}
		}
	}

	public void preorderMorris(Node node) {
		Node current = node;
		while (current != null) {
			if (current.left == null) {
				System.out.print(current.key + " ");
				current = current.right;
			} else {
				Node predecessor = current.left;
				while (predecessor.right != null
						&& predecessor.right != current) {
					predecessor = predecessor.right;
				}
				if (predecessor.right == null) {
					predecessor.right = current;
					System.out.print(current.key + " ");
					current = current.left;
				} else {
					predecessor.right = null;
					current = current.right;
				}
			}
		}
	}

	public void preorder(Node node) {
		if (node == null) {
			return;
		}
		System.out.print(node.key + " ");
		preorder(node.left);
		preorder(node.right);
	}

	public void preorderWRS(Node node) {
		if (node == null) {
			return;
		}
		Stack<Node> stack = new Stack<Node>();
		Node current = node;
		stack.push(current);
		while (stack.size() > 0) {
			current = stack.pop();
			System.out.print(current.key + " ");
			if (current.right != null) {
				stack.push(current.right);
			}
			if (current.left != null) {
				stack.push(current.left);
			}
		}
	}

	public void postorder(Node node) {
		if (node == null) {
			return;
		}
		postorder(node.left);
		postorder(node.right);
		System.out.print(node.key + " ");
	}

	public void postorderWRS(Node node) {
		if (node == null) {
			return;
		}
		Stack<Node> stack1 = new Stack<Node>();
		Stack<Node> stack2 = new Stack<Node>();
		Node current = node;
		stack1.push(current);
		while (stack1.size() > 0) {
			current = stack1.pop();
			stack2.push(current);
			if (current.left != null) {
				stack1.push(current.left);
			}
			if (current.right != null) {
				stack1.push(current.right);
			}
		}
		while (stack2.size() > 0) {
			System.out.print(stack2.pop().key + " ");
		}
	}

	public void levelorder(Node node) {
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(node);
		while (!queue.isEmpty()) {
			Node tempNode = queue.poll();
			System.out.print(tempNode.key + " ");
			if (tempNode.left != null)
				queue.add(tempNode.left);
			if (tempNode.right != null)
				queue.add(tempNode.right);
		}
	}

	public void reverseLevelOrder(Node node) {
		Queue<Node> queue = new LinkedList<Node>();
		Stack<Node> stack = new Stack<Node>();
		Node current = node;
		queue.add(current);
		while (!queue.isEmpty()) {
			current = queue.poll();
			if (current.right != null) {
				queue.add(current.right);
			}
			if (current.left != null) {
				queue.add(current.left);
			}
			stack.push(current);
		}
		while (stack.size() > 0) {
			System.out.print(stack.pop().key + " ");
		}
	}

	public void spiralorder(Node node) {
		if (node == null) {
			return;
		}
		Stack<Node> stack1 = new Stack<Node>();
		Stack<Node> stack2 = new Stack<Node>();
		Node current = node;
		stack1.push(current);
		while (!stack1.isEmpty() || !stack2.isEmpty()) {
			while (!stack1.isEmpty()) {
				current = stack1.pop();
				if (current.left != null) {
					stack2.push(current.left);
				}
				if (current.right != null) {
					stack2.push(current.right);
				}
				System.out.print(current.key + " ");
			}
			while (!stack2.isEmpty()) {
				current = stack2.pop();
				if (current.right != null) {
					stack1.push(current.right);
				}
				if (current.left != null) {
					stack1.push(current.left);
				}
				System.out.print(current.key + " ");
			}
		}
	}

	public int height(Node node) {
		if (node == null) {
			return 0;
		}
		int lheight = height(node.left);
		int rheight = height(node.right);

		return lheight > rheight ? lheight + 1 : rheight + 1;
	}

	public int levelwidth(Node node) {
		int result = 0;
		int count = 0;
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(node);
		while (!queue.isEmpty()) {
			count = queue.size();
			result = result > count ? result : count;
			while (count > 0) {
				Node temp = queue.poll();
				if (temp.left != null) {
					queue.add(temp.left);
				}
				if (temp.right != null) {
					queue.add(temp.right);
				}
				count--;
			}
		}
		return result;
	}

	public void kNodesFromRoot(Node node, int k) {
		// (Without Recursion)
		// int result = 0;
		// int count = 0;
		// Queue<Node> queue = new LinkedList<Node>();
		// queue.add(node);
		// while (!queue.isEmpty()) {
		// count = queue.size();
		// k--;
		// result = result > count ? result : count;
		// while (count > 0) {
		// Node temp = queue.poll();
		// if (k == 0) {
		// System.out.print(temp.key + " ");
		// }
		// if (temp.left != null) {
		// queue.add(temp.left);
		// }
		// if (temp.right != null) {
		// queue.add(temp.right);
		// }
		// count--;
		// }
		// }
		// (With Recursion)
		if (node == null) {
			return;
		}
		if (k == 0) {
			System.out.print(node.key + " ");
			return;
		} else {
			kNodesFromRoot(node.left, k - 1);
			kNodesFromRoot(node.right, k - 1);
		}
	}

	public boolean ancestors(Node node, int key) {
		if (node == null) {
			return false;
		}
		if (node.key == key) {
			return true;
		}
		if (ancestors(node.left, key) || ancestors(node.right, key)) {
			System.out.print(node.key + " ");
			return true;
		}
		return false;
	}

	public Node mirror(Node node) {
		if (node == null) {
			return node;
		}
		Node left = mirror(node.left);
		Node right = mirror(node.right);

		node.left = right;
		node.right = left;

		return node;
	}

	public Node commonAncestors(Node node, int n1, int n2) {
		if (node == null) {
			return null;
		}
		if (node.key == n1 || node.key == n2) {
			return node;
		}
		Node left = commonAncestors(node.left, n1, n2);
		Node right = commonAncestors(node.right, n1, n2);

		if (left != null && right != null) {
			return node;
		}
		if (left == null && right == null) {
			return null;
		}
		return left != null ? left : right;
	}

	public int sumOfNodes(Node node) {
		if (node == null) {
			return 0;
		}
		int suml = sumOfNodes(node.left);
		int sumr = sumOfNodes(node.right);
		return suml + sumr + node.key;
	}

	public static void main(String[] args) {
		BinaryTree tree = new BinaryTree(1);
		// tree.root = new Node(1);
		tree.root.left = new Node(2);
		tree.root.right = new Node(3);
		tree.root.left.left = new Node(4);
		tree.root.right.left = new Node(5);
		System.out.println("In-Order Traversal: ");
		tree.inorder(tree.root);
		System.out.println("\nIn-Order Traversal(Without Recursion - Stack): ");
		tree.inorderWRS(tree.root);
		System.out.println("\nIn-Order Traversal(Morris): ");
		tree.inorderMorris(tree.root);
		System.out.println("\nPre-Order Traversal: ");
		tree.preorder(tree.root);
		System.out
				.println("\nPre-Order Traversal(Without Recursion - Stack): ");
		tree.preorderWRS(tree.root);
		System.out.println("\nPre-Order Traversal(Morris): ");
		tree.preorderMorris(tree.root);
		System.out.println("\nPost-Order Traversal: ");
		tree.postorder(tree.root);
		System.out
				.println("\nPost-Order Traversal(Without Recursion - Stack): ");
		tree.postorderWRS(tree.root);
		System.out.println("\nLevel-Order Traversal(Queue): ");
		tree.levelorder(tree.root);
		System.out.println("\nReverse Level-Order Traversal(Queue): ");
		tree.reverseLevelOrder(tree.root);
		System.out.println("\nSpiral-Order Traversal(Queue): ");
		tree.spiralorder(tree.root);
		System.out.println("\nDepth/Height of Tree: ");
		System.out.print(tree.height(tree.root));
		System.out.println("\nMaximum Level Width");
		System.out.print(tree.levelwidth(tree.root));
		System.out.println("\nK Nodes from root: ");
		tree.kNodesFromRoot(tree.root, 2);
		System.out.println("\nAncestors of given node: ");
		tree.ancestors(tree.root, 4);
		System.out.println("\nMirror effect: ");
		tree.mirror(tree.root);
		tree.inorder(tree.root);
		System.out.println("\nAncestor of given nodes is: ");
		System.out.println(tree.commonAncestors(tree.root, 2, 4).key);
		System.out.println("Sum of all the nodes is: ");
		System.out.println(tree.sumOfNodes(tree.root));
	}
}
