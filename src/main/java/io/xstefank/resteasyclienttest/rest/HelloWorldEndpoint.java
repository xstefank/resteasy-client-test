package io.xstefank.resteasyclienttest.rest;


import javax.ws.rs.Path;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import java.util.concurrent.ForkJoinPool;


@Path("/hello")
public class HelloWorldEndpoint {
    @GET
    @Produces("text/plain")
    public Response doGet() {
        ForkJoinPool.commonPool()
            .execute(() -> {
                Client client = ClientBuilder.newClient();
                String s = client.target("http://www.google.com")
                    .request()
                    .get()
                    .readEntity(String.class);

                client.close();
                System.out.println(s);
            });


        return Response.ok("ok").build();
    }
}
