import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class OptimalAccountBalancing_465 {

	public int minTransfers(int[][] transactions) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int[] txn : transactions) {
			int amount = txn[2];
			add(map, txn[0], amount);
			add(map, txn[1], -amount);
		}

		PriorityQueue<Integer> pos = new PriorityQueue<>((o1, o2) -> o2 - o1);
		PriorityQueue<Integer> neg = new PriorityQueue<>();
		for (int val : map.values()) {
			if (val > 0) {
				pos.add(val);
			} else {
				neg.add(val);
			}
		}

		int count = 0;
		while (!pos.isEmpty() && !neg.isEmpty()) {
			int credit = pos.poll();
			int debt = Math.abs(neg.poll());
			if (credit > debt) {
				pos.add(credit - debt);
			} else if (credit < debt) {
				neg.add(credit - debt);
			}
			count++;
		}

		return count;
	}

	private void add(Map<Integer, Integer> map, int person, int amount) {
		int existingAmount = map.get(person) == null ? 0 : map.get(person);
		int newAmount = existingAmount + amount;
		if (newAmount == 0) {
			map.remove(person);
		} else {
			map.put(person, newAmount);
		}
	}
	
	
	public int minTransfersDFS(int[][] transactions) {
	      Map<Integer, Integer> map = new HashMap<>();
			for (int[] txn : transactions) {
				int amount = txn[2];
				add(map, txn[0], amount);
				add(map, txn[1], -amount);
			}

			Map<Integer, Integer> pos = new HashMap<>();
			Map<Integer,Integer> neg = new HashMap<>();
			for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
				if (entry.getValue() > 0) {
					pos.put(entry.getKey(), entry.getValue());
				} else {
					neg.put(entry.getKey(), Math.abs(entry.getValue()));
				}
			}
			return dfs(pos, neg);
		}
		
		
		private int dfs(Map<Integer, Integer> pos, Map<Integer, Integer> neg) {
		    if(pos.isEmpty() || neg.isEmpty()) {
		        return 0;
		    }
		    int min = Integer.MAX_VALUE;
		    for(Map.Entry<Integer, Integer> posEntry : pos.entrySet()) {
		        for(Map.Entry<Integer, Integer> negEntry : neg.entrySet()) {
		            int posVal = posEntry.getValue();
		            int negVal = negEntry.getValue(); 
		            int count = 0;
		            if(posVal > negVal) {
		                neg.remove(negEntry.getKey());
		                pos.put(posEntry.getKey(), posVal - negVal);
		            }else if( negVal > posVal) {
		                pos.remove(posEntry.getKey());
		                neg.put(negEntry.getKey(), Math.abs(negVal - posVal));
		            }else {
		                pos.remove(posEntry.getKey());
		                neg.remove(negEntry.getKey());
		            }
		           count =  1 + dfs(pos, neg);
		           neg.put(negEntry.getKey(), negVal);
		           pos.put(posEntry.getKey(), posVal);
		           min = Math.min(count, min); 
		        }
		    }
		    return min;
		}


	public static void main(String[] args) {
		int[][] transactions = { { 0, 1, 10 }, { 2, 0, 5 } };
		OptimalAccountBalancing_465 obj = new OptimalAccountBalancing_465();
		System.out.println(obj.minTransfersDFS(transactions));

	}

}
