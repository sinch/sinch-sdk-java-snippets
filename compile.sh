#!/bin/sh

(cd snippets && mvn clean spotless:apply) || exit 1
(cd snippets && ./compile.sh)  ||exit 1

