package web.testops.models;

import lombok.Data;

@Data
public class CreateTestCaseResponse {

    private Integer id;
    private String name, statusName, statusColor;
    private Boolean automated, external;
    private Long createdDate;

}