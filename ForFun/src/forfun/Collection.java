package forfun;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Collection {
	private Map<Integer, Integer> hashMap;
	private List<Integer> arrayList;

	public Collection() {
		this.hashMap = new HashMap<Integer, Integer>();
		this.arrayList = new ArrayList<Integer>();
	}

	public void add(int val) {
		if (!contains(val)) {
			arrayList.add(val);
			int idx = arrayList.size() - 1;
			hashMap.put(val, idx);
		} else {
			System.out.println("element : " + val + " already been added");
			return;
		}
	}

	public void remove(int val) {
		if (!contains(val)) {
			System.out
					.println("element : " + val + " is not in the Collection");
			return;
		}
		int idx = hashMap.get(val);
		int value = arrayList.get(arrayList.size() - 1);
		arrayList.set(idx, value);
		arrayList.remove(arrayList.size() - 1);
		hashMap.put(value, idx);
		hashMap.remove(val);
	}

	public int getRandomElement() {
		Random ran = new Random();
		int idx = ran.nextInt(arrayList.size());
		return arrayList.get(idx);
	}

	public boolean contains(int val) {
		return hashMap.containsKey(val);
	}

	public void printAllEle() {
		if (arrayList.isEmpty()) {
			System.out.println("Collection is empty");
			return;
		} else {
			System.out.println(arrayList);
		}
	}

	public static void main(String[] args) {
		Collection test = new Collection();
		for (int i = 0; i < 20; i++) {
			Random ran = new Random();
			int value = ran.nextInt(15);
			test.add(value);
			// test.printAllEle();
		}
		test.printAllEle();

		for (int i = 0; i < 20; i++) {
			Random ran = new Random();
			int value = ran.nextInt(15);
			System.out.println("Removing : " + value);
			test.remove(value);
			test.printAllEle();
		}
	}
}
