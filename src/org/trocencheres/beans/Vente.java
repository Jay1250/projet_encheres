package org.trocencheres.beans;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class Vente {
	private int noVente, prixInitial, prixVente;
	private String nomArticle, description;
	private Date dateFinEncheres;
	private ArrayList<Enchere> encheres;
	private Utilisateur utilisateur;
	private Categorie categorie;
	private Retrait retrait;

	public Retrait getRetrait() {
		return retrait;
	}

	public Vente(int noVente, int prixInitial, int prixVente, String nomArticle, String description,
			Date dateFinEncheres, ArrayList<Enchere> encheres, Utilisateur utilisateur, Categorie categorie,
			Retrait retrait) {
		super();
		this.noVente = noVente;
		this.prixInitial = prixInitial;
		this.prixVente = prixVente;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateFinEncheres = dateFinEncheres;
		this.encheres = encheres;
		this.utilisateur = utilisateur;
		this.categorie = categorie;
		this.retrait = retrait;
	}

	public void setRetrait(Retrait retrait) {
		this.retrait = retrait;
	}

	public Vente() {
		super();
	}

	public int getNoVente() {
		return noVente;
	}

	public void setEncheres(ArrayList<Enchere> encheres) {
		this.encheres = encheres;
	}

	public void setNoVente(int noVente) {
		this.noVente = noVente;
	}

	public int getPrixInitial() {
		return prixInitial;
	}

	public void setPrixInitial(int prixInitial) {
		this.prixInitial = prixInitial;
	}

	public int getPrixVente() {
		return prixVente;
	}

	public void setPrixVente(int prixVente) {
		this.prixVente = prixVente;
	}

	public String getNomArticle() {
		return nomArticle;
	}

	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDateFinEncheres() {
		return dateFinEncheres;
	}

	public void setDateFinEncheres(Date dateFinEncheres) {
		this.dateFinEncheres = dateFinEncheres;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Vente [noVente=");
		builder.append(noVente);
		builder.append(", prixInitial=");
		builder.append(prixInitial);
		builder.append(", prixVente=");
		builder.append(prixVente);
		builder.append(", nomArticle=");
		builder.append(nomArticle);
		builder.append(", description=");
		builder.append(description);
		builder.append(", dateFinEncheres=");
		builder.append(dateFinEncheres);
		builder.append(", encheres=");
		builder.append(encheres);
		builder.append(", utilisateur=");
		builder.append(utilisateur);
		builder.append(", categorie=");
		builder.append(categorie);
		builder.append(", retrait=");
		builder.append(retrait);
		builder.append("]");
		return builder.toString();
	}

}
