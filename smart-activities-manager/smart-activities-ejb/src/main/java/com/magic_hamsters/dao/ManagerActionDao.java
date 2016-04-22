package com.magic_hamsters.dao;

/**
 * Created by mikolevy on 22.04.16.
 */

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import com.smartactivities.ManagerAction;
import org.apache.ibatis.session.SqlSession;

public class ManagerActionDao {


    @Inject
    private SqlSession sqlSession;
    public  List<ManagerAction> selectAllManagerActions() {
        List<ManagerAction> managerActions = sqlSession.selectList("ManagerActionMapper.selectAllManagerActions");
        if(managerActions == null)
            return new ArrayList<>();
        return managerActions;
    }

    public ManagerAction selectManagerActionById(int managerActionId) {
        return sqlSession.selectOne("ManagerActionMapper.selectAllManagerActionsById", managerActionId);
    }

    public void insertManagerAction(ManagerAction managerAction) {
        sqlSession.insert("ManagerActionMapper.insertManagerAction", managerAction);
    }

    public void deleteManagerAction(ManagerAction managerAction) {
        sqlSession.delete("ManagerActionMapper.deleteManagerAction", managerAction);
    }

}

