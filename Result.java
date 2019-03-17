//Anna Efimova anef3448
//Ella Elonen elel2233
public class Result implements Comparable<Result>{
    
    private Participant participant;
    private Event event;
    private double score;

    public Result(double score, Participant participant)
    {
        this.score = score;
        this.participant = participant;
    }
    
    public double getScore(){
            return score;
    }
    
    public Participant getParticipant(){
        return participant;
    }
    
    public void printResult()
    {
        System.out.print(score + " " + participant.getFirstName() 
                + " " + participant.getLastName());
    }

    @Override
    public int compareTo(Result o) {
        return Double.compare(o.getScore(), score);
    }
}

