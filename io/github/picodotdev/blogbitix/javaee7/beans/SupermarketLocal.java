package io.github.picodotdev.blogbitix.javaee7.beans;

import java.util.List;

@Local

public interface SupermarketLocal {

 List<Product> findProducts();

 Product findProduct(Long id);

 void persistProduct(Product product);

 void deleteProduct(Product product);

 List<Purchase> findPurchases();

 Purchase findPurchase(Long id);

 Purchase buy(Cart cart, User buyer) throws Exception;
}