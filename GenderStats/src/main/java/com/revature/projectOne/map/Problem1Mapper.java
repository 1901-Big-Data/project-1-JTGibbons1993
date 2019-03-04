package com.revature.projectOne.map;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class Problem1Mapper extends Mapper<LongWritable, Text, Text, DoubleWritable> {
	
	//Education - upper secondary
	public static final String EducationCode = "SE.SEC.CUAT.UP.FE.ZS";


	@Override
	public void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {

		//header row filter
		if(!value.toString().contains("Country Name")) {

			String[] row = value.toString().trim().split("\",\"");
			String outputKey = row[0]; //Country Name
			String indicatorCode = row[3];
			String lastColumn = row[60]; //Last Year Column = 2016
			
			Double lastColumnDouble = Double.parseDouble(
					lastColumn.replace("\",", "0")); //(last column, 0)
			
			lastColumn = lastColumnDouble.toString();
		
			double yearPercent = new Double(0);
			
			if(indicatorCode.equals(EducationCode)) 
			{
				//4 = first year, 6 = last year (columns)
				for (int i = 4; i < 60; i++){
					if(!row[i].isEmpty())
					{
						yearPercent = new Double(row[i]);

					}
					//remove trailing ",
					if(lastColumnDouble > 0 && lastColumn.length() >= 2){
						yearPercent = new Double(
								lastColumn.substring(0, lastColumn.length() - 2));
					}
					//remove trailing ",
					else if(lastColumnDouble > 0) {
						yearPercent = new Double(lastColumn.substring(0, 2));
					}
				}
				context.write(new Text(outputKey.substring(1)), 
						      new DoubleWritable(yearPercent));
			}
		}
	}
}