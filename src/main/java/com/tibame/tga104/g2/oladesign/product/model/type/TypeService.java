package com.tibame.tga104.g2.oladesign.product.model.type;

import java.util.List;

public class TypeService {

	//create productDAO object
		private TypeDAO typeDao = new TypeDAOJdbc();
		
		public static void main(String[] args) {
			

		}
		
		public List<TypeBean> getAll(){
			List<TypeBean> result = null;
			result = typeDao.getAll(); 
			return result;
		}
}
