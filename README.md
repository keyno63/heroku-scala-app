# heroku-scala-app

## Usage
### set up

Use version below.

[comment]: <> (- Java17+)

- Java11  
because guice do not run on JDK17
- Scala 2.13

### build

```shell
sbt compile
```

### test

```shell
sbt test
```

### formatting

Automatically, scalafmt fix formatting automatically by using custom sbt command.

```shell
sbt fmt
```

To check format without changing source codes.
```shell
sbt check
```

## Sample Request
see file:  
[Request Sample Doc](./doc/request/sample.md)

## LICENSE

This repository is MIT License.  
see [License](./LICENSE) file.
