package kmitl.sp.smp.model.server.response.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

/**
 * Created by Jo on 4/3/2017.
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiBaseErrorResponse {
    @JsonProperty("code")
    private int code;

    @JsonProperty("status")
    private String status;

    @JsonProperty("message")
    private String message;

    @JsonProperty("developerMessage")
    private String developerMessage;

    @JsonProperty("timestamp")
    private Date timestamp;
}
