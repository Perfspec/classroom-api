package com.qa.rest;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.qa.business.service.ClassroomService;

@Path("/classroom")
public class ClassroomEndpoint {

	@Inject
	private ClassroomService service;

	@Path("/getAll")
	@GET
	@Produces({ "application/json" })
	public String getAll() {
		return service.getAll();
	}

	@Path("/getClass/{idC}")
	@GET
	@Produces({ "application/json" })
	public String getClass(@PathParam("idC") Integer idClass) {
		return service.getClass(idClass);
	}

	@Path("/getTrainee/{idC}/{idT}")
	@GET
	@Produces({ "application/json" })
	public String getTrainee(@PathParam("idC") Integer idClass, @PathParam("idT") Integer idTrainee) {
		return service.getTrainee(idClass, idTrainee);
	}

	@Path("/createClass")
	@POST
	@Produces({ "application/json" })
	public String createClass(String newClass) {
		return service.createClass(newClass);
	}

	@Path("/createTrainee/{idC}")
	@POST
	@Produces({ "application/json" })
	public String createTrainee(@PathParam("idC") Integer idClass, String newTrainee) {
		return service.createTrainee(idClass, newTrainee);
	}

	@Path("/updateTrainee/{idC}")
	@PUT
	@Produces({ "application/json" })
	public String updateTrainee(@PathParam("idC") Integer idClass, String newTrainee) {
		return service.updateTrainee(idClass, newTrainee);
	}

	@Path("/updateTrainer/{idC}")
	@PUT
	@Produces({ "application/json" })
	public String updateTrainer(@PathParam("idC") Integer idClass, String trainerName) {
		return service.updateTrainer(idClass, trainerName);
	}

	@Path("/deleteClass/{idC}")
	@DELETE
	@Produces({ "application/json" })
	public String deleteClass(@PathParam("idC") Integer idClass) {
		return service.deleteClass(idClass);
	}

	@Path("/deleteTrainee/{idC}/{idT}")
	@DELETE
	@Produces({ "application/json" })
	public String deleteTrainee(@PathParam("idC") Integer idClass, @PathParam("idT") Integer idTrainee) {
		return service.deleteTrainee(idClass, idTrainee);
	}

	public void setService(ClassroomService service) {
		this.service = service;
	}
}
