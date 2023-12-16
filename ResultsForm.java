import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ResultsForm extends JFrame {
    private ElectionService electionService;
    private JTextArea winnerTextArea;

    public ResultsForm(ElectionService electionService) {
        this.electionService = electionService;
        setTitle("Candidates Progress - See Results");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Add Announce Winner Button
        JButton winnerButton = new JButton("Announce Winner");
        winnerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(Box.createVerticalStrut(20));
        panel.add(winnerButton);
        winnerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                announceWinner();
            }
        });

        // Add TextArea with Placeholder
        winnerTextArea = new JTextArea("The winner will be announced here");
        winnerTextArea.setEditable(false);
        winnerTextArea.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(Box.createVerticalStrut(20));
        panel.add(winnerTextArea);

        add(panel);
        setVisible(true);
    }

    private void announceWinner() {
        List<String> winners = electionService.calculateElectionWinner();
        StringBuilder winnerText = new StringBuilder();

        for (String s : winners) {
            winnerText.append(s).append("\n");
        }

        winnerTextArea.setText(winnerText.toString());
    }
}


