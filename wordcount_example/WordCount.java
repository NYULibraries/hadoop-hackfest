import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class WordCount {

  public static void main(String[] args) throws Exception {
    /**  We get a configuration for a job.
         This is the default configuration defined by hadoop. */
    Configuration conf = new Configuration();
    Job job = Job.getInstance(conf, "word count");

    // We set this class as the main jar
    job.setJarByClass(WordCount.class);

    // We set our Mapper and Reducer classes
    job.setMapperClass(TokenizerMapper.class);
    job.setReducerClass(IntSumReducer.class);

    // We can define our output key and value classes (from the reducer)
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(IntWritable.class);

    // We take in data from command line here
    FileInputFormat.addInputPath(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, new Path(args[1]));

    // Exits with 0 if job completes
    System.exit(job.waitForCompletion(true) ? 0 : 1);
  }
}
