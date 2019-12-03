package Ex1;

public class PolynomTree {

    PolynomNode root;

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
    public void printpreOrder() {
        printpreOrder(root);
        System.out.println();
    }
    void printpreOrder(PolynomNode node) {//PreOrder
        if (node != null) {
            System.out.print(node.OP+", ");
            printpreOrder(node.left);
            printpreOrder(node.right);
        }
    }


}
