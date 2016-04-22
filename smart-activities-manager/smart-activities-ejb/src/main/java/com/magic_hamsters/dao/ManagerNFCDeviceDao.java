package com.magic_hamsters.dao;

/**
 * Created by mikolevy on 22.04.16.
 */

import com.smartactivities.ManagerNFCDevice;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class ManagerNFCDeviceDao {


    @Inject
    private SqlSession sqlSession;
    public  List<ManagerNFCDevice> selectAllManagerNFCDevices() {
        List<ManagerNFCDevice> managerNFCDevices = sqlSession.selectList("ManagerNFCDeviceMapper.selectAllManagerNFCDevices");
        if(managerNFCDevices == null)
            return new ArrayList<>();
        return managerNFCDevices;
    }

    public ManagerNFCDevice selectManagerNFCDeviceById(int managerNFCDeviceId) {
        return sqlSession.selectOne("ManagerNFCDeviceMapper.selectManagerNFCDeviceById", managerNFCDeviceId);
    }

    public void insertManagerNFCDevice(ManagerNFCDevice managerNFCDevice) {
        sqlSession.insert("ManagerNFCDeviceMapper.insertManagerNFCDevice", managerNFCDevice);
    }

    public void deleteManagerNFCDevice(ManagerNFCDevice managerNFCDevice) {
        sqlSession.delete("ManagerNFCDeviceMapper.deleteManagerNFCDevice", managerNFCDevice);
    }

}

