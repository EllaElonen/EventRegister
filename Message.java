//Anna Efimova anef3448
//Ella Elonen elel2233
public class Message{    
   
    private int n = 0;
    private int k = 56;
    public void printMessage(String text){
    	text=text.substring(8,text.length()).toUpperCase();
    	
    System.out.println("############################################################");
    System.out.println("#                                                          #");

    if (text.length() > k) {
        System.out.print("# "+text.substring(0, 56)+" #");
    } else {
        n = k - text.length();
        String m=new String(new char[n]).replace("\0"," ");
        System.out.print("# "+ text +m+ " #");
    }       
    System.out.println("");
    System.out.println("#                                                          #");
    System.out.println("############################################################");


}
        
}
