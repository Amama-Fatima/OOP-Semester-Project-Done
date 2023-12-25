
import javax.swing.*;


import java.awt.*;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

// import com.electiondatabase.ElectionService;
// import com.electiondatabase.FileService;

public class MainMenu extends JFrame {
    private ElectionService electionService;

    public MainMenu() {
        initServices();
        setTitle("Election Database System - Main Menu - Group 1");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);

        // Create the main menu panel
        JPanel gridPane = new JPanel();
        gridPane.setLayout(new BoxLayout(gridPane, BoxLayout.Y_AXIS));
        gridPane.setAlignmentX(Component.CENTER_ALIGNMENT); 

        
        // Add the panel to the frame's content pane and wrap it in a flow layout to center it
        setLayout(new FlowLayout(FlowLayout.CENTER));
        add(gridPane);
        // Add UI to the main menu panel
        addUI(gridPane);
        pack(); // Auto-size the window based on the components
    }

    protected void addUI(JPanel gridPane) {
        // Add Header
        JLabel headerLabel = new JLabel("Election Database", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gridPane.add(headerLabel);
    
        // Create buttons
        JButton registerCandidatesButton = new JButton("Register Candidates");
        JButton castVotersButton = new JButton("Cast Vote");
        JButton viewResultsButton = new JButton("View Results/Progress");
        JButton exitButton = new JButton("Exit");
    
        // Set layout for button panel
        JPanel buttonPanel = new JPanel(new GridLayout(0, 1, 0, 10)); // 0 rows, 1 column, 10 pixels vertical gap
        buttonPanel.add(registerCandidatesButton);
        buttonPanel.add(castVotersButton);
        buttonPanel.add(viewResultsButton);
        buttonPanel.add(exitButton);
    
        // Add the button panel to the main panel
        gridPane.add(buttonPanel);
    
        // Comment out the event handling and keep the stubs
        registerCandidatesButton.addActionListener(e -> {  openRegisterCandidatesForm(); });
        castVotersButton.addActionListener(e -> {  openCastVoteForm();  });
        viewResultsButton.addActionListener(e -> {  openResultsForm();  });
        exitButton.addActionListener(e -> dispose());
    }
    

    private void openRegisterCandidatesForm() {
        RegisterCandidatesForm registerCandidates = new RegisterCandidatesForm(electionService);
        registerCandidates.setVisible(true);
    }
    

    
    private void openResultsForm() {
        ResultsForm resultsForm = new ResultsForm(electionService);
        resultsForm.setVisible(true);
    }
    
    private void openCastVoteForm() {
        CastVoteForm castVoteForm = new CastVoteForm(electionService);
        castVoteForm.setVisible(true);
    }

    private void initServices() {
        String votersFilePath =  "votersCollection.ser";
        String candidatesFilePath =  "candidatesCollection.ser";

        FileService votersCollectionFileService = new FileService(votersFilePath);
        FileService candidatesColletionFileService = new FileService(candidatesFilePath);
        electionService = new ElectionService(votersCollectionFileService, candidatesColletionFileService);
    }
}