package org.trocencheres.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author JY
 */
public class Vente {
	private int noVente, prixInitial, prixVente, noUtilisateur, noCategorie;
	private String nomArticle, description;
	private Date dateFinEncheres;
	private List<Integer> idsEncheres;
	private Retrait retrait;

	public Vente() {}

	public Vente(int noVente, String nomArticle, String description, Date dateFinEncheres,
				 int prixInitial, int prixVente, ArrayList<Integer> idsEncheres, int noUtilisateur,
				 int noCategorie) {
		this.setNoVente(noVente);
		this.setNomArticle(nomArticle);
		this.setDescription(description);
		this.setDateFinEncheres(dateFinEncheres);
		this.setPrixInitial(prixInitial);
		this.setPrixVente(prixVente);
		this.setEncheresIds(idsEncheres);
		this.setNoUtilisateur(noUtilisateur);
		this.setNoCategorie(noCategorie);
		this.setRetrait(new Retrait());
	}

	public Vente(int noVente, String nomArticle, String description, Date dateFinEncheres,
				 int prixInitial, int prixVente, ArrayList<Integer> idsEncheres, int utilisateur,
				 int noCategorie, Retrait retrait) {
		this(noVente, nomArticle, description, dateFinEncheres, prixInitial, prixVente, idsEncheres, utilisateur, noCategorie);
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

	public void setEncheresIds(ArrayList<Integer> idsEncheres) {
		this.idsEncheres = idsEncheres;
	}

	public List<Integer> getEncheresIds() {
		return this.idsEncheres;
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

	public int getNoUtilisateur() {
		return noUtilisateur;
	}

	public void setNoUtilisateur(int noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}

	public int getNoCategorie() {
		return noCategorie;
	}

	public void setNoCategorie(int noCategorie) {
		this.noCategorie = noCategorie;
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
		builder.append(idsEncheres);
		builder.append(", utilisateur=");
		builder.append(noUtilisateur);
		builder.append(", categorie=");
		builder.append(noCategorie);
		builder.append(", retrait=");
		builder.append(retrait);
		builder.append("]");
		return builder.toString();
	}

}
