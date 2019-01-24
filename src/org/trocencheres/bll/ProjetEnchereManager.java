package org.trocencheres.bll;

import org.trocencheres.beans.Categorie;
import org.trocencheres.beans.Utilisateur;
import org.trocencheres.beans.Vente;
import org.trocencheres.dal.*;

import java.util.HashMap;
import java.util.Map;

public class ProjetEnchereManager {
    private static ProjetEnchereManager instance;
    private UtilisateurDAO utilisateurDAO;
    private VenteDAO venteDAO;
    private CategorieDAO categorieDAO;
    private Map<Integer, Utilisateur> utilisateursIndex;
    private Map<Integer, Vente> ventesIndex;
    private Map<Integer, Categorie> categoriesIndex;


    private ProjetEnchereManager() {
        this.utilisateurDAO = UtilisateurDAOFactory.getUtilisateurDao();
        this.utilisateursIndex = new HashMap<>();

        this.venteDAO = VenteDAOFactory.getVenteDAO();
        this.ventesIndex = new HashMap<>();

        this.categorieDAO = CategorieDAOFactory.getCategorieDAO();
        this.categoriesIndex = new HashMap<>();
    }

    public static ProjetEnchereManager getInstance() {
        if(instance == null) instance = new ProjetEnchereManager();
        return instance;
    }

    public void addUser(Utilisateur utilisateur) throws BLLException {
        try {
            this.validateUser(utilisateur);
            this.utilisateurDAO.insert(utilisateur);
            this.utilisateursIndex.put(utilisateur.getNoUtilisateur(), utilisateur);
        } catch (DALException e) {
            e.printStackTrace();
        }
    }

    public void updateUser(Utilisateur utilisateur) throws BLLException  {
        try {
            this.validateUser(utilisateur);
            this.utilisateurDAO.update(utilisateur);
            this.utilisateursIndex.put(utilisateur.getNoUtilisateur(), utilisateur);
        } catch (DALException e) {
            e.printStackTrace();
        }
    }

    public void removeUser(int noUtilisateur) throws BLLException  {
        try {
            this.utilisateurDAO.delete(noUtilisateur);
            this.utilisateursIndex.remove(noUtilisateur);
        } catch (DALException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new BLLException("Rmoving user", e);
        }
    }

    public Utilisateur getUserById(int noUtilisateur) throws BLLException {
        Utilisateur utilisateur = this.utilisateursIndex.get(noUtilisateur);
        try {
            if (utilisateur == null) {
                utilisateur = this.utilisateurDAO.selectById(noUtilisateur);
                this.utilisateursIndex.put(utilisateur.getNoUtilisateur(), utilisateur);
            }
            this.validateUser(utilisateur);
        } catch (DALException e) {
            e.printStackTrace();
        }
        return utilisateur;
    }

    public Utilisateur getUserByLogin(String pdeusoOrEmail, String password) throws BLLException {
        Utilisateur utilisateur = null;
        try {
            utilisateur = this.utilisateurDAO.selectByLogin(pdeusoOrEmail, password);
        } catch (DALException e) {
            e.printStackTrace();
        }
        if (utilisateur != null && utilisateur.getNoUtilisateur() != 0) {
            this.utilisateursIndex.put(utilisateur.getNoUtilisateur(), utilisateur);
            this.validateUser(utilisateur);
        }
        return utilisateur;
    }

    public boolean pseudoExists(String pseudo) {
        boolean exists = false;
        try {
            exists = this.utilisateurDAO.pseudoExists(pseudo);
        } catch (DALException e) {
            e.printStackTrace();
        }
        return exists;
    }

    public boolean emailExists(String pseudo) {
        boolean exists = false;
        try {
            exists = this.utilisateurDAO.emailExists(pseudo);
        } catch (DALException e) {
            e.printStackTrace();
        }
        return exists;
    }

    public boolean telephoneExists(String pseudo) {
        boolean exists = false;
        try {
            exists = this.utilisateurDAO.telephoneExists(pseudo);
        } catch (DALException e) {
            e.printStackTrace();
        }
        return exists;
    }

    private void validateUser(Utilisateur utilisateur) throws BLLException {
        if(utilisateur.getPseudo() == null
                || utilisateur.getNom() == null
                || utilisateur.getprenom() == null
                || utilisateur.getRue() == null
                || utilisateur.getCodePostal() == null
                || utilisateur.getVille() == null
                || utilisateur.getMotDePasse() == null
                || utilisateur.getCredit() < 0) {
            throw new BLLException("Invalid user");
        }
    }
}
