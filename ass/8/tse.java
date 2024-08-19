package dsaa;

public class tse {
    static class node {
        long val;
        long height;
        node left;
        node right;

        public node(long val) {
            this.val = val;
            this.height = 0;
            this.left = null;
            this.right = null;
        }
    }

    static class AVLTree {
        node root;
        int size;

        public AVLTree() {
            this.root = null;
            this.size = 0;
        }

        public long treeHeight(node no) {
            if (no != null) return no.height;
            return 0;
        }

        public long Factor(node no) {
            if (no == null) return 0;
            else {
                if (no.left == null && no.right == null) return 0;
                else if (no.right == null) return (no.left.height - no.height);
                else if (no.left == null) return (no.right.height - no.height);
                else return (no.left.height - no.right.height);
            }
        }

        public boolean isBalanced(node no) {
            if (no == null) return true;
            long fac = Math.abs(Factor(no));
            if (fac > 1) return false;
            return (isBalanced(no.right) && isBalanced(no.left));
        }

        public node rightRotate(node y) {
            node x = y.left;
            node tmp = x.right;

            x.right = y;
            y.left = tmp;

            y.height = 1 + Math.max(treeHeight(y.left), treeHeight(y.right));
            x.height = 1 + Math.max(treeHeight(x.left), treeHeight(x.right));
            return x;
        }

        public node leftRotate(node y) {
            node x = y.right;
            node tmp = x.left;

            x.left = y;
            y.right = tmp;

            y.height = 1 + Math.max(treeHeight(y.left), treeHeight(y.right));
            x.height = 1 + Math.max(treeHeight(x.left), treeHeight(x.right));
            return x;
        }

        public node rebalance(node no) {
            //LL
            if (Factor(no) > 1) {
                //LR
                if (Factor(no.left) < 0) {
                    no.left = leftRotate(no.left);
                }
                return rightRotate(no);
            }
            //RR
            if (Factor(no) < -1) {
                //RL
                if (Factor(no.right) > 0) {
                    no.right = rightRotate(no.right);
                }
                return leftRotate(no);
            }

            return no;
        }

        public node search(long val) {
            return search(root, val);
        }

        public node search(node root, long val) {
            while (root != null) {
                if (val < root.val) root = root.left;
                else if (val > root.val) root = root.right;
                else break;
            }
            return root;
        }

        public node minNode() {
            return minNode(this.root);
        }

        public node minNode(node no) {
            if (no == null) return null;
            while (no.left != null) no = no.left;
            return no;
        }

        public node maxNode() {
            return maxNode(this.root);
        }

        public node maxNode(node no) {
            if (no == null) return null;
            while (no.right != null) no = no.right;
            return no;
        }

        public void add(long val) {
            this.root = add(this.root, val);
        }

        public node add(node no, long val) {
            if (no == null) {
                no = new node(val);
                this.size++;
            } else {
                if (val < no.val) {
                    no.left = add(no.left, val);
                } else if (val > no.val) {
                    no.right = add(no.right, val);
                }

                no.height = 1 + Math.max(treeHeight(no.left), treeHeight(no.right));
            }
            rebalance(no);
            return no;
        }

        public void remove(long val) {
            node tmp;
            if ((tmp = search(val)) != null) remove(this.root, tmp);
        }

        public node remove(node no, node out) {
            if (no == null || out == null) return null;
            if (out.val < no.val) {
                no.left = remove(no.left, out);
                rebalance(no);
            } else if (out.val > no.val) {
                no.right = remove(no.right, out);
                rebalance(no);
            } else {
                if (no.left != null && no.right != null) {
                    if (treeHeight(no.left) > treeHeight(no.right)) {
                        node pred = maxNode(no.left);
                        no.val = pred.val;
                        no.left = remove(no.left, pred);
                    } else {
                        node succ = minNode(no.right);
                        no.val = succ.val;
                        no.right = remove(no.right, succ);
                    }
                } else {
                    node tmp = no;
                    no = (no.left != null) ? no.left : no.right;
                    tmp = null;
                }
            }
            rebalance(no);
            return no;
        }
    }

    public static void main(String[] args) {
        AVLTree a = new AVLTree();
        a.root = new node(10);
        for (int i = 0; i < 10; i++) {
            a.add(i);
        }
        for (int i = 11; i < 21; i++) {
            a.add(i);
        }
        System.out.print("");
    }
}
