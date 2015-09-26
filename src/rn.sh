#!/bin/sh

javac -cp /usr/share/java/lib/junit-4.12.jar Roman.java RomanTest.java
java -ea -cp .:/usr/share/java/lib/*  org.junit.runner.JUnitCore  RomanTest

# git add .
# git commit -m 
# git push origin dev
# Merge with master:
# git fetch origin master
# git merge master
# git push origin dev:master

