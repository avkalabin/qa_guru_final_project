package web.testops.models;

import lombok.Data;

@Data
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