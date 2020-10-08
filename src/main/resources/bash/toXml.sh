#!/bin/bash

# to generate jenkins voew and job tempaltes

VIEW_TEMPALTE="    <hudson.plugins.report.genericchart.GenericChartColumn plugin=\"jenkins-report-generic-chart-column@0.1-SNAPSHOT\">
      <fileNameGlob>%filename%</fileNameGlob>
      <key>%key%</key>
      <limit>%limit%</limit>
      <columnCaption>%key%</columnCaption>
      <chartColor>%color%</chartColor>
      <resultsBlackList/>
      <resultsWhiteList/>
      <rangeAroundWlist>0</rangeAroundWlist>
    </hudson.plugins.report.genericchart.GenericChartColumn>"
JOB_TEMPALTE_HEADER="    <hudson.plugins.report.genericchart.GenericChartPublisher plugin=\"jenkins-report-generic-chart-column@0.1-SNAPSHOT\">
      <charts>"
JOB_TEMPALTE_ITEM="        <hudson.plugins.report.genericchart.ChartModel>
          <title>%key%</title>
          <fileNameGlob>%filename%</fileNameGlob>
          <key>%key%</key>
          <limit>%limit%</limit>
          <resultsBlackList></resultsBlackList>
          <chartColor>%color%</chartColor>
        </hudson.plugins.report.genericchart.ChartModel>"
JOB_TEMPALTE_CLOSER="      </charts>
    </hudson.plugins.report.genericchart.GenericChartPublisher>"

alias rgb2hex='printf "#%02x%02x%02x\n"'

for file in $@ ; do
  filename=`basename $file`
  limit=15
  viewFile=$file.view.xml
  jobFile=$file.job.xml
  echo -n "" > $viewFile
  echo "$JOB_TEMPALTE_HEADER" > $jobFile
  items=`cat $file | wc -l`
  let step=\(255*3\)/$items
  r=0
  g=0
  b=0
  lineNumber=0
  cat $file | while IFS= read -r line; do
    let lineNumber=lineNumber+1
    if [ $(($lineNumber % 3)) -eq 0 ] ; then 
      let r=r+$step
    fi
    if [ $(($lineNumber % 3)) -eq 1 ] ; then 
      let g=g+$step
    fi
    if [ $(($lineNumber % 3)) -eq 2 ] ; then 
      let b=b+$step
    fi
    echo "$r $g $b"
    color="`rgb2hex $r $g $b`"
    key=`echo "$line" | sed "s;=.*;;g"`
    echo "$VIEW_TEMPALTE" | sed "s;%filename%;$filename;g" | sed "s;%key%;$key;g" | sed "s;%limit%;$limit;g" | sed "s;%color%;$color;g" >> $viewFile
    echo "$JOB_TEMPALTE_ITEM" | sed "s;%filename%;$filename;g" | sed "s;%key%;$key;g" | sed "s;%limit%;$limit;g" | sed "s;%color%;$color;g" >> $jobFile
  done
  echo "$JOB_TEMPALTE_CLOSER" >> $jobFile
done


