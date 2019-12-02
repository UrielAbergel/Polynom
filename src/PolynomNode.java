public class PolynomNode {
    Monom m;
    Polynom p;
    PolynomNode left, right, father;
    Operation OP;
    function f;

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
        this.p.PolynomList = p.PolynomList;
        left = null;
        right = null;
    }
    PolynomNode(Monom m){
        left = null;
        right = null;
    }
    PolynomNode(function f){
        left = null;
        right = null;
    }
    PolynomNode(Operation O){
        left = null;
        right = null;
    }
}