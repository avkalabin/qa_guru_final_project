package web.testops.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateTestCaseResponse {

    private Integer id;
    private String name, statusName, statusColor;
    private Boolean automated, external;
    private Long createdDate;

}