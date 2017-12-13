import java.util.Arrays;


/**
 * A class that runs implements several simple transformations on 2D image arrays.
 * <p>
 * This file contains stub code for your image transformation methods that are called by the main
 * class. You will do your work for this MP in this file.
 * <p>
 * Note that you can make several assumptions about the images passed to your functions, both by the
 * web front end and by our testing harness:
 * <ul>
 * <li>You will not be passed empty images.</li>
 * <li>All images will have even width and height.</li>
 * </ul>
 *
 * @see <a href="https://cs125.cs.illinois.edu/MP/4/">MP4 Documentation</a>
 */
public class Transform {

    /**
     * Default amount to shift an image's position. Not used by the testing suite, so feel free to
     * change this value.
     */
    public static final int DEFAULT_POSITION_SHIFT = 16;

    /**
     * Pixel value to use as filler when you don't have any valid data. All white with complete
     * transparency. DO NOT CHANGE THIS VALUE: the testing suite relies on it.
     */
    public static final int FILL_VALUE = 0x00FFFFFF;


    /**
     * Shift the image left by the specified amount.
     * <p>
     * Any pixels shifted in from off screen should be filled with FILL_VALUE. This function <i>does
     * not modify the original image</i>.
     *
     * @param originalImage the image to shift to the left
     * @param amount the amount to shift the image to the left
     * @param left boolean left is true and when shift left
     * @return the shifted image
     */

   public static int[][] shiftHorizontal(final int[][] originalImage,
           final int amount, final boolean left) {

       int[][] shiftResult = new int[originalImage.length][originalImage[0].length];
       int yLength = originalImage.length;

       for (int i = 0; i < yLength - amount; i++) {


           if (left) {
               shiftResult[i] = originalImage[i + amount].clone();
           } else {
               shiftResult[yLength - 1 - i] =
                       originalImage[yLength - 1 - i - amount].clone();
           }

   }
       for (int fillY = yLength - amount; fillY < yLength; fillY++) {
               if (fillY >= 0) {
                   for (int j = 0; j < originalImage[fillY].length; j++) {
                       if (left) {
                           shiftResult[fillY][j] = FILL_VALUE;
                       } else {
                           shiftResult[yLength - 1 - fillY][j] = FILL_VALUE;
                       }
                   }
               }
           }
       return shiftResult;
   }
   /**
    * Shift the image left by the specified amount.
    * <p>
    * Any pixels shifted in from off screen should be filled with FILL_VALUE. This function <i>does
    * not modify the original image</i>.
    *
    * @param originalImage the image to shift to the left
    * @param amount the amount to shift the image to the left
    * @return the shifted image
    */

   public static int[][] shiftLeft(final int[][] originalImage, final int amount) {
       return shiftHorizontal(shiftVertical(originalImage, amount, true), amount, true);
    }

    /*
     * Shift right like above.
     */
   /**
   *
   * @param originalImage original image
   * @param amount shift amount
   * @return image array
   */

   public static int[][] shiftRight(final int[][] originalImage, final int amount) {
        return shiftHorizontal(originalImage, amount, false);
    }
   /**
   *
   * @param originalImage original image
   * @param amount shift amount
   * @param up boolean up is true if it is shifting up
   * @return image array
   */
   public static int[][] shiftVertical(final int[][] originalImage,
           final int amount, final boolean up) {

       int[][] shiftedImage = new int[originalImage.length][originalImage[0].length];
       int yLength = originalImage.length;

       for (int i = 0; i < yLength; i++) {
           int xLength = originalImage[i].length;

       for (int j = 0; j < xLength - amount; j++) {
           if (up) {

               shiftedImage[i][j] = originalImage[i][j + amount];
           } else {
               shiftedImage[i][xLength - 1 - j] =
                       originalImage[i][xLength - 1 - j - amount];
           }
       }
       for (int fillX = xLength - amount; fillX < xLength; fillX++) {
           if (fillX >= 0) {
               if (up) {
                   shiftedImage[i][fillX] = FILL_VALUE;
               } else {
                   shiftedImage[i][xLength - fillX - 1] = FILL_VALUE;
               }
           }
       }

       }
       return shiftedImage;
       }
    /**
     * Shift up like above.
     */
   /**
   *
   * @param originalImage original image
   * @param amount shift amount
   * @return image array
   */
    public static int[][] shiftUp(final int[][] originalImage, final int amount) {
        return shiftVertical(originalImage, amount, true);

    }

    /**
     * Shift down like above.
     */
    /**
    *
    * @param originalImage original image
    * @param amount shift amount
    * @return image array
    */
    public static int[][] shiftDown(final int[][] originalImage, final int amount) {
        return shiftVertical(originalImage, amount, false);
    }

    /**
     * Rotate the image left by 90 degrees around its center.
     * <p>
     * Any pixels rotated in from off screen should be filled with FILL_VALUE. This function <i>does
     * not modify the original image</i>.
     *
     * @param originalImage the image to rotate left
     * @return the rotated image
     */

    public static int[][] rotateLeft(final int[][] originalImage) {

        int[][] rotatedImage = new int[originalImage.length][originalImage[0].length];

        int cc = (originalImage[0].length - originalImage.length) / 2;
        int rr = 0;

        if (cc < 0) {
            cc = 0;
            rr = (originalImage.length - originalImage[0].length) / 2;
        }

        for (int c = 0; c < rotatedImage.length; c++) {
            Arrays.fill(rotatedImage[c], FILL_VALUE);
        }

        int yLength = originalImage.length;
        for (int i = 0 + rr; i < yLength - rr; i++) {
            int xLength = originalImage[i].length;

            for (int j = 0 + cc; j < xLength - cc; j++) {
                    if ((originalImage[0].length - originalImage.length) / 2 > 0) {
                        rotatedImage[j - cc][i + cc] =
                                originalImage[yLength - i - 1][j];
                    } else {
                        rotatedImage[j + rr][i - rr] =
                                originalImage[yLength - i - 1][j];
                    }

                }
        }

        return rotatedImage;

   }


    /*
     * Rotate the image right like above.
     */
    /**
    * @param originalImage the image to rotate right
    * @return the rotated image
    */
    public static int[][] rotateRight(final int[][] originalImage) {

        int[][] rotatedImage = new int[originalImage.length][originalImage[0].length];

        int cc = (originalImage[0].length - originalImage.length) / 2;
        int rr = 0;

        if (cc < 0) {
            cc = 0;
            rr = (originalImage.length - originalImage[0].length) / 2;
        }

        for (int r = 0; r < rotatedImage.length; r++) {
            Arrays.fill(rotatedImage[r], FILL_VALUE);
        }


        for (int i = 0 + rr; i < originalImage.length - rr; i++) {
            for (int j = 0 + cc; j < originalImage[i].length - cc; j++) {

                    if ((originalImage[0].length - originalImage.length) / 2 >= 0) {
                        rotatedImage[j - cc][i + cc] =
                                originalImage[i][originalImage[i].length - 1 - j];
                    } else {
                        rotatedImage[j + rr][i - rr] =
                                originalImage[i][originalImage[i].length - 1 - j];
                    }
                }
            }


        return rotatedImage;

    }



    /*
     * Flip the image on the vertical axis across its center.
     */
    /**
    *
    * @param originalImage original image
    * @return flipped image
    */
    public static int[][] flipVertical(final int[][] originalImage) {

        int[][] flippedImage = new int[originalImage.length][originalImage[0].length];

        for (int i = 0; i < originalImage.length; i++) {
            for (int j = 0; j < originalImage[i].length; j++) {
                flippedImage[i][j] = originalImage[i][originalImage[i].length - 1 - j];
            }
        }
        return flippedImage;
    }

    /*
     * Flip the image on the horizontal axis across its center.
     */
    /**
    *
    * @param originalImage original image
    * @return flipped image
    */
    public static int[][] flipHorizontal(final int[][] originalImage) {

        int[][] flippedImage = new int[originalImage.length][originalImage[0].length];

        for (int i = 0; i < originalImage.length; i++) {
            flippedImage[i] = originalImage[originalImage.length - 1 - i].clone();

        }
        return flippedImage;
    }


    /**
     * Default amount to shift colors by. Not used by the testing suite, so feel free to change this
     * value.
     */


    public static final int DEFAULT_COLOR_SHIFT = 32;


    /**
     *
     * @param originalImage original image
     * @param amount amount of color to add or remove
     * @return changed Image
     */
    public static int[][] changeSepia(final int[][] originalImage, final int amount) {
        int[][] recoloredImage = new int[originalImage.length][originalImage[0].length];

        for (int i = 0; i < originalImage.length; i++) {
            for (int j = 0; j < originalImage[0].length; j++) {
                int color = originalImage[i][j];

                int r = (color) & BASE;
                int g = (color >> SHIFT_G) & BASE;
                int b = (color >> SHIFT_B) & BASE;
                int a = (color >> SHIFT_A) & BASE;

                    r += amount;
                    if (r > MAX_COLOR) {
                        r = MAX_COLOR;
                    }
                    if (r < MIN_COLOR) {
                        r = MIN_COLOR;
                    }

                    recoloredImage[i][j] = (a << SHIFT_A) + (b << SHIFT_B) + (g << SHIFT_G) + (r);
                }
        }
        return recoloredImage;
    }

    /**
     * Add red to the image.
     * <p>
     * This function <i>does not modify the original image</i>. It should also not generate any new
     * filled pixels.
     *
     * @param originalImage the image to add red to
     * @param amount the amount of red to add
     * @return the recolored image
     */

    /**
    * maximum color.
    */
    public static final int MAX_COLOR = 255;
    /**
    * minimum color.
    */
    public static final int MIN_COLOR = 0;
    /**
    * amount to shift for green.
    */
    public static final int SHIFT_G = 8;
    /**
    * amount to shift for blue.
    */
    public static final int SHIFT_B = 16;
    /**
    * amount to shift for alpha.
    */
    public static final int SHIFT_A = 24;
    /**
    * 8 bits base to read colors.
    */
    public static final int BASE = 0xff;
    /**
     * Add red to the image.
     * <p>
     * This function <i>does not modify the original image</i>. It should also not generate any new
     * filled pixels.
     *
     * @param originalImage the image to add red to
     * @param amount the amount of red to add
     * @return the recolored image
     */

    public static int[][] moreSepia(final int[][] originalImage, final int amount) {
        //sepia image
        int[][] recoloredImage = new int[originalImage.length][originalImage[0].length];

        for (int i = 0; i < originalImage.length; i++) {
            for (int j = 0; j < originalImage[0].length; j++) {
                int color = originalImage[i][j];

                int r = (color) & BASE;
                int g = (color >> SHIFT_G) & BASE;
                int b = (color >> SHIFT_B) & BASE;
                int a = (color >> SHIFT_A) & BASE;

                int tr = (int)(.393*r + .769*g + .189*b);
                int tg = (int)(.349*r + .686*g + .168*b);
                int tb = (int)(.272*r + .534*g + .131*b);

                if(tr>MAX_COLOR) {
                    r = 255;

                }else {
                    r = tr;
                }
                if(tg>MAX_COLOR) {
                    g = 255;

                }else {
                    g = tg;
                }
                if(tb>255) {
                    b = 255;

                }else {
                    b = tb;
                }

            recoloredImage[i][j] = ((a << SHIFT_A) + (b << SHIFT_B) + (g << SHIFT_G) + r);
        }
        }





        return recoloredImage;

    }



    /*
     * Remove red from the image.
     */
    /**
    *
    * @param originalImage original image
    * @param amount amount of red to remove
    * @return image array
    */
    public static int[][] lessSepia(final int[][] originalImage, final int amount) {
        return changeSepia(originalImage, -amount);
    }


    /*
     * Add green to the image.
     */
    /**
    *
    * @param originalImage original image
    * @param amount amount of green to add
    * @return image array
    */
    public static int[][] changeNegative(final int[][] originalImage, final int amount) {
        int[][] recoloredImage = new int[originalImage.length][originalImage[0].length];


        for (int i = 0; i < originalImage.length; i++) {
            for (int j = 0; j < originalImage[0].length; j++) {
                int color = originalImage[i][j];

                int r = (color) & BASE;
                int g = (color >> SHIFT_G) & BASE;
                int b = (color >> SHIFT_B) & BASE;
                int a = (color >> SHIFT_A) & BASE;

                g = g + amount;
                if (g > MAX_COLOR) {
                    g = MAX_COLOR;
                }
                if (g < MIN_COLOR) {
                    g = MIN_COLOR;
                }
               recoloredImage[i][j] = ((a << SHIFT_A) + (b << SHIFT_B) + (g << SHIFT_G) + r);
            }
        }
        return recoloredImage;
        }

    /**
    *
    * @param originalImage original image
    * @param amount amount of green to remove
    * @return image array
    */
    public static int[][] moreNegative(final int[][] originalImage, final int amount) {
        int[][] recoloredImage = new int[originalImage.length][originalImage[0].length];
        //negative

        for (int i = 0; i < originalImage.length; i++) {
            for (int j = 0; j < originalImage[0].length; j++) {
                int color = originalImage[i][j];



                int r = (color & BASE);
                int g = ((color >> SHIFT_G) & BASE);
                int b = ((color >> SHIFT_B) & BASE);
                int a = ((color >> SHIFT_A) & BASE);

                r = MAX_COLOR - r;
                g = MAX_COLOR - g;
                b = MAX_COLOR - b;

               recoloredImage[i][j] = ((a << SHIFT_A) + (b << SHIFT_B) + (g << SHIFT_G) + r);
            }
        }
        return recoloredImage;
        }


    /*
     * Remove green from the image.
     */
    /**
    *
    * @param originalImage original image
    * @param amount amount of green to remove
    * @return image array
    */
    public static int[][] lessNegative(final int[][] originalImage, final int amount) {
            return changeNegative(originalImage, -amount);
        }



    /*
     * Add blue to the image.
     */
    /**
    *
    * @param originalImage original image
    * @param amount amount of blue to change
    * @return resultImage
    */
    public static int[][] changeGray(final int[][] originalImage, final int amount) {
        int[][] recoloredImage = new int[originalImage.length][originalImage[0].length];

        for (int i = 0; i < originalImage.length; i++) {
            for (int j = 0; j < originalImage[0].length; j++) {
                int color = originalImage[i][j];



                int r = (color & BASE);
                int g = ((color >> SHIFT_G) & BASE);
                int b = ((color >> SHIFT_B) & BASE);
                int a = ((color >> SHIFT_A) & BASE);


               recoloredImage[i][j] = ((a << SHIFT_A) + (b << SHIFT_B) + (g << SHIFT_G) + r);
            }
        }
        return recoloredImage;
        }

    /**
    *
    * @param originalImage original image
    * @param amount amount of blue to add
    * @return image array
    */
    public static int[][] moreGray(final int[][] originalImage, final int amount) {
            //gray scale
        int[][] recoloredImage = new int[originalImage.length][originalImage[0].length];

            for (int i = 0; i < originalImage.length; i++) {
                for (int j = 0; j < originalImage[0].length; j++) {
                    int color = originalImage[i][j];
                    final int z = 3;


                    int r = (color & BASE);
                    int g = ((color >> SHIFT_G) & BASE);
                    int b = ((color >> SHIFT_B) & BASE);
                    int a = ((color >> SHIFT_A) & BASE);

                    r = (r + g + b) / z;
                    g = (r + g + b) / z;
                    b = (r + g + b) / z;

                   recoloredImage[i][j] = ((a << SHIFT_A) + (b << SHIFT_B) + (g << SHIFT_G) + r);
                }
            }
            return recoloredImage;
            }


    /*
     * Remove blue from the image.
     */
    /**
    *
    * @param originalImage original image
    * @param amount amount of blue to remove
    * @return image array
    */
    public static int[][] lessGray(final int[][] originalImage, final int amount) {
        return changeGray(originalImage, -amount);
        }


    /*
     * Increase the image alpha channel.
     */
    /**
    *
    * @param originalImage original image
    * @param amount of transparency increases or decrease
    * @return image array
    */
    public static int[][] changeAlpha(final int[][] originalImage, final int amount) {
        int[][] recoloredImage = new int[originalImage.length][originalImage[0].length];

        for (int i = 0; i < originalImage.length; i++) {
            for (int j = 0; j < originalImage[0].length; j++) {
                int color = originalImage[i][j];

                int r = (color) & BASE;
                int g = (color >> SHIFT_G) & BASE;
                int b = (color >> SHIFT_B) & BASE;
                int a = (color >> SHIFT_A) & BASE;


                    a += amount;
                    if (a > MAX_COLOR) {
                        a = MAX_COLOR;
                    }
                    if (a < MIN_COLOR) {
                        a = MIN_COLOR;
                    }
                    recoloredImage[i][j] = (a << SHIFT_A) + (b << SHIFT_B) + (g << SHIFT_G) + (r);
                }
        }
        return recoloredImage;
    }

    /**
    *
    * @param originalImage original image
    * @param amount of transparency increases
    * @return image array
    */
    public static int[][] moreAlpha(final int[][] originalImage, final int amount) {

        return changeAlpha(originalImage, amount);
        }


    /*
     * Decrease the image alpha channel.
     */
    /**
    *
    * @param originalImage original image
    * @param amount of transparency decreases
    * @return image array
    */
    public static int[][] lessAlpha(final int[][] originalImage, final int amount) {

        return changeAlpha(originalImage, -amount);
    }


    /**
     * The default resize factor. Not used by the testing suite, so feel free to change this value.
     */
    public static final int DEFAULT_RESIZE_AMOUNT = 2;

    /**
     * Shrink in the vertical axis around the image center.
     * <p>
     * An amount of 2 will result in an image that is half its original height. An amount of 3 will
     * result in an image that's a third of its original height. Any pixels shrunk in from off
     * screen should be filled with FILL_VALUE. This function <i>does not modify the original
     * image</i>.
     *
     * @param originalImage the image to shrink
     * @param amount the factor
     * @return the shrunken image
     */
    public static int[][] shrinkVertical(final int[][] originalImage, final int amount) {
        return originalImage;
    }

    /*
     * Shrink in the horizontal axis around the image center.
     */
    /**
     *
     * @param originalImage original image
     * @param amount amount to shrink
     * @return image array
     */
    public static int[][] shrinkHorizontal(final int[][] originalImage, final int amount) {
        int[][] resultImage = new int[originalImage.length][originalImage[0].length];

        return resultImage;
    }

    /*
     * Expand in the vertical axis around the image center.
     */
    /**
     *
     * @param originalImage original image
     * @param amount amount to expand
     * @return image array
     */
    public static int[][] expandVertical(final int[][] originalImage, final int amount) {
        int[][] resultImage = new int[originalImage.length][originalImage[0].length];

        boolean half = true;
        int halfOfColumn = originalImage[0].length / 2;
        int value = 0;


        for (int i = 0; i < originalImage.length; i++) {
            value = 0;
            for (int j = 0; j < originalImage[i].length; j++) {
                for (int c = 0; c < amount; c++) {
                    if (half) {

                        resultImage[i][halfOfColumn - 1 - value - c] =
                                originalImage[i][halfOfColumn - 1 - j];

                        if (halfOfColumn - 1 - value - c == 0) {
                            value = halfOfColumn;
                            half = false;
                            break;
                        }
                        if (c == amount - 1) {
                            value += amount;
                        }
                    }
                    if (j >= halfOfColumn) {

                        resultImage[i][value + c] = originalImage[i][j];

                        if (value + c == originalImage[i].length - 1) {
                            half = true;
                            j = originalImage[i].length;
                            break;
                        }
                        if (c == amount - 1) {
                            value += amount;
                        }
                    }

                }
        }
        }
            return resultImage;
    }

    /*
     * Expand in the horizontal axis around the image center.
     */
    /**
     *
     * @param originalImage original image
     * @param amount amount to expand
     * @return image array
     */
    public static int[][] expandHorizontal(final int[][] originalImage, final int amount) {

        int[][] resultImage = new int[originalImage.length][originalImage[0].length];

        int halfOfRow = originalImage.length / 2;

        boolean half = true;
        int value = 0;

        for (int i = 0; i < originalImage.length; i++) {
            for (int c = 0; c < amount; c++) {
                if (half) {
                    resultImage[halfOfRow - 1 - value - c] = originalImage[halfOfRow - 1 - i];
                    if (halfOfRow - 1 - value - c == 0) {
                        half = false;
                        value = halfOfRow;
                        break;
                    }
                    if (c == amount - 1) {
                        value += amount;
                    }
                }
                if (i >= halfOfRow) {
                    resultImage[value + c] = originalImage[i];
                    if (value + c == resultImage.length - 1) {
                        i = resultImage.length;
                        break;
                    }
                    if (c == amount - 1) {
                        value += amount;
                    }
                }
            }
        }

        return resultImage;
    }

    /**
     * Remove a green screen mask from an image.
     * <p>
     * This function should remove primarily green pixels from an image and replace them with
     * transparent pixels (FILL_VALUE), allowing you to achieve a green screen effect. Obviously
     * this function will destroy pixels, but it <i>does not modify the original image</i>.
     * <p>
     * While this function is tested by the test suite, only extreme edge cases are used. Getting it
     * work work will with real green screen images is left as a challenge for you.
     *
     * @param originalImage the image to remove a green screen from
     * @return the image with the green screen removed
     */
    public static int[][] greenScreen(final int[][] originalImage) {
        int[][] rocoloredImage = new int[originalImage.length][originalImage[0].length];


               return rocoloredImage;
    }


    /**
     * A wild and mysterious image transform.
     * <p>
     * You are free to implement this in any way you want. It is not tested rigorously by the test
     * suite, but it should do something (change the original image) and <i>not modify the original
     * image</i>.
     * <p>
     * Call this function mystery. It should take only the original image as a single argument and
     * return a modified image.
     *
     * @param originalImage the image to perform a strange and interesting transform on
     * @return the image transformed in wooly and frightening ways
     */
    public static int[][] mystery(final int[][] originalImage) {
        int[][] recoloredImage = new int[originalImage.length][originalImage[0].length];
        //mosaic

        for (int i = 0; i < originalImage.length; i++) {
            for (int j = 0; j < originalImage[0].length; j++) {
                int color = originalImage[i][j];



                int r = (color & BASE);
                int g = ((color >> SHIFT_G) & BASE);
                int b = ((color >> SHIFT_B) & BASE);
                int a = ((color >> SHIFT_A) & BASE);


                    a = (int)(Math.random()*256);


               recoloredImage[i][j] = ((a << SHIFT_A) + (b << SHIFT_B) + (g << SHIFT_G) + r);
            }
        }
        return recoloredImage;
        }
}
