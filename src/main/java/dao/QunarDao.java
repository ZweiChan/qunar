package dao;

import javabean.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class QunarDao extends BaseDao {
    public User readUser(String id){
        String sql="SELECT * FROM user WHERE id= ?;";
        User user=new User();
        try (
                Connection conn=connectionPool.getConnection();
                PreparedStatement pstmt=conn.prepareStatement(sql)
        ){
            pstmt.setString(1,id);
            try(
                    ResultSet resultSet=pstmt.executeQuery()
            ){
                if (resultSet.next()){
                    user.setId(id);
                    user.setName(resultSet.getString("name"));
                    user.setPwd(resultSet.getString("password"));
                }
                return user;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
