/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhavagaweb.model.cgd;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author landerson
 * @param <GenericType>
 */
public interface GenericDAO<GenericType> {
    
    public List<GenericType> getAll()throws SQLException, ClassNotFoundException;
    public void insert(GenericType obj) throws SQLException, ClassNotFoundException;
    public void update(GenericType obj) throws SQLException, ClassNotFoundException;
    public void delete(GenericType obj) throws SQLException, ClassNotFoundException ;
    public GenericType getById(int id) throws SQLException, ClassNotFoundException ;
    public int getNextId() throws SQLException, ClassNotFoundException;
}
