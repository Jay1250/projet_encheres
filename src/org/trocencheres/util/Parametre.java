package org.trocencheres.util;

import java.util.ResourceBundle;

public class Parametre {

	public static String lire(String cle){
		ResourceBundle bundle = ResourceBundle.getBundle("org.trocencheres.util.param");
		
		return (null!=bundle) ? bundle.getString(cle) : null;
	}

}
