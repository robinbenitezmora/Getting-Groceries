package io.github.picodotdev.blogbitix.javaee7.rest;

import java.util.List;

import io.github.picodotdev.blogbitix.javaee7.beans.Cart;
import io.github.picodotdev.blogbitix.javaee7.beans.Consumes;
import io.github.picodotdev.blogbitix.javaee7.beans.Context;
import io.github.picodotdev.blogbitix.javaee7.beans.EJB;
import io.github.picodotdev.blogbitix.javaee7.beans.GET;
import io.github.picodotdev.blogbitix.javaee7.beans.NoStockException;
import io.github.picodotdev.blogbitix.javaee7.beans.POST;
import io.github.picodotdev.blogbitix.javaee7.beans.Path;
import io.github.picodotdev.blogbitix.javaee7.beans.PathParam;
import io.github.picodotdev.blogbitix.javaee7.beans.Produces;
import io.github.picodotdev.blogbitix.javaee7.beans.Purchase;
import io.github.picodotdev.blogbitix.javaee7.beans.RequestScoped;
import io.github.picodotdev.blogbitix.javaee7.beans.RolesAllowed;
import io.github.picodotdev.blogbitix.javaee7.beans.SecurityContext;
import io.github.picodotdev.blogbitix.javaee7.beans.SupermarketLocal;
import io.github.picodotdev.blogbitix.javaee7.beans.User;

@Path("purchases")
@RequestScoped
public class PurchasesResource {

    @EJB
    private SupermarketLocal supermarket;

    @Context
    SecurityContext securityContext;

    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    @RolesAllowed({ "buyer" })
    public List<Purchase> list() {
        return supermarket.findPurchases();
    }

    @GET
    @Path("{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    @RolesAllowed({ "buyer" })
    public Purchase get(@PathParam("id") Long id) {
        return supermarket.findPurchase(id);
    }

    @POST
    @Consumes({ MediaType.APPLICATION_JSON })
    @RolesAllowed({ "buyer" })
    public Purchase post(Cart cart) throws NoStockException {
        User user = supermarket.findUser(securityContext.getUserPrincipal().getName());
        return supermarket.buy(cart, user);
    }
}