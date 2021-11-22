import java.util.*;
import java.sql.*;

public class EmpDao {

	public static Connection getConnection() throws Exception {
		Connection con = null;
		
		String url = "jdbc:mysql://localhost:3306/addressbook";
		String username = "root";
		String password = "Suryateja@2014";
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(url, username, password);
		return con;
	}

	public static int save(Emp e) {
		int status = 0;
		try {
			Connection con = EmpDao.getConnection();
			PreparedStatement ps = con
					.prepareStatement("insert into servlet_crud(id,name,door,city,state) values (?,?,?,?,?)");
			ps.setInt(1, e.getId());
			ps.setString(2, e.getName());
			ps.setString(3, e.getDoor());
			ps.setString(4, e.getCity());
			ps.setString(5, e.getState());

			status = ps.executeUpdate();

			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return status;
	}
	 public static int update(Emp e){  
	        int status=0;  
	        try{  
	            Connection con=EmpDao.getConnection();  
	            PreparedStatement ps=con.prepareStatement(  
	                         "update servlet_crud set name=?,door=?,city=?,state=? where id=?");
	            ps.setInt(1,e.getId());
				ps.setString(2, e.getName());
				ps.setString(3, e.getDoor());
				ps.setString(4, e.getCity());
				ps.setString(5, e.getState());
	              
	            status=ps.executeUpdate();  
	              
	            con.close();  
	        }catch(Exception ex){ex.printStackTrace();}  
	          
	        return status;  
	    }  
public static int delete(int id){
	int status=0;
	try{
		Connection con=EmpDao.getConnection();
		PreparedStatement ps=con.prepareStatement("delete from servlet_crud where id=?");
		ps.setInt(1,id);
		status=ps.executeUpdate();
		
		con.close();
	}catch(Exception e){e.printStackTrace();}
	
	return status;
}
public static Emp getAddressById(int id){
	Emp e=new Emp();
	
	try{
		Connection con=EmpDao.getConnection();
		PreparedStatement ps=con.prepareStatement("select * from servlet_crud where id=?");
		ps.setInt(1,id);
		ResultSet rs=ps.executeQuery();
		if(rs.next()){
			e.setId(rs.getInt(1));
			e.setName(rs.getString(2));
			e.setDoor(rs.getString(3));
			e.setCity(rs.getString(4));
			e.setState(rs.getString(5));
		}
		con.close();
	}catch(Exception ex){ex.printStackTrace();}
	
	return e;
}
public static List<Emp> getAllAddress(){
	List<Emp> list=new ArrayList<Emp>();
	
	try{
		Connection con=EmpDao.getConnection();
		PreparedStatement ps=con.prepareStatement("select * from servlet_crud");
		ResultSet rs=ps.executeQuery();
		while(rs.next()){
			Emp e=new Emp();
			e.setId(rs.getInt(1));
			e.setName(rs.getString(2));
			e.setDoor(rs.getString(3));
			e.setCity(rs.getString(4));
			e.setState(rs.getString(5));
			list.add(e);
		}
		con.close();
	}catch(Exception e){e.printStackTrace();}
	
	return list;
}
}
