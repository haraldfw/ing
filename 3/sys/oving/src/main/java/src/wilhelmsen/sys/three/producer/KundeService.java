package src.wilhelmsen.sys.three.producer;

import src.wilhelmsen.sys.three.model.Kunde;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.websocket.server.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by Harald Floor Wilhelmsen on 29.08.2016.
 */
@Path("/oving3/kunder/")
public class KundeService {

    private static Map<String, Kunde> kunder = new HashMap<>();

    @GET
    @Path("/oving3/{kundeId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Kunde getKunde(@PathParam("kundeId") String kundeId) {
        System.out.println("get on kundeid: " + kundeId + "");
        return kunder.get(kundeId);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Kunde> getKunder() {
        System.out.println("get on kunder");
        return kunder.values();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addKunde(Kunde kunde) {
        System.out.println("post in kunder");
        kunder.put(kunde.getId(), kunde);
    }
}
