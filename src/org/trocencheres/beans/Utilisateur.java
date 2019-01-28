package org.trocencheres.beans;

import java.util.ArrayList;
import java.util.List;

public class Utilisateur {
	private int noUtilisateur, credit;
	private String pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse;
	private boolean administrateur;
	private List<Integer> idsVentes;

	public Utilisateur() {
	}

	// constructeur sans motDePasse
	public Utilisateur(int noUtilisateur, String pseudo, String nom, String prenom, String email,
						String telephone, String rue, String codePostal, String ville, int credit,
						boolean administrateur, ArrayList<Integer> idsVentes) {
		this.setNoUtilisateur(noUtilisateur);
		this.setPseudo(pseudo);
		this.setNom(nom);
		this.setPrenom(prenom);
		this.setEmail(email);
		this.setTelephone(telephone);
		this.setRue(rue);
		this.setCodePostal(codePostal);
		this.setVille(ville);
        this.setCredit(credit);
		this.setAdministrateur(administrateur);
		this.setVentesIds(idsVentes);
	}

	// constructeur complet
	public Utilisateur(int noUtilisateur, String pseudo, String nom, String prenom, String email, String telephone, String rue,
					   String codePostal, String ville, int credit, boolean administrateur,
					   ArrayList<Integer> idsVentes, String motDePasse) {
		this(noUtilisateur, pseudo, nom, prenom, email, telephone, rue, codePostal, ville, credit, administrateur, idsVentes);
		this.motDePasse = motDePasse;
	}

	public List<Integer> getVentesIds() {
		return this.idsVentes;
	}

	public void setVentesIds(ArrayList<Integer> idsVentes) {
		this.idsVentes = idsVentes;
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

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
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
		builder.append(idsVentes);
		builder.append("]");
		return builder.toString();
	}

}
