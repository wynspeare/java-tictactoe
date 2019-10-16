# TicTacToe  ![build--badge](https://travis-ci.com/wynspeare/java-tictactoe.svg?branch=master)


## Getting Started

#### Requirements

* [JDK SE 12](https://www.oracle.com/technetwork/java/javase/downloads/index.html)
* [Gradle](https://gradle.org/install/)

#### 1. Clone the project

```
git clone git@github.com:wynspeare/java-tictactoe.git
```

#### 2. Build the project and start the application

First navigate into the root directory of the project:
```
cd java-tictactoe
```

Next build this TicTacToe project by entering the following command.  This script builds the project using the server from [this repo](https://github.com/wynspeare/http-server). 
```
./start-ttt.sh
```
#### 3. Play TicTacToe!

Open a browser and navigate to the following address to play the game!  Note this game is only playable using [Firefox](https://www.mozilla.org/en-US/firefox/new/) or [Chrome](https://support.google.com/chrome/answer/95346?co=GENIE.Platform%3DDesktop&hl=en).

```
http://localhost:5000/ttt
```

#### 4. Close the application
From the root directory of the project enter the following command:
```
lsof -i:5000 
```
Take note of the `PID` number. This is the `process id` for the application. Next enter the following command using the `PID`:
```
kill <PID>
```

For example `lsof -i:5000` will return something similar to this:
```
COMMAND  PID      USER   FD   TYPE             DEVICE SIZE/OFF NODE NAME
java    1234  username   10u  IPv6 0x2caf93869bf93d53      0t0  TCP *:commplex-main (LISTEN)
```

So enter `kill 1234` to end the game, stop the server and close the port.

## Running Tests
```
./gradlew test
```

## Built With

* [Java 12](https://www.oracle.com/technetwork/java/javase/downloads/index.html)
* [Gradle](https://gradle.org//)
* [JUnit 4](https://junit.org/junit4/)
* [Travis CI](https://travis-ci.org/)
* [JavaScript](https://www.javascript.com/)
* [jQuery/AJAX](https://api.jquery.com/jquery.ajax/)
