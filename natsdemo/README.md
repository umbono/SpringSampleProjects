# NATS Demo using Spring Boot 

NATS offers three primary modes of message exchange. 

1. Publish/Subscribe -> Delivers messages to all subscribers of a topic. 
2. Request/Reply -> Sends requests via topics and routes responses back to the requestor.
3. Subscribers can also join message queue groups when they subscribe to a topic. Messages sent to the associated topic are only delivered to one subscriber in the queue group.

