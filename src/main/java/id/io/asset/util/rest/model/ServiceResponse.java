package id.io.asset.util.rest.model;

import javax.ws.rs.core.Response.Status;

public class ServiceResponse {

    private int status;
    private String description;

    public ServiceResponse() {
    }

    public ServiceResponse(Status responseStatus) {
        status = responseStatus.getStatusCode();
        description = responseStatus.getReasonPhrase();
    }

    public ServiceResponse(Status responseStatus, String description) {
        status = responseStatus.getStatusCode();
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
