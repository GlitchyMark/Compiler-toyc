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
ant -Dflags="-verbose" -Dsource="some_dir/tests.tc" test
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