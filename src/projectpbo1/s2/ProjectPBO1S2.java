/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package projectpbo1.s2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class Customer {
    private String nama;

    public Customer(String nama) {
        this.nama = nama;
    }

    public String getNama() {
        return nama;
    }
}

class AntriCustomer {
    private ArrayList<Customer> antri;

    public AntriCustomer() {
        this.antri = new ArrayList<>();
    }

    public void addCustomer(Customer customer) {
        antri.add(customer);
    }

    public void deleteCustomer(int index) {
        if (index >= 1 && index <= antri.size()) {
            antri.remove(index - 1);
        } else {
            JOptionPane.showMessageDialog(null, "Nomor antrian tidak ditemukan.");
        }
    }

    public String displayAntrian() {
        if (antri.isEmpty()) {
            return "Antrian Kosong.";
        } else {
            StringBuilder antrianText = new StringBuilder("Antrian Saat ini :\n");
            for (int i = 0; i < antri.size(); i++) {
                antrianText.append((i + 1)).append(". ").append(antri.get(i).getNama()).append("\n");
            }
            return antrianText.toString();
        }
    }
}

public class ProjectPBO1S2 {
    private JFrame frame;
    private AntriCustomer antri;
    private JTextField namaField;
    private JTextArea antrianArea;

    public ProjectPBO1S2() {
        antri = new AntriCustomer();

        frame = new JFrame("Antri Customer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());

        JLabel namaLabel = new JLabel("Nama Customer:");
        namaField = new JTextField(20);

        JButton addButton = new JButton("Tambahkan Antrian");
        addButton.addActionListener(new AddButtonListener());

        JButton deleteButton = new JButton("Hapus Antrian");
        deleteButton.addActionListener(new DeleteButtonListener());

        JButton displayButton = new JButton("Display Antrian");
        displayButton.addActionListener(new DisplayButtonListener());

        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(new QuitButtonListener());

        inputPanel.add(namaLabel);
        inputPanel.add(namaField);
        inputPanel.add(addButton);
        inputPanel.add(deleteButton);
        inputPanel.add(displayButton);
        inputPanel.add(quitButton);

        antrianArea = new JTextArea(10, 20);
        antrianArea.setEditable(false);

        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(new JScrollPane(antrianArea), BorderLayout.CENTER);

        frame.pack();
        frame.setVisible(true);
    }

    private class AddButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String nama = namaField.getText();
            if (!nama.isEmpty()) {
                antri.addCustomer(new Customer(nama));
                namaField.setText("");
                antrianArea.setText(antri.displayAntrian());
            }
        }
    }

    private class DeleteButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String input = JOptionPane.showInputDialog("Masukkan nomor antrian yang akan dihapus:");
            if (input!= null) {
                int index = Integer.parseInt(input);
                antri.deleteCustomer(index);
                antrianArea.setText(antri.displayAntrian());
            }
        }
    }

    private class DisplayButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            antrianArea.setText(antri.displayAntrian());
        }
    }

    private class QuitButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ProjectPBO1S2();
            }
        });
    }
}