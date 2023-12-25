import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.List;

public class CastVoteForm extends JFrame {
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField regionField;
    private ElectionService electionService;
    private JButton castVoteButton;
    private ButtonGroup candidateButtonGroup;

    public CastVoteForm(ElectionService electionService) {
        this.electionService = electionService;
        setTitle("Voting Form - Cast Vote");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);

        // Main Panel with GridLayout and Padding
        JPanel mainPanel = new JPanel(new GridLayout(8, 2, 5, 5));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Add padding

        // Row 1: First Name
        mainPanel.add(new JLabel("First Name:"));
        firstNameField = new JTextField();
        mainPanel.add(firstNameField);

        // Row 2: Last Name
        mainPanel.add(new JLabel("Last Name:"));
        lastNameField = new JTextField();
        mainPanel.add(lastNameField);

        // Row 3: Region
        mainPanel.add(new JLabel("Region:"));
        regionField = new JTextField();
        mainPanel.add(regionField);

        // Row 4: Candidates
        candidateButtonGroup = new ButtonGroup();
        addCandidatesRadioButtons(mainPanel);

        // Row 5: Add Cast Vote Button
        castVoteButton = new JButton("Cast Vote");
        castVoteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerVoterAndCastVote();
                System.out.println("Voter Registered and vote casted");
            }
        });
        mainPanel.add(castVoteButton);

        // Add the main panel to the frame
        add(mainPanel);
    }

    private void addCandidatesRadioButtons(JPanel mainPanel) {
        List<String> candidateNames = electionService.getAllCandidateNames();
        for (String name : candidateNames) {
            JRadioButton candidateRadioButton = new JRadioButton(name);
            candidateButtonGroup.add(candidateRadioButton);
            mainPanel.add(candidateRadioButton);
        }
    }

    private void registerVoterAndCastVote() {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String region = regionField.getText();

        // Check which candidate is selected
        String votedCandidate = getSelectedCandidate();
        System.out.println("Voted Candidate: " + votedCandidate);

        if (votedCandidate != null && electionService.registerVoter(new Voter(firstName, lastName, region, votedCandidate))) {
            showAlert("Success!", "Voter registered and vote casted successfully");
        } else {
            showAlert("Registration Failed", "Voter not registered or no candidate selected.");
        }
    }

    private String getSelectedCandidate() {
        for (Enumeration<AbstractButton> buttons = candidateButtonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                return button.getText();
            }
        }
        return null;
    }

    private void showAlert(String title, String message) {
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.INFORMATION_MESSAGE);
    }
}




