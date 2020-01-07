import java.util.ArrayList;
import java.util.List;

public class InsertInterval_57 {
	
	static class Interval {
		      int start;
		      int end;
		      Interval() { start = 0; end = 0; }
		     public Interval(int s, int e) { start = s; end = e; }
			@Override
			public String toString() {
				return "(" + start + ", " + end + ")";
			}
		 }
	
	 public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
	        List<Interval> res = new ArrayList<>();
	        //Null/Empty check
	        if(intervals == null || intervals.isEmpty()) {
	            res.add(newInterval);
	            return res;
	        }
	        
	        //if new interval is before first interval
	        if(newInterval.end < intervals.get(0).start) {
	            res.add(newInterval);
	            res.addAll(intervals);
	            return res;
	        }
	        
	        //new interval belongs to end 
	        if(newInterval.start > intervals.get(intervals.size()-1).end) {
	            res.addAll(intervals);
	            res.add(newInterval);
	            return res;
	        }
	        //if new interval contains all intervals
	        if(newInterval.start < intervals.get(0).start && newInterval.end > intervals.get(intervals.size()-1).end) {
	            res.add(newInterval);
	            return res;
	        }
	        int start = -1, end = intervals.size();
	        for(int i=0; i<intervals.size(); i++) {
	            Interval current  = intervals.get(i);
	            if(newInterval.start >= current.start && newInterval.end <= current.end) {
	                continue;
	            }
	            if(newInterval.start <= current.end ) {
	                start = i;
	            }
	            if(newInterval.end <= current.end) {
	                end =i;
	            }
	        }
	        
	        if(start < 0 && end == intervals.size()) {
	            return intervals;
	        }
	        
	        for(int i=0; i<start; i++) {
	            res.add(intervals.get(i));
	        }
	        
	        Interval mergedInterval = getMergedInterval(intervals, start, end, newInterval);
	        res.add(mergedInterval);
	        
	        for(int i=end+1; i<intervals.size(); i++) {
	            res.add(intervals.get(i));
	        }
	        return res;
	    }
	    
	    private Interval getMergedInterval(List<Interval> intervals, int start, int end, Interval newInterval) {
	        Interval mergedInterval = null;
	        if(start >=0 && end < intervals.size()) {
	           mergedInterval =  new Interval(intervals.get(start).start, intervals.get(end).end);
	        }
	        
	        if(start >= 0) {
	            mergedInterval =  new Interval(intervals.get(start).start, newInterval.end);
	        }
	        
	        if(end < intervals.size()) {
	            mergedInterval =  new Interval(newInterval.start, intervals.get(end).end);
	        }
	        return mergedInterval;
	    }

	public static void main(String[] args) {
		InsertInterval_57 obj = new InsertInterval_57();
		List<Interval> intervals = new ArrayList<>();
		intervals.add(new Interval(1,5));
		intervals.add(new Interval(6,8));
		System.out.println(obj.insert(intervals, new Interval(5,6)));
		

	}

}
