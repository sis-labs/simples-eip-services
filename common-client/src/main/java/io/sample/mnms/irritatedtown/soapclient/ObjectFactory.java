
package io.sample.mnms.irritatedtown.soapclient;

import javax.xml.namespace.QName;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the io.sample.mnms.irritatedtown.soapclient package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private static final QName _Person_QNAME = new QName("http://sample.service.io/", "person");
    private static final QName _FetchPersons_QNAME = new QName("http://sample.service.io", "fetchPersons");
    private static final QName _FetchPersonsResponse_QNAME = new QName("http://sample.service.io", "fetchPersonsResponse");
    private static final QName _FindById_QNAME = new QName("http://sample.service.io", "findById");
    private static final QName _FindByIdResponse_QNAME = new QName("http://sample.service.io", "findByIdResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: io.sample.mnms.irritatedtown.soapclient
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Person }
     * 
     * @return
     *     the new instance of {@link Person }
     */
    public Person createPerson() {
        return new Person();
    }

    /**
     * Create an instance of {@link FetchPersons }
     * 
     * @return
     *     the new instance of {@link FetchPersons }
     */
    public FetchPersons createFetchPersons() {
        return new FetchPersons();
    }

    /**
     * Create an instance of {@link FetchPersonsResponse }
     * 
     * @return
     *     the new instance of {@link FetchPersonsResponse }
     */
    public FetchPersonsResponse createFetchPersonsResponse() {
        return new FetchPersonsResponse();
    }

    /**
     * Create an instance of {@link FindById }
     * 
     * @return
     *     the new instance of {@link FindById }
     */
    public FindById createFindById() {
        return new FindById();
    }

    /**
     * Create an instance of {@link FindByIdResponse }
     * 
     * @return
     *     the new instance of {@link FindByIdResponse }
     */
    public FindByIdResponse createFindByIdResponse() {
        return new FindByIdResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Person }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Person }{@code >}
     */
    @XmlElementDecl(namespace = "http://sample.service.io/", name = "person")
    public JAXBElement<Person> createPerson(Person value) {
        return new JAXBElement<>(_Person_QNAME, Person.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FetchPersons }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link FetchPersons }{@code >}
     */
    @XmlElementDecl(namespace = "http://sample.service.io", name = "fetchPersons")
    public JAXBElement<FetchPersons> createFetchPersons(FetchPersons value) {
        return new JAXBElement<>(_FetchPersons_QNAME, FetchPersons.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FetchPersonsResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link FetchPersonsResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://sample.service.io", name = "fetchPersonsResponse")
    public JAXBElement<FetchPersonsResponse> createFetchPersonsResponse(FetchPersonsResponse value) {
        return new JAXBElement<>(_FetchPersonsResponse_QNAME, FetchPersonsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindById }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link FindById }{@code >}
     */
    @XmlElementDecl(namespace = "http://sample.service.io", name = "findById")
    public JAXBElement<FindById> createFindById(FindById value) {
        return new JAXBElement<>(_FindById_QNAME, FindById.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindByIdResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link FindByIdResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://sample.service.io", name = "findByIdResponse")
    public JAXBElement<FindByIdResponse> createFindByIdResponse(FindByIdResponse value) {
        return new JAXBElement<>(_FindByIdResponse_QNAME, FindByIdResponse.class, null, value);
    }

}
