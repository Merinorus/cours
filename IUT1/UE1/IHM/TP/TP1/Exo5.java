package exo_01_05;

import java.util.List;
import java.util.ArrayList;

public class AuHasard {
	
	public static void afficher(List<Integer> tab)
	{
		System.out.print("[ ");
		for(int i = 0; i < tab.size(); i++)
		{
			System.out.print(tab.get(i) + " ");
		}
		System.out.print("]");
	}
	
	public static void main(String[] args) {
		List<Integer> tab = new ArrayList<Integer>();
		do
		{
			tab.add((int)Math.random());
		} while ((Integer)tab.get(tab.size() - 1) >= 5);
		
		afficher(tab);
	}

}
