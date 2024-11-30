package entity;

import jakarta.xml.bind.annotation.*;

import java.io.Serializable;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"name"})
@XmlRootElement(name = "HELLO_REQUEST")
public class HELLO_REQUEST implements Serializable {

    private static final long serialVersionUID = 1L;
    @XmlElement(name = "NAME", required = true)
    protected String name;

    public String getNAME() {
        return name;
    }

    public void setNAME(String name) {
        this.name = name;
    }

}

