package com.github.jendap.qibernate.cxf;

import com.github.jendap.qibernate.cxf.domain.Cat;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.Collection;

@Path("/catservice/")
@Produces({"application/json", "application/xml"})
public interface CatServiceRS {
    @GET
    @Path("/cat/{name}")
    Collection<Cat> findByName(@PathParam("name") final String name);

    @GET
    @Path("/byage")
    Collection<Cat> findByAge(@FormParam("from") final int from,
                                     @FormParam("to") final int to);

    @POST
    @Path("/new")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    String newCat(final Cat cat) throws RuntimeException;

    @GET
    @Path("/feedallstarvingcats")
    String feedAllStarvingCats();
}
