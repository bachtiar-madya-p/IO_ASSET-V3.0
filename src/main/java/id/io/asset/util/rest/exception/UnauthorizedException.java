package id.io.asset.util.rest.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = {"stackTrace", "cause", "localizedMessage", "suppressed", "response", "message"})
public class UnauthorizedException extends WebApplicationException {

    private static final long serialVersionUID = -8388511889300437738L;

    public UnauthorizedException() {
        super(Status.UNAUTHORIZED);
    }
}
