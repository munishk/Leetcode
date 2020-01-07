import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Vector2D implements Iterator<Integer>{
	
	List<List<Integer>> vec2dList;
    int currList = 0;
    int currElement = 0;

    public Vector2D(List<List<Integer>> vec2d) {
        if(vec2d != null) {
            vec2dList = vec2d;
        }
    }

    @Override
    public Integer next() {
        Integer res = null;
        if(hasNext()) {
            res = vec2dList.get(currList).get(currElement++);
            if(currElement == vec2dList.get(currList).size()) {
                while(++currList < vec2dList.size() && vec2dList.get(currList).isEmpty()) {
                }
                currElement = 0;
            }
        }
        return res;
    }

    @Override
    public boolean hasNext() {
        return vec2dList!= null && vec2dList.size() > currList && vec2dList.get(currList).size() > currElement;
    }

	public static void main(String[] args) {
		List<List<Integer>> list2d = new ArrayList<>();
		list2d.add(Arrays.asList(new Integer[] {1,2}));
		list2d.add(Arrays.asList(new Integer[] {3}));
		list2d.add(Arrays.asList(new Integer[] {4,5,6}));
		
		Vector2D vector = new Vector2D(list2d);
		Iterator<Integer> iter = vector;
		while(iter.hasNext()) {
			System.out.println(iter.next());
		}

	}

}
