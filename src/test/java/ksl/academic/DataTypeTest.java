package ksl.academic;

import org.junit.Test;

import mockit.Mock;
import mockit.MockUp;

public class DataTypeTest {
	
	
	@Test
	public void test() {
		
		new MockUp<ClassToTest>() {
			
			@Mock
			public boolean methodToMock() {
				System.out.println("hi");
				return false;
			}
		};
		
		ClassToTest a = new ClassToTest();
		a.methodToTest();
		
		
		
		
	}
	
}

class ClassToTest {
	public void methodToTest() {
		System.out.println(methodToMock());
	}
	
	private boolean methodToMock() {
		return true;
	}
}
