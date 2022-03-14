package meta.projet.SolverBFS.Codification;

public class Codification {
    
    public static void DisplayAsMAtrix(int code)
    {
        int j = 0, k = 100000000;
        System.out.println("Matrice sous forme d'entier : "+code);
        System.out.println("Affichage sous forme matriciel :");
        for(int i=0;i<9;++i)
        {
            int a = (code / k) % 10;
            k /= 10;
            System.out.print(a+" ");
            j++;
            if(j==3)
            {
                System.out.println("");
                j = 0;
            }
        }
    }

    public static int limits(int p){
        double d = (float)p / 3 -0.01;
        int l = 0;
        switch((int)d){
            case 0:
                l = 13;
                break;
            case 1:
                l = 46;
                break;
            case 2:
                l = 79;
                break;
        }
        return l;
    }

    public static int position(int c)
    {
        int zpos = 10;
        int val = -1;
        int t = c;
        while(val != 0)
        {
            val = t % 10;
            t /= 10;
            zpos--;
        }
        int a1 = 0, a2 = 0, a3 = 0, a4 =0;
        int posa1 = zpos - 1, posa2 = zpos + 1, posa3 = zpos - 3, posa4 =zpos + 3;
        int save, tempcode, l1 = (int)(limits(zpos)/10) ,l2 = (int)(limits(zpos)%10);

        if((posa1 >= l1))
        {
            save = 10;
            tempcode = c;
            while(posa1 != save)
            {
                a1 = tempcode % 10;
                tempcode /= 10;
                save--;
            }
        }

        if((l2 >= posa2))
        {
            save = 10;
            tempcode = c;
            while(posa2 != save)
            {
                a2 = tempcode % 10;
                tempcode /= 10;
                save--;
            }
        }

        if(posa3 > 0)
        {
            save = 10;
            tempcode = c;
            while(posa3 != save)
            {
                a3 = tempcode % 10;
                tempcode /= 10;
                save--;
            }
        }

        if(posa4 < 10)
        {
            save = 10;
            tempcode = c;
            while(posa4 != save)
            {
                a4 = tempcode % 10;
                tempcode /= 10;
                save--;
            }
        }
        return (int) (zpos*Math.pow(10, 4) + a1*Math.pow(10, 3) + a2*Math.pow(10, 2) + a3*Math.pow(10, 1) + a4);
    }

    public static int SwitchCell(int c, int vi)
    {
        int newcode = 0;
        int digit;
        for(int i = 0;i<9;++i)
        {
            digit = c % 10;
            c = (int) c / 10;
            if(digit == vi)
                newcode += 0;
            else if (digit == 0)
                newcode += vi * Math.pow(10, i);
            else 
                newcode += digit * Math.pow(10, i);
        }
        return newcode;
    }

    //! Ne marche pas pour certaines valeurs à améliorer car plus optimal que la précédentes
    public static int SwitchCells(int c,int pz, int pi, int vi)
    {
        int Before = 0, After = 0, End = 0, SwitchedValue = 0;
        int BeforeInter;
        if(pz < pi)
        {
            BeforeInter = (int)(c /  Math.pow(10,(9-pz+1)));
            Before = (int)(BeforeInter * Math.pow(10,(9-pz+1)));
            int ND = String.valueOf(BeforeInter).length();
            SwitchedValue = (int)(vi*Math.pow(10,(9-ND-1)));
            After = (int)((int)((c %  Math.pow(10,(9-pz)))/Math.pow(10,(9-pz+1)))*Math.pow(10,(9-pi+1)));
            End = (int)(c % Math.pow(10, (9-pi)));
            return Before + SwitchedValue + After + End;
        }
        else
        {
            BeforeInter = (int)(c /  Math.pow(10,(9-pi+1)));
            Before = (int)(BeforeInter * Math.pow(10,(9-pi+1)));
            int ND = String.valueOf(BeforeInter).length();
            After = (int)((int)((c % Math.pow(10,9-pi))/Math.pow(10,(9-pz+1))) * Math.pow(10,(pi-ND+1)));
            SwitchedValue = (int)(vi*Math.pow(10,(9-pz)));
            End = (int)(c % Math.pow(10, (9-pz)));
            return Before + After + SwitchedValue + End;
        }
    }
}
