package meta.projet.classesi.solver;

/**
 * Implements the codification of the matrix to an integer
 *
 */
public class Codification {
    
    /**
     * static method that returns the cells of the bounds of the line
     * of the matrix where the 0 is for example the bounds of the first
     * line are the cells 1 and 3
     * @param p the position of the 0 in the matrix
     * @return an integer of 2 digits each digit represent a bound
     */
    public static int limits(int p){
        /**
         * We divide the position by 3 and we sub 0.01 from it to avoid getting an integer
         * then we retrieve the integer portion of the number and deduce the bounds from it
         */
        double d = (float)p / 3 -0.01;
        switch((int)d){
            case 0:
                return 13;
            case 1:
                return 46;
            case 2:
                return 79;
            default:
                return 0;
        }
    }

    /**
     * static method which get the position of the 0 in the matrix and its adjacent values
     * @param c the matrix in an integer form
     * @return an integer containing the position of the 0 and its adjacent values
     */
    public static int position(int c)
    {
        int zpos = 10;
        int val = -1;
        int t = c;
        //looping through the integer until we reach the 0
        while(val != 0)
        {
            val = t % 10;
            t /= 10;
            zpos--;
        }
        //init the values of the adjacent cells of the 0
        int a1 = 0, a2 = 0, a3 = 0, a4 =0;
        //compute the positions of the adjacent cells of the 0
        int posa1 = zpos - 1, posa2 = zpos + 1, posa3 = zpos - 3, posa4 =zpos + 3;
        int save, tempcode, l1 = (int)(limits(zpos)/10) ,l2 = (int)(limits(zpos)%10);

        //if the adjacent left cell exist 
        if((posa1 >= l1))
        {
            save = 10;
            tempcode = c;
            /**
             * looping through the integer until we reach the value of
             * the adjacent left cell
            */
            while(posa1 != save)
            {
                a1 = tempcode % 10;
                tempcode /= 10;
                save--;
            }
        }

        //if the adjacent right cell exist
        if((l2 >= posa2))
        {
            save = 10;
            tempcode = c;
            /**
             * looping through the integer until we reach the value of 
             * the adjacent right cell
            */
            while(posa2 != save)
            {
                a2 = tempcode % 10;
                tempcode /= 10;
                save--;
            }
        }

        //if the adjacent upper cell exist
        if(posa3 > 0)
        {
            save = 10;
            tempcode = c;
            /**
             * looping through the integer until we reach the value of the
             * the adjacent upper cell
            */
            while(posa3 != save)
            {
                a3 = tempcode % 10;
                tempcode /= 10;
                save--;
            }
        }

        //if the adjacent down cell exist
        if(posa4 < 10)
        {
            save = 10;
            tempcode = c;
            /**
             * looping through the integer until we reach the value of the
             * the adjacent down cell
            */
            while(posa4 != save)
            {
                a4 = tempcode % 10;
                tempcode /= 10;
                save--;
            }
        }
        /**
         * return interger of this form 
         * PositionOfTheZero/LeftCellValue/RightCellValue/UpCellValue
         * if one of the adjacent cells doesn't exist the value will be 0
         */
        return (int) (zpos*Math.pow(10, 4) + a1*Math.pow(10, 3) + a2*Math.pow(10, 2) + a3*Math.pow(10, 1) + a4);
    }

    /**
     * static method which switch the 0 with an adjacent cell using math operations
     * @param c the matrix in an integer form
     * @param pz the position of the zero
     * @param pi the position of the the adjacent cell
     * @param vi the value of the adjacent cell
     * @return the new matrix in an integer form
     */
    public static int SwitchCell(int c,int pz, int pi, int vi)
    {
        if(pz < pi)
        {
            return Integer.parseInt((((int)(c /  Math.pow(10,(9 - pz + 1))) == 0) ?  "" : (int)(c /  Math.pow(10,(9-pz+1)))) + "" +
                                        vi + "" +
                                        (((int)((int)((c %  Math.pow(10,(9-pz)))/Math.pow(10,(9 - pi + 1)))) == 0) ?  "" : (int)((int)((c %  Math.pow(10,(9 - pz))) / Math.pow(10,(9 - pi + 1))))) +
                                        "0" + 
                                        (((int)(c % Math.pow(10, (9 - pi)))== 0) ?  "" : (int)(c % Math.pow(10, (9 - pi)))));
        }
        else
        {
            return Integer.parseInt((((int)(c /  Math.pow(10,(9 - pi + 1))) == 0) ?  "" : (int)(c /  Math.pow(10,(9 - pi + 1)))) +
                                        "0" +
                                        (((int)((int)(c % Math.pow(10, 9 - pi)) / Math.pow(10, 9 - pz + 1)) == 0) ?  "" : (int)((int)(c % Math.pow(10, 9 - pi)) / Math.pow(10, 9 - pz + 1))) + "" +
                                        vi + "" + 
                                        (((int)(c % Math.pow(10, 9 - pz)) == 0) ?  "" : (int)(c % Math.pow(10, 9 - pz))));
        }

    }
}
