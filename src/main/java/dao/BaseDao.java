package dao;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

import java.util.concurrent.TimeUnit;

public class BaseDao {
    protected BoneCP connectionPool;

    public BaseDao() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            BoneCPConfig config=new BoneCPConfig();
            config.setJdbcUrl("jdbc:mysql://localhost:3306/qunar?useSSL=false");
            config.setUsername("root");
            config.setPassword("root");
            config.setMinConnectionsPerPartition(2);
            config.setMaxConnectionsPerPartition(10);
            config.setConnectionTimeout(5000,TimeUnit.MILLISECONDS);
            //设置分区数
            config.setPartitionCount(1);
            connectionPool=new BoneCP(config);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
