package com.infinity.bumblebee.util;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.infinity.bumblebee.data.BumbleMatrix;
import com.infinity.bumblebee.data.BumbleMatrixFactory;

public class BumbleMatrixUtilsTest {
	
	private BumbleMatrixUtils cut;

	@Before
	public void setup() {
		this.cut = new BumbleMatrixUtils();
	}

	@Test
	public void ensureOnesColumnAdded() {
		// original is a 2x3
		BumbleMatrix matrix = new BumbleMatrixFactory().createMatrix(new double[][]{{2,2,2}, {3,3,3}});
		BumbleMatrix withOnes = cut.onesColumnAdded(matrix);
		
		// resulting matrix should now be 2x4 with ones in the first column
		assertThat(withOnes.getRowDimension(), is(equalTo(2)));
		assertThat(withOnes.getColumnDimension(), is(equalTo(4)));
		
		assertThat(withOnes.getColumn(0)[0], is(equalTo(1d)));
		assertThat(withOnes.getColumn(0)[1], is(equalTo(1d)));
		
		assertThat(withOnes.getColumn(1)[0], is(equalTo(2d)));
		assertThat(withOnes.getColumn(1)[1], is(equalTo(3d)));
	}
	
	@Test
	public void ensureLog() {
		BumbleMatrix matrix = new BumbleMatrixFactory().createMatrix(new double[][]{{.1, .5, .9}});
		BumbleMatrix log = cut.log(matrix);
		
		assertEquals(log.getEntry(0, 0), -2.3025850929940455, 0.0000001);
		assertEquals(log.getEntry(0, 1), -0.6931471805599453, 0.0000001);
		assertEquals(log.getEntry(0, 2), -0.10536051565782628, 0.0000001);
	}

	@Test
	public void ensureElementwiseSubtract() {
		BumbleMatrix one = new BumbleMatrixFactory().createMatrix(new double[][]{{2, 4}});
		BumbleMatrix two = new BumbleMatrixFactory().createMatrix(new double[][]{{1, 2}});
		BumbleMatrix subtract = cut.elementWiseSubtract(one, two);
		
		assertThat(subtract.getEntry(0, 0), is(equalTo(1d)));
		assertThat(subtract.getEntry(0, 1), is(equalTo(2d)));
	}
	
	@Test
	public void ensureElementwiseAddition() {
		BumbleMatrix one = new BumbleMatrixFactory().createMatrix(new double[][]{{2, 4}});
		BumbleMatrix two = new BumbleMatrixFactory().createMatrix(new double[][]{{1, 2}});
		BumbleMatrix addition = cut.elementWiseAddition(one, two);
		
		assertThat(addition.getEntry(0, 0), is(equalTo(3d)));
		assertThat(addition.getEntry(0, 1), is(equalTo(6d)));
	}
	
	@Test
	public void ensureElementwiseSquare() {
		BumbleMatrix one = new BumbleMatrixFactory().createMatrix(new double[][]{{1,2}, {3,4}});
		BumbleMatrix square = cut.elementWiseSquare(one);
		
		assertThat(square.getEntry(0, 0), is(equalTo(1d)));
		assertThat(square.getEntry(0, 1), is(equalTo(4d)));
		assertThat(square.getEntry(1, 0), is(equalTo(9d)));
		assertThat(square.getEntry(1, 1), is(equalTo(16d)));
	}
	
	@Test
	public void ensureElementWiseSubtractArrays() {
		double[] one = new double[]{1,2};
		double[] two = new double[]{1,1};
		
		BumbleMatrix answer = cut.elementWiseSubtract(one, two);
		
		assertThat(answer.getRowDimension(), is(equalTo(1)));
		assertThat(answer.getColumnDimension(), is(equalTo(2)));
		
		assertThat(answer.getEntry(0, 0), is(equalTo(0d)));
		assertThat(answer.getEntry(0, 1), is(equalTo(1d)));
	}
	
	@Test
	public void ensureRemovingFirstColumn() {
		BumbleMatrix twoColumns = new BumbleMatrixFactory().createMatrix(new double[][]{{1,2}, {3,4}});
		BumbleMatrix oneColumn = cut.removeFirstColumn(twoColumns);
		
		assertThat(oneColumn.getColumnDimension(), is(equalTo(1)));
		assertThat(oneColumn.getRowDimension(), is(equalTo(2)));
		
		assertThat(oneColumn.getEntry(0, 0), is(equalTo(2d)));
		assertThat(oneColumn.getEntry(1, 0), is(equalTo(4d)));
	}
}
