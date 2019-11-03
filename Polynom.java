import java.util.LinkedList;
import java.util.Iterator;


/**
 * This class represents a Polynom with add, multiply functionality, it also should support the following:
 * 1. Riemann's Integral: https://en.wikipedia.org/wiki/Riemann_integral
 * 2. Finding a numerical value between two values (currently support root only f(x)=0).
 * 3. Derivative
 *
 * @author Boaz
 *
 */
public class Polynom implements Polynom_able{
	LinkedList<Monom> PolynomList;
	/**
	 * Zero (empty polynom)
	 */
	public Polynom() {

	}
	/**
	 * init a Polynom from a String such as:
	 *  {"x", "3+1.4X^3-34x", "(2x^2-4)(-1.2x-7.1)", "(3-3.4x+1)((3.1x-1.2)-(3X^2-3.1))"};
	 * @param s: is a string represents a Polynom
	 */

	public Polynom(String s) {
		String t = "";
		int Startindex  = 0 ,EndIndex = 0;
		LinkedList<Monom> TempMonomList = new LinkedList<Monom>();
		for (int i = 0 ; i < s.length() ; i++) {
			if (s.charAt(i) == '(' || s.charAt(i) == ')') {
				EndIndex = s.length()+1	;
			}
		}
		while (EndIndex < s.length()) {
			if (EndIndex != 0) {
				if (s.charAt(EndIndex) == '+' || s.charAt(EndIndex) == '-' || EndIndex == s.length()-1) {

					t = s.substring(Startindex,EndIndex);
					if(EndIndex == s.length()-1) t = t +s.charAt(s.length()-1);
					Monom newMonom = new Monom(t);
					TempMonomList.add(newMonom);
					Startindex = EndIndex;
				}
			}
			EndIndex++;
		}
		PolynomList = TempMonomList;
	}
	@Override
	public double f(double x) {

		int Index = 0;
		double sum =0;
		while(Index<PolynomList.size()){
			sum = sum + PolynomList.get(Index++).f(x);
		}
		return sum;
	}

	@Override
	public void add(Polynom_able p1) {
		Iterator<Monom> iter = p1.iteretor();
		while (iter.hasNext()) {
			Monom m = iter.next();

		}
	}

	@Override
	public void add(Monom m1) {
		int pow = m1.get_power() , x=0;
		while(x<PolynomList.size()){
			if(pow>PolynomList.get(x).get_power()){
				PolynomList.add(x,m1);
				break;
			}
			else if(pow < PolynomList.get(x).get_power()) x++;
			else {
				PolynomList.get(x).add(m1);
				break;
			}
		}
		if(x>=PolynomList.size()) PolynomList.add(m1);

	}

	@Override
	public void substract(Polynom_able p1) {
		//aaaaaaa


	}

	@Override
	public void multiply(Polynom_able p1) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean equals(Polynom_able p1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isZero() {
		int index = 0;
		while(index<PolynomList.size()){
			if(PolynomList.get(index).get_coefficient() != 0) return false;
		}
		return true;
	}

	@Override
	public double root(double x0, double x1, double eps) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Polynom_able copy(){
		Polynom poliTemp = new Polynom();
		for (int i = 0; i < PolynomList.size() ; i++) {
			poliTemp.PolynomList.add(PolynomList.get(i));
		}
		return poliTemp;
	}

	@Override
	public Polynom_able derivative() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double area(double x0, double x1, double eps) {
		double TillTheEnd = 0 , ans = 0;
		while(TillTheEnd < x1){
			double dot = f(x0);
			if(dot>0){
				double xPluxEps = x0+eps;
				f(xPluxEps);
				ans +=dot*(eps);
			}
		}
		return ans;
	}
	@Override
	public Iterator<Monom> iteretor() {
		return PolynomList.iterator();
	}
	@Override
	public void multiply(Monom m1) {
		int Runner = 0;
		while(Runner!=PolynomList.size()){
			PolynomList.get(Runner++).multipy(m1);
		}
	}
	public void toStr(){
		int Runner = 0;
		while(Runner!=PolynomList.size()){
			String s = PolynomList.get(Runner++).toString();
			System.out.println(s);
		}
	}

	public static void main(String[] args) {
		Polynom p = new Polynom("11x^9+3.76x^8");
		Monom r = new Monom("3x^2");
		p.multiply(r);
		p.toStr();
		//double s = p.f(2);
		//System.out.println(s);
		//System.out.println(p.PolynomSave);
		//	System.out.println(p.PolynomSave);



	}

}