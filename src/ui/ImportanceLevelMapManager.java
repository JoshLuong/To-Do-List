package ui;

import model.SaveAndLoad;
import model.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ImportanceLevelMapManager{
    private SaveAndLoad saveAndLoad = new SaveAndLoad();
    private Map<String, List<String>> searchImptLvl;

    public ImportanceLevelMapManager() throws IOException {
        searchImptLvl= loadMap();
    }

    public void removeTaskFromLevelMap(Map<String, Task> toDoList, String search) {
        List<String> tasks = searchImptLvl.get(toDoList.get(search).getImportanceLvl());
        tasks.remove(toDoList.get(search).getName());
    }

    public void printLevelMap(String s) {
        for (int i = 0; i < searchImptLvl.get(s).size(); i++) {
            System.out.println(searchImptLvl.get(s).get(i));
        }
    }

    public void addToMap(Task t){
        if (!searchImptLvl.containsKey(t.getImportanceLvl())){
            List<String> tasks = new ArrayList<>();
            tasks.add(t.getName());
            searchImptLvl.put(t.getImportanceLvl(), tasks);
        }
        else searchImptLvl.get(t.getImportanceLvl()).add(t.getName());
    }

    public Map<String, List<String>> loadMap() throws IOException {
        return saveAndLoad.loadMap();
    }

    public void saveMap() throws IOException {
        saveAndLoad.saveMap(searchImptLvl);
    }
}
