package net.omikee.watercalc;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.*;
import java.util.stream.IntStream;

/**
 * Unit test for WaterCalcImpl.
 */
public class WaterCalcImplTest {
    /**
     * Check maxLength exceed
     */
    @Test(expected = IllegalArgumentException.class)
    public void maxLengthExceed() {
        WaterCalc waterCalc = new WaterCalcImpl();
        waterCalc.calculateWaterAmount(new int[WaterCalcImpl.maxLength + 1]);
    }

    /**
     * Check height is negative
     */
    @Test(expected = IllegalArgumentException.class)
    public void heightIsNegative() {
        WaterCalc waterCalc = new WaterCalcImpl();
        waterCalc.calculateWaterAmount(new int[]{-1});
    }

    /**
     * Check maxHeight exceed
     */
    @Test(expected = IllegalArgumentException.class)
    public void maxHeightExceed() {
        WaterCalc waterCalc = new WaterCalcImpl();
        waterCalc.calculateWaterAmount(new int[]{WaterCalcImpl.maxHeight + 1});
    }

    /**
     * Empty landscape
     */
    @Test
    public void emptyLandscape() {
        WaterCalc waterCalc = new WaterCalcImpl();
        assertEquals(0, waterCalc.calculateWaterAmount(new int[0]));
    }

    /**
     * Less than 3 landscape
     */
    @Test
    public void lessThanThreeLandscape() {
        WaterCalc waterCalc = new WaterCalcImpl();
        Random rand = new Random();
        int[] landscape = IntStream.generate(
                () -> rand.nextInt(WaterCalcImpl.maxHeight)
        ).limit(rand.nextInt(2)).toArray();
        assertEquals(0, waterCalc.calculateWaterAmount(landscape));
    }

    /**
     * Flat shape
     */
    @Test
    public void flatShape() {
        WaterCalc waterCalc = new WaterCalcImpl();
        Random rand = new Random();
        int height = rand.nextInt(WaterCalcImpl.maxHeight);
        int[] landscape = IntStream.generate(
                () -> height
        ).limit(rand.nextInt(WaterCalcImpl.maxLength)).toArray();
        assertEquals(0, waterCalc.calculateWaterAmount(landscape));
    }

    /**
     * Pyramid shape
     */
    @Test
    public void pyramidShape() {
        WaterCalc waterCalc = new WaterCalcImpl();
        long result = waterCalc.calculateWaterAmount(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 7, 6, 5, 4, 3, 2, 1});
        assertEquals(0, result);
    }

    /**
     * Example test 1
     */
    @Test
    public void exampleTest1() {
        WaterCalc waterCalc = new WaterCalcImpl();
        long result = waterCalc.calculateWaterAmount(new int[]{5, 2, 3, 4, 5, 4, 0, 3, 1});
        assertEquals(9, result);
    }

    /**
     * Example test 2
     */
    @Test
    public void exampleTest2() {
        WaterCalc waterCalc = new WaterCalcImpl();
        long result = waterCalc.calculateWaterAmount(new int[]{1, 3, 2, 4, 1, 3, 1, 4, 5, 2, 2, 1, 4, 2, 2});
        assertEquals(15, result);
    }

    /**
     * Example test 3
     */
    @Test
    public void exampleTest3() {
        WaterCalc waterCalc = new WaterCalcImpl();
        long result = waterCalc.calculateWaterAmount(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1});
        assertEquals(6, result);
    }

    /**
     * Example test 4
     */
    @Test
    public void exampleTest4() {
        WaterCalc waterCalc = new WaterCalcImpl();
        long result = waterCalc.calculateWaterAmount(new int[]{6, 2, 3, 4, 8, 4, 0, 3, 1});
        assertEquals(12, result);
    }

    /**
     * Example test 5
     */
    @Test
    public void exampleTest5() {
        WaterCalc waterCalc = new WaterCalcImpl();
        long result = waterCalc.calculateWaterAmount(new int[]{5, 2, 3, 4, 5, 4, 0, 3, 1});
        assertEquals(9, result);
    }

    /**
     * Example test 6
     */
    @Test
    public void exampleTest6() {
        WaterCalc waterCalc = new WaterCalcImpl();
        long result = waterCalc.calculateWaterAmount(new int[]{1, 3, 0, 4, 5, 4, 3, 2, 5});
        assertEquals(9, result);
    }

    /**
     * Example test 7
     */
    @Test
    public void exampleTest7() {
        WaterCalc waterCalc = new WaterCalcImpl();
        long result = waterCalc.calculateWaterAmount(new int[]{7, 5, 3, 1, 4, 1, 3, 5, 7, 9, 2, 2, 5, 6, 3, 5});
        assertEquals(38, result);
    }

}
