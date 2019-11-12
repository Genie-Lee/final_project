package com.DataAccessObject;

import java.sql.*;
import java.util.ArrayList;

import javax.websocket.Session;

import com.DataObject.UserUsageDO;
import com.DataObject.driverDO;
import com.DataObject.enter_main_viewerDO;
import com.DataObject.reviewDO; 
import com.DataObject.enterpriseDO;
import com.DataObject.orderDO;
import com.DataObject.userDO; 


public class memberDAO {
	
    static Connection conn = null; 
	static PreparedStatement psmt = null;
	static ResultSet rs = null; 
	
	int cnt = 0;
	private Object userDO;
	
	public static void getConnection() {

		try {
		   Class.forName("oracle.jdbc.driver.OracleDriver"); 
			      
			 String driver = "jdbc:oracle:thin:@221.156.35.218:1521:xe";
			      String userid = "project"; 
			      String userpwd = "gozldshsh";
			
		conn = DriverManager.getConnection(driver, userid , userpwd);
		
		if(conn!=null) {
		System.out.println("connected는 오지게 잘되네 ㅡ 와 세상에 젠장 db 올리고 commit 은 필수구나");
		}
		
		else {
			System.out.println("error");
		}
	} catch(Exception e) {
		e.printStackTrace();
		System.out.println(e);
	}
	
}
	
	//점포명을 가져온다  commit 은 필수... 
	public static String getBusiness(int b_num) {

		String bname = ""; 
		try {
			
			getConnection();
			
			String sql = "select * from business where b_num = ?"; 
			
			PreparedStatement psmt = conn.prepareStatement(sql); 
			psmt.setInt(1, b_num);
			
			ResultSet rs = psmt.executeQuery(); 
			
			if(rs.next()) {
				
				bname = rs.getString(2); 
			
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	
		return bname; 
	}
	
	
	//드라이버 정보를 가져온다 
	public static String getDriver(String u_id , int b_num) {
		String answer = ""; 

try {
			
			getConnection();
			
			String sql =  "select * from driver where d_id = (select d_id from order_t where u_id = ? and b_num=?)";
			
			PreparedStatement psmt = conn.prepareStatement(sql); 
			psmt.setString(1, u_id);
			psmt.setInt(2 , b_num); 
			
			ResultSet rs = psmt.executeQuery(); 
			
			if(rs.next()) {
				
				answer = rs.getString(1) + "&"; 
				answer = answer + rs.getString(3) + "&";
				answer = answer + rs.getString(4) + "&";   //별점은 나중에 .
			    answer = answer + rs.getString(5); 
				System.out.println(answer);
				
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
return answer; 
		
		
	}
	
	
	
	//드라이버 정보를 가져온다 
	public static int getStar(String driverName) {
		
		int answer = 0; 

try {
			
			getConnection();
			
			String sql =  "select * from reliability where d_id = ?";
			
			PreparedStatement psmt = conn.prepareStatement(sql); 
			System.out.println(driverName);
			psmt.setString(1, driverName);
			
			ResultSet rs = psmt.executeQuery(); 
			
			if(rs.next()) {
				
				answer = rs.getInt(2); 
				
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
return answer; 
		
	}
	
	
	
	//user_usageDetails 을 얻기 위한 order_num , d_name , b_name , date  
	
	public ArrayList<UserUsageDO> getUserUsage(String u_id) {
		
		UserUsageDO usage = null;
		
		ArrayList<UserUsageDO> usage_arr = new ArrayList<>() ; 
		
		try {
			getConnection();
			System.out.println(u_id);
			String sql="select o.order_date, d.d_name, o.b_name, o.order_num from (select o.order_date, o.d_id, b.b_name, o.order_num , o.u_id from order_t o, (select b_name, b_num from business) b where o.b_num = b.b_num) o, (select o.order_date, d.d_name, o.order_num from order_t o, (select d_name, d_id from driver) d where o.d_id = d.d_id) d where o.order_num = d.order_num and o.u_id = ?";
			
			PreparedStatement psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, u_id);
			
			ResultSet rs = psmt.executeQuery();
			
			while(rs.next()) {
				
				String date = rs.getString(1);
				String d_name = rs.getString(2);
				String b_name = rs.getString(3);
				int order_num = rs.getInt(4);
				
				usage = new UserUsageDO(order_num , d_name , b_name , date, null);
				usage_arr.add(usage);
				
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usage_arr;
	}
	
	public userDO Login(String u_id, String u_pw) {
		userDO u_do = null;
		
		try {
			getConnection();
			
			String sql="select * from user_t where u_id = ? and u_pw = ?";
			PreparedStatement psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, u_id);
			psmt.setString(2, u_pw);
			
			ResultSet rs = psmt.executeQuery();
			//executeUpdate : insert / update / delete 
			//Return : int(sql문 성공횟수)
			
			//executeQuery : select
			//Return : ResultSet(검색한 데이터)
			if(rs.next()) {
				//if : 검색한 데이터가 하나일 경우
				//while : 검색한 데이터가 여러개 일경우
				String get_u_id = rs.getString(1);
				String get_u_pw = rs.getString(2);
				String get_u_name = rs.getString(3);
				String get_u_num = rs.getString(4);
				
				u_do = new userDO(get_u_id, get_u_pw, get_u_name, get_u_num);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return u_do;
	}
	
	public driverDO Login_driver(String d_id, String d_pw) {
		driverDO d_do = null;
		
		try {
			getConnection();
			
			String sql="select * from driver where d_id = ? and d_pw = ?";
			PreparedStatement psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, d_id);
			psmt.setString(2, d_pw);
			
			ResultSet rs = psmt.executeQuery();
			//executeUpdate : insert / update / delete 
			//Return : int(sql문 성공횟수)
			
			//executeQuery : select
			//Return : ResultSet(검색한 데이터)
			if(rs.next()) {
				//if : 검색한 데이터가 하나일 경우
				//while : 검색한 데이터가 여러개 일경우
				String get_d_id = rs.getString(1);
				String get_d_pw = rs.getString(2);
				String get_d_name = rs.getString(3);
				String get_d_num = rs.getString(4);
				String get_e_id = rs.getString(5);
				String get_photo = rs.getString(6);
				
				d_do = new driverDO(get_d_id, get_d_pw, get_d_name, get_d_num, get_e_id, get_photo);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return d_do;
	}

	//10초 단위로 gps 를 생성한다. 
	
    public static void sendGPS(String gps , String d_id) {
    	
    	try {
			getConnection();

			String sql="update location set rt_location = ? where d_id = ? and order_num = 1000";
			PreparedStatement psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, gps);
			psmt.setString(2, d_id);
    	
    	
			int cnt = psmt.executeUpdate(sql); 
			if(cnt >0) System.out.println("gps ok");
    	
    } catch(Exception e) {
    	
    	e.printStackTrace();
    }
	
   }
	
    
	public enterpriseDO Login_enter(String e_id, String e_pw) {
		enterpriseDO e_do = null; 
		
		try {
			getConnection();
			
			String sql="select * from enterprise where e_id = ? and e_pw = ?";
			PreparedStatement psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, e_id);
			psmt.setString(2, e_pw);
			
			ResultSet rs = psmt.executeQuery();
			//executeUpdate : insert / update / delete 
			//Return : int(sql문 성공횟수)
			
			//executeQuery : select
			//Return : ResultSet(검색한 데이터)
			if(rs.next()) {
				//if : 검색한 데이터가 하나일 경우
				//while : 검색한 데이터가 여러개 일경우
				String get_e_id = rs.getString(1);
				String get_e_pw = rs.getString(2);
				String get_e_name = rs.getString(3);
				
				e_do = new enterpriseDO(get_e_id, get_e_pw, get_e_name);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return e_do;
	}

	public int Join(userDO u_do) {
		try {
			getConnection();
			
			String sql="insert into user values(?, ?, ?, ?)";
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, u_do.getU_id());
			psmt.setString(2, u_do.getU_pw());
			psmt.setString(3, u_do.getU_name());
			psmt.setString(4, u_do.getU_num());
			
			cnt = psmt.executeUpdate();	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cnt;
	}
	
	public int Join_driver(driverDO d_do) {
		// TODO Auto-generated method stub
		try {
			getConnection();
			
			String sql="insert into driver values(?, ?, ?, ?, ?, ?)";
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, d_do.getD_id());
			psmt.setString(2, d_do.getD_pw());
			psmt.setString(3, d_do.getD_name());
			psmt.setString(4, d_do.getD_num());
			psmt.setString(5, d_do.getE_id());
			psmt.setString(6, d_do.getPhoto());
			
			cnt = psmt.executeUpdate();	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cnt;
	}

	public int Join_enter(enterpriseDO e_do) {
		try {
			getConnection();
			
			String sql="insert into enterprise values(?, ?, ?)";
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, e_do.getE_id());
			psmt.setString(2, e_do.getE_pw());
			psmt.setString(3, e_do.getE_name());
			
			cnt = psmt.executeUpdate();	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cnt;
	}

	public ArrayList<enterpriseDO> getEnter() {
		enterpriseDO e_do = null;
		ArrayList<enterpriseDO> en_arr = new ArrayList<>();
		
		try {
			getConnection();
			
			String sql ="select e_id, e_name from enterprise";
			psmt = conn.prepareStatement(sql);
			
			ResultSet rs = psmt.executeQuery();
			
			while(rs.next()) {
				String getE_id = rs.getString(1);
				String getE_name = rs.getString(2);
				
				e_do = new enterpriseDO(getE_id, null ,getE_name);
				en_arr.add(e_do);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return en_arr;
	}

	public ArrayList<enter_main_viewerDO> enter_main(String e_id) { // 텍스트 마이닝 정보 없음 추후 추가 예정
		System.out.println(e_id);
		enter_main_viewerDO emv_do = null;
		ArrayList<enter_main_viewerDO> emv_arr = new ArrayList<>();
		
		try {
			getConnection();
			
			String sql = "select e.d_name, e.d_p_number, r.star_avg, e.photo from reliability r right outer join (select d_id, d_name, d_p_number, photo from driver where e_id = ?) e on e.d_id = r.d_id";
			//텍스트 마이닝 정보 없음
			PreparedStatement psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, e_id);
			
			ResultSet rs = psmt.executeQuery();
			
			while(rs.next()) {
				
				String d_name = rs.getString(1);
				String d_num = rs.getString(2);
				String star_avg = rs.getString(3);
				String photo = rs.getString(4);
				
				emv_do = new enter_main_viewerDO(d_name, d_num, star_avg, photo, null); 
				emv_arr.add(emv_do);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return emv_arr;
		
	}

	public int star_avg(String e_id) {
		ArrayList<enter_main_viewerDO> emv_arr = enter_main(e_id);
		
		int star =0;
		int n=0;
		for(int i=0;i<emv_arr.size();i++){
			if(emv_arr.get(i).getStar_rate()!=null){
				star+=Integer.parseInt(emv_arr.get(n).getStar_rate());
				n++;
			}
		}
		if(star==0) {
			return 0;
		}else {
			return star/=n;
		}
	}

	
	public ArrayList<reviewDO> get_review_driver(String d_id) { // 드라이버가 보는 리뷰
		System.out.println(d_id);
		reviewDO r_do = null;
		ArrayList<reviewDO> r_arr = new ArrayList<>();
		
		try {
			getConnection();
			
			String sql = "select star_rate, post from review where order_num in (select order_num from order_t where d_id = ?)";
			//텍스트 마이닝 정보 없음
			PreparedStatement psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, d_id);
			
			ResultSet rs = psmt.executeQuery();
			
			while(rs.next()) {
				
				String star_rate = rs.getString(1);
				String post = rs.getString(2);
				
				r_do = new reviewDO(star_rate, post); 
				r_arr.add(r_do);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return r_arr;
	}

	public ArrayList<UserUsageDO> review_page(String order){ // 리뷰페이지 정보 표시
		System.out.println("주문번호 : "+order);
		UserUsageDO u_u_do = null;
		ArrayList<UserUsageDO> u_u_arr = new ArrayList<>();
		
		try {
			getConnection();
			
			String sql = "select d.d_name, order_date, d.photo from (select order_date, d_id from order_t where order_num = ?) o, driver d where o.d_id = d.d_id";
			//텍스트 마이닝 정보 없음
			PreparedStatement psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, order);
			
			ResultSet rs = psmt.executeQuery();
			
			while(rs.next()) {
				
				String d_name = rs.getString(1);
				String date = rs.getString(2);
				String photo = rs.getString(3);
				
				u_u_do = new UserUsageDO(0, d_name, null, date, photo);
				u_u_arr.add(u_u_do);
				
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return u_u_arr;
	}
	
	


	public String get_u_id(String order_num) { // u_id 가져오기
		String answer = ""; 
		System.out.println("아이디 긁어올 번호 : "+order_num);

		try {
					
					getConnection();
					
					String sql =  "select u_id from order_t where order_num = ?";
					
					PreparedStatement psmt = conn.prepareStatement(sql); 
					System.out.println(order_num);
					psmt.setString(1, order_num);
					
					ResultSet rs = psmt.executeQuery(); 
					
					if(rs.next()) {	
					answer = rs.getString(1); 	
					}
					
				} catch(Exception e) {
					e.printStackTrace();
				}
		return answer; 
	}
	
	public int reviewInsert(reviewDO r_do) {
		int cnt = 0;
		
		String order_num = r_do.getOrder_num();
		String u_id = get_u_id(order_num);
		System.out.println("U_ID : "+u_id);
		System.out.println("별점"+r_do.getStar_rate());
		try {
			
			getConnection();

			String sql = "insert into review values (review_seq.nextval, ?, ?, ?, ?, sysdate)";

			
			psmt = conn.prepareStatement(sql);
		
			psmt.setString(1, u_id);
			psmt.setString(2, r_do.getOrder_num());
			psmt.setString(3, r_do.getStar_rate());
			psmt.setString(4, r_do.getPost());
			
			cnt = psmt.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	} 

	
	// 보류 합니다.
	
public ArrayList<reviewDO> userMyReview() {
	reviewDO vo = null;
	ArrayList<reviewDO> arr = new ArrayList<>();
	try {

	getConnection();

	String sql = "select * from REVIEW where u_id=?";
	//"select D_NAME,PHOTO from ORDER_T where ORDER_NUM=?";
	
	psmt = conn.prepareStatement(sql);
	
	ResultSet rs = psmt.executeQuery();
	
	
	
	while(rs.next()) {
			String post_num = rs.getString("POST_NUM");
			String photo = rs.getString("PHOTO");
			String d_name = rs.getString("D_NAME");
			String r_date = rs.getString("R_DATE");
			String post = rs.getString("POST");
			
		//	vo = new reviewDO(post_num, photo, d_name, r_date, post);
			//DO는 어떻게?
			arr.add(vo);
			
	}
	
	}catch (Exception e) {

	}
	return arr;
}

public ArrayList<driverDO> driverManagement() {
	driverDO vo = null;
	ArrayList<driverDO> arr = new ArrayList<>();
	try {

	getConnection();

	String sql = "select * from driver where e_id=?";
	
	psmt = conn.prepareStatement(sql);
	
	ResultSet rs = psmt.executeQuery();
	
	
	
	while(rs.next()) {
			String photo = rs.getString("photo");
			String d_id = rs.getString("d_id");
			String d_name = rs.getString("d_name");
			String d_num = rs.getString("d_num");

			
			vo = new driverDO(photo, d_id, d_name, d_num);
			arr.add(vo);
			
	}
	
	}catch (Exception e) {

	}
	return arr;
}

	
}
