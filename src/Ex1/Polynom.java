package Ex1;

import java.util.Comparator;
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
	public LinkedList<Monom> PolynomList;
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
	 * @param: is a string represents a Polynom
	 */
	private String fixThisFileText(String ans) {
		ans = ans.replaceAll(" ", "");
		return ans;
	}
	public Polynom(String s) {
		s = fixThisFileText(s);
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
			this.sort();
			this.conferenceOrgans();
			this.sort();
			if (PolynomList == null)
			{
				throw new ExceptionInInitializerError("your polynom is dont have any monoms , is empty Polynom");
			}
		}
		catch (Exception e)
		{
			System.out.println("Alert , The String that you try to put in is incorrect");
		}
	}
	/**
	 * function that calculate the value of the polynom when we put number in x
	 * @param x
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

	@Override
	public function initFromString(String s) {
		return new Polynom(s);
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
		conferenceOrgans();
		sort();
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
		this.conferenceOrgans();
		sort();
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
					PolynomList.get(polyIndex).toSetcoefficient(PolynomList.get(polyIndex).get_coefficient() - temp.PolynomList.get(tempIndex).get_coefficient()); // if they similar substract
				}
				polyIndex++;
			}
			if(CheckIfNot == 0) // if the monom dont have monom with similar power
			{
				temp.PolynomList.get(tempIndex).toSetcoefficient(-temp.PolynomList.get(tempIndex).get_coefficient());
				PolynomList.add(temp.PolynomList.get(tempIndex));
			}
			CheckIfNot = 0 ;
			polyIndex = 0 ;
			tempIndex++;
		}
		this.conferenceOrgans();
		sort();
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
	public boolean equals(Object p1) {
		if(p1 instanceof Polynom_able){
			Polynom_able p2 = (Polynom_able)p1;
			return this.equalPa(p2);
		}
		else if(p1 instanceof Polynom){
			Polynom p3 = (Polynom)p1;
			return this.epualPo(p3);
		}
		return false;
	}

	public boolean epualPo(Polynom p1){
		double EPSILON = 0.0000001;
		if(this.PolynomList.size() == 0 && p1.PolynomList.size()!=0) return false;
		if(p1.PolynomList.size() == 0 && this.PolynomList.size()!=0) return false;
		int index = 0 ;
		boolean flag = true;
		while(index < p1.PolynomList.size())
		{
			if(Math.abs(p1.PolynomList.get(index).get_coefficient() - this.PolynomList.get(index).get_coefficient())>EPSILON)
			{
				flag = false;
			}
			if(p1.PolynomList.get(index).get_power() != this.PolynomList.get(index).get_power())
			{
				flag = false;
			}
			index++;
		}
		return flag;

	}


	public boolean equalPa(Polynom_able p1) {
		Polynom temp = new Polynom();
		Iterator<Monom> Pointer = p1.iteretor(); // make iterator for check the monoms in p1
		while(Pointer.hasNext()){
			temp.add(Pointer.next()); // add to temp p1
		}
		temp.conferenceOrgans();
		if(this.PolynomList.size() == 0 && temp.PolynomList.size()!=0) return false;
		if(temp.PolynomList.size() == 0 && this.PolynomList.size()!=0) return false;
		int index = 0 ;
		boolean flag = true;
		double EPSILON = 0.0000001;
		while(index < temp.PolynomList.size())
		{
			if(Math.abs(temp.PolynomList.get(index).get_coefficient() - this.PolynomList.get(index).get_coefficient())>EPSILON)
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

	/**
	 * func that check if the polynom is zero
	 * @return
	 */
	@Override
	public boolean isZero() {
		int index = 0;
		while(index<PolynomList.size()){
			if(PolynomList.get(index).get_coefficient() != 0) return false;
			index++;
		}
		return true;
	}
	/**
	 * @param x0 starting point
	 * @param x1 end point
	 * @param eps>0 (positive) representing the epsilon range the solution should be within.
	 * The function is checked by the middle value statement.
	 * The function does this by calculating a point derivative and dividing it when it is positive and when it is negative
	 * @return the value of funciton in the zero point
	 */
	@Override
	public double root(double x0, double x1, double eps){
		if(f(x0)*f(x1)>0) throw new RuntimeException("The func` do not Cross the X line");
		double valueMed = (x0+x1)/2;
		while(Math.abs(f(valueMed))>eps){
			if(f(valueMed)*f(x0)<0) x1 = valueMed;
			else x0 = valueMed;
			valueMed = (x0+x1)/2;
		}
		return valueMed;
	}
	/**
	 * @return copy of the polynom
	 */
	@Override
	public Polynom_able copy(){
		Polynom_able poliTemp = new Polynom();
		Iterator<Monom> iter = this.iteretor();
		while(iter.hasNext()){
			Monom t = iter.next();
			poliTemp.add(new Monom(t));
		}
		return poliTemp;
	}
	/**
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
	 * @param x0 starting pooint
	 * @param x1 end point
	 * @param eps positive step value
	 * @return the area of function of the polynom and calculate it
	 * if x0>x1 the func return 0.
	 */
	@Override
	public double area(double x0, double x1, double eps) {
		if(x0>x1) return 0;
		double TillTheEnd = x0 , ans = 0;
		while(TillTheEnd < x1){
			double dot = f(TillTheEnd);
			if(dot>=0){
				double xPluxEps = x0+eps;
				f(xPluxEps);
				ans+=dot*(eps);
				TillTheEnd+=eps;
			}
			else TillTheEnd+=eps;
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
		conferenceOrgans();
	}
	public String toString () {
		if(PolynomList.size()==0) {return "0.0x^0";}
		int counter = 0;
		String ans = "";

		while (counter < PolynomList.size()) {
			if (counter !=0 && this.PolynomList.get(counter).get_coefficient() > 0)
				ans += "+";
			ans += this.PolynomList.get(counter).toString();

			counter++;
		}

		return ans;
	}
	/**
	 * The function arranges the organs and connects them, if need be to erase organs reset
	 * function that take polynom and conference Organs
	 * in the end of the method the function sort all the polynom
	 */
	public void conferenceOrgans()
	{
		boolean flag = false;
		int indexI = 0 , indexJ = 0 ;
		while (indexI < PolynomList.size())
		{
			indexJ = indexI +1 ;
			while (indexJ < PolynomList.size())
			{

				if(PolynomList.get(indexI).get_power() == PolynomList.get(indexJ).get_power())
				{
					PolynomList.get(indexI).toSetcoefficient(PolynomList.get(indexI).get_coefficient()+PolynomList.get(indexJ).get_coefficient());
					PolynomList.remove(indexJ);
					flag = true;
				}
				if(flag == false) indexJ++;
				flag = false;
			}
			indexI++;
		}
		sort();
		removeZero();
		sort();
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
	public void sort(){
		Comparator<Monom> sorting = new Monom_Comperator();
		this.PolynomList.sort(sorting);
	}
	public String returnPolynomToString(){
		String ans = "";
		int x=0;
		while((this.PolynomList.size())>x){
			ans = ans + this.PolynomList.get(x++).returnMonomToString() + "+";
		}
		return ans;
	}
	public static void main(String[] args) {
		Polynom r = new Polynom("5x^2+55");
		Polynom_able p = r.copy();
		Iterator<Monom> ite = p.iteretor();
		while (ite.hasNext()){
			System.out.println(ite.next().toString());
		}
	}
}