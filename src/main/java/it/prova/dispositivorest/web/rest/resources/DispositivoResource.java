package it.prova.dispositivorest.web.rest.resources;

import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import it.prova.dispositivorest.dao.DispositivoDAOMock;
import it.prova.dispositivorest.model.Dispositivo;


@Path("/dispositivo")
public class DispositivoResource {

	private static final Logger LOGGER = Logger.getLogger(DispositivoResource.class.getName());

	@Context
	HttpServletRequest request;

	private DispositivoDAOMock dispositivoDaoMock;

	public DispositivoResource() {
		dispositivoDaoMock = new DispositivoDAOMock();
	}

	@GET
	@Path("{id : \\d+}")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public Response getDispositivo(@PathParam("id") Long id) {
		LOGGER.info("Verbo Http.........................." + request.getMethod());
		Dispositivo dispositivoDaCaricare = dispositivoDaoMock.findById(id);
		return Response.status(200).entity(dispositivoDaCaricare).build();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertNuovoDispositivo(Dispositivo dispositivoInput) {
		LOGGER.info("Verbo Http.........................." + request.getMethod());
		dispositivoDaoMock.insert(dispositivoInput);
		return Response.status(200).entity(dispositivoInput).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listAll() {
		LOGGER.info("Verbo Http.........................." + request.getMethod());
		List<Dispositivo> result = dispositivoDaoMock.findAll();
		return Response.status(200).entity(result).build();
	}

	@GET
	@Path("/search")
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchDispositivo(@QueryParam("marca") String marca, @QueryParam("modello") String modello) {
		LOGGER.info("Verbo Http.........................." + request.getMethod());
		List<Dispositivo> result = dispositivoDaoMock.findByFields(marca, modello);
		return Response.status(200).entity(result).build();
	}

	@DELETE
	@Path("{id : \\d+}")
	public Response deleteDispositivo(@PathParam("id") Long id) {
		LOGGER.info("Verbo Http.........................." + request.getMethod());
		if (dispositivoDaoMock.delete(id))
			return Response.status(200).entity("Rimossa Dispositivo con id: " + id).build();

		return Response.status(Response.Status.NOT_FOUND).entity("not found").build();
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Response aggiornaDispositivo(Dispositivo dispositivoInput) {
		LOGGER.info("Verbo Http.........................." + request.getMethod());
		Dispositivo result = dispositivoDaoMock.update(dispositivoInput);
		return Response.status(200).entity(result).build();
	}
	
	@GET
	@Path("/searchUnder1000AndNotYetProduced")
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchDispositivoSottoMilleENonAncoraProdotto() {
		LOGGER.info("Verbo Http.........................." + request.getMethod());
		List<Dispositivo> result = dispositivoDaoMock.findSotto1000EuroENonAncoraProdotti();
		return Response.status(200).entity(result).build();
	}
	
	
	@GET
	@Path("/searchAllSamsungAfter2020")
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchAllSamsungAfter2020() {
		LOGGER.info("Verbo Http.........................." + request.getMethod());
		List<Dispositivo> result = dispositivoDaoMock.findAllSamsungAfter2020();
		return Response.status(200).entity(result).build();
	}
	

}
