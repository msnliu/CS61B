Map<String, integer> m = new TreeMap<>();
String[] text = {"sumomo", "mo", "momo", "mo", "momo", "no", "uchi"};
for (String s : text) {
	int currentCount = m.getOrDefault(s, 0);
	m.put(s, currentCount + 1);
}