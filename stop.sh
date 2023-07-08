docker stop $(docker ps | grep rest-producer | cut -d' ' -f1)
docker stop $(docker ps | grep soap-producer | cut -d' ' -f1)
docker stop $(docker ps | grep camel-server | cut -d' ' -f1)
docker stop $(docker ps | grep html-producer | cut -d' ' -f1)
