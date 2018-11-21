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


    private JLabel label;
    private JLabel output;
    private JTextField field;
    private JTextArea list;
    public ToDoListRun() throws IOException {

        super("Todo List");


        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400, 700));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13) );
        setLayout(null);



        JButton btn = new JButton("Regular Task");
        JButton schoolBtn = new JButton("School Task");
        JButton crossOffBtn = new JButton("Cross off");
        btn.setActionCommand("myButton");
        btn.addActionListener(this);
        schoolBtn.setActionCommand("mySchoolButton");
        schoolBtn.addActionListener(this);
        crossOffBtn.setActionCommand("crossOff");
        crossOffBtn.addActionListener(this);


        //sets "this" class as an action listener for btn.
        //that means that when the btn is clicked,
        //this.actionPerformed(ActionEvent e) will be called.
        //You could also set a different class, if you wanted
        //to capture the response behaviour elsewhere
        label = new JLabel("What would you like to do?");
        output = new JLabel();
        field = new JTextField(5);
        list = new MyTextArea(24,30);
        JTextArea up = new MyTextArea(24,30);
        StringBuilder str = getStringBuilder();
        Font font1 = new Font("Bradley Hand ITC", Font.BOLD, 20);


        list.setText(str.toString());
        add(field);
        field.setBounds(50,50,300,30);
        btn.setBounds(40,80,150,40);
        add(btn);
        add(schoolBtn);
        schoolBtn.setBounds(210,80,150,40);
        add(crossOffBtn);
        crossOffBtn.setBounds(120,120,150,40);
        add(label);
        add(up);
        up.setBounds(0,0,400,170);
        up.setEditable(false);
        label.setBounds(50,15,300,30);
        label.setFont(font1);
        output.add(list);
        add(output);
        add(list);
        list.setBounds(0,170,400,500);
        list.setFont(font1);
        list.setBackground(new Color(1,1,1, (float) 0.01));
        up.setBackground(new Color(1,1,1, (float) 0.01));
        list.setEditable(false);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    private StringBuilder getStringBuilder() {
        StringBuilder str = new StringBuilder();
        ArrayList<Task> school = new ArrayList<>();
        ArrayList<Task> regular = new ArrayList<>();
        for (String key : toDoList.keySet()) {
            if (toDoList.get(key).getType().equals("School")) {
                school.add(toDoList.get(key));
            }
            else {
                regular.add(toDoList.get(key));
            }
        }
        str.append("\tMy Task Manager\n");
        str.append("SCHOOL TASKS:\n\n");
        for (Task t : school){
            str.append("\t"+t.getName())
                    .append(" on ")
                    .append(t.getTime().getDay()+"!")
                    .append("\n");

        }
        str.append("\n\nREGULAR TASKS:\n\n");
        for (Task t : regular){
            str.append("\t"+t.getName())
                    .append(" on ")
                    .append(t.getTime().getDay()+"!")
                    .append("\n");

        }
        return str;
    }

    //this is the method that runs when Swing registers an action on an element
    //for which this class is an ActionListener
    public void actionPerformed(ActionEvent e)
    {
        ToDoListManager toDoListManager = new ToDoListManager();
        if(e.getActionCommand().equals("myButton"))
        {
            try {
                ArrayList<String> str = splitOnSpace(field.getText());

                     Task t = toDoListManager.addTask(scanner, toDoList, str.get(0), "Regular", str.get(1), "urgent");
                     toDoListManager.addToMap(t);
                     label.setText("You've added "+t.getName()+" to the To-Do List!");

                } catch (AlreadyInList alreadyInList) {
                label.setText("This task is already in the To-Do List!");

                } catch (EmptyTaskException emptyTaskException){
                label.setText("You cannot add an empty task!");
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


        else {
            try {
                ArrayList<String> str = splitOnSpace(field.getText());

                Task t = toDoListManager.addTask(scanner, toDoList, str.get(0), "School", str.get(1), "urgent");
                toDoListManager.addToMap(t);

            } catch (AlreadyInList alreadyInList) {
                label.setText("This task is already in the To-Do List!");

            } catch (EmptyTaskException emptyTaskException){
                label.setText("You cannot add an empty task!");
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
    private static ArrayList<String> splitOnSpace(String line){
        String[] splits = line.split(" ");
        return new ArrayList<>(Arrays.asList(splits));
    }


    public static void main(String[] args) throws IOException {
    new ToDoListRun();

}

}
