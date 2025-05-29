package gui;

import logic.StudentManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class StudentManagementGUI extends JFrame {
    private final StudentManager manager;
    private final JTable table;
    private final DefaultTableModel tableModel;

    public StudentManagementGUI() {
        manager = new StudentManager();

        setTitle("Student Management System");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());

        tableModel = new DefaultTableModel(new Object[]{"Name", "Faculty Number", "Average Grade"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add Student");
        JButton removeButton = new JButton("Remove Student");
        JButton sortButton = new JButton("Sort by Avg Grade");
        JButton saveButton = new JButton("Save to File");
        JButton loadButton = new JButton("Load from File");

        addButton.addActionListener(this::addStudent);
        removeButton.addActionListener(this::removeStudent);
        sortButton.addActionListener(e -> {
            manager.sortByAverageGrade();
            refreshTable();
        });
        saveButton.addActionListener(e -> manager.saveToFile("students.txt"));
        loadButton.addActionListener(e -> {
            manager.loadFromFile("students.txt");
            refreshTable();
        });

        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(sortButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        setVisible(true);
    }

    private void addStudent(ActionEvent e) {
        String name = JOptionPane.showInputDialog("Enter name:");
        String number = JOptionPane.showInputDialog("Enter faculty number:");
        try {
            manager.addStudent(name, number);
            refreshTable();
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    private void removeStudent(ActionEvent e) {
        String number = JOptionPane.showInputDialog("Enter faculty number to remove:");
        manager.removeStudent(number);
        refreshTable();
    }

    private void refreshTable() {
        tableModel.setRowCount(0);
        manager.forEach(student ->
                tableModel.addRow(new Object[]{student.getName(), student.getFacultyNumber(), student.getAverageGrade()}));
    }
}
