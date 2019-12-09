package Ex1;

import java.security.cert.PolicyNode;
import java.util.LinkedList;

public class PolynomTree {
    String SaveToFileReader = "";
    PolynomNode root;
    int flag = 0;
    public PolynomTree(){
        this.root = null;
    }
    public PolynomTree(function f){
        ComplexFunction s = new ComplexFunction();
        s = (ComplexFunction) f;
        this.root = s.pt.root;
    }

    public void printInOrder() {
        printInOrder(root);
        System.out.println();
    }

    private void printInOrder(PolynomNode node) {//PreOrder
        if (node != null) {
            printInOrder(node.left);
            System.out.print(node.OP + ", ");
            printInOrder(node.right);
        }
    }

    private void printpreOrder() {
        SaveToFileReader ="";
        printpreOrder(root);
        this.fixSaveToFile();
    }

    private void printpreOrder(PolynomNode node) {//PreOrder

        if (node != null) {

            if (node.func != null)
            {
                SaveToFileReader += node.func.toString();
            }

            if (node.OP != Operation.None)
            {
                SaveToFileReader += node.toStringOP(node.OP);
            }
            printpreOrder(node.left);
            printpreOrder(node.right);
            SaveToFileReader += ",";
        }
    }

    public void fixSaveToFile(){
        char[] arr = SaveToFileReader.toCharArray();
        for (int i = 0; i < arr.length-1; i++) {
            if(arr[i] == ',' && arr[i+1] == ',')
            {
               arr[i] = ')' ;
            }

        }
        String s = "";
        for (int i = 0; i < arr.length-1; i++) {
            s += arr[i];
        }
        SaveToFileReader = s;
    }

    public String toString(){
        printpreOrder();
        return SaveToFileReader;
    }


    public static void main(String[] args) {

    }
}
