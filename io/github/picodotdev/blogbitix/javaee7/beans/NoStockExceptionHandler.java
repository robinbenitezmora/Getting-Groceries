package io.github.picodotdev.blogbitix.javaee7.beans;

public class NoStockExceptionHandler implements ExceptionMapper<NoStockException> {
 @Override
 public Response toResponse(NoStockException exception) {
  return Response.status(Response.Status.FORBIDDEN.getStatusCode()).entity(exception.getMessage()).build();
 }
}
