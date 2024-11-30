
package entity;

import jakarta.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {

    public ObjectFactory() {
    }

    public HELLO_REQUEST createHELLO_REQUEST() {
        return new HELLO_REQUEST();
    }

    public HELLO_RESPONSE createHELLO_RESPONSE() {
        return new HELLO_RESPONSE();
    }

}
