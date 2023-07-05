# springboot-microservices-ankit
 multiple microservices using springboot and spring cloud. 
# Includes config server, service registry, api gateway and kafka message broker

## Kafka reated 
 cd to Download\kafka
# Start the ZooKeeper service
$ bin\windows\zookeeper-server-start.bat config\zookeeper.properties

# Start the Kafka broker service
$ bin\windows\kafka-server-start.bat config\server.properties

# topic
$ bin\windows\kafka-topics.bat --create --topic topic-example --bootstrap-server localhost:9092

# producer
bin\windows\kafka-console-producer.bat --topic topic-example --bootstrap-server localhost:9092

# consumer
.\bin\windows\kafka-console-consumer.bat --topic topic-example --from-beginning --bootstrap-server localhost:9092

# READ THE EVENTS (to see the meesage sent by producer to the topic)
bin\windows\kafka-console-consumer.bat --topic learnwithankit --from-beginning --bootstrap-server localhost:9092
