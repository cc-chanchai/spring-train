package com.example.demo.repository.impl;

import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepositoryCustom;
import com.example.demo.util.AliasToBeanNestedResultTransformer;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class EmployeeRepositoryCustomImpl implements EmployeeRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Employee> findByName(String name) {
        Criteria criteria = ((Session) entityManager.getDelegate()).createCriteria(Employee.class);
        criteria.createAlias("titleName", "TitleName");

        ProjectionList projectionList = Projections.projectionList();
        projectionList.add(Projections.property("name"),"name");
        projectionList.add(Projections.property("lastName"),"lastName");
        projectionList.add(Projections.property("TitleName.code"),"titleName.code");
        projectionList.add(Projections.property("TitleName.id"),"titleName.id");
        criteria.setProjection(projectionList);

//        criteria.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP); //TOMAP

//        criteria.setResultTransformer(Transformers.aliasToBean(Employee.class)); //TO Class

//        criteria.setResultTransformer(Transformers.TO_LIST);

        criteria.setResultTransformer(new AliasToBeanNestedResultTransformer(Employee.class));



//        criteria.add(Restrictions.eq("Dep.type","1");


//        Disjunction disjunction=Restrictions.disjunction();
//        disjunction.add(Restrictions.eq("name",name));
//        disjunction.add(Restrictions.eq("lastName",name));
//
//        Conjunction conjunction=Restrictions.conjunction();
//
//        criteria.add(disjunction);
//        criteria.add(Restrictions.eq("TitleName.code","01"));
//        criteria.add(Restrictions.eq("name",name));
//        criteria.add(Restrictions.isNotNull("name"));
//        String [] code={"01","02"};
//        criteria.add(Restrictions.in("TitleName.code",code));
//
//        criteria.addOrder(Order.asc("name"));
//        criteria.addOrder(Order.asc("lastName"));
        criteria.setFirstResult(1);
        criteria.setMaxResults(20);
        return criteria.list();
    }
}
