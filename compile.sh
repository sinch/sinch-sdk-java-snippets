#!/bin/sh

(cd snippets && mvn clean spotless:apply)
(cd snippets && ./compile.sh)

