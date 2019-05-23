package ses1grp6.DBSystemBE.model;

/**
 * Created by Will Smith on 5/4/19.
 */
public class Response {
    ResponseStatus status;
    Object body;

    public Response(ResponseStatus status, Object body) {
        this.status = status;
        this.body = body;
    }

    public ResponseStatus getStatus() {
        return status;
    }

    public void setStatus(ResponseStatus status) {
        this.status = status;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    public static <T> Response success(T body) {
        return new Response(ResponseStatus.SUCCESS, body);
    }

    public static <T> Response fail(T body) {
        return new Response(ResponseStatus.FAIL, body);
    }
}
