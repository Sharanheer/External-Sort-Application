#!/bin/bash

#SBATCH --output=linsort20GB.log

start_time=$SECONDS
LC_ALL=C sort /input/data-20GB.in 1>/tmp/20GBsort.log 2>/tmp/20GBsort.error
end_time=$SECONDS

run_time=$end_time-$start_time

echo 'Time taken to sort 20GB using linsort' $run_time 'seconds' > linsort20GB.log

valsort /tmp/20GBsort.log
