package io.github.picodotdev.blogbitix.javaee7.beans;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SupermarketClient {
 public static void main(String[] args) throws Exception {
  List<Product> products = new ArrayList<>(); // Initialize the products variable
  printProductsSummary(products);
  List<Purchase> purchases = new ArrayList<>(); // Initialize the purchases variable
  printPurchasesSummary(purchases);
 }

 private static void printProductsSummary(List<Product> products) {
  BigDecimal maxPriceProduct = products.stream().map(a -> {
   return a.getPrice();
  }).sorted((a, b) -> {
   return b.compareTo(a);
  }).findFirst().orElse(new BigDecimal("0.00"));
  BigDecimal minPriceProduct = products.stream().map(a -> {
   return a.getPrice();
  }).sorted((a, b) -> {
   return a.compareTo(b);
  }).findFirst().orElse(new BigDecimal("0.00"));
  BigDecimal sumPriceProduct = products.stream().map(p -> p.getPrice())
    .collect(Collectors.reducing(new BigDecimal("0.00"), (result, element) -> {
     return result.add(element);
    }));
  BigDecimal avgPriceProduct = (products.size() == 0) ? new BigDecimal("0.00")
    : sumPriceProduct.divide(new BigDecimal(products.size()), RoundingMode.HALF_UP);
  Double avgStockProduct = products.stream().collect(Collectors.averagingDouble(p -> {
   return p.getStock();
  }));
  System.out.printf("Products summary(count: %d, maxPrice: %s, minPrice: %s, avgPrice: %s, avgStock: %s)\n",
    products.size(), maxPriceProduct, minPriceProduct, avgPriceProduct, avgStockProduct);
 }

 private static void printPurchasesSummary(List<Purchase> purchases) {
  Integer numProducts = purchases.size();
  BigDecimal maxPriceProduct = (BigDecimal) purchases.stream().map(a -> {
   return a.getPrice();
  }).sorted((a, b) -> {
   return ((BigDecimal) b).compareTo((BigDecimal) a);
  }).findFirst().orElse(new BigDecimal("0.00"));
  BigDecimal minPriceProduct = (BigDecimal) purchases.stream().map(a -> {
   return a.getPrice();
  }).sorted((a, b) -> {
   return ((BigDecimal) a).compareTo((BigDecimal) b);
  }).findFirst().orElse(new BigDecimal("0.00"));
  BigDecimal sumPriceProduct = (BigDecimal) purchases.stream().map(p -> p.getPrice())
    .collect(Collectors.reducing(new BigDecimal("0.00"), (result, element) -> {
     return ((BigDecimal) result).add((BigDecimal) element);
    }));
  BigDecimal avgPriceProduct = (purchases.size() == 0) ? new BigDecimal("0.00")
    : sumPriceProduct.divide(new BigDecimal(purchases.size()), RoundingMode.HALF_UP);
  System.out.printf("Purchases summary(count: %d, maxPrice: %s, minPrice: %s, avgPrice: %s)\n", numProducts,
    maxPriceProduct, minPriceProduct, avgPriceProduct);
 }
}
