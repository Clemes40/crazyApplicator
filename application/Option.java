package application;

public abstract class Option {
    private String nom;
    private Float prix;

    public Option(String nom, Float prix) {
        this.nom = nom;
        this.prix = prix;
    }

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Float getPrix() {
		return prix;
	}

	public void setPrix(Float prix) {
		this.prix = prix;
	}
}

class Taille extends Option {
    public Taille(String nom, Float prix) {
        super(nom, prix);
    }
    @Override
    public String toString() {
        return getNom();
    }
}

class Personnalisation extends Option {
    public Personnalisation(String nom, Float prix) {
        super(nom, prix);
    }
    public String toString() {
    	return getNom();
    }
}

class Marque extends Option {
	public Marque(String nom, Float prix) {
		super(nom, prix);
	}
	public String toString() {
		return getNom();
	}
}
