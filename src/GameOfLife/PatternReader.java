package GameOfLife;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class PatternReader {
    private ArrayList<String> list = new ArrayList<>();
    private String path;
    private String line = null;
    private File file;

    public PatternReader(String path) throws IOException {

        this.path = path;
        this.file = new File(path);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        while (true) {
            try {
                if (!((line = reader.readLine()) != null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (!line.contains("!") && !line.contains("#")) list.add(line);
        }
    }

    public ArrayList<String> getList() {
        return list;
    }
}
