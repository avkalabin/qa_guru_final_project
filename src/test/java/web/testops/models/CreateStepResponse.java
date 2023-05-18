package web.testops.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateStepResponse {

    private List<Steps> steps;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Steps {

        private String  name;
        private Integer stepsCount;
        private boolean hasContent, leaf;
        private List<Steps> steps;

    }
}