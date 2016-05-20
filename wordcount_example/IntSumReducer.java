import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class IntSumReducer
     extends Reducer<Text,IntWritable,Text,IntWritable> {

  // We use an IntWritable to store the result of our summation
  private IntWritable result = new IntWritable();

  /**********************************
  Input: <Key, [list of values]> Hadoop takes the all the key-value
  pairs that were created by the mapper and then creates new pairs,
  this time with no duplication of keys, and the values become the list of
  all values originally assigned to that key. Reducer is run for each key.
  Output a <key, value> pair, where key is every word found
  in the input, including repetition, and value is the summation of the
  the values that was obtained from the mapper
  **********************************/
  public void reduce(Text key, Iterable<IntWritable> values,
                     Context context
                     ) throws IOException, InterruptedException {
    // Naturally, our sum starts at 0
    int sum = 0;
    // We iterate through all our values
    for (IntWritable val : values) {
      // And we simply add them up.
      sum += val.get();
    }
    // We set the result
    result.set(sum);
    // We report our findings back to context (this will be the output for this excercise)
    context.write(key, result);
  }
}
