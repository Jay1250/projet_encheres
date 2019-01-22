package org.trocencheres.beans;

import java.util.ArrayList;
import java.util.Arrays;

public class Categorie {

	int noCategorie;
	String libelle;
	ArrayList<Vente> ventes;

	public Categorie() {
		super();
	}

	public Categorie(int noCategorie, String libelle, ArrayList<Vente> ventes) {
		super();
		this.noCategorie = noCategorie;
		this.libelle = libelle;
		this.ventes = ventes;
	}

	public void setVentes(ArrayList<Vente> ventes) {
		this.ventes = ventes;
	}

	public int getNoCategorie() {
		return noCategorie;
	}

	public void setNoCategorie(int noCategorie) {
		this.noCategorie = noCategorie;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Categorie [noCategorie=");
		builder.append(noCategorie);
		builder.append(", libelle=");
		builder.append(libelle);
		builder.append(", ventes=");
		builder.append(ventes);
		builder.append("]");
		return builder.toString();
	}

}
