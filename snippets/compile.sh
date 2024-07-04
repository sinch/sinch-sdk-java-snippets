#!/bin/sh

SNIPPETS=`find . -type f -regex '.*Snippet\.java$'`

echo "Compiling snippets:"
for snippet in $SNIPPETS
do
 echo " - Snippet: $snippet"
  mvn compile -Dsnippet="$(dirname $snippet)" || exit 1
done
