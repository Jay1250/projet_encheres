package org.trocencheres.bll;

import org.trocencheres.beans.*;
import org.trocencheres.dal.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Kévin Le Devéhat
 */
public class ProjetEnchereManager {
    private static ProjetEnchereManager instance;

    private IUtilisateurDAO IUtilisateurDAO;
    private IVenteDAO IVenteDAO;
    private ICategorieDAO ICategorieDAO;
    private IRetraitDAO IRetraitDAO;
    private IEnchereDAO IEnchereDAO;

    private Map<Integer, Utilisateur> utilisateursIndex;
    private Map<Integer, Vente> ventesIndex;
    private Map<Integer, Categorie> categoriesIndex;


    private ProjetEnchereManager() throws BLLException {
        this.IUtilisateurDAO = UtilisateurDAOFactory.getUtilisateurDao();
        this.utilisateursIndex = new HashMap<>();

        this.IVenteDAO = VenteDAOFactory.getVenteDAO();
        this.ventesIndex = new HashMap<>();

        this.ICategorieDAO = CategorieDAOFactory.getCategorieDAO();
        Map<Integer, Categorie> categories = new HashMap<>();
        try {
            List<Categorie> categoriesList = ICategorieDAO.selectAll();
            for(Categorie c : categoriesList) {
                this.validateCategory(c);
                categories.put(c.getNoCategorie(), c);
            }
        } catch (DALException e) {
            e.printStackTrace();
        }

        this.categoriesIndex = categories;


        this.IRetraitDAO = RetraitDAOFactory.getRetraitDAO();

        this.IEnchereDAO = EnchereDAOFactory.getEnchereDAO();
    }

    public static ProjetEnchereManager getInstance() throws BLLException {
        if(instance == null) instance = new ProjetEnchereManager();
        return instance;
    }



    public Utilisateur getUserById(int noUtilisateur) throws BLLException {
        Utilisateur utilisateur = this.utilisateursIndex.get(noUtilisateur);
        try {
            if (utilisateur == null) {
                utilisateur = this.IUtilisateurDAO.selectById(noUtilisateur);
                this.validateUser(utilisateur);
                this.utilisateursIndex.put(utilisateur.getNoUtilisateur(), utilisateur);
            }
        } catch (DALException e) {
            e.printStackTrace();
        }
        return utilisateur;
    }

    public Utilisateur getUserByLogin(String pdeusoOrEmail, String password) throws BLLException {
        Utilisateur utilisateur = null;
        try {
            utilisateur = this.IUtilisateurDAO.selectByLogin(pdeusoOrEmail, password);
        } catch (DALException e) {
            e.printStackTrace();
        }
        if (utilisateur != null && utilisateur.getNoUtilisateur() != 0) {
            this.validateUser(utilisateur);
            this.utilisateursIndex.put(utilisateur.getNoUtilisateur(), utilisateur);
        }
        return utilisateur;
    }

    public void addUser(Utilisateur utilisateur) throws BLLException {
        try {
            this.validateUser(utilisateur);
            this.IUtilisateurDAO.insert(utilisateur);
            this.utilisateursIndex.put(utilisateur.getNoUtilisateur(), utilisateur);
        } catch (DALException e) {
            e.printStackTrace();
        }
    }

    public void updateUser(Utilisateur utilisateur) throws BLLException  {
        try {
            this.validateUser(utilisateur);
            this.IUtilisateurDAO.update(utilisateur);
            this.utilisateursIndex.put(utilisateur.getNoUtilisateur(), utilisateur);
        } catch (DALException e) {
            e.printStackTrace();
        }
    }

    public void removeUser(int noUtilisateur) {
        try {
            this.IUtilisateurDAO.delete(noUtilisateur);
            this.utilisateursIndex.remove(noUtilisateur);
        } catch (DALException e) {
            e.printStackTrace();
        }
    }

    public boolean pseudoExists(String pseudo) {
        boolean exists = false;
        try {
            exists = this.IUtilisateurDAO.pseudoExists(pseudo);
        } catch (DALException e) {
            e.printStackTrace();
        }
        return exists;
    }

    public boolean emailExists(String pseudo) {
        boolean exists = false;
        try {
            exists = this.IUtilisateurDAO.emailExists(pseudo);
        } catch (DALException e) {
            e.printStackTrace();
        }
        return exists;
    }

    public boolean telephoneExists(String pseudo) {
        boolean exists = false;
        try {
            exists = this.IUtilisateurDAO.telephoneExists(pseudo);
        } catch (DALException e) {
            e.printStackTrace();
        }
        return exists;
    }

    private void validateUser(Utilisateur utilisateur) throws BLLException {
        if(utilisateur.getPseudo() == null
                || utilisateur.getPseudo().length() > 30
                || utilisateur.getNom() == null
                || utilisateur.getNom().length() > 30
                || utilisateur.getPrenom() == null
                || utilisateur.getPrenom().length() > 30
                || utilisateur.getEmail() == null
                || utilisateur.getEmail().length() > 20
                || (utilisateur.getMotDePasse() != null && utilisateur.getMotDePasse().length() > 30)
                || (utilisateur.getTelephone() != null && utilisateur.getTelephone().length() > 15)
                || utilisateur.getRue() == null
                || utilisateur.getRue().length() > 30
                || utilisateur.getCodePostal() == null
                || utilisateur.getCodePostal().length() > 10
                || utilisateur.getVille() == null
                || utilisateur.getVille().length() > 30
                || utilisateur.getCredit() < 0) {
            throw new BLLException("Invalid user");
        }
    }



    public Vente getSaleById(int noVente) throws BLLException {
        Vente vente = this.ventesIndex.get(noVente);
        try {
            if (vente == null) {
                vente = this.IVenteDAO.selectById(noVente);
                Retrait retrait = this.IRetraitDAO.selectByIdVente(noVente);
                this.validateWithdrawal(retrait);
                vente.setRetrait(retrait);
                this.validateSale(vente);
                this.ventesIndex.put(vente.getNoUtilisateur(), vente);
            }
        } catch (DALException e) {
            e.printStackTrace();
        }
        return vente;
    }

    public void addSale(Vente vente, Retrait retrait) throws BLLException {
        try {
            this.validateSale(vente);
            this.IVenteDAO.insert(vente);
            retrait.setNoVente(vente.getNoVente());
            this.validateWithdrawal(retrait);
            vente.setRetrait(retrait);
            this.IRetraitDAO.insert(retrait);
            this.ventesIndex.put(vente.getNoVente(), vente);
        } catch (DALException e) {
            e.printStackTrace();
        }
    }

    public void updateSale(Vente vente) throws BLLException  {
        try {
            this.validateSale(vente);
            Retrait retrait = vente.getRetrait();
            this.validateWithdrawal(retrait);
            this.IVenteDAO.update(vente);
            this.IRetraitDAO.update(retrait);
            this.ventesIndex.put(vente.getNoVente(), vente);
        } catch (DALException e) {
            e.printStackTrace();
        }
    }

    public void removeSale(int noVente) {
        try {
            this.IRetraitDAO.delete(noVente);
            this.IVenteDAO.delete(noVente);
            this.ventesIndex.remove(noVente);
        } catch (DALException e) {
            e.printStackTrace();
        }
    }

    private void validateSale(Vente vente) throws BLLException {
        if(vente.getNomArticle() == null
                || vente.getDescription() == null
                || vente.getDateFinEncheres() == null
                || vente.getNoUtilisateur() == 0
                || vente.getNoCategorie() == 0
                || vente.getRetrait() == null) {
            throw new BLLException("Invalid sale");
        }
    }



    private void updateWithdrawal(Retrait retrait) throws BLLException {
        try {
            this.validateWithdrawal(retrait);
            Vente vente = this.getSaleById(retrait.getNoVente());
            vente.setRetrait(retrait);
            this.validateSale(vente);
            this.IRetraitDAO.update(retrait);
        } catch (DALException e) {
            e.printStackTrace();
        }
    }

    private void validateWithdrawal(Retrait retrait) throws BLLException {
        if(retrait.getNoVente() == 0
                || retrait.getRue() == null
                || retrait.getCodePostal() == null
                || retrait.getVille() == null) {
            throw new BLLException("Invalid withdrawal");
        }
    }



    public Categorie getCategoryById(int noCategorie) throws BLLException {
        Categorie categorie = this.categoriesIndex.get(noCategorie);
        try {
            if (categorie == null) {
                categorie = this.ICategorieDAO.selectById(noCategorie);
                this.validateCategory(categorie);
                this.categoriesIndex.put(categorie.getNoCategorie(), categorie);
            }
        } catch (DALException e) {
            e.printStackTrace();
        }
        return categorie;
    }

    public Map<Integer, Categorie> getCategories() {
        return this.categoriesIndex;
    }

    public void addCategory(Categorie categorie) throws BLLException {
        try {
            this.validateCategory(categorie);
            this.ICategorieDAO.insert(categorie);
            this.categoriesIndex.put(categorie.getNoCategorie(), categorie);
        } catch (DALException e) {
            e.printStackTrace();
        }
    }

    public void updateCategory(Categorie categorie) throws BLLException {
        try {
            this.validateCategory(categorie);
            this.ICategorieDAO.update(categorie);
            this.categoriesIndex.put(categorie.getNoCategorie(), categorie);
        } catch (DALException e) {
            e.printStackTrace();
        }
    }

    public void removeCategory(int noCategorie) {
        try {
            this.ICategorieDAO.delete(noCategorie);
            this.categoriesIndex.remove(noCategorie);
        } catch (DALException e) {
            e.printStackTrace();
        }
    }

    private void validateCategory(Categorie categorie) throws BLLException {
        if(categorie.getNoCategorie() == 0 || categorie.getLibelle() == null)
            throw new BLLException("Invalid withdrawal");
    }



    public Enchere getAuctionByIds(Integer noVente, Integer noUtilisateur) throws BLLException {
        Enchere enchere = new Enchere();
        try {
            enchere = this.IEnchereDAO.selectByIds(noVente, noUtilisateur);
            this.validateAuction(enchere);
        } catch (DALException e) {
            e.printStackTrace();
        }
        return enchere;
    }

    public void addAuction(Enchere enchere) throws BLLException {
        try {
            this.validateAuction(enchere);
            this.IEnchereDAO.insert(enchere);
        } catch (DALException e) {
            e.printStackTrace();
        }
    }

    public void removeAuction(int noVente, int noUtilisateur) throws BLLException {
        try {
            this.IEnchereDAO.delete(noVente, noUtilisateur);
        } catch (DALException e){
            e.printStackTrace();
        }
    }

    private void validateAuction(Enchere enchere) throws BLLException {
        if(enchere.getDateEnchere() == null || enchere.getNoUtilisateur() == 0 || enchere.getNoVente() == 0)
            throw new BLLException("Invalid auction");
    }
}
