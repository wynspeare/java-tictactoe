#!/bin/bash
set -ex
./gradlew build
./gradlew jar
java -cp tictactoe-1.0-SNAPSHOT.jar:build/libs/*:. application.TTT & echo $!
