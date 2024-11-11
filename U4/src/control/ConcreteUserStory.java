package U4.src.control;

public class ConcreteUserStory implements UserStory{
    Integer ID;
    int relValue,relPenalty,relRisk,expense;
    String title,acceptanceCriteria,project;

    public ConcreteUserStory(Integer ID, String title, int relValue,int relPenalty, int relRisk, int expense,String acceptanceCriteria,String project){
        this.ID=ID;
        this.title=title;
        this.relValue=relValue;
        this.relPenalty=relPenalty;
        this.relRisk=relRisk;
        this.expense=expense;
        this.acceptanceCriteria=acceptanceCriteria;
        this.project=project;
    }
    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public Integer getID() {
        return this.ID;
    }

    @Override
    public int getRelativeValue() {
        return this.relValue;
    }

    @Override
    public int getRelativePenalty() {
        return this.relPenalty;
    }

    @Override
    public int getRelativeRisk() {
        return this.relRisk;
    }

    @Override
    public int getExpense() {
        return this.expense;
    }

    @Override
    public float calculatePrio(int relativeValue, int relativePenalty, int relativeRisk, int expense) {
        return (float) (relativeValue+relativePenalty)/(expense+relativeRisk);
    }

    @Override
    public float getPrio() {
        return this.calculatePrio(this.getRelativeValue(),this.getRelativePenalty(),this.getRelativeRisk(),this.getExpense());
    }

    @Override
    public String getAcceptanceCriteria() {
        return this.acceptanceCriteria;
    }

    @Override
    public String getProject() {
        return this.project;
    }

    @Override
    public String toString(){
        return String.format("%3s %20s %20s %5s %20s",this.getID(),this.getTitle(),this.getAcceptanceCriteria(),(double)Math.round(this.getPrio()*100)/100,this.getProject());
        //return this.getID()+"\t"+this.getTitle()+"\t"+this.getAcceptanceCriteria()+"\t\t\t"+this.getPrio()+"\t"+this.getProject();
        /*
        return  "User Story (ID="+this.getID()+")" +"\n"+
                "Titel:\t"+this.getTitle() +"\n"+
                "Akzeptanzkriterium:\t"+this.getAcceptanceCriteria() +"\n"+
                "Projekt:\t"+this.getProject() +"\n"+
                "Priorität:\t" +this.getPrio();
        */

    }

    @Override
    public int compareTo(UserStory o) {
        if(this.getPrio()>o.getPrio()){
            return -1;
        }else if(this.getPrio()<o.getPrio()){
            return 1;
        }
        return 0;
    }
}
