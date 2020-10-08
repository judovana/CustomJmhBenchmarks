#!/bin/bash

set -x
set -e
set -o pipefail

## resolve folder of this script, following all symlinks,
## http://stackoverflow.com/questions/59895/can-a-bash-script-tell-what-directory-its-stored-in
SCRIPT_SOURCE="${BASH_SOURCE[0]}"
while [ -h "$SCRIPT_SOURCE" ]; do # resolve $SOURCE until the file is no longer a symlink
  SCRIPT_DIR="$( cd -P "$( dirname "$SCRIPT_SOURCE" )" && pwd )"
  SCRIPT_SOURCE="$(readlink "$SCRIPT_SOURCE")"
  # if $SOURCE was a relative symlink, we need to resolve it relative to the path where the symlink file was located
  [[ $SCRIPT_SOURCE != /* ]] && SCRIPT_SOURCE="$SCRIPT_DIR/$SCRIPT_SOURCE"
done
readonly SCRIPT_DIR="$( cd -P "$( dirname "$SCRIPT_SOURCE" )" && pwd )"

#to allow differnt buildjdk and run jdk
# build and run with same, set java home
# build by system and run by custom set jre home
# build by custom set java home and run by different set also jre home
if [ "x$JRE_HOME" == "x" ] ; then 
  if [ "x$JAVA_HOME" == "x" ] ; then 
    JAVA=java
  else
    JAVA=$JAVA_HOME/bin/java
  fi

else
  JAVA=$JRE_HOME/bin/java
fi

if [ ! -e $SCRIPT_DIR/target/benchmarks.jar ] ; then
  pushd $SCRIPT_DIR
    # maven usually follows java home
    mvn clean install
  popd
fi

for preffix in Throughput AverageTime ; do
  if [ "x$preffix" = "xAverageTime" ] ;  then
   unit="-tu us"
  else
   unit=
  fi
  OUT=CustomJmhBenchmarks-$preffix.log
  $JAVA -jar $SCRIPT_DIR/target/benchmarks.jar -bm $preffix $unit | tee $OUT
  sh $SCRIPT_DIR/src/main/resources/bash/toProperties.sh $OUT "$preffix." | tee CustomJmhBenchmarks-$preffix.properties
done

