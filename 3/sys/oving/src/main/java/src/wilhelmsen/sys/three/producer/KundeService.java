package src.wilhelmsen.sys.three.producer;

import src.wilhelmsen.sys.three.model.Kunde;

import javax.websocket.server.PathParam;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

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
        Kunde kunde = kunder.get(kundeId);
        if (kunde == null) {
            throw new NotFoundException("Kunde with id '" + kundeId + "' does not exist");
        }
        return kunde;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Kunde> getKunder() {
        System.out.println("get kunder");
        return kunder.values();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addKunde(Kunde kunde) {
        kunder.put(kunde.getId(), kunde);
    }

    @DELETE
    @Path("/oving3/kunder/{kundeId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteKunde(@PathParam("kundeId") String kundeId) {
        System.out.println("Kunde with id '" + kundeId + "' was deleted");
        if (!kunder.containsKey(kundeId)) {
            throw new NotFoundException("Kunde with id '" + kundeId + "' does not exist");
        }
        kunder.remove(kundeId);
        System.out.println("Kunde with id '" + kundeId + "' was deleted");
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateKunde(Kunde kunde) {
        String kundeId = kunde.getId();
        Kunde localKunde = kunder.get(kundeId);
        if (localKunde == null) {
            throw new NotFoundException("Kunde with id '" + kundeId + "' does not exist");
        }
        localKunde.setNavn(kunde.getNavn());
        System.out.println("Kunde with kundeid '" + kundeId + "' updated");
    }

    // TODO: 01.09.2016
    // ny DELETE-metode i KundeService.
    // method=DELETE
    // path /kunder/${kundeId}.
    // Slett elementet med gitt id fra map-objektet på serveren

    // oppdatering av et element
    // method=PUT
    // returnere 404 hvis elementet ikke eksisterer.
    //     kaste "javax.ws.rs.NotFoundException" i metoden.

}
