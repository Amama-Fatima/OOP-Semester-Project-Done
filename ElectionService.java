import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ElectionService {
    private FileService votersCollectionFileService, candidatesCollectionFileService;

    public ElectionService(FileService votersCollectionFileService, FileService candidatesColletionFileService) {
        this.votersCollectionFileService = votersCollectionFileService;
        this.candidatesCollectionFileService = candidatesColletionFileService;
    }

    public void registerCandidate(Candidate candidate) {
        try {
            candidatesCollectionFileService.writeObjectToFile(candidate);
            System.out.println("Your Candidate was registered successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean registerVoter(Voter voter) {
        Candidate candidate = getCandidateByName(voter.getVotedCandidate());
        if (candidate != null && matchRegion(voter, candidate)) {
            incrementCandidateVotes(voter.getVotedCandidate());
    
            try {
                votersCollectionFileService.writeObjectToFile(voter);
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            System.out.println("Voter's region does not match the candidate's region. Vote not registered.");
            return false;
        }
    }
    
    

    public ArrayList<String> getAllCandidateNames() {
        ArrayList<String> candidateNames = new ArrayList<>();
            try {
                ArrayList<User> candidates = candidatesCollectionFileService.readUsersFromFile();
                
                for (User user : candidates) {
                    if (user instanceof Candidate) {
                        Candidate candidate = (Candidate) user;
                        String name = candidate.getFirstName() + " " + candidate.getLastName();
                        candidateNames.add(name);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return candidateNames;
        }

        private void incrementCandidateVotes(String candidateName) {
            try {
                ArrayList<User> candidates = candidatesCollectionFileService.readUsersFromFile();
        
                for (User user : candidates) {
                    if (user instanceof Candidate) {
                        Candidate candidate = (Candidate) user;
                        String currentName = candidate.getFirstName() + " " + candidate.getLastName();
        
                        if (currentName.equals(candidateName)) {
                            int votes = candidate.getVotes();
                            votes++;
                            candidate.setVotes(votes);
        
                            // Update the user in the file
                            candidatesCollectionFileService.updateUser(candidate, candidate);
                            break;
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        

        public ArrayList<String> calculateElectionWinner() {
            int highestVotes = 0;
            ArrayList<String> winners = new ArrayList<>();
            try {
                List<User> candidates = candidatesCollectionFileService.readUsersFromFile();
                
                // First loop: Find the highest number of votes
                for (User user : candidates) {
                    if (user instanceof Candidate) {
                        Candidate candidate = (Candidate) user;
                        int votes = candidate.getVotes();
                        if (votes > highestVotes) {
                            highestVotes = votes;
                        }
                    }
                }
                
                // Second loop: Find all candidates with the highest number of votes
                for (User user : candidates) {
                    if (user instanceof Candidate) {
                        Candidate candidate = (Candidate) user;
                        int votes = candidate.getVotes();
                        if (votes == highestVotes) {
                            String name = candidate.getFirstName() + " " + candidate.getLastName();
                            winners.add(name);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return winners;
        }
        

        public Candidate getCandidateByName(String candidateName) {
            try {
                ArrayList<User> candidates = candidatesCollectionFileService.readUsersFromFile();
        
                for (User user : candidates) {
                    if (user instanceof Candidate) {
                        Candidate candidate = (Candidate) user;
                        String currentName = candidate.getFirstName() + " " + candidate.getLastName();
        
                        if (currentName.equals(candidateName)) {
                            return candidate;
                        }
                    } else {
                        System.out.println("User is not a candidate");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
        

    public boolean matchRegion(User user1, User user2) {
        return user1.getRegion().equals(user2.getRegion());
    }
    
}