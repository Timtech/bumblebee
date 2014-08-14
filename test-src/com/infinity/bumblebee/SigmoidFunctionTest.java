package com.infinity.bumblebee;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.apache.commons.math3.linear.RealMatrix;
import org.junit.Before;
import org.junit.Test;

import com.infinity.bumblebee.util.DataReader;

public class SigmoidFunctionTest {

	private SigmoidFunction cut;
	
	@Before
	public void setUp() throws Exception {
		cut = new SigmoidFunction();
	}

	@Test
	public void ensureCalculation() {
		DataReader reader = new DataReader();
		RealMatrix theta1 = reader.getMatrixFromFile("./test-data/Theta1.csv");
		RealMatrix X = reader.getMatrixFromFile("./test-data/X.csv");
		
		RealMatrix answer = cut.calculate(X.multiply(theta1.transpose()));
		
		System.out.println(answer.getEntry(1, 0));
		System.out.println(0.008057821627182112);
		
		assertThat(answer.getRowDimension(), is(equalTo(5000)));
		assertThat(answer.getColumnDimension(), is(equalTo(25)));
		
		assertEquals(0.05036186851871643, answer.getEntry(0, 0), 0.0000001);
		assertEquals(0.008057821627182112, answer.getEntry(1, 0), 0.0000001);
	}

}
