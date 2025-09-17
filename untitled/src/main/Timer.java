package main;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.function.Supplier;

/*
 class timer how it works?
 timeIt< class > ( "task name you want", timeunit task)




*/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface TimeIt {
    String name() default "";
    Timer.TimeUnit unit() default Timer.TimeUnit.MILLISECONDS;
}



public class Timer {
    public static enum TimeUnit {
        NANOSECONDS, MICROSECONDS, MILLISECONDS, SECONDS
    }


    public static <T> T timeIt(String taskName, Supplier<T> task) {
        return timeIt(taskName, TimeUnit.MILLISECONDS, task);
    }

    public static <T> T timeIt(String taskName, TimeUnit unit, Supplier<T> task) {
        long startTime = System.nanoTime();

        T result = task.get();

        long endTime = System.nanoTime();
        long duration = endTime - startTime;

        printExecutionTime(taskName, duration, unit);
        return result;
    }

    public static void timeIt(String taskName, Runnable task) {
        timeIt(taskName, TimeUnit.MILLISECONDS, task);
    }

    public static void timeIt(String taskName, TimeUnit unit, Runnable task) {
        long startTime = System.nanoTime();

        task.run();

        long endTime = System.nanoTime();
        long duration = endTime - startTime;

        printExecutionTime(taskName, duration, unit);
    }

    private static void printExecutionTime(String taskName, long durationNanos, TimeUnit unit) {
        double convertedTime = convertTime(durationNanos, unit);
        System.out.printf("%s executed in %.3f %s%n",
                taskName, convertedTime, unit.name().toLowerCase());
    }

    private static double convertTime(long nanos, TimeUnit unit) {
        switch (unit) {
            case NANOSECONDS: return nanos;
            case MICROSECONDS: return nanos / 1000.0;
            case SECONDS: return nanos / 1_000_000_000.0;
            default: return nanos / 1_000_000.0;
        }
    }


}

