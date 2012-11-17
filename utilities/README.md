##Various Utility Classes

This is just a collection of "quick and dirty" classes I've written to accomplish a specific task.

###IntString.java

This class will take a set of numbers delimited by ", " and turn it into a string.  This is useful when debugging in Eclipse and you are trying to figure out what data is in a stream.  Eclipse will present the data like so:

    [80, 79, 83, 84, 32, 47, 100, 101, ...

That's not very easy to read, so you can copy that stream in Eclipse to your clipboard and in Mac OS X run something like this:

    pbpaste | java IntString

