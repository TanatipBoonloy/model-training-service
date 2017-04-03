package kmitl.sp.smp.model.server.response.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Created by Jo on 4/4/2017.
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserModel {
    @JsonProperty("w12")
    private double[][] w12;

    @JsonProperty("w23")
    private double[][] w23;
}
