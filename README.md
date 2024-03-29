## About The Project

[Messaging System](https://github.com/AmjadKhader/messging.system)

This project is simple messaging system between users (POC).

## Built With

* Java
* Spring boot
* Postgres
* Kafka
* Docker
* DB Flyway migrations

## Getting Started

### Prerequisites

To run this project, Docker shall be up and running

### Installation

```
$ docker compose up
```

### Usage

All you need at this stage is to run the application

### Functionalities

* [Create a user](http://localhost:8092/api/messaging-system/user/create)
* [Send a message to user](http://localhost:8092/api/messaging-system/message/send/{sender_id})
* [View all messages I sent](http://localhost:8092/api/messaging-system/message/view/send/{user_id})
* [View all messages I received](http://localhost:8092/api/messaging-system/message/view/receive/{user_id})
* [View all messages I received from particular user](http://localhost:8092/api/messaging-system/message/view/{user_id}/receive/{sender_id})

## Roadmap

- [x] Use Kafka for sending messages
- [x] Easy deploying
- [x] OpenApi specs
- [x] Add unit test for the main points
- [ ] Future suggestions:
    - [ ] Make sure to consume kafka message only once. 
    - [ ] Test system parts individually
    - [ ] E2E Test (Cucumber maybe?)
    - [ ] Move Kafka configuration to yaml file
    - [ ] More generic Kafka producer and consumer
