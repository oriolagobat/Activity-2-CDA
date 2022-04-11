# Intro

In this project, I'll implement a quick and simple example of Kafka, using Java.

# Install Kafka

First, we'll need to install Kafka.

```sh
# Install curl if not installed
curl https://downloads.apache.org/kafka/2.5.0/kafka.12-2.5.0.tgz -o Downloads/kafka.tgz
# Move to desired installation folder
mkdir kafka
cd kafka
tar -xvzf ~/Downloads/kafka.tgz — strip 1
# Export bin folder to PATH 
```

To check if it has gone well, run:

```sh
kafka-topics.sh
```

# Kafka Producer

In this project, we'll implement a simple producer that sends a message to a topic.

First, we'll need to know Kafka's version, running:

```sh
kafka-topics.sh --version
```

## Kafka dependency

Go to the [Kafka maven repository](https://mvnrepository.com/artifact/org.apache.kafka/kafka-clients) and find the
version page that matches your Kafka's version. Click on it, and copy the dependencies.

Next, create a new Java Maven project and on `pom.xml` file, add the copied dependency inside the project, inside a
dependencies block. For example:

```xml

<dependencies>
    <dependency>
        <groupId>org.apache.kafka</groupId>
        <artifactId>kafka-clients</artifactId>
        <version>2.5.0</version>
    </dependency>
</dependencies>
```

### Fixing possible error

If when inserting the dependencies, these appear in read, we need to refresh Maven's auto import. In intellij IDEA, go
to `View > Tool Windows > Maven`. On the new screen, hit the refresh button.

## sl4j dependency
Next, we also need to include the sl4j dependency. 
1. Go to the maven window, and under dependencies, check its version
2. Go to the [sl4j maven repository](https://mvnrepository.com/artifact/org.slf4j/slf4j-simple), and one more time, click on the version page that matches your version.
3. Copy the dependencies, in the dependencies block we made earlier.
4. Remove the <scope>test</scope> tag, so these dependencies is included all the project scope.


## Running code
