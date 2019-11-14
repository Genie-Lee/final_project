package com.DataAccessObject;

import java.sql.*;
import java.util.ArrayList;
import java.util.Random;

import javax.websocket.Session;

import com.DataObject.UserUsageDO;
import com.DataObject.driverDO;
import com.DataObject.enter_main_viewerDO;
import com.DataObject.reviewDO;
import com.DataObject.review_viewerDO;
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
			      
			 String driver = "jdbc:oracle:thin:@10.10.46.255:1521:xe";
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
	public static driverDO getDriver(String u_id , int b_num) {
		driverDO d_do = null; 

		try {
			
			getConnection();
			
			String sql =  "select d.d_id, d.d_pw, d.d_name, d.d_p_number, e.e_name, d.photo from (select * from driver where d_id = (select d_id from order_t where u_id = ? and b_num=?)) d, enterprise e where d.e_id = e.e_id";
			
			PreparedStatement psmt = conn.prepareStatement(sql); 
			psmt.setString(1, u_id);
			psmt.setInt(2 , b_num); 
			
			ResultSet rs = psmt.executeQuery(); 
			
			if(rs.next()) {
				
				String d_id = rs.getString(1); 
				String d_name = rs.getString(3);
				String d_num = rs.getString(4);   //별점은 나중에 .
			    String e_id = rs.getString(5);  //
			    String photo = rs.getString(6); 
				
				d_do = new driverDO(d_id, null, d_name, d_num, e_id, photo);			
							
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return d_do;
		
		
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
	
	public ArrayList<reviewDO> get_review(String u_id) { // 리뷰가 있는지 확인용
		reviewDO r_do = null;
		ArrayList<reviewDO> r_arr = new ArrayList<>();
		try {
			getConnection();
			String sql="select order_num from review where u_id = ? order by order_num desc";
			
			PreparedStatement psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, u_id);
			
			ResultSet rs = psmt.executeQuery();
			
			while(rs.next()) {
				
				String order_num = rs.getString(1);
				
				r_do = new reviewDO(null, order_num, null, null);
				r_arr.add(r_do);
				
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return r_arr;
	}
	
	public ArrayList<UserUsageDO> getUserUsage(String u_id) {
		
		UserUsageDO usage = null;
		
		ArrayList<UserUsageDO> usage_arr = new ArrayList<>() ; 
		
		try {
			getConnection();
			System.out.println(u_id);
			String sql="select o.order_date, d.d_name, o.b_name, o.order_num from (select o.order_date, o.d_id, b.b_name, o.order_num , o.u_id from order_t o, (select b_name, b_num from business) b where o.b_num = b.b_num) o, (select o.order_date, d.d_name, o.order_num from order_t o, (select d_name, d_id from driver) d where o.d_id = d.d_id) d where o.order_num = d.order_num and o.u_id = ? order by o.order_num desc";
			
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
	
	public int get_order_num(String user_id) {
		int order_num = 0;
		System.out.println(user_id);
		orderDO o_do = null;
		ArrayList<orderDO> arr = new ArrayList<>();
		try {
			getConnection();
			
			String sql="select order_num from order_t where u_id = ?";
			PreparedStatement psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, user_id);
			
			ResultSet rs = psmt.executeQuery();
			
			while(rs.next()) {
				int order_num1 = rs.getInt(1);
				

				System.out.println(order_num1);
				o_do = new orderDO(order_num1, null, null, 0, null, 0, null);
				arr.add(o_do);
				
			}
		Random ran = new Random();
		int size = arr.size();
		order_num += arr.get(ran.nextInt(size)).getOrder_num();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return order_num;
	}
	
	public orderDO get_order(String user_id) {
		orderDO o_do = null;
		
		String get_order_num = String.valueOf(get_order_num(user_id));
		System.out.println("주문번호"+get_order_num);
		try {
			getConnection();
			
			String sql="select * from order_t where order_num = ?";
			PreparedStatement psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, get_order_num);
			
			ResultSet rs = psmt.executeQuery();

			if(rs.next()) {
				//if : 검색한 데이터가 하나일 경우
				//while : 검색한 데이터가 여러개 일경우
				int order_num = rs.getInt(1);
				String u_id = rs.getString(2);
				String d_id = rs.getString(3);
				int b_num = rs.getInt(4);
				String destination = rs.getString(5);
				int estimated_time = rs.getInt(6);
				String date = rs.getString(7);
				
				o_do = new orderDO(order_num, d_id, u_id, b_num, destination, estimated_time, date);
			
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o_do;
	}
	
	public userDO Login(String u_id, String u_pw) {
		userDO u_do = null;
		
		try {
			getConnection();
			
			String sql="select * from (select u.u_id, u.u_pw, u.u_name, u.u_p_number from(select u.rnum, u.u_id, u.u_pw, u.u_name, u.u_p_number from (select rownum as rnum, u_id, u_pw, u_name, u_p_number from user_t) u where rownum <=10) u where rnum >= 1) where u_id = ? and u_pw = ?";
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

	public String d_main_get_e_name(String e_id) {
		String e_name = "";
		
		try {
			getConnection();
			
			String sql = "select e_name from enterprise where e_id = ?";
			//텍스트 마이닝 정보 없음
			PreparedStatement psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, e_id);
			
			ResultSet rs = psmt.executeQuery();
			
			if(rs.next()) {
				
				e_name = rs.getString(1);
				
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return e_name;
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
			
			String sql = "select star_rate, post from (select rownum rnum, star_rate, post from (select star_rate, post, order_num from review where order_num in (select order_num from order_t where d_id = ?) order by order_num desc) where rownum <=20) where rnum >= 1";
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

			String sql = "insert into review values (review_seq.nextval, ?, ?, ?, ?, sysdate, null)";

			
			psmt = conn.prepareStatement(sql);
		
			psmt.setString(1, u_id);
			psmt.setString(2, order_num);
			psmt.setString(3, r_do.getStar_rate());
			psmt.setString(4, r_do.getPost());
			
			cnt = psmt.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	} 

	public ArrayList<review_viewerDO> userMyReview(String u_id) {
		review_viewerDO rv_do = null;
		ArrayList<review_viewerDO> rv_arr = new ArrayList<>();
		try {
	
		getConnection();
	
		String sql = "select d_name, photo, r_date, star_rate, post from (select r.u_id, d.d_name, d.photo, r.r_date, r.star_rate, r.post from review r, (select d.d_name, d.photo, o.order_num from order_t o, driver d where o.d_id = d.d_id)d where r.order_num = d.order_num) where u_id = ? order by r_date desc";
		//"select D_NAME,PHOTO from ORDER_T where ORDER_NUM=?";
		
		PreparedStatement psmt = conn.prepareStatement(sql); 
		psmt.setString(1, u_id);
		
		ResultSet rs = psmt.executeQuery(); 
		
		while(rs.next()) {
			
			String d_name = rs.getString(1);
			String photo = rs.getString(2);
			String r_date = rs.getString(3);
			String star_rate = rs.getString(4);
			String post = rs.getString(5);
			
			rv_do = new review_viewerDO(d_name, photo, r_date, star_rate, post);
			rv_arr.add(rv_do);
					
		}
		
		}catch (Exception e) {
	
		}
		return rv_arr;
	}

	public ArrayList<review_viewerDO> another_user_review(String d_id) {
		
		System.out.println("드라이버 아이디"+d_id);
		review_viewerDO rv_do = null;
		ArrayList<review_viewerDO> rv_arr = new ArrayList<>();
		
		
		try {
	
		getConnection();
	
		String sql = "select r.d_name, r.photo, r.r_date, r.star_rate, r.post from (select rownum as rnum, r.d_name, r.photo, r.r_date, r.star_rate, r.post from (select d.d_name, d.photo, r.r_date, r.star_rate, r.post, r.post_num from review r, (select d.d_id, d.photo, d.d_name, o.u_id from driver d, (select d_id, u_id from order_t where order_num in (select order_num from review where d_id = ?)) o where d.d_id = o.d_id) d where r.u_id = d.u_id order by r_date desc) r where rownum <=20) r where r.rnum >= 1";
		//"select D_NAME,PHOTO from ORDER_T where ORDER_NUM=?";
		
		PreparedStatement psmt = conn.prepareStatement(sql); 
		psmt.setString(1, d_id);

		
		ResultSet rs = psmt.executeQuery(); 
		
		while(rs.next()) {
			
			String d_name = rs.getString(1);
			String photo = rs.getString(2);
			String r_date = rs.getString(3);
			String star_rate = rs.getString(4);
			String post = rs.getString(5);
			
			rv_do = new review_viewerDO(d_name, photo, r_date, star_rate, post);
			rv_arr.add(rv_do);
					
		}
		
		}catch (Exception e) {
	
		}
		return rv_arr;
	}

	
	
	
	public int page_count(String d_id) { // 보류
		int totalCount = 0;
		try {
			
			getConnection();
			
			String sql =  "select count(*) as totalCount from (select d.d_name, d.photo, r.r_date, r.star_rate, r.post from review r, (select d.d_id, d.photo, d.d_name, o.u_id from driver d, (select d_id, u_id from order_t where order_num in (select order_num from review where d_id = ?)) o where d.d_id = o.d_id) d where r.u_id = d.u_id) ";
			
			PreparedStatement psmt = conn.prepareStatement(sql); 
			psmt.setString(1, d_id);
			
			ResultSet rs = psmt.executeQuery(); 
			
			if(rs.next()) {	
			totalCount = rs.getInt(1); 	
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return totalCount; 
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
