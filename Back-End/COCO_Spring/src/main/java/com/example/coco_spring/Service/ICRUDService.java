package com.example.coco_spring.Service;

import com.example.coco_spring.Entity.AfterSaleServices;

import java.util.List;

public interface ICRUDService <Class,TypeId>{
    List<Class> findAll();
    Class add(Class class1) ;

    void delete(TypeId id);

    Class update(Class Class1, TypeId id) throws Exception;
}
