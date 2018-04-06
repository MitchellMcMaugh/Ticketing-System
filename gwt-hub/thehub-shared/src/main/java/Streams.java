import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Streams {

	
	public static class Thing{
		private String t1;
		private String t2;
		public String getT1() {
			return t1;
		}
		public String getT2() {
			return t2;
		}
		public Thing(String t1, String t2) {
			super();
			this.t1 = t1;
			this.t2 = t2;
		}
		@Override
		public String toString() {
			return String.format("Thing [t1=%s, t2=%s]", t1, t2);
		}
		
	}
	
	public static void main(String[] args) {

		List<Thing> things = Arrays.asList(
				new Thing("one", "one"),
				new Thing("one", "two"),
				new Thing("one", "three"),
				new Thing("two", "one"),
				new Thing("two", "two"),
				new Thing("two", "three")
				);
		//java.lang.IllegalStateException: Duplicate key Thing [t1=one, t2=one]
//		Map<String, Thing> toMap = things.stream().collect(Collectors.toMap(Thing::getT1, t -> t));
//		System.out.println("toMap " + toMap);
		
		
		Map<String, List<Thing>> grouped = things.stream().collect(Collectors.groupingBy(Thing::getT1));
		
		System.out.println("grouped " + grouped);
		
	}

}
