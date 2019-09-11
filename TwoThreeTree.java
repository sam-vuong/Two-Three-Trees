public class TwoThreeTree {

	private Node root;

	public TwoThreeTree() {
		root = null;
	}

	public boolean insert(int x) {
		if (root == null) {
			Node newNode = new Node(x);
			root = newNode;
			return true;
		} else {
			root.insert(x);
			return true;
		}
	}
	
	public String search(int x) {
		Node location = root.search(x);
		if (location.numKeys == 1) return Integer.toString(location.keys[0]);
		else return Integer.toString(location.keys[0]) + " " + Integer.toString(location.keys[1]);
	}

	class Node {

		private int[] keys;
		private Node[] children;
		private int numKeys;
		private Node parent;
		
		public Node(int key) {
			keys = new int[3];
			children = new Node[4];
			keys[0] = key;
			numKeys++;
		}
		
		private Node search(int key) {
			
			//look for duplicates
			for (int i=0; i < numKeys; i++) {
				if (keys[i] == key) return this;
			}
			//check if leaf node has been reached
			if (children[0] == null)
				return this;
			//leaf node has not yet been reached; recursively search/traverse
			int a = 0;
			while (a < numKeys && key > keys[a]) {
				a++;
			}
			//assumes that if there are two children, they are at 0 and 1
			return children[a].search(key);
		}

		private void addKey(int key) {
			//check for duplicates
			for (int i=numKeys-1; i>=0 ; i--) {
				if (key == keys[i]) return;
			}
			//no duplicates; add key to appropriate location within array
			int index = numKeys-1;
			while(index>=0 && keys[index]>key)
			{
				keys[index+1]= keys[index];
				index--;
			}
			keys[index+1] = key;
			numKeys++;
			
			if (numKeys == 3) { //overfull node
				if (parent == null)
					reconstruct(keys[0], keys[1], keys[2], children[0], children[1], children[2], children[3]);
				else {
					promote(keys[1]);
				}
				
			}
				
		}
		
		
		//takes three nodes and splits it
		private void reconstruct(int key1, int key2, int key3, Node child1, Node child2, Node child3, Node child4) {
			numKeys = 1;
			this.keys[0] = key2;
			this.keys[1] = 0;
			this.keys[2] = 0;
			this.children[2] = null;
			this.children[3] = null;
			Node left = new Node(key1);
			Node right = new Node(key3);
			this.children[0] = left;
			left.parent = this;
			this.children[1] = right; 
			right.parent = this;
			left.children[0] = child1;
			left.children[1] = child2;
			right.children[0] = child3;
			right.children[1] = child4;
			if (left.children[0] != null) left.children[0].parent = left;
			if (left.children[1] != null) left.children[1].parent = left;
			if (right.children[0] != null) right.children[0].parent = right;
			if (right.children[1] != null) right.children[1].parent = right;
			
		}
		
		private void promote(int key) {
			Node child2 = new Node(keys[2]);
			child2.children[0] = this.children[2];
			child2.children[1] = this.children[3];
			if (child2.children[0] != null) child2.children[0].parent = child2;
			if (child2.children[1] != null) child2.children[1].parent = child2;
			this.keys[1] = 0;
			this.keys[2] = 0;
			this.numKeys = 1;
			this.children[2] = null;
			this.children[3] = null;
			if (parent.numKeys != 2) {
				if (parent.children[0].keys[0] == keys[0]) {
					parent.children[0] = this;
					Node temp = parent.children[1];
					parent.children[2] = temp;
					parent.children[1] = child2;
					child2.parent = parent;
				}
				else {
					parent.children[1] = this;
					parent.children[2] = child2;
					child2.parent = parent;
				}
			}
			else if (parent.numKeys == 2) {
				Node temp = parent.children[1];
				Node temp2 = parent.children[2];
				if (parent.children[0].keys[0] == keys[0]) {
					parent.children[0] = this;
					parent.children[1] = child2;
					child2.parent = parent;
					parent.children[2] = temp;
					parent.children[3] = temp2;
				}
				else if (parent.children[1].keys[0] == keys[0]) {
					parent.children[1] = this;
					parent.children[2] = child2;
					child2.parent = parent;
					parent.children[3] = temp2;
				}
				else {
					parent.children[2] = this;
					parent.children[3] = child2;
					child2.parent = parent;
				}
			}
			parent.addKey(key);
		}
		
		private void insert(int x) {
			Node location = search(x);
			location.addKey(x);
		}
	}
}
