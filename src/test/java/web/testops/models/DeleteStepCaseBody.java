package web.testops.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DeleteStepCaseBody {

    private Selection selection;

    @Data
    public static class Selection {

        private boolean inverted;
        private int[] groupsInclude,
                groupsExclude,
                leafsInclude,
                leafsExclude;
        private String kind;
        private int projectId;
        private String[] path;

    }

}