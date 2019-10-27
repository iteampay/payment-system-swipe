
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
 *         &lt;element name="swipeCardPromptResult" type="{http://za.co.itempay.paymentsystemswipe.backend.ws}cardSwipe" minOccurs="0"/>
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
    "swipeCardPromptResult"
})
@XmlRootElement(name = "swipeCardPromptResponse")
public class SwipeCardPromptResponse {

    protected CardSwipe swipeCardPromptResult;

    /**
     * Gets the value of the swipeCardPromptResult property.
     * 
     * @return
     *     possible object is
     *     {@link CardSwipe }
     *     
     */
    public CardSwipe getSwipeCardPromptResult() {
        return swipeCardPromptResult;
    }

    /**
     * Sets the value of the swipeCardPromptResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link CardSwipe }
     *     
     */
    public void setSwipeCardPromptResult(CardSwipe value) {
        this.swipeCardPromptResult = value;
    }

}
