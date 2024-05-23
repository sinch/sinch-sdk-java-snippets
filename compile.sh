#!/bin/sh

(cd snippets && mvn clean spotless:apply compile)

