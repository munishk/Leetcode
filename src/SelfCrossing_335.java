public class SelfCrossing_335 {
	
	 class Point {
	        int x, y;
	    
	      public Point(int x, int y) {
	          this.x = x;
	          this.y = y;
	      }

		@Override
		public String toString() {
			return "(" + x + ", " + y + ")";
		}
	      
	      
	    }
	    
	    public boolean isSelfCrossing(int[] x) {
	        Point topLeft = null, topRight = null, bottomLeft = null, bottomRight = null;
	        Point current  = new Point(0,0); Point next = null;
	        bottomRight = current;
	        for(int i=0; i<x.length; i++) {
	            int dir = i%4;
	            switch(dir) {
	                case 0: //north/up
	                      next = new Point(current.x, current.y+x[i]);
	                      if(topRight != null && topLeft != null && next.x >= topLeft.x && next.x <= topRight.x && topLeft.y >= current.y && topLeft.y <= next.y) {
	                          return true;
	                      }
	                      //update boundary
	                      topRight = next;
	                      break;
	                case 1: //West/left
	                      next = new Point(current.x-x[i], current.y);
	                      if(topLeft != null && bottomLeft != null && next.y >= bottomLeft.y && next.y <= topLeft.x && topLeft.x >= current.x && topLeft.y <= next.x) {
	                          return true;
	                      }
	                      //update boundary
	                      topLeft = next;
	                      break;
	                case 2:
	                     next = new Point(current.x, current.y-x[i]);
	                      if(bottomLeft != null && bottomRight != null && next.x >= bottomLeft.x && next.x <= bottomLeft.x && bottomLeft.y >= current.y && bottomLeft.y <= next.y) {
	                          return true;
	                      }
	                      bottomLeft = next;
	                      break;
	               case 3: //East/right
	                      next = new Point(current.x+x[i], current.y);
	                      if(topRight != null && bottomRight != null && next.y >= bottomRight.y && next.y <= topRight.y && bottomRight.x >= current.x && bottomRight.y <= next.x) {
	                          return true;
	                      }
	                      bottomRight = next;
	                      break;
	            default: throw new IllegalArgumentException();
	            }
	            current = next;
	        }
	        return false;
	    }

	public static void main(String[] args) {
		SelfCrossing_335 obj = new SelfCrossing_335();
		int[] X = {2,1,1,2};
		System.out.println(obj.isSelfCrossing(X));

	}

}
