/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhavagaweb.model.cdp;

/**
 *
 * @author landerson
 */
public enum TipoVaga {
    COMUM(1), MOTO(2), IDOSO(3), DEFICIENTE(4);
    private final int value;
    
    public int getValue() {
        return value;
    }
    
    private TipoVaga(int value) {
        this.value = value;
    }
}
