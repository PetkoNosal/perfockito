# Perfockito
Performance testing framework

#Work in progress

Kinda working perfockito engine, you can build and use as maven dependency or simply provide jar to your class path and you are
ready to go.

If you want to perform some meassurement of execution time, just annotate your method with @PerfockitoMarker.
Then in your main or wherewer you wish use PerfockitoEngine interface to get processor, then simply register your class
and add instance of that class if your annotated methods aren't static and call run and print...

see test if you want...

Annotation features
* if name of the method is uninformative to you, name it
* If you want compare some methods, group them
* If one call is not enough to determine execution time, repeat it

This perfockito aproach is most likely best idea so far so I will try to finish it to some extend and maybe, maybe 
I give another try to get down to compile level and use custom annotation processor at compile time level instead
of runtime (this isn't processor whatsoever...)
