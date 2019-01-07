package ee.ttu.usability.guideliner.util;

import java.util.StringTokenizer;

import org.openqa.selenium.support.Color;

public class ContrastUtils {
	  /**
     * The minimum text size considered large for contrast checking purposes, as taken from the WCAG
     * standards at http://www.w3.org/TR/UNDERSTANDING-WCAG20/visual-audio-contrast-contrast.html
     */
    public static final int WCAG_LARGE_TEXT_MIN_SIZE = 16;

    /**
     * The minimum text size for bold text to be considered large for contrast checking purposes,
     * as taken from the WCAG standards at
     * http://www.w3.org/TR/UNDERSTANDING-WCAG20/visual-audio-contrast-contrast.html
     */
    public static final int WCAG_LARGE_BOLD_TEXT_MIN_SIZE = 14;

    public static final double CONTRAST_RATIO_WCAG_NORMAL_TEXT = 4.5;

    public static final double CONTRAST_RATIO_WCAG_AAA_NORMAL_TEXT = 7.1;
    
    public static final double CONTRAST_RATIO_WCAG_LARGE_TEXT = 3.0;

    private ContrastUtils() {
        // Not instantiable
    }

    /**
     * Calculates the luminance value of a given {@code int} representation of {@link Color}.
     * <p>
     * Derived from formula at http://gmazzocato.altervista.org/colorwheel/algo.php
     *
     * @param color The {@link Color} to evaluate
     * @return the luminance value of the given color
     */
   /* public static double calculateLuminance(int color) {
        final double[] sRGB = new double[3];
        sRGB[0] = Color.red(color) / 255.0d;
        sRGB[1] = Color.green(color) / 255.0d;
        sRGB[2] = Color.blue(color) / 255.0d;

        final double[] lumRGB = new double[3];
        for (int i = 0; i < sRGB.length; ++i) {
            lumRGB[i] = (sRGB[i] <= 0.03928d) ? sRGB[i] / 12.92d
                    : Math.pow(((sRGB[i] + 0.055d) / 1.055d), 2.4d);
        }

        return 0.2126d * lumRGB[0] + 0.7152d * lumRGB[1] + 0.0722d * lumRGB[2];
    }*/
    
    public static double calculateLuminance(int red, int green, int blue) {
        final double[] sRGB = new double[3];
        sRGB[0] = red / 255.0d;
        sRGB[1] = green / 255.0d;
        sRGB[2] = blue / 255.0d;

        final double[] lumRGB = new double[3];
        for (int i = 0; i < sRGB.length; ++i) {
            lumRGB[i] = (sRGB[i] <= 0.03928d) ? sRGB[i] / 12.92d
                    : Math.pow(((sRGB[i] + 0.055d) / 1.055d), 2.4d);
        }

        return 0.2126d * lumRGB[0] + 0.7152d * lumRGB[1] + 0.0722d * lumRGB[2];
    }

    /**
     * Calculates the contrast ratio of two order-independent luminance values.
     * <p>
     * Derived from formula at http://gmazzocato.altervista.org/colorwheel/algo.php
     *
     * @param lum1 The first luminance value
     * @param lum2 The second luminance value
     * @return The contrast ratio of the luminance values
     * @throws IllegalArgumentException if luminance values are < 0
     */
    public static double calculateContrastRatio(double lum1, double lum2) {
        if ((lum1 < 0.0d) || (lum2 < 0.0d)) {
            throw new IllegalArgumentException("Luminance values may not be negative.");
        }

        return (Math.max(lum1, lum2) + 0.05d) / (Math.min(lum1, lum2) + 0.05d);
    }

    /**
     * Converts a collection of {@code int} representations of colors to a
     * string representation of those colors in hex format.
     *
     * @param colors Collection of {@link Color} values to convert
     * @return The hex string representation of the colors
     */
    public static CharSequence colorsToHexString(Iterable<Integer> colors) {
        StringBuilder colorStr = new StringBuilder(7);
        for (int color : colors) {
            if (colorStr.length() != 0) {
                colorStr.append(", ");
            }

            colorStr.append(ContrastUtils.colorToHexString(color));
        }

        return colorStr;
    }
    
    public static String convertCssColorToHexadecimalFormat(String color) {
		String s1 = color.substring(5);
		StringTokenizer st = new StringTokenizer(s1);
		int r = Integer.parseInt(st.nextToken(",").trim());
		int g = Integer.parseInt(st.nextToken(",").trim());
		int b = Integer.parseInt(st.nextToken(",").trim());
		return String.format("#%02x%02x%02x", r, g, b);
	}

    /**
     * Converts an {@code int} representation of a {@link Color} to a hex string.
     *
     * @param color The {@link Color} value to convert
     * @return The hex string representation of the color
     */
    public static CharSequence colorToHexString(int color) {
        return String.format("#%06X", (0xFFFFFF & color));
    }
}
