// referenced from LittleCalculatorLecLab

package ui;

import exceptions.AlreadyInList;
import exceptions.EmptyTaskException;
import exceptions.NoTaskFoundException;
import exceptions.NullOutputException;
import model.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class ToDoListRun extends JFrame  implements ActionListener {

    private ToDoListManager toDoListManager = new ToDoListManager();
    private final String FILE = "outputfile.txt";
    private Scanner scanner = new Scanner(System.in);
    private Map<String, Task> toDoList = toDoListManager.load("outputfile.txt", toDoListManager.times);
    private String selectedUrgency;
    private String selectedDay;


    private JLabel label;
    private JLabel output;
    private JTextField field;
    private JTextArea list;
    private  JComboBox<String> comboBox;
    private JComboBox<String> dayComboBox;
    public ToDoListRun() throws IOException {

        super("The 'Task Manager'");


        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400, 700));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13) );
        setLayout(null);


        // Add & Cross off buttons=======================================================
        JButton regularTaskBtn = new JButton("Regular Task");
        JButton schoolTaskBtn = new JButton("School Task");
        JButton crossOffBtn = new JButton("Cross off");
        regularTaskBtn.setActionCommand("myButton");
        regularTaskBtn.addActionListener(this);
        schoolTaskBtn.setActionCommand("mySchoolButton");
        schoolTaskBtn.addActionListener(this);
        crossOffBtn.setActionCommand("crossOff");
        crossOffBtn.addActionListener(this);

        // User input & output fields====================================================
        label = new JLabel("What would you like to do?");
        output = new JLabel();
        field = new JTextField(5);
        list = new MyTextArea(24,30);
        JTextArea topWindowBackground = new MyTextArea(24,30);

        //Font and setting the input / output fields=====================================
        StringBuilder str = getStringBuilder();
        Font font1 = new Font("Bradley Hand ITC", Font.BOLD, 20);
        list.setText(str.toString());

        // ComboBox for priority and days================================================
        String[] urgencyString = {"urgent", "medium", "low"};
        comboBox = new JComboBox<>(urgencyString);
        String[] dayString = {"SUNDAY", "MONDAY", "TUESDAY","WEDNESDAY","THURSDAY","FRIDAY","SATURDAY"};
        dayComboBox = new JComboBox<>(dayString);
        comboBox.setActionCommand("urgencyCombo");
        comboBox.addActionListener(this);
        dayComboBox.addActionListener(this);
        dayComboBox.setActionCommand("dayCombo");


        // Adding buttons/ fields and setting=============================================
        add(comboBox);
        comboBox.setBounds(150,50,100,30);
        selectedUrgency = (String) comboBox.getSelectedItem();

        add(dayComboBox);
        dayComboBox.setBounds(275,50,100,30);
        selectedDay = (String) dayComboBox.getSelectedItem();

        add(field);
        field.setBounds(20,50,100,30);

        add(regularTaskBtn);
        regularTaskBtn.setBounds(40,80,150,40);

        add(schoolTaskBtn);
        schoolTaskBtn.setBounds(210,80,150,40);

        add(crossOffBtn);
        crossOffBtn.setBounds(120,120,150,40);

        add(label);
        add(topWindowBackground);
        topWindowBackground.setBounds(0,0,400,170);
        topWindowBackground.setEditable(false);
        label.setBounds(50,15,300,30);
        label.setFont(font1);

        add(output);
        output.add(list);
        add(list);

        list.setBounds(0,170,400,500);
        list.setFont(font1);
        list.setBackground(new Color(1,1,1, (float) 0.01));
        topWindowBackground.setBackground(new Color(1,1,1, (float) 0.01));
        list.setEditable(false);


        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }


    //this is the method that runs when Swing registers an action on an element
    //for which this class is an ActionListener
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("urgencyCombo")){
        selectedUrgency = (String) comboBox.getSelectedItem();}
        if (e.getActionCommand().equals("dayCombo")){
            selectedDay = (String) dayComboBox.getSelectedItem();}
        ToDoListManager toDoListManager = new ToDoListManager();
        if(e.getActionCommand().equals("myButton"))
        {
            try {
                     Task t = toDoListManager.addTask(scanner, toDoList, field.getText(), "Regular", selectedDay, selectedUrgency);
                     toDoListManager.addToMap(t);
                     label.setText("You've added "+t.getName());

                } catch (AlreadyInList alreadyInList) {
                label.setText(alreadyInList.getMessage());

                } catch (EmptyTaskException emptyTaskException){
                label.setText(emptyTaskException.getMessage());
                }

            }
            if (e.getActionCommand().equals("crossOff")){
                try {
                    toDoListManager.crossOff(scanner, toDoList, field.getText());
                    label.setText("You've crossed off "+field.getText());

                } catch (NoTaskFoundException e1) {
                    label.setText("There was no task found!");
                }

            }


         if (e.getActionCommand().equals("mySchoolButton")){
            try {


                Task t = toDoListManager.addTask(scanner, toDoList, field.getText(), "School", selectedDay, selectedUrgency);
                toDoListManager.addToMap(t);

            } catch (AlreadyInList alreadyInList) {
                label.setText(alreadyInList.getMessage());

            } catch (EmptyTaskException emptyTaskException){
                label.setText(emptyTaskException.getMessage());
            }

            }
        StringBuilder str = getStringBuilder();
        list.setText(str.toString());
        try {
            try {
                toDoListManager.save(toDoList, FILE);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            try {
                toDoListManager.saveMap();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }catch (NullOutputException nullOutputException) {
            nullOutputException.printStackTrace();
            System.out.println("\nNo FILE exists!");
        }

    }

    private StringBuilder getStringBuilder() {
        StringBuilder str = new StringBuilder();
        ArrayList<Task> school = new ArrayList<>();
        ArrayList<Task> newSchool = new ArrayList<>();
        ArrayList<Task> regular = new ArrayList<>();
        ArrayList<Task> newRegular = new ArrayList<>();
        for (String key : toDoList.keySet()) {
            if (toDoList.get(key).getType().equals("School")) {
                school.add(toDoList.get(key));
            }
            else {
                regular.add(toDoList.get(key));
            }
        }
        toDoListManager.sortTasks(school,newSchool,"urgent");
        toDoListManager.sortTasks(school,newSchool,"medium");
        toDoListManager.sortTasks(school,newSchool,"low");
        toDoListManager.sortTasks(regular,newRegular,"urgent");
        toDoListManager.sortTasks(regular,newRegular,"medium");
        toDoListManager.sortTasks(regular,newRegular,"low");

        str.append("                   MY NOTE PAD\n\n");
        parseTasks(str, newSchool, "  SCHOOL TASKS:\n\n");
        parseTasks(str, newRegular, "\n\n  REGULAR TASKS:\n\n");
        return str;
    }

    private void parseTasks(StringBuilder str, ArrayList<Task> newSchool, String s) {
        str.append(s);
        for (Task t : newSchool) {
            str.append("            " + t.getName())
                    .append(" on ")
                    .append(t.getTime().getDay() + "!")
                    .append("\n");

        }
    }


    public static void main(String[] args) throws IOException {
    new ToDoListRun();

}

}
