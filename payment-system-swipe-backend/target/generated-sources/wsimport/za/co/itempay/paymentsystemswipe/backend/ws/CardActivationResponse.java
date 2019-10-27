
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
 *         &lt;element name="cardActivationResult" type="{http://za.co.itempay.paymentsystemswipe.backend.ws}cardAct" minOccurs="0"/>
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
    "cardActivationResult"
})
@XmlRootElement(name = "cardActivationResponse")
public class CardActivationResponse {

    protected CardAct cardActivationResult;

    /**
     * Gets the value of the cardActivationResult property.
     * 
     * @return
     *     possible object is
     *     {@link CardAct }
     *     
     */
    public CardAct getCardActivationResult() {
        return cardActivationResult;
    }

    /**
     * Sets the value of the cardActivationResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link CardAct }
     *     
     */
    public void setCardActivationResult(CardAct value) {
        this.cardActivationResult = value;
    }

}
