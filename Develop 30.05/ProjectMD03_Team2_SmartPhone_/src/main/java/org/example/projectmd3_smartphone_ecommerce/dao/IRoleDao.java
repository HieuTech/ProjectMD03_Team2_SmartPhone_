package org.example.projectmd3_smartphone_ecommerce.dao;

import org.example.projectmd3_smartphone_ecommerce.entity.Roles;

import java.util.List;

public interface IRoleDao extends IGenericDao<Roles,Roles,Integer>{
    List<Roles> getAllRoles();
}
