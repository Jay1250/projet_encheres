package org.trocencheres.beans;

/**
 * @author JY
 */
public class Retrait {

	private int noVente;
	private String rue, codePostal, ville;

	public Retrait() {}

	public Retrait(int noVente) {
		this.setNoVente(noVente);
	}

	public Retrait(int noVente , String rue, String codePostal, String ville) {
		this(noVente);
		this.setRue(rue);
		this.setCodePostal(codePostal);
		this.setVille(ville);
	}

	public int getNoVente() {
		return noVente;
	}

	public void setNoVente(int noVente ) {
		this.noVente  = noVente ;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Retrait [noVente=");
		builder.append(noVente);
		builder.append(", rue=");
		builder.append(rue);
		builder.append(", codePostal=");
		builder.append(codePostal);
		builder.append(", ville=");
		builder.append(ville);
		builder.append("]");
		return builder.toString();
	}

}
