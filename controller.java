package controllers;

import entities.medicine;
import repositories.MedicineRep;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

    @Path("users")
    public class controller {
        @Inject
        private MedicineRep repo;

        @GET
        @Produces(MediaType.APPLICATION_JSON)
        public Response medicinefinder() {
            List<medicine> medicines;
            try {
                medicines = repo.medicinefinder();
            } catch (ServerErrorException ex) {
                return Response
                        .status(500).entity(ex.getMessage()).build();
            }

            return Response
                    .status(Response.Status.OK)
                    .entity(medicines)
                    .build();
        }

        @POST
        @Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
        public Response setMedicine(medicine med) {
            boolean created;
            try {
                created = repo.setMedicine(med);
            } catch (ServerErrorException ex) {
                return Response.serverError().entity(ex.getMessage()).build();
            }

            if (!created) {
                return Response
                        .status(Response.Status.BAD_REQUEST)
                        .entity("medicine cannot be created!")
                        .build();
            }

            return Response
                    .status(Response.Status.CREATED)
                    .entity("medicine created successfully!")
                    .build();
        }

        @GET
        @Path("/{id}")
        @Produces(MediaType.APPLICATION_JSON)
        public Response getId(@PathParam("id") int id) {
            medicine med;
            try {
                med = repo.getId(id);
            } catch (ServerErrorException ex) {
                return Response
                        .status(500).entity(ex.getMessage()).build();
            }

            if (med == null) {
                return Response
                        .status(Response.Status.NOT_FOUND)
                        .entity("medicine does not exist!")
                        .build();
            }

            return Response
                    .status(Response.Status.OK)
                    .entity(med)
                    .build();
        }
    }

