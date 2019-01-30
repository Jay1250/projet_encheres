package org.trocencheres.beans;

import java.util.Date;

/**
 * @author JY
 */
public class Enchere {

	private Date dateEnchere;
	private int noVente, noUtilisateur, montantEnchere;

	public Enchere() {}
	

	public Enchere(Date dateEnchere, int noVente, int noUtilisateur) {
		super();
		this.dateEnchere = dateEnchere;
		this.noVente = noVente;
		this.noUtilisateur = noUtilisateur;
	}


	public Enchere(int noVente, int noUtilisateur, Date dateEnchere, int montantEnchere) {
		this.dateEnchere = dateEnchere;
		this.noVente = noVente;
		this.noUtilisateur = noUtilisateur;
		this.montantEnchere=montantEnchere;
	}

	public int getMontantEnchere() {
		return montantEnchere;
	}

	public void setMontantEnchere(int montantEnchere) {
		this.montantEnchere = montantEnchere;
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
		builder.append(", montantEnchere=");
		builder.append(montantEnchere);
		builder.append("]");
		return builder.toString();
	}

}
