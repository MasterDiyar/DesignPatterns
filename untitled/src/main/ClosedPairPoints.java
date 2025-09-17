package main;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ClosedPairPoints extends Sorter{
    public String divider;
    private List<Point> points;
    private Point[] closedPoint = new Point[2];
    private Timer  timer = new Timer();

    @Override
    public void sort(int[] array) {
        Timer.timeIt("closedPairPoints algorithm work", Timer.TimeUnit.MILLISECONDS,()-> BruteForce(points));
        closedPoint = Timer.timeIt("closedPair algorithm work", Timer.TimeUnit.MILLISECONDS,()-> BrutalForce(points));

    }

    @Override
    public void print() {
        System.out.println("Closed Pair Points:");
        System.out.println("1st point:"+closedPoint[0].X + closedPoint[0].Y);
        System.out.println("2nd point:"+closedPoint[1].X + closedPoint[1].Y);
    }

    @Override
    public void TXTEnter(String s) {
        File file = new File(s);
        points = new ArrayList<Point>();
        if(file.exists()){
            try (BufferedReader br = new BufferedReader(new FileReader(file)))
            {
                String line;
                while((line = br.readLine()) != null){
                    String[] array = line.split(",");
                    for (String str : array) {
                        int[] num = stringToIntArray(str.split("\\.",2), 2);
                        Point nPoint = new Point(num[0], num[1]);
                        points.add(nPoint);
                    }
            }
            } catch (IOException e) {
                System.out.println(e);
            }
        }

    }

    @Override
    public void CSVEnter(String s) {
        File file = new File(s);
        points = new ArrayList<Point>();
        if(file.exists()){
            try (BufferedReader br = new BufferedReader(new FileReader(file)))
            {
                String line;
                while((line = br.readLine()) != null){
                    String[] array = line.split(",");
                    for (String str : array) {
                        int[] num = stringToIntArray(str.split("\\.",2), 2);
                        Point nPoint = new Point(num[0], num[1]);
                        points.add(nPoint);

                    }
                }
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    private float BruteForce(List<Point> pts)
    {
        float min = Float.MAX_VALUE;
        for (int i = 0; i < pts.size(); i++)
            for (int j = i + 1; j < pts.size(); j++) {
                if (min != Math.min(min, getDistance(pts.get(i), pts.get(j)))){
                    closedPoint[0] = pts.get(i);
                    closedPoint[1] = pts.get(j);
                }
                min = Math.min(min, getDistance(pts.get(i), pts.get(j)));
            }
        return min;
    }

    private Point[] BrutalForce(List<Point> pts)
    {float min = Float.MAX_VALUE;
        for (int i = 0; i < pts.size(); i++)
            for (int j = i + 1; j < pts.size(); j++) {
                if (min != Math.min(min, getDistance(pts.get(i), pts.get(j)))){
                    closedPoint[0] = pts.get(i);
                    closedPoint[1] = pts.get(j);
                }
                min = Math.min(min, getDistance(pts.get(i), pts.get(j)));
            }
        return closedPoint;
    }

    public static float getDistance(Point p1, Point p2) {
        return (float) Math.sqrt((p1.X-p2.X)*(p1.X-p2.X)+(p1.Y-p2.Y)*(p1.Y-p2.Y));
    }

    public static int[] stringToIntArray(String[] str, int length) {
        int[] array = new int[length];
        for (int i = 0; i < length; i++) array[i] = Integer.parseInt(str[i]);
        return array;
    }

    public record Point(int X, int Y) {}
}
