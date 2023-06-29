cd camel-server/
./mvnw clean install
docker build -t com.idorasi/camel-server .

cd ../rest-producer/
./mvnw clean install
docker build -t com.idorasi/rest-producer .

cd ../soap-producer
./mvnw clean install
docker build -t com.idorasi/soap-producer .

docker network create bridge-network

docker run --network bridge-network -d -p 9090:9090 -t com.idorasi/camel-server
docker run --network bridge-network --network-alias rest-producer -d -p 9091:9091 -t com.idorasi/rest-producer
docker run --network bridge-network --network-alias soap-producer -d -p 9092:9092 -t com.idorasi/soap-producer

