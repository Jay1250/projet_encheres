package fr.eni.encheres.util;

import java.util.ResourceBundle;

public class Parametre {

	public static String lire(String cle){
		ResourceBundle bundle = ResourceBundle.getBundle("fr.eni.encheres.util.param");
		
		return (null!=bundle) ? bundle.getString(cle) : null;
	}

}
