#!/bin/bash
#SBATCH --output=mysort20GB.log

javac MySort.java
javac Merger.java
javac MQuickSort.java
javac HeapData.java
javac MyPriorityQueue.java

start_time=$SECONDS
java -Xms5g -Xmx6g MySort 20 >mysort20GB.log
end_time=$SECONDS

running_time=$end_time-$start_time

echo 'Time taken to sort 20 GB: ' $running_time 'seconds' >mysort20GB.log

valsort /tmp/2GBFinalOutput.log
