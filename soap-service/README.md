# Persons Soap Service

This small services is design to serve as SOAP backend service to use
to perform conversion from SOAP response (XML) to REST response (JSON).

## Principles
This is a code firs web service where the WSDL is generated after the
code provided in the `services` package.
The `conf` folder holds the configuration of the service (which is where
the magic happens). The `domain` package embed the base of the business.

## Tools
We are using the CXF classic approach to create the service.
[See here for information](https://cxf.apache.org/docs/a-simple-jax-ws-service.html).

There are other way to perform such implementation. You can follow the
[sample provided here](https://spring.io/guides/gs/producing-web-service).

> Note: You can find some sample [here](https://github.com/apache/cxf/tree/main/distribution/src/main/release/samples)

## Running the program
In order to run the program, you should import it in your IDE / editor.
We are using maven.
```bash
# Build the application
mvn clean package

# Then start the webservice
java -jar soap-service/target/soap-service-1.0-SNAPSHOT.jar
```

## Specs
In order to test the service, you should import the zip file under specs
folder into SOAPUi.