# hadoop-hackfest


Welcome to the Hadoop hackfest.

As an example, we will ask you to run the wordcount program we've created and documented in the wordcount_example directory.

# Hadoop

If you already have hadoop 2.0+ running on your machine, feel free to use that. Otherwise, using CloudEra's Hadoop virtual machine is a good place to start.

**VirtualBox**: https://www.virtualbox.org/wiki/Downloads

**Cloudera Quickstart VM for Hadoop**: http://www.cloudera.com/downloads/quickstart_vms/5-7.html


## To run

After navigating to the `wordcount_example` directory in your command line, compile the java files with:
```sh
$ javac -cp $(hadoop classpath):. *.java
```
This will compile all files and create the `.class` files for Java. We then need to JAR up these files:
```sh
$ jar cf wc.jar *.class
```
This will give us a `wc.jar` file to use with hadoop. We then need to pu the input directory on to the hadoop file system:
```sh
$ hadoop fs -put input
```
After this, we can run our jar file with hadoop:
```sh
$ hadoop jar wc.jar WordCount input output
```
To print out the output, get the Hadoop output and print it out with `cat`:
```sh
$ hadoop fs -get output
$ cat output/part-r-00000
```
