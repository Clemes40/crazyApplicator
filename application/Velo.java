package application;
import javafx.scene.image.Image;


public class Velo {

	private String type;
	private Option couleur;
	private Option marque;
	private String lienPhoto;
	private Float prix;
	private Option personnalisation;
	private Option taille;
	private Image image;
	
	public Velo(String type, Option couleur, Option marque, String lienPhoto, Float prix, Option personnalisation, Option taille, Image image) {
		this.type = type;
		this.couleur = couleur;
		this.marque = marque;
		this.lienPhoto = lienPhoto;
		this.prix = prix;
		this.personnalisation = personnalisation;
		this.taille = taille;
		this.image = image;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Option getCouleur() {
		return couleur;
	}

	public void setCouleur(Option couleur) {
		this.couleur = couleur;
	}

	public Option getMarque() {
		return marque;
	}

	public void setMarque(Option marque) {
		this.marque = marque;
	}

	public String getLienPhoto() {
		return lienPhoto;
	}

	public void setLienPhoto(String lienPhoto) {
		this.lienPhoto = lienPhoto;
	}

	public Float getPrix() {
		return prix;
	}

	public void setPrix(Float prix) {
		this.prix = prix;
	}

	public Option getPersonnalisation() {
		return personnalisation;
	}

	public void setPersonnalisation(Option personnalisation) {
		this.personnalisation = personnalisation;
	}

	public Option getTaille() {
		return taille;
	}

	public void setTaille(Option taille) {
		this.taille = taille;
	}
	
	public Image getImage() {
	    return image;
	}
	
	public void setImage(Image image) {
	    this.image = image;
	}
	
	 public float calculerPrixTotal() {
	        float prixTotal = this.prix;

	        if (this.couleur != null) {
	            prixTotal += this.couleur.getPrix();
	        }
	        return prixTotal;
	    }
}
