# ToyC Compiler

Please ensure your pull request adheres to the following guidelines:

Made by Mark Johnston and George Constantine

## Program functionality
Program is completely functional to project specs.
Extra credit functions have also been implemented

## Getting started
To build this program run these commands at the root of the program(assuming you have ANT installed)
```
ant clean
ant compile
ant -Dflags="-verbose" -Dsource="some_dir/[fileName].tc" test
```

## Running the .j files
Move the compiled .j file to the directory where jasmin is stored, and run the following commands to execute the .j file. [fileName] is just the name of the original compiled .tc file
```
java -jar jasmin.jar [fileName].j
java [filename]
```

## Program operands
```
Usage: java [classpath] parser.tc [options] [toyc_source_file]
where options include:
-help display this usage message\n" +
-debug <level> display messages that aid in tracing the compilation process. If level is:
    0 - all messages
    1 - scanner, parser, and compiler messages
    2 - parser and compiler messages only
    3 - compiler messages only(unused)
-verbose display all information
Note: path to toyc_source_file is defaultly set to [project]/tests. 
Add full directory for external file access.
```
