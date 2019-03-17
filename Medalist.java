//Anna Efimova anef3448
//Ella Elonen elel2233
public class Medalist {
    private Participant participant;
    private double score;
    private int medal; // 1 = gold, 2 = silver, 3 = bronze  

    public Medalist(Participant p, double score, int medal)
    {
        this.participant = p;
        this.score = score;
        this.medal = medal;
    }
    
    public Participant getParticipant() {
        return participant;
    }
    
    public double getScore() {
        return score;
    }

    public int getMedal() {
        return medal;
    }

    public void printMedalist() {
        System.out.println(this.getMedal() + " " + this.getScore() + " "
                + this.getParticipant().getFirstName() + " "
                + this.getParticipant().getLastName());
    }
}
