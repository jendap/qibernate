package com.github.jendap.qibernate.cxf;

import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.github.jendap.qibernate.cxf.domain.Cat;

@Path("/catservice/")
@Produces({ "application/json", "application/xml" })
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
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String newCat(final Cat cat) throws RuntimeException;

	@GET
	@Path("/feedallstarvingcats")
	public String feedAllStarvingCats();
}
