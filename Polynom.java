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
public class Polynom implements Polynom_able {
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
	 * {"x", "3+1.4X^3-34x", "(2x^2-4)(-1.2x-7.1)", "(3-3.4x+1)((3.1x-1.2)-(3X^2-3.1))"};
	 *
	 * @param s: is a string represents a Polynom
	 */


	public Polynom(String s) {
		try {
			String t = "";
			int Startindex = 0, EndIndex = 0;
			LinkedList<Monom> TempMonomList = new LinkedList<Monom>(); // make new LinkList that save the Polynom
			if (s.length() == 1) {
				Monom onlyOne = new Monom(s);
				TempMonomList.add(onlyOne);
			} else {
				while (EndIndex < s.length()) { // make 2 index one that move until he found + or - and then Substring from index to index and make monom
					if (EndIndex != 0) {
						if (s.charAt(EndIndex) == '+' || s.charAt(EndIndex) == '-' || EndIndex == s.length() - 1) {

							t = s.substring(Startindex, EndIndex);
							if (EndIndex == s.length() - 1) t = t + s.charAt(s.length() - 1);
							Monom newMonom = new Monom(t);
							TempMonomList.add(newMonom);
							Startindex = EndIndex;
						}
					}
					EndIndex++;
				}
			}

			PolynomList = TempMonomList; // pointer that the linklist of the polynom is the list that we made
		}
		catch (Exception e)
		{
			System.out.println("Alert , The String that you try to put in is incorrect");
		}
	}



	/**
	 * function that calculate the value of the polynom when we put number in x
	 * @param x
	 *
	 *
	 * @return Returns the value in the function
	 */
	@Override
	public double f(double x) {

		int Index = 0;
		double sum =0;
		while(Index<PolynomList.size()){
			sum = sum + PolynomList.get(Index++).f(x);
		}
		return sum;
	}

	/**
	 * function that take polynom and add him to other polynom
	 * @param p1
	 * @return original polynom with p1
	 */
	@Override
	public void add(Polynom_able p1) {
		Iterator<Monom> iter = p1.iteretor();
		while (iter.hasNext()) {
			Monom m = iter.next();
			add(m);
		}
	}

	/**
	 * function that take monom and add him to the polynom
	 * @param m1 Monom
	 * @return polynom with the monom m1
	 */
	@Override

	public void add(Monom m1) {
		int pow = m1.get_power() , x=0;
		while(x<PolynomList.size()){ // found where to add him
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

	/**
	 * function that take polynom and substract him from the original Polynom
	 * @param p1
	 * The function passes value and subtracts by two loops, with each iteration progressing on what has already been passed to save time
	 */
	@Override
	public void substract(Polynom_able p1) {
		int polyIndex = 0 , tempIndex = 0 , CheckIfNot = 0 ;
		Polynom temp = new Polynom();
		Iterator<Monom> Pointer = p1.iteretor(); // to run on p1
		while(Pointer.hasNext()){ // copy p1 to polynom
			temp.add(Pointer.next());
		}
		while (tempIndex < temp.PolynomList.size())
		{
			while(polyIndex < PolynomList.size())
			{
				if (PolynomList.get(polyIndex).get_power() == temp.PolynomList.get(tempIndex).get_power()) // check if the power of the original is similar to other power
				{
					CheckIfNot++;
					PolynomList.get(polyIndex).set_coefficient(PolynomList.get(polyIndex).get_coefficient() - temp.PolynomList.get(tempIndex).get_coefficient()); // if they similar substract
				}
				polyIndex++;
			}

			if(CheckIfNot == 0) // if the monom dont have monom with similar power
			{
				temp.PolynomList.get(tempIndex).set_coefficient(-temp.PolynomList.get(tempIndex).get_coefficient());
				PolynomList.add(temp.PolynomList.get(tempIndex));
			}
			CheckIfNot = 0 ;
			polyIndex = 0 ;
			tempIndex++;
		}


	}

	/**
	 * function that multiply polynom by other polynom
	 * take every monom in p1 and multiply him with all the Polynom then conference Organs and return
	 * The function is multiplied by loops, multiplying and retaining what is already doubled for connection later
	 * @param p1
	 */
	@Override
	public void multiply(Polynom_able p1) {
		int p1Size = P_ableSize(p1);
		Polynom temPolinom = new Polynom();
		for (int i = 0; i < PolynomList.size() ; i++) { // take p1 and copy it to other polynom
			Monom m = new Monom(this.PolynomList.get(i));
			temPolinom.PolynomList.add(m);
		}
		int pointer=0 , pointAtThisList=0;
		Iterator<Monom> iter = p1.iteretor();
		while(iter.hasNext()) {
			Monom monomPoint = iter.next();
			while (pointer != temPolinom.PolynomList.size()) {
				temPolinom.PolynomList.get(pointer).multipy(monomPoint); // take the monom and multiply it with all the polynom
				pointer++;
			}
			if (temPolinom.PolynomList.size() != this.PolynomList.size() * p1Size) {
				while (pointAtThisList < this.PolynomList.size()) {
					Monom m = new Monom (this.PolynomList.get(pointAtThisList));
					temPolinom.PolynomList.add(m);
					pointAtThisList++;
				}
				pointAtThisList = 0;
			}
		}
		this.PolynomList = temPolinom.PolynomList;
		conferenceOrgans(); // Enables a function that arranges the organs
	}

	/**
	 * function that make counter for how much monoms is in the polynom
	 * @param p1
	 * @return counter of the monoms
	 */
	private int P_ableSize(Polynom_able p1) {
		Iterator<Monom> iter = p1.iteretor();
		int count =0;
		while(iter.hasNext()){
			count++;
			iter.next();
		}
		return count;
	}

	/**
	 * function that check if polynom equals to the other on
	 *
	 * @param p1
	 * @return boolean true of false if equal or not
	 */
	@Override
	public boolean equals(Polynom_able p1) {
		Polynom temp = new Polynom();
		Iterator<Monom> Pointer = p1.iteretor(); // make iterator for check the monoms in p1
		while(Pointer.hasNext()){
			temp.add(Pointer.next()); // add to temp p1
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

	/**
	 *
	 * @param x0 starting point
	 * @param x1 end point
	 * @param eps>0 (positive) representing the epsilon range the solution should be within.
	 * The function is checked by the middle value statement.
	 * The function does this by calculating a point derivative and dividing it when it is positive and when it is negative
	 * @return the value of funciton in the zero point
	 */
	@Override
	public double root(double x0, double x1, double eps) {
		if(x0>=x1) throw new NullPointerException("x0 suppose to be smaller then x1");
		//if(f(x0)*f(x1)>0) throw new NullPointerException("The func` do not Cross the X line");
		Polynom temPolinom = new Polynom();
		for (int i = 0; i < PolynomList.size() ; i++) {
			Monom m = new Monom(this.PolynomList.get(i));
			temPolinom.PolynomList.add(m);
		}
		double valueMed = (x0+x1)/2;
		double der = temPolinom.derivative().f(valueMed); // check if derivative is minus or plus
		double ans = 0;
		double thisF = f(valueMed);
		if(der==0) return 0;
		else if(der>0) {
			if ((Math.abs(thisF) <= eps)) ans = valueMed;
			else if (thisF > 0) return root(x0, valueMed, eps);
			else if (thisF < 0) return root(valueMed, x1, eps);
		}
		else{
			if ((Math.abs(thisF) <= eps)) ans = valueMed;
			else if (thisF > 0) return root(valueMed, x1, eps);
			else if (thisF < 0) return root(x0, valueMed, eps);
		}
		return ans;
	}

	/**
	 *
	 * @return copy of the polynom
	 */
	@Override
	public Polynom_able copy(){
		Polynom poliTemp = new Polynom();
		for (int i = 0; i < this.PolynomList.size() ; i++) {
			poliTemp.PolynomList.add(PolynomList.get(i));
		}
		return poliTemp;
	}

	/**
	 *
	 * @return the derivative of the polynom
	 * Calculates the derivative of the function.
	 * The calculation works by a function derived from a monom and from a limb organ
	 */
	@Override
	public Polynom_able derivative() {
		int index = 0 ;
		Polynom temPolinom = new Polynom();
		for (int i = 0; i < PolynomList.size() ; i++) {
			Monom m = new Monom(this.PolynomList.get(i));
			temPolinom.PolynomList.add(m);
		}
		while(index < this.PolynomList.size()){
			temPolinom.PolynomList.set(index , this.PolynomList.get(index).derivative());
			index++;
		}
		temPolinom.conferenceOrgans();
		this.PolynomList = temPolinom.PolynomList;
		return temPolinom;
	}

	/**
	 *
	 * @param x0 starting pooint
	 * @param x1 end point
	 * @param eps positive step value
	 * @return the area of function of the polynom and calculate it
	 */
	@Override
	public double area(double x0, double x1, double eps) {
		if(x0>x1) return -1;
		double TillTheEnd = x0 , ans = 0;
		while(TillTheEnd < x1){
			double dot = f(TillTheEnd);
			if(dot>=0){
				double xPluxEps = x0+eps;
				f(xPluxEps);
				ans+=dot*(eps);
				TillTheEnd+=eps;
			}
		}
		return ans;
	}
	@Override
	public Iterator<Monom> iteretor() {
		return PolynomList.iterator();
	}

	/**
	 * multiply m1 with all the polynom
	 * @param m1
	 */
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
			System.out.print(s + "+");
		}
	}

	/**
	 * The function arranges the organs and connects them, if need be to erase organs reset
	 * function that take polynom and conference Organs
	 */
	public void conferenceOrgans()
	{
		int indexI = 0 , indexJ = 0 ;
		while (indexI < PolynomList.size())
		{
			indexJ = indexI +1 ;
			while (indexJ < PolynomList.size())
			{

				if(PolynomList.get(indexI).get_power() == PolynomList.get(indexJ).get_power())
				{
					PolynomList.get(indexI).set_coefficient(PolynomList.get(indexI).get_coefficient()+PolynomList.get(indexJ).get_coefficient());
					PolynomList.remove(indexJ);
				}

				indexJ++;
			}
			indexI++;
		}
		removeZero();
	}

	/**
	 * function that check if the polynom have monom zero and delete it
	 */
	private void removeZero() {
		int index = 0 ;
		while(index < PolynomList.size())
		{
			if(PolynomList.get(index).get_coefficient() == 0)
			{
				PolynomList.remove(index);
			}
			index++;
		}
	}

	public static void main(String[] args) {
		Polynom p = new Polynom("x^4+x^3-5x^2+5");
		//Polynom r = new Polynom("x^2+2x+8");
		//p.multiply(r);
		//Monom temp = new Monom("2x^2");
		//System.out.println(p.area(0,5,0.00001));
		System.out.println(p.root(-2,0,0.01));
		//p.toStr();
		//p.derivative();
		System.out.println();
		p.toStr();
		//double s = p.f(2);
		//System.out.println(s);
		//System.out.println(p.PolynomSave);
		//	System.out.println(p.PolynomSave);
	}

}
