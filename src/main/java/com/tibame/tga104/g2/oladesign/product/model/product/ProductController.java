package com.tibame.tga104.g2.oladesign.product.model.product;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class ProductController {
	ProductService service = new ProductService();

	@GetMapping("/product")
	public ProductBean getProd(@RequestParam("productId") int productId) {
		return service.selectByProdId(productId);
		
	}
}
