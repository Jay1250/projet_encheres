package org.trocencheres.util;

import java.util.ResourceBundle;

class Parametre {

	static String lireJulie(String cle){
		ResourceBundle bundle = ResourceBundle.getBundle("org.trocencheres.util.param");
		
		return (null!=bundle) ? bundle.getString(cle) : null;
	}
	static String lireBddJulie(String cle) {
ResourceBundle bundle = ResourceBundle.getBundle("org.trocencheres.util.paramJulie");
		
		return (null!=bundle) ? bundle.getString(cle) : null;
	}

}
