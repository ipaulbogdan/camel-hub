# Camel server hub

## Instalation

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



