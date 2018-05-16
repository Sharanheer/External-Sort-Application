#!/bin/bash

#SBATCH --output=linsort2GB.log

start_time=$SECONDS
LC_ALL=C sort /input/data-2GB.in 1>/tmp/2GBsort.log 2>/tmp/2GBsort.error
end_time=$SECONDS

run_time=$end_time-$start_time

echo 'Time taken to sort 2GB using linsort' $run_time 'seconds' > linsort2GB.log

valsort /tmp/2GBsort.log
