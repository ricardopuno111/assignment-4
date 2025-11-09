# Java compiler and flags
JAVAC = javac
JAVA = java
MAIN = Server
SOURCES = *.java
CLASSES = *.class

# Default target: compile everything
all: compile

compile:
	$(JAVAC) $(SOURCES)

# Run the main program
run: compile
	$(JAVA) $(MAIN)

# Clean up compiled .class files
clean:
	rm -f $(CLASSES)