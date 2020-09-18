#!/bin/bash
LOG=$1

#for counting geometrical avarage
geomMsumm=1
geomCount=0
geomrf=``mktemp #the while block is insubshel, vars are not updated
cat $LOG | grep "Benchmark\s\+Mode\s\+Cnt\s\+Score\s\+Error\s\+Units" -A 1000 | tail -n +2 | while IFS= read -r line; do
    key=`echo "$line" | sed "s;\s\+;|;g" | cut -d "|" -s  -f 1`
    val=`echo "$line" | sed "s;\s\+;|;g" | cut -d "|" -s  -f 4`
    echo "$key=$val"
    dec_result=`echo "$val" | sed "s/\..*//g"`
    if [ ${dec_result} -gt 0 ] ; then
        geomMsumm=`echo "${geomMsumm}*${val}" | bc -l`
        let geomCount=$geomCount+1
        geom=`echo "e(l($geomMsumm)/$geomCount)" |  bc -l | sed "s/\..*//"`
        echo -n $geom > $geomrf
    fi
done
echo "geom=`cat $geomrf`"
rm $geomrf

