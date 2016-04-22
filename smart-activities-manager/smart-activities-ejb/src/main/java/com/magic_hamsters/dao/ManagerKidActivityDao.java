package com.magic_hamsters.dao;

/**
 * Created by mikolevy on 22.04.16.
 */

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.smartactivities.ManagerKidActivity;
import org.apache.ibatis.session.SqlSession;

public class ManagerKidActivityDao {


    @Inject
    private SqlSession sqlSession;
    public  List<ManagerKidActivity> selectAllManagerKidActivities() {
        List<ManagerKidActivity> managerKidActivities = sqlSession.selectList("ManagerKidActivityMapper.selectAllManagerKidActivities");
        if(managerKidActivities == null)
            return new ArrayList<>();
        return managerKidActivities;
    }

    public ManagerKidActivity selectAllManagerKidActivityById(int managerKidActivityId) {
        return sqlSession.selectOne("ManagerKidActivityMapper.selectAllManagerKidActivityById", managerKidActivityId);
    }

    public void insertManagerKidActivity(ManagerKidActivity managerKidActivity) {
        sqlSession.insert("ManagerKidActivityMapper.insertManagerKidActivity", managerKidActivity);
    }

    public void deleteManagerKidActivity(ManagerKidActivity managerKidActivity) {
        sqlSession.delete("ManagerKidActivityMapper.deleteManagerKidActivity", managerKidActivity);
    }

}

