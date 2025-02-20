package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche marche;

	public Village(String nom, int nbVillageoisMaximum, int nbEtals) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		marche=new Marche(nbEtals);
	}
	
	private static class Marche{
		private Etal[] etals;
		
		private Marche(int nb_etals) {
			etals = new Etal[nb_etals];
			for(int i=0;i<nb_etals;i++) etals[i]=new Etal();
		}
		
		private void utiliserEtal(int indiceEtal, Gaulois vendeur,String produit, int nbProduit) {
			etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
		}
		
		private int trouverEtalLibre() {
			int nb_etals=etals.length;
			for(int i=0;i<nb_etals;i++) {
				if(!(etals[i].isEtalOccupe())) {
					return i;
				}
			}
			return -1;
		}
		
		private Etal[] trouverEtals(String produit) {
			int nb_etals=etals.length;
			int taille_tab=0;
			for(int i=0;i<nb_etals;i++) {
				if(etals[i].contientProduit(produit)) taille_tab++;
			}
			Etal[] etals_trouves=new Etal[taille_tab];
			int j=0;
			for(int i=0;i<nb_etals && j<taille_tab;i++) {
				if(etals[i].contientProduit(produit)) { 
					etals_trouves[j]=etals[i];
					j++;
				}
			}
			return etals_trouves;
		}
		
		private Etal trouverVendeur(Gaulois gaulois) {
			int nb_etals=etals.length;
			for(int i=0;i<nb_etals;i++) {
				if(gaulois.equals(etals[i].getVendeur())) {
					return etals[i];
				}
			}
			return null;
		}
		
		private String afficherMarche() {
			StringBuilder chaine = new StringBuilder("");
			int nb_etals=etals.length;
			int nb_etals_libre=0;
			for(int i=0;i<nb_etals;i++) {
				if(etals[i].isEtalOccupe()) {
					chaine.append(etals[i].afficherEtal());
				}
				else {
					nb_etals_libre++;
				}
			}
			chaine.append("Il reste " +nb_etals_libre + " étals non utilisés dans le marché.\n");
			return chaine.toString();
		}
	}


	public String installerVendeur(Gaulois vendeur, String produit,int nbProduit) {
		StringBuilder chaine = new StringBuilder(vendeur.getNom() +" cherche un endroit pour vendre "+nbProduit+" "+produit+".\n");
		int indMarche=marche.trouverEtalLibre();
		if(indMarche>=0) {
			marche.utiliserEtal(indMarche, vendeur, produit, nbProduit);
			chaine.append("Le vendeur "+vendeur.getNom()+" vend des "+produit+" à l'étal n°"+(indMarche+1)+".\n");
		}
		else {
			chaine.append("Le vendeur "+vendeur.getNom()+"n'a pas trouvé d'étal libre.\n");
		}
		return chaine.toString();
	}
	
	public String rechercherVendeursProduit(String produit) {
		Etal[] etalsTrouves=marche.trouverEtals(produit);
		StringBuilder chaine = new StringBuilder("");
		if(etalsTrouves.length==0) {
			chaine.append("Il n'y a pas de vendeur qui propose des "+produit+" au marché.\n");
		}
		else if(etalsTrouves.length==1) {
			chaine.append("Seul le vendeur "+etalsTrouves[0].getVendeur().getNom()+" propose des "+produit+" au marché.\n");
		}
		else {
			chaine.append("Les vendeurs qui proposent des "+produit+" sont :\n");
			int nbEtals=etalsTrouves.length;
			for(int i=0;i<nbEtals;i++) {
				chaine.append("-"+etalsTrouves[i].getVendeur().getNom()+"\n");
			}
		}
		return chaine.toString();
	}
	
	public Etal rechercherEtal(Gaulois vendeur) {
		return marche.trouverVendeur(vendeur);
	}
	
	public String partirVendeur(Gaulois vendeur) {
		Etal etal=marche.trouverVendeur(vendeur);
		if(etal!=null) {
			return etal.libererEtal();
		}
		return "";
	}
	
	public String afficherMarche() {
		return "Le marché du village \""+nom+"\" possède plusieurs étals :\n" + marche.afficherMarche();
	}
	
	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les lÃ©gendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	
}