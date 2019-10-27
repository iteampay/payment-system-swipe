
package za.co.itempay.paymentsystemswipe.backend.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
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
 *         &lt;element name="AedoAuthResult" type="{http://za.co.itempay.paymentsystemswipe.backend.ws}AuthRsp" minOccurs="0"/>
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
    "aedoAuthResult"
})
@XmlRootElement(name = "AedoAuthResponse")
public class AedoAuthResponse {

    @XmlElement(name = "AedoAuthResult")
    protected AuthRsp aedoAuthResult;

    /**
     * Gets the value of the aedoAuthResult property.
     * 
     * @return
     *     possible object is
     *     {@link AuthRsp }
     *     
     */
    public AuthRsp getAedoAuthResult() {
        return aedoAuthResult;
    }

    /**
     * Sets the value of the aedoAuthResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link AuthRsp }
     *     
     */
    public void setAedoAuthResult(AuthRsp value) {
        this.aedoAuthResult = value;
    }

}
