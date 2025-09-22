package main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CSVWriter {
    public static void WriteClear(){

    }
    public static void AppendWriting(String what,int input_size, float millisecond, int rec_depth, int comparison){
        try{
            File file = new File("output/info.csv"); // будет создана папка output и файл info.csv
            file.getParentFile().mkdirs(); // создаём директории если их нет

            try (FileWriter fw = new FileWriter(file, true)) {
                String str = what + "," + input_size + "," + millisecond + "," + rec_depth + "," + comparison;
                System.out.println(str);
                fw.write(str + "\n");
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

