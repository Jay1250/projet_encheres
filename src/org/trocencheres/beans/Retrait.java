package org.trocencheres.beans;

public class Retrait {

	private Vente vente;
	private String rue, codePostal, ville;

	public Retrait() {
		super();
	}

	public Retrait(Vente vente, String rue, String codePostal, String ville) {
		super();
		this.vente = vente;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
	}

	public Vente getVente() {
		return vente;
	}

	public void setVente(Vente vente) {
		this.vente = vente;
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
		builder.append("Retrait [vente=");
		builder.append(vente);
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
