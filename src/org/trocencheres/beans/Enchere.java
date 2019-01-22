package org.trocencheres.beans;

import java.util.Date;

public class Enchere {

	Date dateEnchere;
	Vente vente;
	Utilisateur utilisateur;

	public Enchere() {
		super();
	}

	public Enchere(Date dateEnchere, Vente vente, Utilisateur utilisateur) {
		super();
		this.dateEnchere = dateEnchere;
		this.vente = vente;
		this.utilisateur = utilisateur;
	}

	public Date getDateEnchere() {
		return dateEnchere;
	}

	public void setDateEnchere(Date dateEnchere) {
		this.dateEnchere = dateEnchere;
	}

	public Vente getVente() {
		return vente;
	}

	public void setVente(Vente vente) {
		this.vente = vente;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Enchere [dateEnchere=");
		builder.append(dateEnchere);
		builder.append(", vente=");
		builder.append(vente);
		builder.append(", utilisateur=");
		builder.append(utilisateur);
		builder.append("]");
		return builder.toString();
	}

}
