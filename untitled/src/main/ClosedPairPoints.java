package main;

import java.io.*;
import java.util.*;

public class ClosedPairPoints extends Sorter{
    int recurciveCount = 0, switchCount = 0;
    public String divider;
    private List<Point> points;
    private Point[] closedPoint = new Point[2];
    private Timer  timer = new Timer();

    @Override
    public void sort(int[] array) {
    }

    @Override
    public void print() {
        System.out.println("closed pair points:");
        float time = Timer.timeIn(() ->recursive(createRandomPointList(10), 0));
        recursive(createRandomPointList(10), 0);
        System.out.println("closed pair points in recursive: "+time);
        //CSVWriter.AppendWriting("CPP recursive",1000, time, recurciveCount, switchCount);
        time = Timer.timeIn(() ->BrutalForce(createRandomPointList(10)));
        System.out.println("closed pair points in Brutal Force: "+time);
        //CSVWriter.AppendWriting("CPP brutal",1000, time, recurciveCount, switchCount);
    }

    @Override
    public void TXTEnter(String s) {

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
    public Point[] BrutalForce(List<Point> pts)
    {float min = Float.MAX_VALUE;
        recurciveCount = 0;
        switchCount = 0;
        for (int i = 0; i < pts.size(); i++)
            for (int j = i + 1; j < pts.size(); j++) {
                if (min != Math.min(min, getDistance(pts.get(i), pts.get(j)))){
                    closedPoint[0] = pts.get(i);
                    closedPoint[1] = pts.get(j);
                    switchCount++;
                }
                min = Math.min(min, getDistance(pts.get(i), pts.get(j)));
            }
        return closedPoint;
    }
    public Point[] BrutalForce(Point[] pts)
    {float min = Float.MAX_VALUE;
        for (int i = 0; i < pts.length; i++)
            for (int j = i + 1; j < pts.length; j++) {
                if (min != Math.min(min, getDistance(pts[i], pts[j]))){
                    closedPoint[0] = pts[i];
                    closedPoint[1] = pts[j];
                }
                min = Math.min(min, getDistance(pts[i], pts[j]));
            }
        return closedPoint;
    }

    public Point[] createRandomPointList(int size){
        Point[] randList = new Point[size];
        Random rand = new Random();
        for (int i = 0; i < size; i++){

            randList[i] = new Point(rand.nextInt(Integer.MAX_VALUE),rand.nextInt(Integer.MAX_VALUE));
        }
        return randList;
    }

    public Point[] recursive(Point[] points, int rcc) {
        int n = points.length;
        switchCount++;

        if (n <= 3) {
            recurciveCount = rcc;
            return BrutalForce(Arrays.asList(points));
        }

        int mid = n / 2;
        Point midPoint = points[mid];

        Point[] leftPair = recursive(Arrays.copyOfRange(points, 0, mid), rcc++);
        Point[] rightPair = recursive(Arrays.copyOfRange(points, mid, n), rcc++);

        float dLeft = getDistance(leftPair[0], leftPair[1]);
        float dRight = getDistance(rightPair[0], rightPair[1]);
        float d = Math.min(dLeft, dRight);

        Point[] bestPair = (dLeft <= dRight) ? leftPair : rightPair;

        List<Point> strip = new ArrayList<>();
        for (Point p : points) {
            if (Math.abs(p.X - midPoint.X) < d) strip.add(p);
        }

        strip.sort(Comparator.comparingInt(p -> p.Y));

        for (int i = 0; i < strip.size(); i++) {
            for (int j = i + 1; j < strip.size() && (strip.get(j).Y - strip.get(i).Y) < d; j++) {
                float dist = getDistance(strip.get(i), strip.get(j));
                if (dist < d) {
                    d = dist;
                    bestPair[0] = strip.get(i);
                    bestPair[1] = strip.get(j);
                }
            }
        }
        return bestPair;
    }


    public static float getDistance(Point p1, Point p2) {
        return (float) Math.sqrt((p1.X-p2.X)*(p1.X-p2.X)+(p1.Y-p2.Y)*(p1.Y-p2.Y));
    }
    public static int getDistanceSquared(Point p1, Point p2) {
        int dx = p1.X - p2.X;
        int dy = p1.Y - p2.Y;
        return dx * dx + dy * dy;
    }

    public static int[] stringToIntArray(String[] str, int length) {
        int[] array = new int[length];
        for (int i = 0; i < length; i++) array[i] = Integer.parseInt(str[i]);
        return array;
    }

    public record Point(int X, int Y) {}
}
