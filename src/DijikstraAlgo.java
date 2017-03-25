
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;



/**
 *
 * @author A.A.K.T Amarasinghe
 * 2015264
 * w1608473
 * A.A.K.T.Amarasinghe
 */
public class DijikstraAlgo {
 
    public Vertex start;
    public Vertex finish;
    public Vertex[][] grid;
    public boolean pathFound;
    public ArrayList<Vertex> checkedVertex;

    // Horizontal and Vertical Distance
    double horiVertDistance; 
    
    // Diagonal Distance
    double diagnolDistance;
    
    //Method that returns the nodes of the shortest path
    public ArrayList<Vertex> distance(boolean[][] booleanGrid, int startI, int startJ, int endI, int endJ,int disType) {
        //Setting the distances
        switch (disType) {
            case 1:
                horiVertDistance = 1.0;
                diagnolDistance = 1.4;
                break;
            case 2:
                horiVertDistance = 1.0;
                diagnolDistance = 2.0;
                break;
            default:
                horiVertDistance = 1.0;
                diagnolDistance = 1.0;
                break;
        }
        //The distance at start
        
        int size = booleanGrid.length;
        
        
        //Start and end nodes
         start = new Vertex(startI, startJ);
         start.distance =0;
         finish = new Vertex(endI, endJ);
        // Used to store nodes
        grid = new Vertex[size][size];
        
        //Finding blocked cells in the grid 
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                grid[i][j] = new Vertex(i, j);
                if (booleanGrid[i][j] == false) {
                    grid[i][j].blocked = true;
                }
            }
        }
        
        //ArrayList to store checked nodes
        
        
        checkedVertex = new ArrayList(size);
        
        checkedVertex.add(start);
        Vertex current=null;
        
        while(checkedVertex.size()>0 && !grid[startI][startJ].blocked){
             current = checkedVertex.remove(0);
             if((current.i==finish.i)&&(current.j==finish.j)){
                 break;
             }
            Vertex temp;
            
            //Checking all the surrounding cells
            //Checking Above 
            if (current.i - 1 >= 0) {

                //Checking Above
                temp = grid[current.i - 1][current.j];
                checkHoriVert(current, temp);
                

                //Checking Above Left
                if (current.j - 1 >= 0) {
                    temp = grid[current.i - 1][current.j - 1];
                    checkDiagnol(current, temp);
                   
                }

                //Checking Above Right
                if (current.j + 1 < size) {
                    temp = grid[current.i - 1][current.j + 1];
                    checkDiagnol(current, temp);
                   
                }
            }
            //Checking Below
            if (current.i + 1 < size) {

                //Checking Below
                temp = grid[current.i + 1][current.j];
                checkHoriVert(current, temp);
               

                //Checking Below Left
                if (current.j - 1 >= 0) {
                    temp = grid[current.i + 1][current.j - 1];
                    checkDiagnol(current, temp);
                   
                }

                //Checking Below Right
                if (current.j + 1 < size) {
                    temp = grid[current.i + 1][current.j + 1];
                    checkDiagnol(current, temp);
                    
                }
            }
            //Checking Left
            if (current.j - 1 >= 0) {
                temp = grid[current.i][current.j - 1];
                checkHoriVert(current, temp);
                
            }
            // Checking Right
            if (current.j + 1 <size) {
                temp = grid[current.i][current.j + 1];
                checkHoriVert(current, temp);
                
            }
            current.checked = true;
        }
        
        //Getting the path
        ArrayList<Vertex> shortestPath  = new ArrayList<>();
        
        //If the distance is infinite no path 
        if (!(grid[finish.i][finish.j].distance == Integer.MAX_VALUE)&&!grid[startI][startJ].blocked) {
            //Getting the path
            //Vertex current = grid[finish.i][finish.j];
            current = grid[finish.i][finish.j];
            pathFound =true;

            while (current.previous != null) {
                shortestPath.add(current.previous);
                current = current.previous;
            }
        } else System.out.println("No path available");

        return shortestPath;
    
    }
    public void checkHoriVert(Vertex current, Vertex temp){
        if (!temp.checked && !temp.blocked && temp.distance > current.distance + horiVertDistance) {
                    temp.distance = current.distance + horiVertDistance;
                    temp.previous = current;
                    checkedVertex.add(temp);
                }
        //return temp;
    }
    
    public void checkDiagnol(Vertex current, Vertex temp){
        if (!temp.checked && !temp.blocked && temp.distance > current.distance + diagnolDistance) {
                    temp.distance = current.distance + diagnolDistance;
                    temp.previous = current;
                    checkedVertex.add(temp);
                }
        //return temp;
    }
    
    
    
}
