import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MonomTest {

    @Test
    void derivative() {
        double ans ;
        Monom test1 = new Monom("5x^3");
        Monom test2 = new Monom("x");
        Monom test3 = new Monom("x^2");
        test1.derivative();
        test2.derivative();
        test3.derivative();

    }

    @Test
    void f() {
    }

    @Test
    void add() {
    }

    @Test
    void multipy() {
    }
}