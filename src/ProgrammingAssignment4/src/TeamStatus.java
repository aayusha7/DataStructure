/**
 * Helper class to represent the status of a team, including statistics such as wins, draws, losses,
 * goals for, goals against, and points in a competition.
 * Implements Comparable to allow for sorting based on points, goal difference, and goals for.
 */

public class TeamStatus implements Comparable<TeamStatus>
{
    /**
     * The name of the team.
     */
    private String teamName;
    /**
     * The count of wins for the team.
     */
    private int wins;
    /**
     * The count of draws for the team.
     */
    private int draws;
    /**
     * The count of looses for the team.
     */
    private int losses;
    /**
     * The count of goals scored by the team.
     */
    private int goalsFor;
    /**
     * The count of goals scored against the team.
     */
    private int goalsAgainst;
    /**
     * The total points for the team.
     */
    private int points;

    /**
     * Initializes the object.
     * @param teamName The name of the team.
     */
    public TeamStatus(String teamName) {
        this.teamName = teamName;
    }

    /**
     * Getters for team name
     * @return The team name
     */
    public String getTeamName() {
        return teamName;
    }

    /**
     * Getters for team wins
     * @return The team wins
     */
    public int getWins() {
        return wins;
    }

    /**
     * Getters for team draws
     * @return The count of team draws
     */
    public int getDraws() {
        return draws;
    }

    /**
     * Getters for team looses
     * @return The count of looses
     */
    public int getLosses() {
        return losses;
    }

    /**
     * Getters for team goals scored
     * @return the team goal scored
     */
    public int getGoalsFor() {
        return goalsFor;
    }

    /**
     * Getters for team goal against
     * @return the team goal against
     */
    public int getGoalsAgainst() {
        return goalsAgainst;
    }
    /**
     * Getters for team points
     * @return the team points
     */

    public int getPoints() {
        return points;
    }
    /**
     * Updates the team statistics based on the given goals for and goals against.
     * Adjust wins, losses, draws, and points accordingly.
     * each wins increases points by 3, wins increases by 1.
     * each looses increases loose by 1, each
     * @param goalsFor The number of goals scored by the team.
     * @param goalsAgainst The number of goals conceded by the team.
     */
    public void updateStats(int goalsFor, int goalsAgainst) {
        this.goalsFor+=goalsFor;
        this.goalsAgainst+=goalsAgainst;
        if (goalsFor>goalsAgainst){
            this.wins+=1;
            this.points+=3;
        } else if(goalsAgainst>goalsFor){
            this.losses+=1;

        } else{
            this.draws+=1;
            this.points+=1;
        }
    }

    /**
     * Helper method to calculate the goal difference between the current team and another team.
     * @param newTeam The TeamStatus of the other team.
     * @return The goal difference.
     */
    private int goalDifference(TeamStatus newTeam) {

        int result = (this.goalsFor - this.goalsAgainst) - (newTeam.goalsFor - newTeam.goalsAgainst);
        return result;
    }

    /**
     * Compares two teams based on their points, goal difference, and goals for.
     * If two or more teams are tied with the largest number of points, the tiebreakers are:
     *      largest goal differential (the difference between goal scored and goals allowed)
     *      most goals scored.
     * @param newTeam The TeamStatus of the team to compare to.
     * @return A positive integer if this team is greater, a negative integer if lesser.
     */
    public int compareTo(TeamStatus newTeam){
        if (this.points>newTeam.getPoints()){
            return 1;
        }  if ((goalDifference(newTeam))>0){
            return 1;
        } if (this.goalsFor > newTeam.getGoalsFor()) {
            return 1;
        }
        return -1;
    }
}// end class