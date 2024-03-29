package Ex1;

import java.util.Comparator;

/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real number and a is an integer (summed a none negative),
 * see: https://en.wikipedia.org/wiki/Monomial
 * The class implements function and support simple operations as: construction, value at x, derivative, add and multiply.
 * @author Boaz
 *
 */
public class Monom implements function{
	public static final Monom ZERO = new Monom(0,0);
	public static final Monom MINUS1 = new Monom(-1,0);
	public static final double EPSILON = 0.0000001;
	public static final Comparator<Monom> _Comp = new Monom_Comperator();
	public static Comparator<Monom> getComp() {return _Comp;}
	public Monom(double a, int b){
		this.set_coefficient(a);
		this.set_power(b);
	}
	public Monom(Monom ot) {
		this(ot.get_coefficient(), ot.get_power());
	}

	public double get_coefficient() {
		return this._coefficient;
	}
	public int get_power() {
		return this._power;
	}
	/**
	 * this method returns the derivative monom of this.
	 * @return
	 */
	public Monom derivative() {
		if(this.get_power()==0) {
			return getNewZeroMonom();
		}
		return new Monom(this.get_coefficient()*this.get_power(), this.get_power()-1);
	}

	/**
	 *
	 * function that Calculates the value in the function
	 */
	public double f(double x) {
		double ans=0;
		double p = this.get_power();
		ans = this.get_coefficient()*Math.pow(x, p);
		return ans;
	}
	public boolean isZero() {return this.get_coefficient() == 0;}
	// ****** add your code below *********

	/**
	 *
	 * Monom(String) function that make monom from String That the user puts in
	 *
	 */
	public Monom(String s) {
		try {
			String num = "";
			String pow = "";
			int x = 0;
			while (x < s.length()) {
				if (s.charAt(x) == 'x' || s.charAt(x) == 'X') break; // count until found char x or X
				num = num + s.charAt(x);
				x++;
			}
			double mekadem = 0;
			if (s.charAt(0) == 'x' || s.charAt(0) == 'X') mekadem = 1; // if the string not have coefficient
			else if ((s.charAt(0) == '+') && (s.charAt(1) == 'x' || s.charAt(1) == 'X')) mekadem = 1;
			else if (( s.charAt(0) == '-') && (s.charAt(1) == 'x' || s.charAt(1) == 'X')) mekadem = -1;
			else mekadem = Double.parseDouble(num);

			if (s.contains("^")) {
				x = x + 2; // check the power of the monom
				while (x < s.length()) {
					pow = pow + s.charAt(x);
					x++;
				}
			} else if ((s.contains("x") || s.contains("X")) && !s.contains("^")) pow = "1";
			else pow = "0";
			if (pow.isEmpty()) {
				pow = "0";
			}
			int power = Integer.parseInt(pow);

			this._power = power;
			this._coefficient = mekadem;
		}
		catch (Exception e) // do Exception if the string was incorrect
		{
			System.out.println("Alert , The String that you try to put in is incorrect");
		}
	}

	public void add(Monom m) {
		this._coefficient = this._coefficient + m._coefficient;
	}

	public void multipy(Monom d) {
		this._coefficient = this._coefficient*d._coefficient;
		this._power = this._power + d._power;
	}

	public String toString() {
		return this._coefficient + "x^" + this._power;
	}
	@Override
	public function initFromString(String s) {
		return new Monom(s);
	}

	@Override
	public function copy() {
		Monom m = new Monom(this._coefficient,this._power);
		return m;
	}

	public void toSetcoefficient(double a){
		set_coefficient(a);
	}

	public void toSetPower(int a){
		set_power(a);
	}

	private void set_coefficient(double a){
		this._coefficient = a;
	}
	private void set_power(int p) {
		if(p<0) {throw new RuntimeException("ERR the power of Monom should not be negative, got: "+p);}
		this._power = p;
	}
	private static Monom getNewZeroMonom() {return new Monom(ZERO);}
	private double _coefficient;
	private int _power;

	/**
	 *
	 * @param obji
	 * @return true if this ans m is equals
	 */
	public boolean equals(Object obji){
		if(obji instanceof Monom) {
			Monom m = (Monom)obji;
			boolean flag1 = false;
			boolean flag2 = false;
			if ((m.get_power() == this.get_power())) flag1 = true;
			double dis = this.get_coefficient() - m.get_coefficient();
			if ((Math.abs(dis) < EPSILON)) flag2 = true;
			return (flag2 && flag1);
		}
		if(obji instanceof function){
			function f = (function) obji;
			return f.equals(this);
		}
		if(obji instanceof Polynom){
			Polynom p = (Polynom) obji;
			if(p.PolynomList.size()>1){return false;}
			Monom check = p.PolynomList.get(0);
			Monom check2 = this;
			if(check.get_coefficient()==check2.get_coefficient() && check.get_power()==check2.get_power()){return true;}
			else return false;
		}

		return false;
	}
	public String returnMonomToString(){
		return this.get_coefficient() + "x^" +this.get_power();
	}
}