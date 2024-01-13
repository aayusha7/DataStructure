import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class SoccerStats {
    /**
     * Holds String as key and TeamStatus(object) as value with each key
     * representing the team name and TeamStatus representing its wins, loose,
     * draws, points, goalsFor, goalsAgainst
     */
    HashMap<String, TeamStatus> teams = new HashMap<>();

    /**
     * Holds TeamStatus object to calculate the beat(high points) and high scoring.
     */
    TreeSet<TeamStatus> teamsCopy= new TreeSet<>();

    /**
     * Initializes the object.
     * Reads from file that contains team names with respective goals for and goals
     * against and populates the HashMaps with teamStatus.
     * @param filename The name of the file.
     */

    public SoccerStats(String filename){
        ArrayList<String> lines = readFiles(filename);
        for(String line:lines){
            String[] gameData = line.split(",");
            TeamStatus homeTeam = getTeam(gameData[0]);
            TeamStatus awayTeam = getTeam(gameData[1]);

            int homeGoal= Integer.parseInt(gameData[2]);
            int awayGoal = Integer.parseInt(gameData[3]);

            homeTeam.updateStats(homeGoal,awayGoal);
            awayTeam.updateStats(awayGoal,homeGoal);
        }
    }

    /**
     * Reads from file that contains instructions and stores in ArrayLists
     * Decodes the particular instruction and performs the switch case based on the given instruction
     * @param statsFile The name of the instruction file
     * @return Formatted output stored as results.
     */
    public String getStats(String statsFile){
        ArrayList<String> lines = readFiles(statsFile);
        String results="";
        for(String stats:lines){
            String[] instruction = stats.split(" ");
            switch(instruction[0]) {
                case "STATS":
                    String teamName = extractTeamName(instruction);
                    results += teamStats(teamName);
                    break;
                case "BEST":
                    results += best();
                    break;
                case "HSCORING":
                    results += highScoring();
                    break;
            }
        }
        return results;
    }

    /**
     * Helper method to search the names of any teams that have scored more goals than the BEST team
     * it fills the TreeSet if it is empty and continues the  goals for comparison between the best team
     * and each of the team
     * @return The string formatted output containing the names of the teams scoring more goals than best team
     */

    private String highScoring() {
        String result="";
        if (teamsCopy.size()==0){
            best();
        }
        int bestTeamGoals= teamsCopy.last().getGoalsFor();
        for(TeamStatus team:teamsCopy){
            if (team.getGoalsFor()>bestTeamGoals){
                result+=team.getTeamName()+", ";
            }
        }
        if (result.length()==0){
            return "HIGH SCORERS: NONE\n";
        }
        return "HIGH SCORERS: "+result.substring(0,result.length()-2)+"\n";
    }

    /**
     * Helper method to search the team with the largest number of points.
     * If two or more teams are tied with the largest number of points, the tiebreakers will be:
     * -the largest goal differential (the difference between goal scored and goals allowed)
     * -most goals scored.
     * @return The string formatted output containing the name of the team as per the above condition.
     */
    private String best() {
        Set<String> teamsKey=teams.keySet();
        for(String team:teamsKey){
            teamsCopy.add(teams.get(team));

        }
        return getFormattedTeamString(teamsCopy.last(),"BEST: ");
    }

    /**\
     * Helper method to check if the teams contains the given team name.
     * @param teamName The name of the team to be searched in the HashMap.
     * @return Formatted output containing the team name and its respective status as per the data entry
     */
    private String teamStats(String teamName) {
        TeamStatus teamStatus=null;
        if (teams.containsKey(teamName)){
            teamStatus=teams.get(teamName);

        } else{
            return "TEAM: "+teamName+" NOT FOUND\n";
        }
        return getFormattedTeamString(teamStatus,"TEAM: ");
    }

    /**
     * Helper method to format the output in specific format
     * @param teamStatus The object containing the status of the given team name.
     * @param statusType The type of status.
     * @return A formatted string representing the team status in the specified format.
     */

    private String getFormattedTeamString(TeamStatus teamStatus, String statusType) {
        String teamFormat= statusType+"%-19s W: %-3s D: %-3s L: %-3s GF: %-3s GA: %-3s PTS: %-3s\n";
        String teamName=teamStatus.getTeamName();
        int wins = teamStatus.getWins();
        int draws = teamStatus.getDraws();
        int losses = teamStatus.getLosses();
        int goalsFor = teamStatus.getGoalsFor();
        int goalsAgainst = teamStatus.getGoalsAgainst();
        int points = teamStatus.getPoints();

        // Using the format string to create the final formatted string
        String formattedTeamString= String.format(teamFormat,teamName,wins,draws,losses,goalsFor,goalsAgainst,points);
        return formattedTeamString;
    }
    /**
     * Extract the street name from a line
     * @return The street name.
     */

    private String extractTeamName(String[] instruction) {
        String teamName="";
        for(int i=1;i< instruction.length;i++){
            teamName+=instruction[i]+" ";
        }
        return teamName.substring(0,teamName.length()-1);
    }

    /**
     * Helper method to populate the HashMap with teamStatus.
     * @param teamName The name of the file
     * @return Team status.
     */
    private TeamStatus getTeam(String teamName) {
        if(!(teams.containsKey(teamName))){
            TeamStatus teamStatus = new TeamStatus((teamName));
            teams.put(teamName,teamStatus);
            return teamStatus;
        }
        return teams.get(teamName);
    }

    /**
     * Helper method to read the file and stores the lines in ArrayList.
     * @param filename The name of the given file to read.
     * @return The ArrayList containing the stores lines from given filename.
     */
    private ArrayList<String> readFiles(String filename) {
        ArrayList<String> lines= new ArrayList<>();
        try{
            File myFile = new File(filename);
            Scanner scan = new Scanner(myFile);
            while(scan.hasNextLine()){
                lines.add(scan.nextLine());
            }
        } catch (FileNotFoundException ex)
        {
            // System.out.println("File "+filename+" was not found");
        }
        return lines;
    }
}// end class
