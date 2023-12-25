import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterCandidatesForm extends JFrame implements Form{
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField regionField;
    private JTextField partyField;
    private ElectionService electionService;
    

    public RegisterCandidatesForm(ElectionService electionService) {
        this.electionService = electionService;

        setTitle("Registration Form For Candidates");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        setSize(400, 300);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1, 10, 10)); // One column for each row

        // Row 1: First Name
        JPanel row1Panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameField = new JTextField(20); // Adjust the size as needed
        row1Panel.add(firstNameLabel);
        row1Panel.add(firstNameField);
        panel.add(row1Panel);

        // Row 2: Last Name
        JPanel row2Panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameField = new JTextField(20);
        row2Panel.add(lastNameLabel);
        row2Panel.add(lastNameField);
        panel.add(row2Panel);

        // Row 3: Region
        JPanel row3Panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel regionLabel = new JLabel("Region:");
        regionField = new JTextField(20);
        row3Panel.add(regionLabel);
        row3Panel.add(regionField);
        panel.add(row3Panel);

        // Row 4: Political Party
        JPanel row4Panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel partyLabel = new JLabel("Political Party:");
        partyField = new JTextField(20);
        row4Panel.add(partyLabel);
        row4Panel.add(partyField);
        panel.add(row4Panel);

        // Row 5: Submit Button
        JPanel row5Panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton submitButton = new JButton("Register");
        row5Panel.add(submitButton);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Comment out the backend logic for now
                Register();
                
            }
        });
        panel.add(row5Panel);

        add(panel);
    }

    // Commented out the backend logic
    
    public void Register() {
        try {
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String region = regionField.getText();
            String party = partyField.getText();
            party = partyField.getText();
            Candidate c = new Candidate(firstName, lastName, region, party);
            electionService.registerCandidate(c);
    
            showAlert("Success!", "Candidate registered successfully with id: " + c.getId());
            firstNameField.setText("");
            lastNameField.setText("");
            regionField.setText("");
            partyField.setText("");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "An error occurred while registering the candidate.");
        }
    }
    
    
    public void showAlert(String title, String message) {
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.INFORMATION_MESSAGE);
    }
}


