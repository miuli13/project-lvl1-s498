run: ; java -jar ./target/casino-1.0-SNAPSHOT-jar-with-dependencies.jar
clean: ; rm -rf ./target
build-run: build run
.DEFAULT_GOAL := build-run
build: ; ./mvnw verify
update: ; ./mvnw versions:display-plugin-updates ; ./mvnw versions:update-properties