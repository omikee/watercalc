package net.omikee.watercalc;

import java.util.Arrays;

/**
 * WaterCalcImpl
 */
public class WaterCalcImpl implements WaterCalc {

    static int maxLength = 32000;
    static int maxHeight = 32000;

    private int[] landscape;
    private int[] waterLevel;

    /**
     * calculateWaterAmount
     */
    @Override
    public long calculateWaterAmount(int[] landscape) {
        if (landscape.length > maxLength) {
            throw new IllegalArgumentException(String.format("Landscape length > %s", maxLength));
        }

        for (int height : landscape) {
            if (height < 0 || maxHeight < height) {
                throw new IllegalArgumentException(
                        String.format("Landscape height %s is not between 0 and %s", height, maxHeight));
            }
        }

        int[] waterLevel = Arrays.copyOf(landscape, landscape.length);
        int[] waterLevelR = Arrays.copyOf(landscape, landscape.length);

        for (int l = 1, r = landscape.length - 2; l < landscape.length; l++, r--) {
            if (landscape[l] < waterLevel[l - 1]) {
                waterLevel[l] = waterLevel[l - 1];
            }
            if (landscape[r] < waterLevelR[r + 1]) {
                waterLevelR[r] = waterLevelR[r + 1];
            }
        }

        long amount = 0;

        for (int i = 0; i < landscape.length; i++) {
            waterLevel[i] = Math.min(waterLevel[i], waterLevelR[i]);
            if (landscape[i] < waterLevel[i]) {
                amount += waterLevel[i] - landscape[i];
            }
        }

        this.landscape = landscape;
        this.waterLevel = waterLevel;
        return amount;
    }

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLACK = "\u001B[30m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_WHITE = "\u001B[37m";

    /**
     * pretty print landscape and water
     */
    void print() {
        int max = Arrays.stream(landscape).max().orElse(0);
        for (int row = max - 1; row >= 0; row--) {
            for (int i = 0; i < landscape.length; i++) {
                if (row < landscape[i]) {
                    System.out.print(ANSI_GREEN + "[X]" + ANSI_RESET);
                } else if (row < waterLevel[i]) {
                    System.out.print(ANSI_CYAN + "~~~" + ANSI_RESET);
                } else {
                    System.out.print("   ");
                }
            }
            System.out.print("\n");
        }
    }

}
