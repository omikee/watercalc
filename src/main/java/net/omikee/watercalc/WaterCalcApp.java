package net.omikee.watercalc;

import java.util.Arrays;

public class WaterCalcApp {

    public static void main(String[] args) {
        if (args.length == 0) {
            printUsage();
            System.exit(-1);
        }

        try {
            int[] landscape = Arrays.stream(args).mapToInt(Integer::valueOf).toArray();

            WaterCalcImpl waterCalc = new WaterCalcImpl();

            long amount = waterCalc.calculateWaterAmount(landscape);

            waterCalc.print();
            System.out.println("Water amount: " + amount);

        } catch (NumberFormatException e) {
            System.out.println("Error: list of landscape heights should be integers");
            printUsage();
            System.exit(-1);
        } catch (Exception e) {
            System.out.println("Error: " + e.getLocalizedMessage());
            printUsage();
            System.exit(-1);
        }
    }

    private static void printUsage() {
        System.out.println("Usage: WaterCalc <list of landscape heights>");
    }
}
