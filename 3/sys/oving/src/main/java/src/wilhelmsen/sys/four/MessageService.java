package src.wilhelmsen.sys.four;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("oving4/")
public class MessageService {

    private static String currMsg = "not set yet";

    @GET
    public Response getMessage() {
        System.out.println("Get currMsg called");
        return Response
                .ok(currMsg, MediaType.TEXT_PLAIN)
                .header("Content-Length", currMsg.length())
                .header("Access-Control-Allow-Origin", "*")
                .build();

    }

    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    public Response setMessage(String msg) {
        System.out.println("Recieved " + msg);
        currMsg = msg;
        return Response
                .status(200)
                .header("Access-Control-Allow-Origin", "*")
                .build();
    }
}
