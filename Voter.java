public class Voter extends User {
    private String votedCandidate;

    public Voter(String firstName, String lastName, String region, String votedCandidate) {
        super(firstName, lastName, region);
        this.votedCandidate = votedCandidate;
    }

    public String getVotedCandidate() {
        return votedCandidate;
    }

    public void setVotedCandidate(String votedCandidate) {
        this.votedCandidate = votedCandidate;
    }
}
