import java.util.*;

public class ReConstructItinerary_332 {

	public List<String> findItinerary(String[][] tickets) {
		List<String> res = new ArrayList<>();
		if (tickets == null || tickets.length == 0) {
			return res;
		}
		Map<String, PriorityQueue<String>> map = new HashMap<>();
		for (String[] ticket : tickets) {
			if (map.get(ticket[0]) == null) {
				map.put(ticket[0], new PriorityQueue<>());
			}
			map.get(ticket[0]).add(ticket[1]);
		}

		String currCity = "JFK";
		Stack<String> stack = new Stack<>();
		while (!map.isEmpty()) {
			while (map.get(currCity) == null) {
				stack.push(currCity);
				currCity = res.remove(res.size() - 1);
			}
			res.add(currCity);
			PriorityQueue<String> destinations = map.get(currCity);
			String targetCity = destinations.poll();
			if (destinations.isEmpty()) {
				map.remove(currCity);
			}
			currCity = targetCity;
		}

		res.add(currCity);
		while (!stack.isEmpty()) {
			res.add(stack.pop());
		}

		return res;
	}

	public static void main(String[] args) {
		String[][] tickets = { { "MUC", "LHR" }, { "JFK", "MUC" }, { "SFO", "SJC" }, { "LHR", "SFO" } };
		ReConstructItinerary_332 obj = new ReConstructItinerary_332();
		System.out.println(obj.findItinerary(tickets));

	}

}
