package org.trocencheres.beans;

import java.util.ArrayList;
import java.util.Arrays;

public class Utilisateur {
	private int noUtilisateur, credit;
	private String pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse;
	private boolean administrateur;
	private ArrayList<Vente> ventes;

	public Utilisateur() {
	}

	// constructeur sans noUtilisateur et motDePasse
	private Utilisateur(String pseudo, String nom, String prenom, String email, String telephone, String rue,
			String codePostal, String ville, int credit, boolean administrateur, ArrayList<Vente> ventes) {
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
        this.credit = credit;
		this.administrateur = administrateur;
		this.ventes = ventes;
	}

    // constructeur sans motDePasse
    public Utilisateur(int noUtilisateur, String pseudo, String nom, String prenom, String email,
                       String telephone, String rue, String codePostal, String ville, int credit, boolean administrateur,
                       ArrayList<Vente> ventes) {
        this(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, credit, administrateur, ventes);
        this.noUtilisateur = noUtilisateur;
    }

    // constructeur sans noUtilisateur
    private Utilisateur(String pseudo, String nom, String prenom, String email, String telephone, String rue,
                        String codePostal, String ville, String motDePasse, int credit, boolean administrateur,
                        ArrayList<Vente> ventes) {
        this(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, credit, administrateur, ventes);
        this.motDePasse = motDePasse;
    }

	// constructeur complet
	public Utilisateur(int noUtilisateur, int credit, String pseudo, String nom, String prenom, String email,
			String telephone, String rue, String codePostal, String ville, String motDePasse, boolean administrateur,
			ArrayList<Vente> ventes) {
		this(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, credit, administrateur, ventes);
		this.noUtilisateur = noUtilisateur;
		this.motDePasse = motDePasse;
	}

	public void setVentes(ArrayList<Vente> ventes) {
		this.ventes = ventes;
	}

	public int getNoUtilisateur() {
		return noUtilisateur;
	}

	public void setNoUtilisateur(int noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getprenom() {
		return prenom;
	}

	public void setprenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public boolean isAdministrateur() {
		return administrateur;
	}

	public void setAdministrateur(boolean administrateur) {
		this.administrateur = administrateur;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Utilisateur [noUtilisateur=");
		builder.append(noUtilisateur);
		builder.append(", credit=");
		builder.append(credit);
		builder.append(", pseudo=");
		builder.append(pseudo);
		builder.append(", nom=");
		builder.append(nom);
		builder.append(", prenom=");
		builder.append(prenom);
		builder.append(", email=");
		builder.append(email);
		builder.append(", telephone=");
		builder.append(telephone);
		builder.append(", rue=");
		builder.append(rue);
		builder.append(", codePostal=");
		builder.append(codePostal);
		builder.append(", ville=");
		builder.append(ville);
		builder.append(", motDePasse=");
		builder.append(motDePasse);
		builder.append(", administrateur=");
		builder.append(administrateur);
		builder.append(", ventes=");
		builder.append(ventes);
		builder.append("]");
		return builder.toString();
	}

}
