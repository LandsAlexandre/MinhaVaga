/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhavagaweb.model.cgd;

import java.util.List;

/**
 *
 * @author landerson
 * @param <GenericType>
 */
public interface GenericDAO<GENERICTYPE> {
    public List<GENERICTYPE> getAll();
    public GENERICTYPE getById(int id);
    public void insert(GENERICTYPE obj);
    public void update(GENERICTYPE obj);
    public void delete(GENERICTYPE obj);
    public int getNextId();
}
