#!/bin/bash
#SBATCH --output=mysort2GB.log

javac MySort.java
javac Merger.java
javac MQuickSort.java
javac HeapData.java
javac MyPriorityQueue.java

start_time=$SECONDS
java -Xms4g -Xmx6g MySort 2 >mysort2GB.log
end_time=$SECONDS

running_time=$end_time-$start_time

echo 'time taken to sort 2 GB data on myjava: ' $running_time 'secs' >mysort2GB.log

valsort /tmp/2GBFinalOutput.log

