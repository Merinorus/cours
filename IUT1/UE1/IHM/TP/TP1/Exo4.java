package exo_01_04;

import java.util.List;
import java.util.ArrayList;

public class AuHasard {
	
	public static void afficher(List tab)
	{
		System.out.print("[ ");
		for(int i = 0; i < tab.size(); i++)
		{
			System.out.print(tab.get(i) + " ");
		}
		System.out.print("]");
	}
	
	public static void main(String[] args) {
		List tab = new ArrayList();
		do
		{
			tab.add((int)Math.random());
		} while ((Integer)tab.get(tab.size() - 1) >= 5);
		
		afficher(tab);
	}

}
