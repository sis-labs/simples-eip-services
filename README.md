# Irritated town

## Preparing the project
In order to prepare the project, use the following procedure.

1. Go on your vcs hosting service (github, gitlab, bitbucket, ...)
2. Create a new project (use the right location according to company / team policies)
3. Copy the uri of the repository (appear in a line like `git remote add origin <an_uri>`)
4. Go on your bash (gitbash if you are using mswin).
5. Position your bash at the root of your local git repository (a clone of this repository normally)
6. Use the command `git remote add origin <uri>` where the uri is the one you copied on point 3.
7. Sync the remote repository with the following command `git push origing --all`.
8. Go on the web application of your remote repository to check if everything is in sync or use `git branch -a`

You are now ready

## Cloning the project

### 1. Prepare your environment

i. Ensure about your git configuration
```bash
git config user.name
# should output your name
git config user.email
# should output your email
```

ii. Run the script to prepare git aliases
```bash
.devtools/scripts/git_aliases.sh

# confirm the correct running by
git config --get-regexp ^alias
```

iii. Clone the projet and reset it to a git initial state

To prepare the project for the internal use, run the following script

> **Note** : It is mandatory to use a bash compliant shell such as mingwt (git bash for window) or cygwin.

```bash
git clone https://github.com/sis-labs/simples-eip-services.git
cd simples-eip-services
rm -rf .git

git init .
git add .
git commit -s -m "Initial commit"
```

> **Note:** you are now ready to start coding.

## Layout of the project
The project has a bunch of submodules. Some are dedicated for testing purpose (see mock)
and the rest is the base for the implementation. There is also both version of the camel route, one
with Springboot, the other with a lightweight implementation which is using camel directly. The last one is
a pure Spring boot implementation for the client webservice which seems to be sufficient for the first iteration.

--- *Here is the screenshot for the layout* --

### SOAP
The mock implementation is dedicated to use as a standalone predictive dynamic backend. It acts as the
Citizen SOAP webservice for person. It is pretty simple but use a code first approach.
The second one, which is almost equivalent, uses a contract first approach, which is the preferred way of
working when you are running with an existing contract since, the webservice will respect this contract.

You can start the project by running the corresponding application `xxxApplication.java#main` on your IDE.
To request the service, you should request it using the SOAPUi project located in the `specs` folder.
Before doing that, be sure that the webservice is running by opening the following url in your browser.
`http://localhost:8080/soap/services/persons?wsdl`. You should get the WSDL for the running service. If you
have a soap client, you can use the WSDL to configure the service and requesting it.

Take care this backend implementation is used during the integration testing. Do not remove it without revewing
implementation from the testing projects (irritated-town-it`).

### Camel Route (Standalone)
The `standalone-route` is a standalone version of the Camel integration. It is using the stack in a plain
old and simple manner.
All configurations are provided by the `application.yml` or `application.properties` available in the `resources` folder
(under the `main` folder). You can change the listening port, the service path and so on.

To start the project, compile and package the project with the following command
```bash
.mvnw clean package
```

You can the run the application using the following command
```bash
java -jar <path_to_app>.jar
```

You can open your browser on the url `http://localhost:8081/rest/persons` to get the list of users and on
the url `http://localhost:8081/rest/persons/123` to get the details about a persons.

### Camel Route (with SpringBoot)
THe `spring-boot` based route is almost equivalent of the previous one. The projet has just a bit more configuration
in the `conf` package. To run the application, simply compile and package it `.mvnw clean compile` and run the produced
jar with the command `java -jar <path_to_the_jar>.jar`.

In the same manner of the previous project, you can open your browser on the url `http://localhost:8081/rest/persons`
to get the list of users and on the url `http://localhost:8081/rest/persons/123` to get the details about a persons.

## How it works

Here you will find out information about the behavior of each project. You'll also see how you can customize it according to
project needs.

### SOAP backend
Since this project is optional, there is no need to customize it. However, you should reflect th real backend inside the project.
To do that, each time the WSDL of the remote backend is modified / discovered, you have to refetch the wsdl of the service and replace
the content of the `resources/wsld/service.wsdl` content by the new content of the file. Then, you have to recompile the project. Depending
on the nature of the change, you may have to adapt the mock service and the tests which are using it (actually, every client should be reviewed).
For instance, if the nature of the exchanged resource is updated (ex : a new field is added to the class `Person`), it is not necessary to update
the implementation. However, if a new method is defined, you have to review the interface of the service and his implementation. Check code comments
to gather more information about that.

### SOAP clients
As you may have seen, you have a `common` module in the project. This modules defines the api of the project. At his heart, it embeds the definition of
the remote service (the service WSDL) so that every part of the client model may be auto generated. If the WSDL is modified on the server side, import
the new definition as the content of the file `resources/wsdl/service.wsdl`. Then recompile the module to get the fresh definition of the java classes
by running the `mvn clean compile` command. Other projects automatically import the `common` and thus, when this project change, all dependent projects
should be updated.

> **Note:** Take care, when you are updating a project, othwer project with a dependance on this one may fails (if the new definition is not backward
> compatible with the prwvious one.

### Camel Integration
When you are updating the `common` project, you should recompile all other projects after fixing potential bugs introduce by the update. Each client is
actually using the wsdl but this is made through the `common` project, this is why you don't have to copy the wsdl in other projects.

You have to provide the open api contract inside the `resources/specs` folder in a file named `oapi3.yml`. After importing this file, you may have to review
some of the processing to adapt the behavior to the new definition (ex: changing fields in the mapping so that json response is compliant with the open api
specification.

### Pure Spring Boot integration.
This implementation is using
