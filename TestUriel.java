public class TestUriel {

    public static void testForPolynom() {
        Polynom a1 = new Polynom("1+1+1+1");
        Polynom b1 = new Polynom("x+x+x+x+x+x");
        Polynom c1 = new Polynom("5x^4+5x+x^2");
        Polynom d1 = new Polynom("2+3");
        if (a1.PolynomList.get(0).get_coefficient() == 4) System.out.println("Ok");
        else System.out.println("Bad");
        if (b1.PolynomList.get(0).get_coefficient() == 6) System.out.println("Ok");
        else System.out.println("Bad");
        if (c1.PolynomList.get(0).get_coefficient() == 5 && c1.PolynomList.get(1).get_coefficient() == 1 && c1.PolynomList.get(2).get_coefficient() == 5)
            System.out.println("Ok");
        else System.out.println("Bad");
        if (d1.PolynomList.get(0).get_coefficient() == 5) System.out.println("Ok");
        else System.out.println("Bad");
        d1.toStr();
    }

    public static void testForAdd() {
        Polynom a = new Polynom("x^4+x^3+x^2+x+1");
        Polynom a1 = new Polynom("-x^4-x^3-x^2-x-1");
        Polynom a2 = new Polynom("3x^4+3x^3+3x^2+3x+1");
        Polynom a3 = new Polynom("1+2+4+5+0000");
        a.add(a1);
        a.add(a2);
        a.add(a3);
        a.toStr();
        System.out.println("If its print 3.0x^4+3.0x^3+3.0x^2+3.0x^1+13.0x^0 its OK. Else NOT!! ");
    }

    public static void substractTest() {
        Polynom a = new Polynom("x^4+x^3+x^2+x+1");
        Polynom a1 = new Polynom("-x^4-x^3-x^2-x-1");
        Polynom a2 = new Polynom("3x^4+3x^3+3x^2+3x+1");
        Polynom a3 = new Polynom("1+2+4+5+0000");
        Polynom a4 = new Polynom("11x^0-3x^4-3x^3-3x^2-3x^1");
        Polynom a5 = new Polynom("0+x");

        a.substract(a1);
        if (a.PolynomList.get(0).get_coefficient() == 2 && a.PolynomList.get(1).get_coefficient() == 2 && a.PolynomList.get(2).get_coefficient() == 2 && a.PolynomList.get(3).get_coefficient() == 2 && a.PolynomList.get(4).get_coefficient() == 2)
            System.out.println("Ok");
        else System.out.println("Bad");
        a3.substract(a2);
        if (a3.PolynomList.get(0).get_coefficient() == -3 && a3.PolynomList.get(1).get_coefficient() == -3 && a3.PolynomList.get(2).get_coefficient() == -3 && a3.PolynomList.get(3).get_coefficient() == -3 && a3.PolynomList.get(4).get_coefficient() == 11)
            System.out.println("Ok");
        else System.out.println("Bad");
        a4.substract(a5);
        a4.toStr();
    }

    public static void testForEquals() {
        /**
         * Should be
         * true
         * true
         * true
         * false
         */
        Polynom a = new Polynom("1");
        Polynom a1 = new Polynom("1");
        Polynom a2 = new Polynom("1+0");
        System.out.println(a.equals(a1));
        System.out.println(a.equals(a2));
        Polynom b = new Polynom("x^4+0+3+3+3+10x+1");
        Polynom b1 = new Polynom("10+10x+x^4");
        System.out.println(b.equals(b1));
        Polynom c = new Polynom("1");
        Polynom c1 = new Polynom("0");
        System.out.println(c.equals(c1));
    }

    public static void testForRoot() {
        /**
         * need to return  -1 , 1 - or close to it.
         */
        Polynom a = new Polynom("5x+5");
        Polynom b = new Polynom("-5x+5");
        System.out.println(a.root(-5, 5, 0.001));
        System.out.println(b.root(-5, 5, 0.001));
        /**
         * Need to return -2.747
         */
        Polynom a1 = new Polynom("-x^3+5x-7");
        System.out.println(a1.root(-4, 0, 0.01));
    }

    public static void testAddMonom(Polynom p) {
        Monom a = new Monom("5x^3");
        Monom b = new Monom("5x^3");
        Monom c = new Monom("0x^2");
        Monom d = new Monom("-3x");
        p.add(a);
        p.add(b);
        p.add(c);
        p.add(d);

        int counter = 0;

        if (p.PolynomList.get(0).get_coefficient() == 5) {
            counter++;
        }
        if (p.PolynomList.get(2).get_coefficient() == 1) {
            counter++;
        }
        if (counter == 2) {
            System.out.println("goodTest");
        }

    }

    public static void testMultiply(Polynom p) {
        Polynom a = new Polynom("-x");
        Polynom b = new Polynom("x^2");
        Polynom c = new Polynom("-3x^3");
        Polynom r = new Polynom("-5x^3+2x^2+4x+15");
        Polynom t = new Polynom("-5x^3+2x^2+4x+15");
        int counter = 0;
        p.multiply(a);
        if (p.PolynomList.get(0).get_power() == 4 && p.PolynomList.get(0).get_coefficient() == 5) {
            counter++;
        }
        p = r;
        p.multiply(b);
        if (p.PolynomList.get(0).get_power() == 5 && p.PolynomList.get(0).get_coefficient() == -5) {
            counter++;
        }
        if (p.PolynomList.get(1).get_power() == 4 && p.PolynomList.get(1).get_coefficient() == 2) {
            counter++;
        }
        p = t;
        p.multiply(c);
        if (p.PolynomList.get(0).get_power() == 6 && p.PolynomList.get(0).get_coefficient() == 15) {
            counter++;
        }
        if (p.PolynomList.get(1).get_power() == 5 && p.PolynomList.get(1).get_coefficient() == -6) {
            counter++;
        }
        if (counter == 5) {
            System.out.println("goodTest");
        }
    }

    public static void testIsZero(Polynom p) {
        int counter = 0;
        if (p.isZero()) {
            System.out.println("false");
        }
        Polynom r = new Polynom("0");
        p = r;
        if (p.isZero()) {
            counter++;
        }
        if (counter == 1) {
            System.out.println("goodTest");
        }
    }

    public static void testArea() {
        Polynom t = new Polynom("-5x^3+2x^2+4x+15");
        Polynom r = new Polynom("-3x^2+2");
        Polynom s = new Polynom("4x^5-5x^3+2x^2+4x+15+1");
        double d = s.area(-1.5, 0, 0.00001);
        System.out.println(d + "the currect is 18.984375");
        double e = r.area(-0.8, 0.8, 0.00001);
        System.out.println(e + "the currect is 2.176");
        double q = t.area(0, 1.787, 0.00001);
        System.out.println(q + "the currect is 24.24909797263208");
    }

    public static void testMultiplyMonom() {
        Polynom p = new Polynom("-5x^3+2x^2+4x+15");
        Monom r = new Monom("3x^2");
        Monom q = new Monom("x");
        p.multiply(r);
        int counter = 0;
        if (p.PolynomList.get(0).get_coefficient() == -15 && p.PolynomList.get(0).get_power() == 5) {
            counter++;
        }
        if (p.PolynomList.get(3).get_coefficient() == 45 && p.PolynomList.get(0).get_power() == 2) {
            counter++;
        }
        p.multiply(q);
        if (p.PolynomList.get(0).get_coefficient() == -15 && p.PolynomList.get(0).get_power() == 6) {
            counter++;
        }
        if (p.PolynomList.get(3).get_coefficient() == 45 && p.PolynomList.get(0).get_power() == 3) {
            counter++;
        }
        if (counter == 4) System.out.println("goodTest");
    }

    public static void main(String[] args) {
        Polynom p = new Polynom("-5x^3+2x^2+4x+15");

//        testForPolynom();
//        testForAdd();
//        substractTest();
//        testForEquals();
//        testForRoot();
//        testMultiply(p);
//        testAddMonom(p);
//        testIsZero(p);
//        testArea();
        testMultiplyMonom();
    }


}
