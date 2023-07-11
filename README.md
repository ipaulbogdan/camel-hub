# Camel server hub

## Installation

Run command: 
```bash
sh run.sh
```

This will install and deploy 3 apps each in a different **docker** container

Once the installation you can test it:
1. Calling rest producer endpoint: 
```bash
curl localhost:9090/rest/hello-world
```
2. Calling soap producer endpoint: 
```bash
curl -XPOST localhost:9090/soap/hello-world -d '{"name": "World"}' -H "Content-Type: application/json"
```

The routing can be checked in logs. Camel logs: 
```
2023-06-29 07:44:32.937 Received GET request at /soap/hello-world
2023-06-29 07:44:33.388 Received body from soap producer: simple{Hello World}
```
Soap producer logs: 
```
2023-06-29T07:44:33.315Z Received request with param: World
```


## Stop services:
```bash
sh stop.sh
```

## Redirect test:
```bash
curl -XGET localhost:9090/rest/hello-world
```
```
{
    "message": "First hello!",
    "redirectUrl": "http://localhost:9090/rest/hello-world?redirectUrl=http://rest-producer:9091/redirect/hello"
}
```
```bash
curl "http://localhost:9090/rest/hello-world?redirectUrl=http://rest-producer:9091/redirect/hello"
```

```
{
    "message": "Second hello",
    "redirectUrl": null
}
```


## HTML test:
1. Go to localhost:9090/login
2. Enter username and submit form
3. You will get redirected to localhost:9090/hello?username={username}

There is a open ticket on the camel side for regex endpoints: https://issues.apache.org/jira/browse/CAMEL-8306


