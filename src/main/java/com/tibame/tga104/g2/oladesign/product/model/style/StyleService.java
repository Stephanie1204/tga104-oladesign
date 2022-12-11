package com.tibame.tga104.g2.oladesign.product.model.style;

import java.util.List;

public class StyleService {

	//create productDAO object
		private StyleDAO styleDao = new StyleDAOJdbc();
		
		public static void main(String[] args) {
			

		}
		
		public List<StyleBean> getAll(){
			List<StyleBean> result = null;
			result = styleDao.getAll(); 
			return result;
		}
}
