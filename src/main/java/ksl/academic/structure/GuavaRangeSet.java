package ksl.academic.structure;

import com.google.common.collect.Range;
import com.google.common.collect.RangeMap;
import com.google.common.collect.RangeSet;
import com.google.common.collect.TreeRangeMap;
import com.google.common.collect.TreeRangeSet;

public class GuavaRangeSet {

	public static void main(String[] args) {
		RangeSet<Integer> rs = TreeRangeSet.create();
		
		rs.add(Range.closed(0, 9));
		
		System.out.println(rs.contains(3));
		System.out.println(rs.rangeContaining(3));
	
		
		
		// RangeMap
		RangeMap<Integer, String> rm = TreeRangeMap.create();
		
		rm.put(Range.closed(1, 3), "Nadia");
		System.out.println(rm);
		
		rm.put(Range.closed(4, 12), "Kent");
		rm.put(Range.closed(0, 9), "Kent");
		
		System.out.println(rm);
		System.out.println(rm.get(2));
		
	}
}
