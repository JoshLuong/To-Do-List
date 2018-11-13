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
import java.util.*;

public class ToDoListRun extends JFrame  implements ActionListener {
    private ToDoListManager toDoListManager = new ToDoListManager();
    private final String FILE = "outputfile.txt";
    private Scanner scanner = new Scanner(System.in);
    private Map<String, Task> toDoList = toDoListManager.load("outputfile.txt", toDoListManager.times);


//    private ToDoListRun() throws IOException{
//        String operation;
//
//        while (true){
//
//            System.out.println("\n----------------------------------------------------------------------------------------------------------------------------------");
//            System.out.println("                                         ------Welcome to 'Task Manager'------");
//            System.out.println("Please select an option: [1] to-do, [2] cross-off, [3] print sorted to-do list, [4] check times, [5] find a task based on urgency");
//            System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
//            operation = scanner.nextLine();
//
//
//
//            if (operation.equals("1")) {
//                try {
//                     Task t = addTask(scanner, toDoList);
//                     addToMap(t);
//                    System.out.println("you added "+t.getName()+" to the to-do list!");
//
//                } catch (AlreadyInList alreadyInList) {
//                    System.out.println("This task is already in the list!");
//
//                } catch (EmptyTaskException emptyTaskException){
//                    System.out.println("You cannot add an empty task!");
//                }
//
//            }
//
//            if (operation.equals("2")) {
//                try {
//                    crossOff(scanner, toDoList);
//                } catch (NoTaskFoundException e) {
//                    System.out.println("There was no task found in the list!");
//                }
//
//            }
//            if (operation.equals("3")) {
//                ArrayList<Task> sortedList = sortedList(toDoList);
//                printList(scanner, sortedList, toDoList);
//            }
//
//            if (operation.equals("4")){
//                Collection<Task> tasks= getTasksFromTime(scanner, toDoList);
//                for (Task t : tasks){
//                    System.out.println(t.getName());
//                }
//            }
//            if (operation.equals("5")){
//                String s = printUrgency(scanner);
//                try {
//                    printLevelMap(s);
//                }
//                catch (NullPointerException n){
//                    System.out.println("There are no existing tasks with the chosen urgency!");}
//                }
//
//
//            else if (operation.equals("quit")) {
//                break;
//            }
//
//        }
//        try {
//            save(toDoList, FILE);
//            saveMap();
//        }catch (NullOutputException nullOutputException) {
//            nullOutputException.printStackTrace();
//            System.out.println("\nNo FILE exists!");
//        }
//        finally {
//            System.out.println("\n      -----Have a productive day!-----");
//            printStatement(toDoList);
//        }
//
//
//    }
    private JLabel label;
    private JLabel output;
    private JTextField field;
    private JTextArea list;
    public ToDoListRun() throws IOException {

        super("Todo List");


        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400, 500));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13) );
        setLayout(new FlowLayout());



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
        label = new JLabel("");
        output = new JLabel();
        // label.add(textArea);
        field = new JTextField(5);
        list = new JTextArea(24,30);
        StringBuilder str = getStringBuilder();

        list.setText(str.toString());
        add(field);
        add(btn);
        add(schoolBtn);
        add(crossOffBtn);
        add(label);
        output.add(list);
        add(output);
        add(list);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    private StringBuilder getStringBuilder() {
        StringBuilder str = new StringBuilder();
        for (String key : toDoList.keySet()) {
            str.append(key)
                    .append(". . .")
                    .append(toDoList.get(key).getTime().getDay())
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

                     Task t = toDoListManager.addTask(scanner, toDoList, str.get(0), "Regular", str.get(1), str.get(2));
                     toDoListManager.addToMap(t);

                } catch (AlreadyInList alreadyInList) {

                } catch (EmptyTaskException emptyTaskException){
                }

            }
            if (e.getActionCommand().equals("crossOff")){
                try {
                    toDoListManager.crossOff(scanner, toDoList, field.getText());

                } catch (NoTaskFoundException e1) {
                }

            }


        else {
            try {
                label.setText(field.getText());
                ArrayList<String> str = splitOnSpace(field.getText());

                Task t = toDoListManager.addTask(scanner, toDoList, str.get(0), "School", str.get(1), str.get(2));
                toDoListManager.addToMap(t);

            } catch (AlreadyInList alreadyInList) {

            } catch (EmptyTaskException emptyTaskException){
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
