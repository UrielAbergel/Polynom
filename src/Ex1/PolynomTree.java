package Ex1;

public class PolynomTree {

    PolynomNode root,left,right;

    public void printInOrder() {
        printInOrder(root);
        System.out.println();
    }
    void printInOrder(PolynomNode node) {//PreOrder
        if (node != null) {
            printInOrder(node.left);
            System.out.print(node.OP+", ");
            printInOrder(node.right);
        }
    }


}
