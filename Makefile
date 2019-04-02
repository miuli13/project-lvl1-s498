compile: clean ; mkdir -p ./target/classes ; javac -d ./target/classes ./src/main/java/games/Slot.java
run: ; java -jar ./target/casino.jar
clean: ; rm -rf ./target
build-run: build run
.DEFAULT_GOAL := build-run
build: compile ; jar cfe ./target/casino.jar games.Slot -C ./target/classes .