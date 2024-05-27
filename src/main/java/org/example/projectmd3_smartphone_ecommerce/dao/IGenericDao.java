package org.example.projectmd3_smartphone_ecommerce.dao;


import java.util.List;

//R Is Request DTO
public interface IGenericDao<T,R,E> {


    List<T> getAll(E currentPage, E size);
    T findById(E id);

    boolean addNew(R object);
    boolean update(R object, E id);

    boolean delete(E id);


}
