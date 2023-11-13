package app.template.api;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import app.template.models.ListVirtualMachines;
import app.template.services.CloudStackService;
import app.template.services.PublisherService;


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
        String vmRequest = payloadMapper(vm);

        System.out.println(vmRequest);

        String response = cs.executeCloudStackRequest("listVirtualMachines", vmRequest);
        vm.listVirtualMachineResponseHandler(response);

        System.out.println(vm.virtualMachinesList);

        publisher(response);

        return Response.ok(response).build();
    }

    @PUT
    @Path("/{vmName}")
    public Response updateVirtualMachine(
            @HeaderParam("x-application-id") String applicationId,
            @PathParam("vmName") String vmName) throws JsonProcessingException {
        ListVirtualMachines vm = new ListVirtualMachines();
        vm.setName(vmName);
        vm.setListAll(true);
        String vmRequest = payloadMapper(vm);

        System.out.println(vmRequest);
        String response = cs.executeCloudStackRequest("listVirtualMachines", vmRequest);

        publisher(response);

        return Response.ok(response).build();
    }

    private String payloadMapper(Object requestModel) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String requestJsonString = objectMapper.writeValueAsString(requestModel);

        return requestJsonString;
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
