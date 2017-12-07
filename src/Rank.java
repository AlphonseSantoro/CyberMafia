import java.util.HashMap;

public class Rank {
    private int rankNr;
    private String rank;
    private HashMap ranks;
    public Rank(){
        ranks = new HashMap();
    }

    public void rankUp(int currentRank){

    }

    public void createNewRank(int rankNr, String rank){
        ranks.put(rankNr, rank);
    }

    public void deleteRank(int rankNr){
        ranks.remove(rankNr);
    }

    public void setRankNr(int rankNr){
        this.rankNr = rankNr;
    }

    public int getRankNr() {
        return rankNr;
    }

    public void setRankName(String rank){
        this.rank = rank;
    }

    public String getRank(){
        return rank;
    }
}
