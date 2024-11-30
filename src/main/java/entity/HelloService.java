package entity;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.xml.bind.annotation.XmlSeeAlso;

@WebService(targetNamespace = "urn:entity", name = "HELLO")
@XmlSeeAlso({ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface HelloService {

    @WebMethod(operationName = "SAY_HELLO", action = "urn:entity:HELLO:HELLO_REQUEST")
    @WebResult(name = "HELLO_RESPONSE", targetNamespace = "urn:entity", partName = "parameter")
    public HELLO_RESPONSE sayHello(

            @WebParam(partName = "parameters", name = "SAY_HELLO", targetNamespace = "urn:entity")
            HELLO_REQUEST parameters
    );

}
