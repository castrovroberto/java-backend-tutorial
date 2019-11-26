SRCS=./backend/app/*.java ./backend/configservice/*.java ./backend/utils/*.java
PKGS=./backend/app/*.class ./backend/configservice/*.class ./backend/utils/*.class

all:
	javac ${SRCS}
	jar -cfv ../lib/engine.jar ${PKGS}

clean: 
	find . -name "*.class" -print -delete
