import java.util.PriorityQueue;

public class NthUglyNumber {
	 static class HeapItem implements Comparable {
	        int item;
	        int count;
	        int val;
	        
	        public HeapItem(int item, int count) {
	            this.item = item;
	            this.count = count;
	            this.val = item * count;
	        }
	        
	       public int compareTo(HeapItem o1) {
	           return this.val - o1.val;
	        }

		@Override
		public int compareTo(Object o) {
			return this.val - ((HeapItem)o).val;
		}
	    }
	    
	    public int nthUglyNumber(int n) {
	       PriorityQueue<HeapItem> pq =  new PriorityQueue<>();
	       pq.add(new HeapItem(2,1));
	       pq.add(new HeapItem(3,1));
	       pq.add(new HeapItem(5,1));
	       int count = 1;
	       int val = 1;
	       while(count < n) {
	          HeapItem item = pq.poll();
	          val = item.val;
	          System.out.println(val);
	          pq.add(new HeapItem(item.item, item.count+1));
	          count++;
	       }
	       return val;
	    }
	    
	    public static void main(String[] args) {
			NthUglyNumber sol = new  NthUglyNumber();
			System.out.println(sol.nthUglyNumber(10));
		}
}
