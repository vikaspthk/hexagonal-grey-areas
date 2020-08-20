
import java.io.BufferedReader;
import java.io.FileReader;
import static java.lang.Math.abs;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class hexagone {

    static hexagone hx = new hexagone();

    public static void main(String args[]) throws Exception {
        
       for(int i=0;i<hx.allAreas().size();i++){
        System.out.println("Area "+(i+1)+":"+hx.allAreas().get(i));
       }

    }

    private char[][] readFile() {
        char[][] list = new char[13][11];
        try {
            String file = "C:\\Users\\vikpatha\\Documents\\NetBeansProjects\\hexagone\\src\\store.txt";
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            for (int r = 0; r < 12; r++) {
                if ((line = br.readLine()) != null) {
                    char[] temp = line.toCharArray();
                    for (int c = 0; c < temp.length; c++) {
                        list[r][c] = line.charAt(c);
                    }
                }
            }
        } catch (Exception ex) {

        }
        return list;
    }

    /**
     * ****get all grey cells************
     */
    public List allGreyCells(char[][] array, int rows, int cols) {
        List greyCell = new LinkedList();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (array[r][c] == 'g') {
                    greyCell.add("C" + r + c);

                }
            }
            // System.out.println("hjj "+greyCell);
        }
        /**
         * ************** Finding adjacent grey cells******
         */
       List adjacentGreyCells = new LinkedList();
        for (int i = 0; i < greyCell.size(); i++) {
            String areaI = (String) greyCell.get(i);
            
            int Irow = areaI.charAt(1);
            
            int Icol = Integer.parseInt(areaI.substring(2));
            
            List temp = new LinkedList();
            for (int j = 0; j < greyCell.size(); j++) {
                if (j == i) {
                    temp.add(areaI);
                }
                if (j != i) {
                    String areaJ = (String) greyCell.get(j);
                    int Jrow = areaJ.charAt(1);
                    int Jcol = Integer.parseInt(areaJ.substring(2));
                    if (abs(Jrow - Irow) <= 1 && abs(Jcol - Icol) <= 1)//check the adjacent cell
                    {
                        temp.add(areaJ);
                    }
                }
            }
            adjacentGreyCells.add(temp);
        }
        
        return (LinkedList) adjacentGreyCells;
    }
/**
 * Finding the Number of Grey Areas
 * @return all the sets of grey areas
 */
    public List allAreas() {
        LinkedList adj_grey;
         char[][] array = readFile();
         adj_grey=(LinkedList) allGreyCells(array, 13, 11);
            List sets = new LinkedList();
            for (int i = 0; i < adj_grey.size(); i++) {
            List A = (LinkedList) adj_grey.get(i);
            for (int j = 0; j < adj_grey.size(); j++) {
            if (i != j) {
            List B = (LinkedList) adj_grey.get(j);
            {for (int a = 0; a < A.size(); a++) {
             for (int b = 0; b < B.size(); b++) {
             if (A.get(a) == B.get(b)) {
             for (int c = 0; c < A.size(); c++) {
             for (int d = 0; d < B.size(); d++) {
             if (!A.contains(B.get(d))) {
             A.add(B.get(d));
              }}}}}}}}}
                                        
            Set set=new HashSet(A);
            if (!sets.contains(set)) {
                sets.add(set);
            }
        }
        return (LinkedList) sets;
    }
}
