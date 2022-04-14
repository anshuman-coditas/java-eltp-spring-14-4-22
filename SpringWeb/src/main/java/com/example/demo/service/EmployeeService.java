package com.example.demo.service;

import com.example.demo.model.*;

import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class EmployeeService  {
    @Autowired
    private EmployeeRepository repository;

    public void insert(Employee e){
        repository.save(e);
    }
    public Employee getEmployee(int id){
        return repository.getById(id);
    }
    public List<Employee> getEmployees(){
        List<Employee> employees= repository.findAll();
        return employees;
    }
    public void delete(int id){
        repository.deleteById(id);
    }
    public List<Employee> fetchsort(){
    	return repository.findAll(Sort.by("salary"));
    }
    public Page<Employee> fetchPagination(int off,int pageSize){
    	return repository.findAll(PageRequest.of(off, pageSize));
    }
}
