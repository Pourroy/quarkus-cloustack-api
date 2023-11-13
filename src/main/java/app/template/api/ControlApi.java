package app.template.api;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.stream.Stream;


@Path("/controls")
public class ControlApi {
  @Inject
    EntityManager entityManager;

    @Transactional
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Stream yourCustomQuery() {
      return entityManager.createNativeQuery("SELECT * FROM controls").getResultStream();
    }
}

