
package LineSearcher;

import javax.swing.*;
import java.io.IOException;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Client {

    public class ClientGUI extends JFrame {

  
        private static final long serialVersionUID = 1L;

        private JTextField textField = new JTextField();
        private JButton connectButton = new JButton();
        private final JList<String> resultList = new JList<>();
        private final DefaultListModel<String> listModel = new DefaultListModel<>();

        public ClientGUI() {

            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(500, 250);

            JPanel panel = new JPanel(new BorderLayout());
            add(panel);

            JLabel label = new JLabel("Enter the line to search:                               Result: ");
            panel.add(label, BorderLayout.NORTH);

            textField = new JTextField();
            panel.add(textField, BorderLayout.CENTER);

            connectButton = new JButton("Find");
            connectButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    listModel.clear();
                    String input = textField.getText();
                    try {
                        Socket connection = new Socket("127.0.0.1", 1239);
                        int userInt = Integer.parseInt(input);

                        lineSearcher lineSearch = new lineSearcher();
                        List<String> lines = lineSearch.getLinesAround(userInt);
                        for (String line : lines) {
                            listModel.addElement(line);
                        }

                        if (!connection.isClosed())
                            connection.close();

                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            });

            panel.add(connectButton, BorderLayout.SOUTH);
            JScrollPane scrollPane = new JScrollPane(resultList);
            JLabel label2 = new JLabel("Result: ");
            panel.add(label2, BorderLayout.EAST);
            panel.add(scrollPane, BorderLayout.EAST);
            resultList.setModel(listModel);
        }
    }

    public static void main(String[] args) {
    	Client client = new Client();
        ClientGUI clientGUI = client.new ClientGUI();
        clientGUI.setVisible(true);
    }
}


