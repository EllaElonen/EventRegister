//Anna Efimova anef3448
//Ella Elonen elel2233
import java.util.ArrayList;

public class Participant implements Comparable<Participant> {

    private Team team;
    private int number;
    private String firstName;
    private String lastName;
    private ArrayList<Result> results;

    public Participant(String firstName, String lastName, Team team, int number){        
        this.number = number;
        this.firstName = firstName;
        this.lastName = lastName;
        this.team = team;
        results = new ArrayList<>();
    }
      
    public int getNumber(){
        return number;
    }
    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    
    public String toString(){
        return firstName + " " + lastName + " " + "from" + " " + team.getTeamName() + " " + "with" + " " + "number" + " " + number;
    }
    public void addResult(Result result){
        results.add(result);
    }
    
    public void printResults(){
        for (Result r: results)
        {
            System.out.print(lastName+", "+firstName+" ");
            r.printResult();
            System.out.println();
        }
    }
    
    public Team getTeam()
    {
        return team;
    }

    @Override
    public int compareTo(Participant p) {
        return p.getNumber() - number;
    }
    
    public void clearData(){
        results.clear();
    }
}
