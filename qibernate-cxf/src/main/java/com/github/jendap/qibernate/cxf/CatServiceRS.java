package com.github.jendap.qibernate.cxf;

import com.github.jendap.qibernate.cxf.domain.Cat;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

@Path("/catservice/")
@Produces({"application/json", "application/xml"})
public interface CatServiceRS {
    @GET
    @Path("/cat/{name}")
    public Collection<Cat> findByName(@PathParam("name") final String name);

    @GET
    @Path("/byage")
    public Collection<Cat> findByAge(@FormParam("from") final int from,
                                     @FormParam("to") final int to);

    @POST
    @Path("/new")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public String newCat(final Cat cat) throws RuntimeException;

    @GET
    @Path("/feedallstarvingcats")
    public String feedAllStarvingCats();
}
