import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class TokenizerMapper
     extends Mapper<Object, Text, Text, IntWritable>{

  /** We can declare an 'IntWritable', basically an Int, but wrapped
      up in Hadoop so we can use it as a key or value, with other benefits,
      and name it one, because it will always have the value of 1. **/
  private final static IntWritable one = new IntWritable(1);
  /** We also declare a Text, the hadoop string, that we would use
      for our words. **/
  private Text word = new Text();

  /**********************************
  Input: <Key, value> Pair defined by Configuration, at this point
  it will be the file offset for the data as the key, and the actual
  string as the value, the key is not useful for us. The context allows
  the mapper and reducer to interact with the rest of the system.
  Output a number of <key, value> pairs, where key is every word found
  in the input, including repetition, and value is simply the number 1
  **********************************/
  public void map(Object key, Text value, Context context
                  ) throws IOException, InterruptedException {
    /** StringTokenizer splits up a string by spaces and makes an Iterable
        collection of strings.
        e.g. "Hello World" would become ["Hello", "World"] if represented as an array **/
    StringTokenizer itr = new StringTokenizer(value.toString());

    // We iterate through the list of 'tokens' or words
    while (itr.hasMoreTokens()) {
      // Using the word variable we defined, we set it to the current token
      word.set(itr.nextToken());
      /** We write the word and the number 1 to the context, sending off a
          <key, value> pair, where the key is Text and the valie is IntWritable.
         In this case, ["Hello", "World"] would write <"Hello", 1> and <"World", 1> **/
      context.write(word, one);
    }
  }
}
