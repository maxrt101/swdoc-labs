# Sw Doc Lab 4

## How to compile
```shell
$ mvn clean scala:compile package 
```

## How to run
Get help:  
```shell
$ java -cp target/swdoclab4-1.0-jar-with-dependencies.jar com.maxrt.App
```

Select `console` output strategy:  
```shell
$ java -cp target/swdoclab4-1.0-jar-with-dependencies.jar com.maxrt.App -c
```

Select `redis` output strategy:  
```shell
$ java -cp target/swdoclab4-1.0-jar-with-dependencies.jar com.maxrt.App -r
```

Select strategy from config file:  
```shell
$ java -cp target/swdoclab4-1.0-jar-with-dependencies.jar com.maxrt.App --config test.properties
```

Note: before stating make sure you have redis up and running.   


## How to retrieve data from redis
```shell
$ redis-cli
127.0.0.1:6379> LLEN swdoc4
(integer) 300
127.0.0.1:6379> LRANGE swdoc4 0 300
```
Note: replace `300` with the actual `LLEN` output