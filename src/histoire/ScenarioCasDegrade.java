package histoire;

import personnages.Gaulois;
import villagegaulois.Etal;
import villagegaulois.Village;
import villagegaulois.VillageSansChefException;

public class ScenarioCasDegrade {
	public static void main(String[] args) {
		Etal etal = new Etal();
		etal.libererEtal();
		etal.acheterProduit(0, null);
		Gaulois gaulois=new Gaulois("Gaulois",10);
		try {
			etal.acheterProduit(-1, gaulois);
		}catch(IllegalArgumentException e) {
			e.printStackTrace();
		}
		try {
			etal.acheterProduit(1, gaulois);
		}catch(IllegalStateException e) {
			e.printStackTrace();
		}
		Village village = new Village("le village des irréductibles", 10, 5);
		try {
			village.afficherVillageois();
		}
		catch(VillageSansChefException e){
			e.printStackTrace();
		}
		System.out.println("Fin du test");
	}
}
