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
		LinkedList<Monom> temp = new LinkedList<Monom>();
		this.PolynomList = temp;
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
			add(m);
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
		int polyIndex = 0 , tempIndex = 0 , CheckIfNot = 0 ;
		Polynom temp = new Polynom();
		Iterator<Monom> Pointer = p1.iteretor();
		while(Pointer.hasNext()){
			temp.add(Pointer.next());
		}
		while (tempIndex < temp.PolynomList.size())
		{
			while(polyIndex < PolynomList.size())
			{
				if (PolynomList.get(polyIndex).get_power() == temp.PolynomList.get(tempIndex).get_power())
				{
					CheckIfNot++;
					PolynomList.get(polyIndex).set_coefficient(PolynomList.get(polyIndex).get_coefficient() - temp.PolynomList.get(tempIndex).get_coefficient());
				}
				polyIndex++;
			}

			if(CheckIfNot == 0)
			{
				temp.PolynomList.get(tempIndex).set_coefficient(-temp.PolynomList.get(tempIndex).get_coefficient());
				PolynomList.add(temp.PolynomList.get(tempIndex));
			}
			CheckIfNot = 0 ;
			polyIndex = 0 ;
			tempIndex++;
		}


	}

	@Override
	public void multiply(Polynom_able p1) {
		int p1Size = P_ableSize(p1);
		Polynom temPolinom = new Polynom();
		for (int i = 0; i < PolynomList.size() ; i++) {
			temPolinom.PolynomList.add(this.PolynomList.get(i));
		}
		int pointer=0 , pointAtThisList=0;
		Iterator<Monom> iter = p1.iteretor();
		while(iter.hasNext()) {
			Monom monomPoint = iter.next();
			while (pointer != temPolinom.PolynomList.size()) {
				temPolinom.PolynomList.get(pointer).multipy(monomPoint);
				pointer++;
			}
			if (temPolinom.PolynomList.size() != this.PolynomList.size() * p1Size) {
				while (pointAtThisList < this.PolynomList.size()) {
					temPolinom.PolynomList.add(this.PolynomList.get(pointAtThisList));
					pointAtThisList++;
				}
				pointAtThisList = 0;
			}
		}
		this.PolynomList = temPolinom.PolynomList;
	}

	private int P_ableSize(Polynom_able p1) {
		Iterator<Monom> iter = p1.iteretor();
		int count =0;
		while(iter.hasNext()){
			count++;
			iter.next();
		}
		return count;
	}

	@Override
	public boolean equals(Polynom_able p1) {
		Polynom temp = new Polynom();
		Iterator<Monom> Pointer = p1.iteretor();
		while(Pointer.hasNext()){
			temp.add(Pointer.next());
		}
		int index = 0 ;
		boolean flag = true;
		while(index < temp.PolynomList.size())
		{
			if(temp.PolynomList.get(index).get_coefficient() != this.PolynomList.get(index).get_coefficient())
			{
				flag = false;
			}
			if(temp.PolynomList.get(index).get_power() != this.PolynomList.get(index).get_power())
			{
				flag = false;
			}
			index++;
		}
		return flag;
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
		for (int i = 0; i < this.PolynomList.size() ; i++) {
			poliTemp.PolynomList.add(PolynomList.get(i));
		}
		return poliTemp;
	}

	@Override
	public Polynom_able derivative() {
		int index = 0 ;
		while(index < this.PolynomList.size()){
			this.PolynomList.set(index , this.PolynomList.get(index).derivative()) ;
			index++;
		}
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
		Polynom p = new Polynom("2x^2+3x");
		Polynom r = new Polynom("3x^2+2");
		//Monom temp = new Monom("2x^2");
		p.multiply(r);
		p.toStr();
		//double s = p.f(2);
		//System.out.println(s);
		//System.out.println(p.PolynomSave);
		//	System.out.println(p.PolynomSave);
	}

}