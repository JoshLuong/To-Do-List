package model;

import exceptions.NullOutputException;
import ui.ToDoListManager;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


public class SaveAndLoad implements  Savable, Loadable{
    @Override
    public void save(Map<String, Task> mapToDoList, String output) throws IOException, NullOutputException {

        if (!output.equals("outputfile.txt") &&	 !(output.equals("saveTestOutputfile.txt"))){
            throw new NullOutputException();
        }
        PrintWriter writer = new PrintWriter(output, "UTF-8");
        for (Map.Entry<String, Task> entry : mapToDoList.entrySet())
        {
            List<String> lines = Files.readAllLines(Paths.get(output));
            Task t = entry.getValue();
            lines.add(entry.getKey()+" "+ t.getName() + " " + t.getImportanceLvl()+" " +t.getType()+ " "+t.getTime().getDay());

            for (String line : lines) {
                writer.println(line);
            }
        }
        writer.close();
    }

    public void saveMap(Map<String, List<String>> map) throws IOException {
        PrintWriter writer = new PrintWriter("mapoutput.txt", "UTF-8");
        for (Map.Entry<String, List<String>> entry : map.entrySet())
        {
            List<String> lines = Files.readAllLines(Paths.get("mapoutput.txt"));
            List<String> tasks = entry.getValue();
            StringBuilder sb = new StringBuilder();
            for (String s : tasks)
            {
                sb.append(s);
                sb.append(" ");
            }
            lines.add(entry.getKey()+" "+ sb);

            for (String line : lines) {
                writer.println(line);
            }
        }
        writer.close();

    }

    public Map<String, List<String>> loadMap() throws IOException {
        Map<String, List<String>> map = new HashMap<>();
        List<String> lines = Files.readAllLines(Paths.get("mapoutput.txt"));
        for (String s: lines){
            ArrayList<String> partsOfLine = splitOnSpace(s);
            List<String> tasks = new ArrayList<>();

            for (String t : partsOfLine.subList(1, partsOfLine.size())){
                tasks.add(t);
            }
            map.put(partsOfLine.get(0), tasks);
        }
        return map;

    }
//    public ArrayList<EstCompletionTime> loadTimes() throws IOException {
//        ArrayList<EstCompletionTime> times = new ArrayList<>();
//        List<String> lines = Files.readAllLines(Paths.get("times.txt"));
//        Task t = new RegularTask("","");
//        EstCompletionTime time = new EstCompletionTime("");
//        ArrayList<EstCompletionTime> time1 = new ArrayList<>();
//        for (String s: lines){
//            ArrayList<String> partsOfLine = splitOnSpace(s);
//            ArrayList<Task> ts = new ArrayList<>();
//            for (int i = 1; i < partsOfLine.size(); i+=4){
//                int type = i+2;
//                int name = i;
//                int imptlvl = i+1;
//                if (partsOfLine.get(type).equals("Regular")){
//                    time = new EstCompletionTime(partsOfLine.get(0));
//                    t = new RegularTask(partsOfLine.get(name), partsOfLine.get(imptlvl));
//                }
//                {}
//                if (partsOfLine.get(type).equals("School")) {
//                    time = new EstCompletionTime(partsOfLine.get(0));
//                    t = new SchoolTask(partsOfLine.get(name), partsOfLine.get(imptlvl));
//                }
//                String day = time.getDay();
//                addTimeAndTask(time1, t, day);
////                for (EstCompletionTime et : timeList) {
////                    if (et.getDay().equals(day)) {
////                        et.addTask(task);
////                        task.addTime(t);
////                        break;
////                    }
////                }
//                times.add(t.getTime());
//
//            }
//
//            }
//
//        return times;
//
//
//
//        }

    @Override
    //MODIFIES: this
    // EFFECTS: returns list of tasks from the output file
    public Map<String, Task> load(String output, List<EstCompletionTime> timeList) throws IOException {
        ToDoListManager manage = new ToDoListManager();
        Task t ;
        List<String> lines = Files.readAllLines(Paths.get(output));
        Map<String, Task> loadedList = new HashMap<>();
        for (String s: lines){
            ArrayList<String> partsOfLine = splitOnSpace(s);
            String newTask = partsOfLine.get(1);
            String type = partsOfLine.get(3);
            String dy = partsOfLine.get(4);
            String lvl = partsOfLine.get(2);

            t = manage.setTask(timeList, newTask, type, dy, lvl);

            loadedList.put(partsOfLine.get(0), t);
        }
        return loadedList;

    }

    private static ArrayList<String> splitOnSpace(String line){
        String[] splits = line.split(" ");
        return new ArrayList<>(Arrays.asList(splits));
    }
}
