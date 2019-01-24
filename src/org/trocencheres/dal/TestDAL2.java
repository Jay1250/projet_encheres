package org.trocencheres.dal;

import org.trocencheres.beans.Categorie;
import org.trocencheres.beans.Utilisateur;
import org.trocencheres.beans.Vente;

import java.util.HashMap;
import java.util.Map;

public class TestDAL2 {
    private UtilisateurDAO utilisateurDAO;
    private VenteDAO venteDAO;
    private CategorieDAO categorieDAO;
    private Map<Integer, Utilisateur> utilisateursIndex;
    private Map<Integer, Vente> ventesIndex;
    private Map<Integer, Categorie> categoriesIndex;

    public TestDAL2() {
        this.utilisateurDAO = UtilisateurDAOFactory.getUtilisateurDao();
        this.venteDAO = VenteDAOFactory.getVenteDAO();
        this.categorieDAO = CategorieDAOFactory.getCategorieDAO();
        this.utilisateursIndex = new HashMap<>();
        this.ventesIndex = new HashMap<>();
        this.categoriesIndex = new HashMap<>();
    }

    public static void main(String[] args) {

    }
}
