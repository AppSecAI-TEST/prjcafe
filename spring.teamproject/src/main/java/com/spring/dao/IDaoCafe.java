package com.spring.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.spring.model.ModelCafe;

@Repository
public interface IDaoCafe {
     
    List<ModelCafe> getCafeList();
    
}
