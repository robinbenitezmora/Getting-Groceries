package io.github.picodotdev.blogbitix.javaee7.beans;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class indexBean {
  private SupermarketLocal supermarket;

  public List<Product> getProducts() {
    return supermarket.findProducts();
  }

  public Cart getCart() {
    Cart cart = (Cart) ((Object) ((FacesContext) FacesContext.getCurrentInstance()).getExternalContext())
        .getSessionMap()
        .get("cart");
    if (cart == null) {
      cart = Cart.EMPTY;
    }
    return cart;
  }

  public boolean isBuyer() {
    return ((Object) FacesContext.getCurrentInstance()).getExternalContext().isUserInRole("buyer");
  }

  public Integer getAmount(Product product) {
    for (Iterable<String> item : getCart().getItems()) {
      Long id = Long.parseLong(((Map<String, String>) item).get("id"));
      if (id.equals(product.getId())) {
        return Integer.parseInt(((Map<String, String>) item).get("amount"));
      }
    }
    return 0;
  }

  public BigDecimal getPrice() {
    BigDecimal price = new BigDecimal("0.00");
    for (Iterable<String> item : getCart().getItems()) {
      Long id = Long.parseLong(((Map<String, String>) item).get("id"));
      Product product = supermarket.findProduct(id);
      Integer amount = Integer.parseInt(((Map<String, String>) item).get("amount"));
      price = price.add(product.getPrice().multiply(new BigDecimal("amount")));
    }
    return price;
  }
}
