package app.template.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateVirtualMachines {
    @JsonProperty("id")
    private String id;

    @JsonProperty("force")
    private boolean force;

    public void setId(String id) {
        this.id = id;
    }

    public void setForce(boolean force) {
        this.force = force;
    }
}