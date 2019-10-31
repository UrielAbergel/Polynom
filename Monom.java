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
		if(this.get_power()==0) {return getNewZeroMonom();}
		return new Monom(this.get_coefficient()*this.get_power(), this.get_power()-1);
	}
	public double f(double x) {
		double ans=0;
		double p = this.get_power();
		ans = this.get_coefficient()*Math.pow(x, p);
		return ans;
	}
	public boolean isZero() {return this.get_coefficient() == 0;}
	// ***************** add your code below **********************
	public Monom(String s) {
		String num = "";
		String pow = "";
		int x = 0;
		while(x < s.length()) {
			if (s.charAt(x) == 'x' || s.charAt(x) == 'X') break;
			num = num + s.charAt(x);
			x++;
		}
		double mekadem = 0;
		if(s.charAt(0) == 'x' || s.charAt(0) == 'X') mekadem = 1;
		else if((s.charAt(0) == '+' || s.charAt(0) == '-') && (s.charAt(1) == 'x' || s.charAt(1) == 'X')) mekadem = -1;
		else mekadem = Double.parseDouble(num);

		if(s.contains("^")) {
			x = x + 2;
			while (x < s.length()) {
				pow = pow + s.charAt(x);
				x++;
			}
		}
		else pow = "1";
		if(pow.isEmpty()) {pow = "0";}
		int power = Integer.parseInt(pow);

		this._power = power ;
		this._coefficient = mekadem;
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
	// you may (always) add other methods.

	//****************** Private Methods and Data *****************


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

	public static void main(String[] args) {
		String s = "+12.59x^4";
		Monom a = new Monom(s);
		System.out.println(a._coefficient);
		System.out.println(a._power);
	}

}
