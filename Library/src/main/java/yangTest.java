import beans.*;
import com.alibaba.fastjson.JSON;
import dao.AdminMapper;
import dao.BasicMapper;
import dao.BookMapper;
import dao.UserMapper;
import org.apache.ibatis.session.SqlSession;
import utils.MybatisUtils;
import utils.ParameterTool;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class yangTest {
    private static SqlSession sqlSession = MybatisUtils.getSqlSession();
    public static void userTest() {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        try {
            Map<String,String> login= new HashMap<>();
            login.put("userId","31930");
            login.put("userPwd","123");
            String userList = mapper.userLogin(login);
            System.out.println(userList);
        }
        catch (Exception e) {
            e.printStackTrace();
        } finally {
//            sqlSession.commit();
            sqlSession.close();
        }

    }

    public static void getOneBook(){
        BookMapper bookMapper = sqlSession.getMapper(BookMapper.class);
        try {
            List<BookDetail> result = bookMapper.getBookByIsbn("isbn");
            //result.forEach(System.out::println);
            System.out.print(JSON.toJSONString(result));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            sqlSession.commit();
            sqlSession.close();
        }
    }

    public static void bookUp(){

        BookMapper bookMapper = sqlSession.getMapper(BookMapper.class);
        try {
            String bookId = "[\"2222\",\"2223\"]";
            String book1 = bookId.replaceAll("\"","");
            String[] book = ParameterTool.toStringArry(book1);
            System.out.println(Arrays.toString(book));
            System.out.println(bookId);
//            for (int i = 0; i < book.length; i++) {
//                System.out.println(book[i]);
//            }
            int result = bookMapper.setBookUp(book);
//            int result = bookMapper.setBookDown(book);
            System.out.println(result);
            //System.out.print(JSON.toJSONString(result));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.commit();
            sqlSession.close();
        }
    }
    public static void getTotalStudent(){
        SqlSession session = MybatisUtils.getSqlSession();
        BasicMapper basicMapper = session.getMapper(BasicMapper.class);
        List<User> users = basicMapper.getAllStudent();
        if(users != null){
            System.out.println(JSON.toJSONString(users));
        }
        session.close();
    }
    public static void getAllBook(){
        SqlSession session = MybatisUtils.getSqlSession();
        String contentJSON = "-1";
        BookMapper bookMapper = session.getMapper(BookMapper.class);
        List<Book22> books = bookMapper.selectAllBook();
        if (books != null)
            contentJSON = JSON.toJSONString(books);
        System.out.println(contentJSON);
    }
    public static void setBooking(){
        SqlSession session = MybatisUtils.getSqlSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        int count = userMapper.getNoReturnBook("1119011");
        if(count == 0){
            //??????????????????
            Map<String, String> map = new HashMap<String, String>();
            map.put("userId","1119011");
            map.put("bookIsbn", "1");
            int no = userMapper.getNoBooking(map);
            if(no == 0){
                //???????????????
                int yes = userMapper.setBookingInfo(map);
                session.commit();
                if(yes != 0){
                    //??????????????????
                    int hhh = userMapper.getBookingCount("1");
                    //??????hhh,??????
                    System.out.println(hhh);
                }
                else{
                    //??????-3
                }
                System.out.println("?????????????????????"+yes);
            }
            else{
                //??????-2
            }
            System.out.println("?????????????????????"+no);
        }
        else
        {
            //??????-1
        }
        System.out.println("????????????????????????"+count);
    }
    public static void getBookingInfo(){
        SqlSession session = MybatisUtils.getSqlSession();
        String contentJSON = "-1";
        UserMapper bookMapper = session.getMapper(UserMapper.class);
        List<BookingInfo> books = bookMapper.getBookingInfo("1119011");
        if (books != null)
            contentJSON = JSON.toJSONString(books);
        System.out.println(contentJSON);
    }
    public static void delSubscribe(){
        SqlSession session = MybatisUtils.getSqlSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        Map<String, String> map = new HashMap<String, String>();
        map.put("userId","1119011");
        map.put("bookIsbn","isbn11");
        int count = userMapper.delSubscribe(map);
        System.out.println(count);
    }
    public static void editPwd(){
        SqlSession session = MybatisUtils.getSqlSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        Map<String, String> map = new HashMap<String, String>();
        map.put("userId","1119011");
        map.put("newPassword","1119011");
        map.put("oldPassword","11");
        int count = userMapper.editPwd(map);
        System.out.println(count);
    }
    public static void getTicket(){
        SqlSession session = MybatisUtils.getSqlSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        int count = userMapper.getNoTicket("1119011");
        System.out.println(count);

    }
    public static void getRule1(){
        SqlSession session = MybatisUtils.getSqlSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        int count = userMapper.getBorrowBookCount("1119011");
        System.out.println(count);
        System.out.println("??????"+Rule.studentMaxTime+"???"+Rule.studentMaxCount);
    }
    public static void setOneBook(){
        SqlSession session = MybatisUtils.getSqlSession();
        try{
            UserMapper userMapper = session.getMapper(UserMapper.class);
            int count1 = userMapper.editBookStatus("10978730220275214781");
            System.out.println("?????????????????????"+count1);
            if(count1 != 0){
                Map<String, String> map = new HashMap<String, String>();
                map.put("userId","1119011");
                map.put("bookId","10978730220275214781");
                float count0 = userMapper.getDay(map);
                System.out.println("?????????"+count0);
                int count2 = userMapper.setReturnBookTime("borrowId");
                if(count2 != 0){
                    System.out.println("???????????????"+count2);
                    String bookIsbn = userMapper.getOneBookIsbn("10978730220275214781");
                    System.out.println("isbn:"+bookIsbn);
                    Map<String, String> map1 = new HashMap<String, String>();
                    map1.put("userId","1119011");
                    map1.put("bookIsbn",bookIsbn);
                    String oneBookingId = userMapper.getFirstBooking(map1);
                    System.out.println(oneBookingId);
                    if(bookIsbn != null && oneBookingId != null){
                        int count3 = userMapper.setBookingStatus(oneBookingId);
                        System.out.println("???????????????"+count3);
                        if(count0 < 0){
                            Map<String, String> map2 = new HashMap<String, String>();
                            double sum = -Rule.lateRatio*count0;
                            System.out.println("?????????"+sum);
                            map2.put("userId","1119011");
                            map2.put("bookId","10978730220275214781");
                            map2.put("ticketMoney", String.valueOf(sum));
                            int count4 = userMapper.setUserTicketInfo(map2);
                            System.out.println("?????????????????????"+count4);
                        }
                    }
                }
                session.commit();
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        finally {
            session.close();
        }
    }
    public static void getBorrowInfo1(){
        SqlSession session = MybatisUtils.getSqlSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        List<BorrowInfo> count = userMapper.getBorrowInfo("1119011");
        System.out.println(JSON.toJSONString(count));
    }
    public static void setBorrow1(){
        SqlSession session = MybatisUtils.getSqlSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        Map<String, String> map = new HashMap<String, String>();
        map.put("userId","1119011");
        map.put("borrowId","3");
        map.put("time", String.valueOf(Rule.addTime));
        int count = userMapper.getAddBorrowTime1(map);
        List<BorrowInfo> borrowInfo = userMapper.getUserBorrowBook("3");
        System.out.println(JSON.toJSONString(borrowInfo));
        session.commit();
        session.close();
        System.out.println(count);
    }
    public static void getMoney(){
        SqlSession session = MybatisUtils.getSqlSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        String count = userMapper.getAllMoney("1119011");
        if(count != null){
            System.out.println(count);
        }
        else
        {
            System.out.println(0);
        }
    }
    public static void setMoney(){
        SqlSession session = MybatisUtils.getSqlSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        int count = userMapper.setAllMoney("1119011");
        System.out.println(count);
    }
    public static void tuiJian(){
        SqlSession session = MybatisUtils.getSqlSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        int count = userMapper.getTwoRecommendBook("4");
        if(count == 0){
            System.out.println(count);
            Map<String, String> map = new HashMap<String, String>();
            map.put("bookIsbn","4");
            map.put("bookName","3");
            map.put("bookAuthor","11");
            map.put("bookMoney","111");
            map.put("bookPress","1111");
            map.put("bookPulicTime","2017/1/1");
            map.put("bookTypeId","12121");
            map.put("userId","1119011");
            int count1 = userMapper.setRecommendBook(map);
            session.commit();
            if(count1 == 1){
                System.out.println("yes");
            }
            else{
                System.out.println("no");
            }
        System.out.println(count);
        }
        else{
            System.out.println(-1);
        }
        session.close();
    }
    public static void showJG(){
        SqlSession session = MybatisUtils.getSqlSession();
        AdminMapper adminMapper = session.getMapper(AdminMapper.class);
        List<RecommendationSet> recommendationList = adminMapper.recommendationInfo();
        System.out.println(JSON.toJSONString(recommendationList));
    }
    public static void getRule12(){
        Map<String, String> map = new HashMap<String, String>();
        map.put("teacherMaxCount", String.valueOf(Rule.teacherMaxCount));
        map.put("studentMaxCount", String.valueOf(Rule.studentMaxCount));
        map.put("teacherMaxTime", String.valueOf(Rule.teacherMaxTime));
        map.put("studentMaxTime", String.valueOf(Rule.studentMaxTime));
        map.put("lateRatio", "(?????????????????? - ????????????)*"+String.valueOf(Rule.lateRatio));
        map.put("addTime", String.valueOf(Rule.addTime));
        System.out.println(JSON.toJSONString(map));
    }
    public static void setRule12(){
        Rule.lateRatio=0.2;
        Rule.studentMaxCount=10;
        Rule.studentMaxTime=20;
        Rule.teacherMaxCount=11;
        Rule.teacherMaxTime=22;
        Rule.addTime=12;
    }
    public static void setBook12(){
        SqlSession session = MybatisUtils.getSqlSession();
        AdminMapper adminMapper = session.getMapper(AdminMapper.class);
        List<Recommendation> list = adminMapper.getRecommendationByIsbn("4");
        System.out.println(JSON.toJSONString(list));
        System.out.println(list.get(0).getBookAuthor());
        System.out.println(list.get(0).getBookIsbn());
        System.out.println(list.get(0).getBookMoney());
        System.out.println(list.get(0).getBookName());
        System.out.println(list.get(0).getBookPress());
        System.out.println(list.get(0).getBookPublicTime());
        System.out.println(list.get(0).getBookTypeId());
        System.out.println(list.get(0).getBookTypeId()+list.get(0).getBookIsbn()+(int)(1+Math.random()*(10-1+1)));
        Map<String, String> map = new HashMap<String, String>();
        map.put("bookAuthor",list.get(0).getBookAuthor());
        map.put("bookIsbn",list.get(0).getBookIsbn());
        map.put("bookMoney",list.get(0).getBookMoney());
        map.put("bookName",list.get(0).getBookName());
        map.put("bookPress",list.get(0).getBookPress());
        map.put("bookPublicTime",list.get(0).getBookPublicTime());
        map.put("bookId",list.get(0).getBookTypeId()+list.get(0).getBookIsbn()+(int)(1+Math.random()*(10-1+1)));
        map.put("bookTypeId",list.get(0).getBookTypeId());
        map.put("bookRackId",list.get(0).getBookTypeId());
        int count = adminMapper.setBookByInfo(map);
        session.commit();
        session.close();
        System.out.println(count);

    }
    public static void delOneRecommendationInfo1(){
        SqlSession session = MybatisUtils.getSqlSession();
        AdminMapper adminMapper = session.getMapper(AdminMapper.class);
        int count = adminMapper.delOneRecommendationInfo("3");
        session.commit();
        if(count != 0){
            System.out.println("yes");
        }
        else {
            System.out.println("no");
        }
    }
    public static void main(String[] args) {
        //userTest();//????????????
        //getOneBook();//????????????????????????
        //bookUp();//????????????
        //getTotalStudent();//??????????????????
        //getAllBook();
        //setBooking();//??????
        //getBookingInfo();//????????????
        //delSubscribe();//????????????
        //editPwd();//??????????????????
        //getTicket();//?????????????????????
        //getRule1();//????????????
//        setOneBook();//??????
        //setOneBook();//??????
//        getBorrowInfo1();//????????????
        //setBorrow1();//????????????
        //getMoney();//??????????????????
        //setMoney();//????????????
        //tuiJian();//??????
        //showJG();//????????????
//        getRule12();//?????????????????????
//        setRule12();//?????????????????????
        setBook12();//?????????????????????
//        delOneRecommendationInfo1();
    }
}
