package com.revature.projectOne.GenderStats;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.apache.hadoop.mrunit.mapreduce.MapReduceDriver;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.junit.Before;
import org.junit.Test;

import com.revature.projectOne.map.Problem1Mapper;
import com.revature.projectOne.reduce.Problem1Reducer;



public class Problem1Test {
	
	public static final String Country = "Ghana";
	public static final Double Double = 14.68;
	

	/*
	 * Declare harnesses
	 */
	private MapDriver<LongWritable, Text, Text, DoubleWritable> mapDriver;
	private ReduceDriver<Text, DoubleWritable, Text, DoubleWritable> reduceDriver;
	private MapReduceDriver<LongWritable, Text, Text, DoubleWritable, Text, DoubleWritable> mapReduceDriver;

	/*
	 * Set up the test. This method will be called before every test.
	 */
	@Before
	public void setUp() {
		
		
		/*
		 * Set up the mapper test harness.
		 */
		Problem1Mapper mapper = new Problem1Mapper();
		mapDriver = new MapDriver<LongWritable, Text, Text, DoubleWritable>();
		mapDriver.setMapper(mapper);

		/*
		 * Set up the reducer test harness.
		 */
		Problem1Reducer reducer = new Problem1Reducer();
		reduceDriver = new ReduceDriver<Text, DoubleWritable, Text, DoubleWritable>();
		reduceDriver.setReducer(reducer);

		/*
		 * Set up the mapper/reducer test harness.
		 */
		mapReduceDriver = new MapReduceDriver<LongWritable, Text, Text, DoubleWritable, Text, DoubleWritable>();
		mapReduceDriver.setMapper(mapper);
		mapReduceDriver.setReducer(reducer);
	}

	/*
	 * Test the mapper.
	 */
	@Test
	public void testMapper() {

		mapDriver.withOutput(new Text(Country), new DoubleWritable(Double));

		mapDriver.runTest();
	}

	/*
	 * Test the reducer.
	 */
	@Test
	public void testReducer() {

		
		mapDriver.withOutput(new Text(Country), new DoubleWritable(Double));

		mapDriver.runTest();
	}

	/*
	 * Test the mapper and reducer working together.
	 */
	@Test
	public void testMapReduce() {


		mapDriver.withOutput(new Text(Country), new DoubleWritable(Double));

		mapDriver.runTest();
	}
}