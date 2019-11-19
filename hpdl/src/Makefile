SRCS=./backend/app/*.java
PKGS=./backend/app/*.class

all:
	javac ${SRCS}
	jar -cfv ../lib/engine.jar ${PKGS}

clean: 
	find . -name "*.class" -print -delete
