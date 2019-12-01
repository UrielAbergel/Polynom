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
    }
    PolynomNode(Polynom p){
        this.p.PolynomList = p.PolynomList;
        left = null;
        right = null;
    }
}