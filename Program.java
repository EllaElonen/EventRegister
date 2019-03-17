//Anna Efimova anef3448
//Ella Elonen elel2233
import java.util.ArrayList;
import java.util.Scanner;
public class Program {

	private ArrayList<Participant> participants = new ArrayList<Participant>();
	private ArrayList<Event> events = new ArrayList<Event>();
	private Scanner keyboard = new Scanner(System.in);
	private int participantNumber;

	public static void main(String[] args) {
		Program program = new Program();
		
		program.runCommandLoop();
		
	}

	public Program() {
		// lowest allowed number
		participantNumber = 100;
	}



	private void runCommandLoop() {
		boolean running = true;
		while (running) {

			System.out.print("command ");
//			String cmd = keyboard.nextLine().toLowerCase();
            String cmd = keyboard.nextLine().toLowerCase().trim();
			switch (cmd) {
			case "add participant":
				addParticipant();
				break;
			case "add event":
				addEvent();
				break;
			case "add result":
				addResult();
				break;
			case "participant":
				printParticipantResults();
				break;
			case "teams":
				printTeamResults();
				break;
			case "remove participant":
				removeParticipant();
				break;
			case "exit":
				
				running = false;
				break;

			default:
				if (commandIsMessage(cmd)) {
					Message m = new Message();
					m.printMessage(cmd);
				} else if (eventExists(cmd)) {
					printEventResults(cmd);
				} else {
					System.out.println("Wrong command");

				}

			}
		}

		keyboard.close();
	}

	private ArrayList<Team> getTeams() {
		ArrayList<Team> teams = new ArrayList<>();
		for (Participant p : participants) {
			Team t = null;
			for (Team ts : teams) {
				if (ts.getTeamName().equals(p.getTeam().getTeamName())) {
					t = ts;
					break;
				}
			}
			if (t == null) {
				teams.add(p.getTeam());
			}
		}
		return teams;
	}

	private boolean commandIsMessage(String cmd) {
		CharSequence cs1 = "message";
		return cmd.contains(cs1);
	}

	private boolean eventExists(String event) {

		for (Event e : events) {

			if (e.getEventName().equalsIgnoreCase(event)) {
				return true;
			}
		}
		return false;
	}

	private void addParticipant() {
		String firstName = normalize(readString("first name: "));
		String lastName = normalize(readString("last name: "));
		String teamName = normalize(readString("team name: "));

		Team t = null;
		for (Team ts : getTeams()) {
			if (ts.getTeamName().equals(teamName)) {
				t = ts;
				break;
			}
		}
		if (t == null) {
			t = new Team(teamName);
		}

		Participant newParticipant = new Participant(firstName, lastName, t, participantNumber++);
		System.out.println(newParticipant);
		participants.add(newParticipant);
	}

	private String readString(String str) {

		String text = null;

		do {
			System.out.print(str);

			text = keyboard.nextLine();
			text = text.trim();
			if (text == null || text.isEmpty()) {
				System.err.println("Names can´t be empty");
			}

		} while (text == null || text.isEmpty());

		return text;
	}

	private void addEvent() {
		String newEventName = readString("Event name: ");
		newEventName = normalize(newEventName);
		for (Event thisEvent : events) {
			if (thisEvent.getEventName().equals(newEventName))
				System.out.println(newEventName + "has already been added");
                        
		}

		System.out.println("Attempts allowed");
		try {
			int noOfAttempts = -1;
                        
                        do {
                            noOfAttempts = keyboard.nextInt();
                            keyboard.nextLine();
                            if (noOfAttempts <= 0) {
				System.out.println("Too low, must allow at least one attempt");
                            }
                        } while (noOfAttempts <= 0);

			Event newEvent = new Event(newEventName, noOfAttempts);
			System.out.println(newEvent);
			events.add(newEvent);
		} catch(Exception e) {
			System.out.println("FEL");
		}
		
		
	}

	private void addResult() {
		System.out.println("number");
		int number = keyboard.nextInt();
		keyboard.nextLine();
		Participant p = null;

		for (Participant pp : participants) {
			if (pp.getNumber() == number) {
				p = pp;
				break;
			}
		}
		if (p == null) {
			System.out.println("No participant with number " + number + " found!");
			return;
		}

		String event = normalize(readString("event: "));
		Event e = null;
		for (Event ee : events) {
			if (ee.getEventName().equals(event)) {
				e = ee;
				break;
			}
		}
		if (e == null) {
			System.out.println("No event with name " + event + " found!");
			return;
		}
		
		
		int numbOfAttempts = e.getNoOfAttempts();
		int currentNumbOfAttempts = e.getNoOfAttemptsForParticipant(p);
		
		if (currentNumbOfAttempts < numbOfAttempts) {
			System.out.println("Register result for " + p.getFirstName() + " " + p.getLastName() + " " + "from "
					+ p.getTeam().getTeamName() + " in " + event + ": ");
			double result = keyboard.nextDouble();
			keyboard.nextLine();
			Result r1 = new Result(result, p);

			e.add(r1);
		}
	}

	private void removeParticipant() {
		System.out.print("Number:");
		int number = keyboard.nextInt();
		keyboard.nextLine();
		for (Participant p : participants) {
			if (p.getNumber() == number) {
				participants.remove(p);
				for (Event e : events) {
					e.removeResultFor(p);
				}
				return;
			}
		}
		System.out.println("Participant with this" + " " + number + " " + "doesn´t exist");
	}

	private void printParticipantResults() {

		System.out.print("Number:");
		int number = keyboard.nextInt();
		keyboard.nextLine();
		Participant person = null;
		for (Participant p : participants) {
			if (p.getNumber() == number) {
				person = p;
			}
		}

		if (person == null) {
			System.out.println("Person with this number doesn´t exist.");
		} else {
			person.printResults();
//			for (Event e : events) {
//				ArrayList<Result> results = e.getResults();
//				boolean first = true;
//				for (Result r : results) {
//					if (r.getParticipant().getNumber() == number) {
//						if (first) {
//							System.out.print(" Results for " + person.getFirstName() + " " + person.getLastName()
//									+ " in " + e.getEventName() + ": " + r.getScore());
//							first = false;
//						} else {
//							System.out.print(", " + r.getScore());
//							break;
//						}
//					}
//				}
//				if (!first) {
//					System.out.println("");
//				}
//			}
		}
	}

	/*
	 * Calculates gold/silver/bronze per team
	 */

	private void calculateGoldSilverBronze() {
		for (Team t : getTeams()) {
			int[] goldSilverBronze = new int[3];
			for (Event e : events) {
				for (Medalist m : e.getMedalists()) {
					if (m.getParticipant().getTeam() == t && m.getMedal() <= 3) {
						goldSilverBronze[m.getMedal() - 1]++;
					}
				}
			}
			t.setGoldSilverBronze(goldSilverBronze);
		}
	}

	/*
	 * Manual sorting, making the team with highest result appear first
	 */

	private void printTeamResults() {
		calculateGoldSilverBronze();
		System.out.println("1st    2nd    3rd      Team Name");
		System.out.println("********************************");

		ArrayList<Team> teamResults = new ArrayList<>(getTeams());

		// Repeat until no more teams
		while (teamResults.size() > 0) {
			Team highestTeamSoFar = teamResults.get(0);
			for (Team t : teamResults) {
				if (t.getGoldSilverBronze()[0] > highestTeamSoFar.getGoldSilverBronze()[0]) {
					highestTeamSoFar = t;
				} else if (t.getGoldSilverBronze()[0] == highestTeamSoFar.getGoldSilverBronze()[0]) {
					if (t.getGoldSilverBronze()[1] > highestTeamSoFar.getGoldSilverBronze()[1]) {
						highestTeamSoFar = t;
					} else if (t.getGoldSilverBronze()[1] == highestTeamSoFar.getGoldSilverBronze()[1]) 
					 if	(t.getGoldSilverBronze()[2] > highestTeamSoFar.getGoldSilverBronze()[2]) 
						highestTeamSoFar = t;
					} else if (t.getGoldSilverBronze()[2] == highestTeamSoFar.getGoldSilverBronze()[2]) {
				}
			}
			System.out.println(highestTeamSoFar.getGoldSilverBronze()[0] + "      "
					+ highestTeamSoFar.getGoldSilverBronze()[1] + "      " + highestTeamSoFar.getGoldSilverBronze()[2]
					+ "      " + highestTeamSoFar.getTeamName());
			teamResults.remove(highestTeamSoFar);
		}
	}
	
	private void printEventResults(String event) {
		// String event = normalize(readString("event: "));
		
		for (Event e : events) {
			if (e.getEventName().equalsIgnoreCase(event)) {
				ArrayList<Medalist> medalists = e.getMedalists();
				System.out.println("Result for " + event + ": ");
				for (Medalist m : medalists) {
					m.printMedalist();
				}
				return;
			}
		}
		System.out.println("Event " + event + " not found");
	}

	private String normalize(String str) {

		str = str.toLowerCase();
		String firstLetter = str.substring(0, 1).toUpperCase();
		String restLetters = str.substring(1).toLowerCase();
		return firstLetter + restLetters;
	}
}
