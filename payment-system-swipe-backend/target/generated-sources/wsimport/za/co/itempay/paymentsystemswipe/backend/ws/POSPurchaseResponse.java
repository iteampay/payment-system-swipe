
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
 *         &lt;element name="POSPurchaseResult" type="{http://za.co.itempay.paymentsystemswipe.backend.ws}POSRsp" minOccurs="0"/>
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
    "posPurchaseResult"
})
@XmlRootElement(name = "POSPurchaseResponse")
public class POSPurchaseResponse {

    @XmlElement(name = "POSPurchaseResult")
    protected POSRsp posPurchaseResult;

    /**
     * Gets the value of the posPurchaseResult property.
     * 
     * @return
     *     possible object is
     *     {@link POSRsp }
     *     
     */
    public POSRsp getPOSPurchaseResult() {
        return posPurchaseResult;
    }

    /**
     * Sets the value of the posPurchaseResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link POSRsp }
     *     
     */
    public void setPOSPurchaseResult(POSRsp value) {
        this.posPurchaseResult = value;
    }

}
