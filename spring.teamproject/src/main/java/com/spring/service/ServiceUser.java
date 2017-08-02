package com.spring.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.dao.IDaoUser;
import com.spring.model.ModelUser;

@Repository("serviceteam")
public class ServiceUser implements IServiceUser {
    // SLF4J Logging
    private static Logger logger = LoggerFactory.getLogger(ServiceUser.class);

    @Autowired
    IDaoUser dao;
    
    @Override
    public int login(ModelUser team) {
        int result = -1;
        try {
            result = dao.login(team);
        } catch (Exception e) {
            logger.error("login" + e.getMessage() );
            throw e;
        }
        return result;
    }

    @Override
    public List<ModelUser> getTeamList(ModelUser team) {
        List<ModelUser> result = null;
        try {
            result = dao.getTeamList(team);
        } catch (Exception e) {
            logger.error("getTeamList" + e.getMessage() );
            throw e;
        }
        return result;
    }

    @Override
    public int insertTeam(ModelUser team) {
        int result = -1;
        try {
            result = dao.insertTeam(team);
        } catch (Exception e) {
            logger.error("insertTeam" + e.getMessage() );
            throw e;
        }
        return result;
    }
}