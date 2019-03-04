package com.revature.projectOne.GenderStats;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import com.revature.projectOne.map.Problem1Mapper;
import com.revature.projectOne.reduce.Problem1Reducer;

// Identify the countries where % of female graduates is less than 30%.

public class Problem1 {

	public static void main(String[] args) throws Exception {

		//end job if input and output files !=2
		if (args.length != 2) {
			System.out.print("Usage: Problem1.class <input dir> <output dir>\n");
			System.exit(-1);
		}
		
		// create job
		Job job = new Job();
		job.setJobName("Problem 1");
		
		//set jar entry point
		job.setJarByClass(Problem1.class);

		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		//set classes for problem1 job
		job.setMapperClass(Problem1Mapper.class);
		job.setReducerClass(Problem1Reducer.class);

		//set class types
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(DoubleWritable.class);

		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}