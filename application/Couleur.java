package application;

public class Couleur extends Option {
    private String codeCouleur;

    public Couleur(String nom, float prix, String codeCouleur) {
        super(nom, prix);
        this.codeCouleur = codeCouleur;
    }

    public String getCodeCouleur() {
        return codeCouleur;
    }

    @Override
    public String toString() {
        return getNom();
    }
}
