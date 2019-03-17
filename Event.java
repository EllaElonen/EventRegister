//Anna Efimova anef3448
//Ella Elonen elel2233
import java.util.ArrayList;
import java.util.Collections;
public class Event {

    private ArrayList<Result> results = new ArrayList<Result>();
    private String eventName;
    private int noOfAttempts;

    //private ArrayList<Result> medalResults = new ArrayList<>();
    
    public Event(String name, int noOfAttempts)
    {
        this.eventName = name;
        this.noOfAttempts = noOfAttempts;
    }
    
    public String getEventName(){
        return eventName;
    }
    
    public String toString(){
      return getEventName() + " added";
   }
    public int getNoOfAttempts(){
        return noOfAttempts;
    }
    
    public ArrayList<Medalist> getMedalists()
    {
        Collections.sort(results);
        int counter = 1;
        int resultPlace = 1;
        double oldScore = -1;
        ArrayList<Medalist> medalists = new ArrayList<>();
        ArrayList<Participant> participantsInResultList = 
                new ArrayList<Participant>();
        for (Result r: results) {
            if (!participantsInResultList.contains(r.getParticipant())) {
                if (r.getScore() != oldScore) {
                    resultPlace = counter;
                }
                medalists.add(new Medalist(r.getParticipant(), r.getScore(), resultPlace));
                oldScore = r.getScore();
                counter++;
                participantsInResultList.add(r.getParticipant());
            }
        }        
        return medalists;
    }
    
    public void removeResultFor(Participant p) {
        for(int i = results.size()-1; i >=0;i--){
           if( results.get(i).getParticipant().getNumber() == p.getNumber())
               results.remove(i);
        }
    }

	public void add(Result r) {
		results.add(r);
	}

	public int getNoOfAttemptsForParticipant(Participant p) {
		
		int currentNumbOfAttempts=0;
		
		for (int i = 0; i < results.size(); i++) {
			if (p == results.get(i).getParticipant()) {
				currentNumbOfAttempts++;
			}
		}
		
		return currentNumbOfAttempts;
	}
   
}
