package org.trocencheres.beans;

import java.util.Date;

/**
 * @author JY
 */
public class Enchere {

	private Date dateEnchere;
	private int noVente, noUtilisateur;

	public Enchere() {}

	public Enchere(int noVente, int noUtilisateur, Date dateEnchere) {
		this.dateEnchere = dateEnchere;
		this.noVente = noVente;
		this.noUtilisateur = noUtilisateur;
	}

	public Date getDateEnchere() {
		return dateEnchere;
	}

	public void setDateEnchere(Date dateEnchere) {
		this.dateEnchere = dateEnchere;
	}

	public int getNoVente() {
		return noVente;
	}

	public void setNoVente(int noVente) {
		this.noVente = noVente;
	}

	public int getNoUtilisateur() {
		return noUtilisateur;
	}

	public void setNoUtilisateur(int noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Enchere [dateEnchere=");
		builder.append(dateEnchere);
		builder.append(", noVente=");
		builder.append(noVente);
		builder.append(", noUtilisateur=");
		builder.append(noUtilisateur);
		builder.append("]");
		return builder.toString();
	}

}
