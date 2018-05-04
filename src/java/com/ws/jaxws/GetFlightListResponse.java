
package com.ws.jaxws;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getFlightListResponse", namespace = "http://ws.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getFlightListResponse", namespace = "http://ws.com/")
public class GetFlightListResponse {

    @XmlElement(name = "return", namespace = "")
    private List<com.data.Flight> _return;

    /**
     * 
     * @return
     *     returns List<Flight>
     */
    public List<com.data.Flight> getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(List<com.data.Flight> _return) {
        this._return = _return;
    }

}
