//Anna Efimova anef3448
//Ella Elonen elel2233
public class Team {
    private String teamName;
    private int[] goldSilverBronze;
      
    public Team(String teamName) {
        this.teamName = teamName;
    }
    
    public int[] getGoldSilverBronze()
    {
        return goldSilverBronze;
    }
    
    public void setGoldSilverBronze(int[] gsb)
    {
        this.goldSilverBronze = gsb;
    }
    
    public String getTeamName(){
        return teamName;
    }
    
    @Override
    public String toString(){
        return getTeamName();
    }
    
    
}
