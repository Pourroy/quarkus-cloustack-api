package app.template.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import app.template.contracts.ListVirtualMachines;
import app.template.services.CloudStackService;
import app.template.services.PublisherService;
import app.template.services.VirtualMachineService;


@Path("/cloud")
public class VirtualMachinesApi {
    CloudStackService cs = new CloudStackService();
    @GET
    @Path("/{vmName}")
    public Response getVirtualMachineByName(
            @HeaderParam("x-application-id") String applicationId,
            @PathParam("vmName") String vmName) throws JsonProcessingException {
        ListVirtualMachines vm = new ListVirtualMachines();
        vm.setName(vmName);
        vm.setListAll(true);

        String response = VirtualMachineService.listVirtualMachines(vm);
        vm.listVirtualMachineResponseHandler(response);

        System.out.println(vm.virtualMachinesList);

        publisher(response);

        return Response.ok(response).build();
    }

    private void publisher(String message) {
        PublisherService publisher = new PublisherService();
        publisher.setQueueName("quarkus-test");
        publisher.setExchange("exg-cloud");
        publisher.setRoutingKey("quarkus-t");
        publisher.setMessage(message);

        publisher.publish();
    }
}
