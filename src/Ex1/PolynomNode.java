package Ex1;

public class PolynomNode {
    Monom mon;
    Polynom poly;
    PolynomNode left, right, father;
    boolean Excist;
    Operation OP;
    function func;

    PolynomNode(Object p) {
        if(p instanceof Polynom){
            Polynom p1 = (Polynom)(p);
            new PolynomNode(p1);
        }
        else if(p instanceof Monom){
            Monom m1 = (Monom)(p);
            new PolynomNode(m1);
        }
        else if(p instanceof function){
                function f = (function)(p);
                new PolynomNode(f);
        }
        else if(p instanceof Operation){
            Operation O = (Operation)(p);
            new PolynomNode(O);
        }
    }
    PolynomNode(Polynom p){
        this.poly.PolynomList = p.PolynomList;
        OP = Operation.None;
        left = null;
        right = null;
    }
    PolynomNode(Monom m){
        OP = Operation.None;
        this.mon = m;
        left = null;
        right = null;
    }
    PolynomNode(function f){
        OP = Operation.None;
        this.func = f;
        left = null;
        right = null;
    }
    PolynomNode(Operation O){
        OP = O;
        left = null;
        right = null;
    }
    PolynomNode(PolynomNode left,PolynomNode right){
        this.left = left;
        this.right = right;
    }
    public void setOP(Operation op){
        this.OP = op;
    }
}