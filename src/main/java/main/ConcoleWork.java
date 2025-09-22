package main;

import java.util.*;

public class ConcoleWork {
    Sorter nowSorter;

    Scanner sc = new Scanner(System.in);
    String whatHappen = "";

    public List<Integer> list = new ArrayList<>();
    public ArrayList<Integer> randomList = new ArrayList<>();

    public void CreateRandomList(int count){
        Random random = new Random();
        for (int i = 0; i < count; i++){
            randomList.add(random.nextInt(10000));

        }
    }

    public ConcoleWork(){
        System.out.println("Which sorter do you prefer? 1.Merge 2.Quick 3.Determine 4.CPP 5.PrintALl");
        GetSorter(sc.nextInt());
        sc.nextLine();

        while(!whatHappen.equals("exit")){


            System.out.println("What you do?");
            System.out.println("Available commands:");
            System.out.println("  sort         - сортировать текущий список");
            System.out.println("  random_sort  - сортировать случайный список");
            System.out.println("  print        - вызвать метод print() сортировщика");
            System.out.println("  insert txt   - загрузить список из TXT файла");
            System.out.println("  insert csv   - загрузить список из CSV файла");
            System.out.println("  print list   - вывести текущий список");
            System.out.println("  exit         - выйти из программы");
            whatHappen = sc.nextLine();
            Do(whatHappen);
        }
    }

    public void GetSorter(int type){
        switch (type){
            case 1: nowSorter = new MergeSort();
                break;
            case 2: nowSorter = new QuickSort();
                break;
            case 3: nowSorter = new DeterministicSelect();
                break;
            case 4: nowSorter = new ClosedPairPoints();
            break;
            case 5:
                whatHappen = "exit";
                nowSorter = new MergeSort();
                nowSorter.print();
                nowSorter = new QuickSort();
                nowSorter.print();
                nowSorter = new DeterministicSelect();
                nowSorter.print();
                nowSorter = new ClosedPairPoints();
                nowSorter.print();
                break;


        }
    }



    public void Do(String what){
        String path;
        switch (what){
            case "sort":
                nowSorter.sort(list.stream().mapToInt(Integer::intValue).toArray());
                break;
            case "random_sort":
                nowSorter.sort(list.stream().mapToInt(Integer::intValue).toArray());
                break;
            case "print":
                nowSorter.print();
                break;
            case "insert txt":
                System.out.println("Write path to file");
                path = sc.nextLine();
                nowSorter.TXTEnter(path);
                //list = Arrays.stream(nowSorter.List).boxed().toList();
                break;
            case "insert csv":
                System.out.println("Write path to file");
                path = sc.nextLine();
                nowSorter.CSVEnter(path);
                //list = Arrays.stream(nowSorter.List).boxed().toList();
                break;
            case "print list":
                if (nowSorter instanceof ClosedPairPoints cpp){
                    Timer.timeIt("cpp test", ()->cpp.recursive(cpp.createRandomPointList(1000), 0));
                }

        }
    }



}
