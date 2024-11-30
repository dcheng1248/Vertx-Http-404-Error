package entity;

import jakarta.xml.bind.annotation.*;

import java.io.Serializable;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"greeting"})
@XmlRootElement(name = "HELLO_RESPONSE")
public class HELLO_RESPONSE implements Serializable {

    private static final long serialVersionUID = 1L;
    @XmlElement(name = "GREETING", required = true)
    protected String greeting;

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }
    
}

