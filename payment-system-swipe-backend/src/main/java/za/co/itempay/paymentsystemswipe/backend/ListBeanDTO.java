/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.itempay.paymentsystemswipe.backend;

public class ListBeanDTO {
    /*private String cod;
    private String name;*/
    private String terminalser;

    public ListBeanDTO() {
    }

    public ListBeanDTO(String terminalser) {
            /*this.cod = cod ;  
            this.name = name;*/
        this.terminalser = terminalser;
    }

    /*public String getCod()
    {
        return this.cod ;
    }

    public String getName()
    {
        return this.name ;
    }*/
    public String getTerminalser() {
        return this.terminalser;
    }
}

