package org.trocencheres.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Vente {
	private int noVente, prixInitial, prixVente;
	private String nomArticle, description;
	private Date dateFinEncheres;
	private List<Enchere> encheres;
	private Utilisateur utilisateur;
	private Categorie categorie;
	private Retrait retrait;

	public Vente() {}

	public Vente(int noVente, String nomArticle, String description, Date dateFinEncheres,
				 int prixInitial, int prixVente, ArrayList<Enchere> encheres, Utilisateur utilisateur,
				 Categorie categorie) {
		this.setNoVente(noVente);
		this.setNomArticle(nomArticle);
		this.setDescription(description);
		this.setDateFinEncheres(dateFinEncheres);
		this.setPrixInitial(prixInitial);
		this.setPrixVente(prixVente);
		this.setEncheres(encheres);
		this.setUtilisateur(utilisateur);
		this.setCategorie(categorie);
		this.setRetrait(new Retrait());
	}

	public Vente(int noVente, String nomArticle, String description, Date dateFinEncheres,
				 int prixInitial, int prixVente, ArrayList<Enchere> encheres, Utilisateur utilisateur,
				 Categorie categorie, Retrait retrait) {
		this(noVente, nomArticle, description, dateFinEncheres, prixInitial, prixVente, encheres, utilisateur, categorie);
		this.setRetrait(retrait);
	}

	public Retrait getRetrait() {
		return retrait;
	}

	public void setRetrait(Retrait retrait) {
		this.retrait = retrait;
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
