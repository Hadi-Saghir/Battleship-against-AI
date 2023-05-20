/**
 * creator: Hadi Saghir
 * user-id: aj4810
 * enrollment: Systemutveckling
 */
package model;


public class LeaderBoard {
    private Topscore[] topscores;
    private int leaderBoardSize = 10;

    public LeaderBoard(){
        this.topscores = new Topscore[leaderBoardSize];
        populateLeaderBoard();
    }
    //populator
    private void populateLeaderBoard() {
        topscores[0] = new Topscore("Hadi Saghir", 10);
        topscores[0].setDate("2000-01-01");
        topscores[1] = new Topscore("Linda Rempis", 15);
        topscores[1].setDate("2000-01-01");
        topscores[2] = new Topscore("Hani Saghir", 30);
        topscores[2].setDate("2000-01-01");
        topscores[3] = new Topscore("Rasha Sayem Eldaher", 50);
        topscores[3].setDate("2000-01-01");
        topscores[4] = new Topscore("Zaina", 100);
        topscores[4].setDate("2000-01-01");
    }
    /**
     *  after each games, the shots recorded are passed on in
     *  @param shots and compared to each top-scores in descending order (0 being highest).
     *  if shots of current position holder surpass said shots,
     *      the position of the holder of said shots is pass back to view.
     */
    public int checkIfTopscore(int shots) {
        boolean madeIt = false;
        int position;
        for(position = 0; position < topscores.length; position++){
            if(shots < topscores[position].getShots()){
                madeIt =true;
                break;
            }
        }
        if(!madeIt){
            position = 5;
        }
        return position;
    }
    /**
     *  if above method returns positon < 5, this method it called with
     * @param name and @param shots passed on from view to be used as params in initializing a new top-score object
     * new top-score place in proper position after all elements shifts after it shift on position to the right
     */
    public void addTopscore(String name,int shots, int position) {
        Topscore newTopscore = new Topscore(name,shots);
        for (int i = topscores.length -2; i >= position; i--) {
            topscores[i+1] = topscores[i];
        }
        topscores[position] = newTopscore;
    }
    //getter of the list of top-scores
    public Topscore[] getTopscores() {
        return topscores;
    }

    public int getLeaderBoardLength() {
        return leaderBoardSize;
    }
}
