package io.sample.mnms.irritatedtown.services.soap.services;

import io.sample.mnms.irritatedtown.services.soap.domain.Person;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.xml.ws.RequestWrapper;
import jakarta.xml.ws.ResponseWrapper;

import java.util.List;

/**
 * Description of the webservice to expose through the SOAP interface.
 *
 * <p>This is a code first approach here. We will review that for a contract first approach
 * during the second phase of the project when we will have all contracts. For now, we defined
 * our own contract.</p>
 *
 * @author miga
 * @version 0.0.1
 * @since 0.0.1
 */
@WebService(targetNamespace = "http://sample.service.io/", name = "PersonInformation")
public interface PersonService {

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "findById",
            targetNamespace = "http://sample.service.io",
            className = "io.sample.service.FindById")
    @WebMethod(operationName = "findById", action = "urn:personInformation")
    @ResponseWrapper(localName = "findByIdResponse",
            targetNamespace = "http://sample.service.io",
            className = "io.sample.service.FindByIdResponse")
    Person findById(@WebParam(name = "personId", targetNamespace = "") String personId);

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "fetchPersons",
            targetNamespace = "http://sample.service.io",
            className = "io.sample.service.FetchPersons")
    @WebMethod(operationName = "fetchPersons", action = "urn:personsInformation")
    @ResponseWrapper(localName = "fetchPersonsResponse",
            targetNamespace = "http://sample.service.io",
            className = "io.sample.service.FetchPersonsResponse")
    List<Person> fetchPersons();
}