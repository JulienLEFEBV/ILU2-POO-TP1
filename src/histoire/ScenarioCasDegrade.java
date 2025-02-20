package histoire;

import personnages.Gaulois;
import villagegaulois.Etal;

public class ScenarioCasDegrade {
	public static void main(String[] args) {
		Etal etal = new Etal();
		etal.libererEtal();
		System.out.println("Fin du test");
		etal.acheterProduit(0, null);
		System.out.println("Fin du test");
		try {
			etal.acheterProduit(-1, null);
		}catch(IllegalArgumentException e) {
			e.printStackTrace();
		}
		System.out.println("Fin du test");
		Gaulois gaulois=new Gaulois("Gaulois",10);
		try {
			etal.acheterProduit(1, gaulois);
		}catch(IllegalStateException e) {
			e.printStackTrace();
		}
		System.out.println("Fin du test");
	}
}
