
package za.co.itempay.paymentsystemswipe.backend.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="handshakeResult" type="{http://za.co.itempay.paymentsystemswipe.backend.ws}HandShakeRsp" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "handshakeResult"
})
@XmlRootElement(name = "handshakeResponse")
public class HandshakeResponse {

    protected HandShakeRsp handshakeResult;

    /**
     * Gets the value of the handshakeResult property.
     * 
     * @return
     *     possible object is
     *     {@link HandShakeRsp }
     *     
     */
    public HandShakeRsp getHandshakeResult() {
        return handshakeResult;
    }

    /**
     * Sets the value of the handshakeResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link HandShakeRsp }
     *     
     */
    public void setHandshakeResult(HandShakeRsp value) {
        this.handshakeResult = value;
    }

}
