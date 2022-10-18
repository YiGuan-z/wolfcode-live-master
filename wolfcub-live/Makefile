build:
	mvnd package -DskipTests -Dos.detected.classifier=osx-x86_64 -X
protobuf:
	mvnd protobuf:compile -f pom.xml -Dos.detected.classifier=osx-x86_64 -X
tomcat:
	mvnd tomcat7:run -f pom.xml -Dos.detected.classifier=osx-x86_64 -X
clean:
	mvnd clean -Dos.detected.classifier=osx-x86_64 -X
tomcat@run:
	mvnd clean -Dos.detected.classifier=osx-x86_64 -X|mvnd tomcat7:run -f pom.xml -Dos.detected.classifier=osx-x86_64 -X