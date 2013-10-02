package exo_01_02;

public class Exo_01_01_02 {
	
	public static void afficher(int[] tab)
	{
		System.out.print("[ ");
		for(int i = 0; i < tab.length; i++)
		{
			System.out.print(tab[i] + " ");
		}
		System.out.print("]");
	}
	
	public static void main(String[] args) {
		int[] tab = new int[Integer.parseInt(args[0])];
		for(int i = 0; i < tab.length; i++)
		{
			tab[i] = (int)(Math.random() * 100); 	
		}
		
		afficher(tab);
	}

}
