package app.template.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import app.template.contracts.ListVirtualMachines;

public class VirtualMachineService {
    private static CloudStackService cs = new CloudStackService();

    public static String listVirtualMachines(ListVirtualMachines virtualMachineParams) throws JsonProcessingException {
        String vmRequest = payloadMapper(virtualMachineParams);
        String response = cs.executeCloudStackRequest("listVirtualMachines", vmRequest);
        return response;
    }

    private static String payloadMapper(Object requestModel) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String requestJsonString = objectMapper.writeValueAsString(requestModel);

        return requestJsonString;
    }
}
