package U4.src.control;

import java.io.Serializable;

public interface UserStory extends Serializable {
    public String getTitle();
    public Integer getID();
    public int getRelativeValue();
    public int getRelativePenalty();
    public int getRelativeRisk();
    public int getExpense();

    public float calculatePrio(int relativeValue, int relativePenalty, int relativeRisk, int expense);

    public float getPrio();
    public String getAcceptanceCriteria();
    public String getProject();
}
