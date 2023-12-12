package AntvirusProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

	
	public class AntivirusGUI extends JFrame {
	    private final SimpleAntivirus antivirus;
	    private final JTextArea textArea;
	    private final JTextField searchField;
	    private final JButton searchButton;
	    private final JButton removeButton;
	    private final JLabel statusLabel;

	    public AntivirusGUI() {
	        antivirus = new SimpleAntivirus();
	        textArea = new JTextArea();
	        searchField = new JTextField("C:\\");
	        searchButton = new JButton("Start Search");
	        removeButton = new JButton("Remove Files");
	        statusLabel = new JLabel("Ready");

	        setLayout(new BorderLayout());
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setSize(500, 500);

	        searchButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                try {
	                    statusLabel.setText("Searching...");
	                    antivirus.searchFiles(Paths.get(searchField.getText()));
	                    displayReport();
	                    statusLabel.setText("Search completed");
	                } catch (IOException ioException) {
	                    statusLabel.setText("Error: " + ioException.getMessage());
	                }
	            }
	        });

	        removeButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                try {
	                    statusLabel.setText("Removing files...");
	                    antivirus.removeFiles();
	                    statusLabel.setText("Files removed");
	                    textArea.setText("");
	                } catch (IOException ioException) {
	                    statusLabel.setText("Error: " + ioException.getMessage());
	                }
	            }
	        });

	        JPanel topPanel = new JPanel(new BorderLayout());
	        topPanel.add(searchField, BorderLayout.CENTER);
	        topPanel.add(searchButton, BorderLayout.EAST);

	        JPanel bottomPanel = new JPanel(new BorderLayout());
	        bottomPanel.add(removeButton, BorderLayout.WEST);
	        bottomPanel.add(statusLabel, BorderLayout.EAST);

	        add(topPanel, BorderLayout.NORTH);
	        add(new JScrollPane(textArea), BorderLayout.CENTER);
	        add(bottomPanel, BorderLayout.SOUTH);
	    }

	    private void displayReport() {
	        textArea.setText("Found files:\n");
	        for (Path path : antivirus.getFoundFiles()) {
	            textArea.append(path.toString() + "\n");
	        }
	    }	 
	    }
	

