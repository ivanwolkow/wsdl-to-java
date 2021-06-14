# WSDL-to-Java Converter
This example demonstrates how to generate Java client from wsdl service description.
This application utilizes the jaxws-maven-plugin which in turn makes use of wsimport utility in order to generate JAX-WS portable artifacts.
## Build and run
Generate sources from wsdl (this may take a while), compile it and build executable (fat) jar:
```bash
./mvnw package
```

Run it with the command: 
```bash
java -jar target/wsdl-to-java-1.0-SNAPSHOT.jar
```

To enable xml request/response logging, set the following system property:
```bash
com.sun.xml.ws.transport.http.client.HttpTransportPipe.dump=true
```
