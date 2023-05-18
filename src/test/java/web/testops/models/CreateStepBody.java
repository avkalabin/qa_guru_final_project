package web.testops.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateStepBody {
    private List<Steps> steps;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Steps {
        String name, spacing;
        Integer workPath;

    }

}
