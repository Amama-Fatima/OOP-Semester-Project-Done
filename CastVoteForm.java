import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CastVoteForm extends JFrame {
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField regionField;
    private JTextField votedCandidateField;
    private ElectionService electionService;

    public CastVoteForm(ElectionService electionService) {
        this.electionService = electionService;
        setTitle("Voting Form - Cast Vote");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 2, 10, 10));

        // Add Header
        JLabel headerLabel = new JLabel("Voting Form");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(headerLabel);

        // Add Registered Candidates Label
        JLabel candNamesLabel = new JLabel("Registered Candidates");
        candNamesLabel.setFont(new Font("Arial", Font.BOLD, 12));
        panel.add(candNamesLabel);

        // Add First Name Label and Text Field
        addRow("First Name:", panel);

        // Add Last Name Label and Text Field
        addRow("Last Name:", panel);

        // Add Region Label and Text Field
        addRow("Region:", panel);

        // Add Chosen Candidate Label and Text Field
        addRow("Chosen Candidate:", panel);

        // Add Cast Vote Button
        JButton castVoteButton = new JButton("Cast Vote");
        panel.add(castVoteButton);
        castVoteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerVoterAndCastVote(panel);
                System.out.println("Voter Registered and vote casted");
            }
        });

        add(panel);
        setVisible(true);
    }

    private void addRow(String label, JPanel panel) {
        JLabel rowLabel = new JLabel(label);
        panel.add(rowLabel);

        JTextField rowTextField = new JTextField(20); // Adjust the size as needed
        panel.add(rowTextField);
        switch (label) {
            case "First Name:":
                firstNameField = rowTextField;
                break;
            case "Last Name:":
                lastNameField = rowTextField;
                break;
            case "Region:":
                regionField = rowTextField;
                break;
            case "Chosen Candidate:":
                votedCandidateField = rowTextField;
                break;
        }
    }

    private void registerVoterAndCastVote(JPanel panel) {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String region = regionField.getText();
        String votedCandidate = votedCandidateField.getText();

        if (electionService.registerVoter(new Voter(firstName, lastName, region, votedCandidate))) {
            showAlert("Success!", "Voter registered and vote casted successfully");
        } else {
            showAlert("Registration Failed", "Voter not registered. Region of candidate and voter do not match.");
        }
    }

    private void showAlert(String title, String message) {
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.INFORMATION_MESSAGE);
    }
}


