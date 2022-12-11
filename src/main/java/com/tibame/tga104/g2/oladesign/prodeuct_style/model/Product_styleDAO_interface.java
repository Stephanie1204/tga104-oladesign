package com.tibame.tga104.g2.oladesign.prodeuct_style.model;
import java.util.List;
public interface Product_styleDAO_interface {
    public void insert(Product_styleVO product_styleVO);
    public void update(Product_styleVO product_styleVO);
    public void delete(String styleCode);
    public Product_styleVO findByPrimaryKey(String styleCode);
    public List<Product_styleVO> getAll();
}
