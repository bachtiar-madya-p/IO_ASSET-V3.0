package id.io.asset.util.rest.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = {"stackTrace", "cause", "localizedMessage", "suppressed", "response", "message"})
public class RESTException extends WebApplicationException {
    private static final long serialVersionUID = -5173911276135556767L;
    private final int status;
    private final String description;

    public RESTException(Status responseStatus) {
        this(responseStatus.getStatusCode(), responseStatus.getReasonPhrase());
    }

    public RESTException(int status, String description) {
        super();
        this.status = status;
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

}
