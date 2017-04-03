package kmitl.sp.smp.model.server.response.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * Created by Jo on 4/3/2017.
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiBaseResponse<T> {
    @JsonProperty("code")
    private int code;

    @JsonProperty("status")
    private String status;

    @JsonProperty("data")
    private T data;

    public ApiBaseResponse(HttpStatus httpStatus, T data) {
        this.code = httpStatus.value();
        this.status = httpStatus.getReasonPhrase();
        this.data = data;
    }

    public ApiBaseResponse(int code, String status, T data) {
        this.code = code;
        this.status = status;
        this.data = data;
    }

}