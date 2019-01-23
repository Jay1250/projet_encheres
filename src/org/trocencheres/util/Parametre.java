package org.trocencheres.util;

import java.util.ResourceBundle;

class Parametre {

	static String lire(String cle){
		ResourceBundle bundle = ResourceBundle.getBundle("org.trocencheres.util.param");
		
		return (null!=bundle) ? bundle.getString(cle) : null;
	}

}
