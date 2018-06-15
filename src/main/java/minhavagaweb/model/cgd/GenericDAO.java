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
 * @param <G>
 */
<<<<<<< HEAD
public interface GenericDAO<G> {
    public List<G> getAll() throws SQLException, ClassNotFoundException;
    public G getById(int id) throws SQLException, ClassNotFoundException;
    public boolean insert(G obj) throws SQLException, ClassNotFoundException;
    public void update(G obj) throws SQLException, ClassNotFoundException;
    public void delete(G obj) throws SQLException, ClassNotFoundException;
   
=======
public interface GenericDAO<GenericType> {
    
    public List<GenericType> getAll()throws SQLException, ClassNotFoundException;
    public boolean insert(GenericType obj) throws SQLException, ClassNotFoundException;
    public void update(GenericType obj) throws SQLException, ClassNotFoundException;
    public void delete(GenericType obj) throws SQLException, ClassNotFoundException ;
    public GenericType getById(int id) throws SQLException, ClassNotFoundException ;
    public int getNextId() throws SQLException, ClassNotFoundException;
>>>>>>> 109b7e285f4c924299584f87d0d7e172b484aae8
}
