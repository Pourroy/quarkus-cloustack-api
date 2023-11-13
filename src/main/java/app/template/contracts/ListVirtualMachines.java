package app.template.contracts;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListVirtualMachines {

    @JsonProperty("virtualMachinesList")
    public JsonNode virtualMachinesList;

    @JsonProperty("name")
    private String name;

    @JsonProperty("listall")
    private boolean listAll;

    @JsonProperty("account")
    private String account;

    @JsonProperty("domainid")
    private String domainid;

    @JsonProperty("networkid")
    private String networkid;

    public void setName(String name) {
        this.name = name;
    }

    public void setListAll(boolean listAll) {
        this.listAll = listAll;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setDomainid(String domainid) {
        this.domainid = domainid;
    }

    public void setNetworkid(String networkid) {
        this.networkid = networkid;
    }

    public void listVirtualMachineResponseHandler(String response) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode vmListJson = objectMapper.readTree(response);
        this.virtualMachinesList = vmListJson.path("listvirtualmachinesresponse").path("virtualmachine");
    }
}
