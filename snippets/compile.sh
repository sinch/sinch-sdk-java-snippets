#!/bin/sh

SNIPPETS=`find . -type f -regex '.*Snippet\.java$'`

echo "Compiling snippets:"
for snippet in $SNIPPETS
do
 echo " - Snippet: $snippet"
  mvn compile -Dsnippet="$(dirname $snippet)"
  if [ $? -ne 0 ]
  then
    exit $?
  fi
done
