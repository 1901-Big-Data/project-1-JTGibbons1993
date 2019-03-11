package com.revature.projectOne.reduce;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
 

public class Problem1Reducer extends Reducer<Text, DoubleWritable, Text, DoubleWritable> {
	
	NumberFormat formatter = new DecimalFormat("#0.00");
	
	@Override
	public void reduce(Text key, Iterable<DoubleWritable> mostCurrentYear, Context context)
			throws IOException, InterruptedException {
		 
		double currentYearPercent = 0;	    
		
		for(DoubleWritable value : mostCurrentYear) {
			currentYearPercent = value.get();
		}
				
		currentYearPercent = Double.parseDouble(formatter.format(currentYearPercent));
		
		//Output countries <30% 
		if(currentYearPercent < 30 && currentYearPercent != 0){
			context.write(key, new DoubleWritable(currentYearPercent));
		}	

	}
}