/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gosrilanka.dao;

import java.util.ArrayList;

/**
 *
 * @author Uresha Lakshani
 */
public interface CrudDAO<T,K> extends SuperDAO{
    public boolean add(T entity) throws Exception;
    public boolean delete(K id) throws Exception;
    public boolean update(T entity) throws Exception;
    public T get(K id) throws Exception;
    public ArrayList<T> getAll()throws Exception;
}
