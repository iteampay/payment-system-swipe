
package za.co.itempay.paymentsystemswipe.backend.ws;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b14002
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "CardManagementService", targetNamespace = "http://za.co.itempay.paymentsystemswipe.backend.ws", wsdlLocation = "file:/C:/home/dtekeshe/github.com/iteampay/payment-system-swipe/payment-system-swipe-backend/src/main/resources/wsdl/card-management.wsdl")
public class CardManagementService
    extends Service
{

    private final static URL CARDMANAGEMENTSERVICE_WSDL_LOCATION;
    private final static WebServiceException CARDMANAGEMENTSERVICE_EXCEPTION;
    private final static QName CARDMANAGEMENTSERVICE_QNAME = new QName("http://za.co.itempay.paymentsystemswipe.backend.ws", "CardManagementService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("file:/C:/home/dtekeshe/github.com/iteampay/payment-system-swipe/payment-system-swipe-backend/src/main/resources/wsdl/card-management.wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        CARDMANAGEMENTSERVICE_WSDL_LOCATION = url;
        CARDMANAGEMENTSERVICE_EXCEPTION = e;
    }

    public CardManagementService() {
        super(__getWsdlLocation(), CARDMANAGEMENTSERVICE_QNAME);
    }

    public CardManagementService(WebServiceFeature... features) {
        super(__getWsdlLocation(), CARDMANAGEMENTSERVICE_QNAME, features);
    }

    public CardManagementService(URL wsdlLocation) {
        super(wsdlLocation, CARDMANAGEMENTSERVICE_QNAME);
    }

    public CardManagementService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, CARDMANAGEMENTSERVICE_QNAME, features);
    }

    public CardManagementService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public CardManagementService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns CardManagementServiceSoap
     */
    @WebEndpoint(name = "CardManagementServiceSoap")
    public CardManagementServiceSoap getCardManagementServiceSoap() {
        return super.getPort(new QName("http://za.co.itempay.paymentsystemswipe.backend.ws", "CardManagementServiceSoap"), CardManagementServiceSoap.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns CardManagementServiceSoap
     */
    @WebEndpoint(name = "CardManagementServiceSoap")
    public CardManagementServiceSoap getCardManagementServiceSoap(WebServiceFeature... features) {
        return super.getPort(new QName("http://za.co.itempay.paymentsystemswipe.backend.ws", "CardManagementServiceSoap"), CardManagementServiceSoap.class, features);
    }

    private static URL __getWsdlLocation() {
        if (CARDMANAGEMENTSERVICE_EXCEPTION!= null) {
            throw CARDMANAGEMENTSERVICE_EXCEPTION;
        }
        return CARDMANAGEMENTSERVICE_WSDL_LOCATION;
    }

}
