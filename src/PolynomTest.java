import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class PolynomTest {

    @Test
    void f() {
        Polynom test1 = new Polynom("-x^4+5x^3+444");
        Polynom test2 = new Polynom("-x");
        Polynom test3 = new Polynom("0");
        Polynom test4 = new Polynom("-10");
        Polynom test5 = new Polynom("10-x^5+x^4");
        double test11 = test1.f(3);
        double test12 = test1.f(0);
        double test13 = test2.f(-1);
        double test14 = test3.f(10);
        double test15 = test4.f(1000);
        double test16 = test5.f(-4);
        assertEquals(498,test11);
        assertEquals(444,test12);
        assertEquals(1,test13);
        assertEquals(0,test14);
        assertEquals(-10,test15);
        assertEquals(1290,test16);
    }

    @Test
    void add() {
        Polynom a = new Polynom("x^4+x^3+x^2+x+1");
        Polynom a1 = new Polynom("-x^4-x^3-x^2-x-1");
        Polynom a2 = new Polynom("3x^4+3x^3+3x^2+3x+1");
        Polynom a3 = new Polynom("1+2+4+5+0000");
        a.add(a1);
        a.add(a2);
        a.add(a3);
        assertEquals("3.0x^4+3.0x^3+3.0x^2+3.0x^1+13.0x^0+",a.returnPolynomToString());
    }

    @Test
    void testAddMonom() {
        Polynom a = new Polynom("x^4+x^3+x^2+x+1");
        Monom m1 = new Monom("5");
        Monom m2 = new Monom("5x");
        Monom m3 = new Monom("-15x^2");
        Monom m4 = new Monom("x^100");
        a.add(m1);
        a.add(m2);
        a.add(m3);
        a.add(m4);
        assertEquals("1.0x^100+1.0x^4+1.0x^3+-14.0x^2+6.0x^1+6.0x^0+",a.returnPolynomToString());
    }

    @Test
    void substract() {
        Polynom test1 = new Polynom("5x^2+3x^2+2");
        Polynom test2 = new Polynom("x-8x");
        Polynom test3 = new Polynom("5x^6-8x^2");
        Polynom test4 = new Polynom("0");
        Polynom test5 = new Polynom("8");
        test1.substract(test2);
        test1.substract(test3);
        test1.substract(test4);
        test1.substract(test5);
        assertEquals("-5.0x^6+16.0x^2+7.0x^1+-6.0x^0+",test1.returnPolynomToString());
    }

    @Test
    void multiply() {
        Polynom test1 = new Polynom("5x^2+3x^2+2");
        Polynom test2 = new Polynom("x-8x");
        Polynom test3 = new Polynom("5x^6-8x^2");
        test1.multiply(new Monom("0"));
        test2.multiply(new Monom("x"));
        test3.multiply(new Monom("3x^2"));
        assertEquals("0.0x^0+",test1.returnPolynomToString());
        assertEquals("-7.0x^2+",test2.returnPolynomToString());
        assertEquals("15.0x^8+-24.0x^4+",test3.returnPolynomToString());
        Polynom p = new Polynom("-5x^3+2x^2+4x+15");
        Polynom a = new Polynom("-x");
        Polynom b = new Polynom("x^2");
        Polynom c = new Polynom("-3x^3");
        Polynom r = new Polynom("-5x^3+2x^2+4x+15");
        Polynom t = new Polynom("-5x^3+2x^2+4x+15");
        int counter = 0;
        p.multiply(a);
        assertEquals(p.PolynomList.get(0).get_power() == 4 , p.PolynomList.get(0).get_coefficient() == 5);
        p = r;
        p.multiply(b);
        assertEquals(p.PolynomList.get(0).get_power() == 5, p.PolynomList.get(0).get_coefficient() == -5);
        p = t;
        p.multiply(c);
        assertEquals(p.PolynomList.get(0).get_power() == 6 ,p.PolynomList.get(0).get_coefficient() == 15);
        assertEquals(p.PolynomList.get(1).get_power() == 5 ,p.PolynomList.get(1).get_coefficient() == -6);


    }

    @Test
    void testEquals() {
        Polynom test1 = new Polynom("5x^2+3x^2+2");
        Polynom test2 = new Polynom("x-8x");
        Polynom test3 = new Polynom("5x^6-8x^2");
        Polynom test11 = new Polynom("5x^2+3x^2+2");
        Polynom test22 = new Polynom("x-8x");
        Polynom test33 = new Polynom("5x^6-8x^2");
        assertEquals(test1,test11);
        assertEquals(test2,test22);
        assertEquals(test3,test33);
        Polynom_able test4 = new Polynom("5x^2+3x^2+2");
        Polynom_able test5 = new Polynom("5x^2+3x^2+2");
        Polynom_able test6 = new Polynom("5x^2+3x^2+2");
        Polynom_able test44 = new Polynom("5x^2+3x^2+2");
        Polynom_able test55 = new Polynom("5x^2+3x^2+2");
        Polynom_able test66 = new Polynom("5x^2+3x^2+2");
        test4.add(new Monom("5x"));
        assertNotEquals(test4,test44);
        assertEquals(test5,test55);
        assertEquals(test6,test66);
    }

    @Test
    void root() {
        /**
         * need to return  -1 , 1 - or close to it.
         */
        Polynom a = new Polynom("5x+5");
        Polynom b = new Polynom("-5x+5");

        assertEquals(-1 ,a.root(-5, 5, 0.001) ,0.1 );
        /**
         * Need to return -2.747
         */

        Polynom a1 = new Polynom("-x^3+5x-7");
        assertEquals(-2.747 ,a1.root(-4, 0, 0.01),0.1);
    }


    @Test
    void copy() {
        Polynom p = new Polynom("5x^2+3x^1+2x");
        Polynom q = new Polynom("5x^2");
        Polynom_able t = q;
        Iterator<Monom> ite = t.iteretor();
        t = p.copy();
        Polynom w = new Polynom("5x^2");
        p.substract(w);
        assertEquals(t.iteretor().next().get_coefficient(),5);
    }

    @Test
    void derivative() {
        Polynom test1 = new Polynom("5x^2+3x^2+2");
        test1.derivative();
        Polynom test2 = new Polynom("x-8x");
        test2.derivative();
        Polynom test3 = new Polynom("5x^6-8x^2");
        test3.derivative();
        Polynom test11 = new Polynom("16x^1");
        Polynom test22 = new Polynom("-7");
        Polynom test33 = new Polynom("30x^5-16x^1");
        assertEquals(test1.equals(test11),true);
        assertEquals(test2.equals(test22),true);
        assertEquals(test3.equals(test33),true);

    }

    @Test
    void area() {
        Polynom t = new Polynom("-5x^3+2x^2+4x+15");
        Polynom r = new Polynom("-3x^2+2");
        Polynom s = new Polynom("4x^5-5x^3+2x^2+4x+15+1");
        double d = s.area(-1.5, 0, 0.000001);
        assertEquals(d,20.484375,0.1);
        double e = r.area(-0.8, 0.8, 0.1);
        assertEquals(e,2.176,0.1);
        double q = t.area(0, 1.787, 0.1);
        System.out.println(q);
        assertEquals(q,24.24909797263208,0.1);

    }
}