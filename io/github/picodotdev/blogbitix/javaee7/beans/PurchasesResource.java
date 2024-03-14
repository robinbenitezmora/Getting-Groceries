package io.github.picodotdev.blogbitix.javaee7.beans;

import java.util.List;

@Path("purchases")
@RequestScoped
public class PurchasesResource {

    @EJB
    private SupermarketLocal supermarket;

    @Context(lookup = (String) "java:comp/SecurityContext")
    SecurityContext securityContext;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({ "buyer" })
    public List<Purchase> list() {
        return supermarket.findPurchases();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
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