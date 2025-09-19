package main;

import java.io.*;

public abstract class Sorter {
    public int[] List;
    public abstract void sort(int[] array);
    public abstract void print();
    public abstract void TXTEnter(String s);
    public abstract void CSVEnter(String s);

    public void WriteToCSV(String algorithmName, String result, int recursionCount, int switchCount, float timeConsumed){
        try (FileWriter writer = new FileWriter("main/resources/info.csv", true)) {
            String w = algorithmName+","+result+","+recursionCount+","+switchCount+","+timeConsumed;
            writer.append(w);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
