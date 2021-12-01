# heroku-scala-app

## Usage
### set up

Use version below.

[comment]: <> (- Java17+)

- Java11  
guice が Java17 で動作しないため
- Scala 2.13

### build

```shell
sbt compile
```

### test

```shell
sbt test
```

## Sample Request
### Rest
like this.
```shell
curl -v -X GET "http://<server domain>:5000/api/controller?type=query&key=1"
```
then, response will be below:
```shell
< HTTP/1.1 200 OK
< Server: akka-http/10.2.7
< Date: XXX, DD Mmm YYYY hh:mm:ss GMT
< Content-Type: text/plain; charset=UTF-8
< Content-Length: 30
<
Some(Issue(1,概要1,説明1))* Connection
```

### GraphQL
Request like this:
```shell
curl -X POST "http://<server domain>:5000/api/graphql"
-d `
{
  "query": "query { <operation> {param1, param2...} }"
}
`
```

```shell
< HTTP/1.1 200 OK
< content-type:	application/json
< date: XXX, DD Mmm YYYY hh:mm:ss GMT
< server:	akka-http/10.2.7
< content-length: 159
{
  "data":{
    "issues":[
      {
        "id":1,
        "summary":"概要1",
        "desc":"説明1"
      },
      {
        "id":2,
        "summary":"概要2",
        "desc":"説明2"
      },
      {
        "id":3,
        "summary":"概要3",
        "desc":"説明3"
      }
    ]
  }
}
```

#### Query
- find All Issues
```shell
{
  "query": "query { issues { id, summary, desc } }"
}
```
- find Issue by id
```shell
{
  "query": "query { issue(id: 1) { id, summary, desc } }"
}
```

#### Mutation
- insert Issue
```shell
{
  "query": "mutation { issue(issue: {summary: \"概要Mutation\", desc: \"説明概要Mutation\"}) }"
}
```

#### Subscription
Not yet...

## LICENSE

This repository is MIT License.  
see [License](./LICENSE) file.
