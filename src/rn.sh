#!/bin/sh

javac -cp /usr/share/java/lib/junit-4.12.jar Roman.java RomanTest.java
java -ea -cp .:/usr/share/java/lib/*  org.junit.runner.JUnitCore  RomanTest
