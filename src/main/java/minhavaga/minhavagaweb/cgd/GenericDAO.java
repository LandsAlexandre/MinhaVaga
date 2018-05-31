/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhavaga.minhavagaweb.cgd;

import java.util.List;

/**
 *
 * @author landerson
 */
public interface GenericDAO<GenericType> {
    public List<GenericType> getAll();
    public void insert(GenericType obj);
    public void update(GenericType obj);
    public void delete(GenericType obj);
}
