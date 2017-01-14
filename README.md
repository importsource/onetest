# OneTest
A simple performance test tool.


## Demo

```java
OneTest oneTest=new OneTest("Redis get(1byte)",10000);
		
		Function function=new Function() {
			public void function(Object args) {
				jedis.get(args.toString()+"1");
			}
		};
		After after=new After() {
			public void after(Object args) {
				jedis.close();
			}
		};
		
		oneTest.setFunction(function);
		oneTest.setAfter(after);
		oneTest.start();
```


## Result
```log
Labels:Redis get(1byte)
#Samples:10000
Average:0.4403
medain:0
10% Line:0
30% Line:0
50% Line:0
90% Line:1
95% Line:1
99% Line:1
Min:0
Max:306
Total:4403
Tps:2271.1787
Error(%):0.0%
Success(%):100.0%
```



## Feature

If you need to see more details,you can use method `setDebug(true)` ,as below:

```java
oneTest.setDebug(false);
```
