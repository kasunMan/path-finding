
/**
 *
 * @author A.A.K.T Amarasinghe
 * 2015264
 * w1608473
 * A.A.K.T.Amarasinghe
 */
public class Vertex {
    
       public int i;
       public int j;
       public double distance;
       public Vertex previous = null;
       public boolean checked;
       public boolean blocked;

        public Vertex(int i, int j) {
            this.i = i;
            this.j = j;
            this.distance = Integer.MAX_VALUE;
        }
    
}
