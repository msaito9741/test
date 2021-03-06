package ms.MyBatisSample;

import java.io.Reader;
import java.util.Date;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import ms.MyBatisSample.entity.jsv.Cities;
import ms.MyBatisSample.entity.jsv.Weather;
import ms.MyBatisSample.mapper.jsv.CitiesMapper;
import ms.MyBatisSample.mapper.jsv.WeatherMapper;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        // resources直下のmybatis-config.xmlを読み込みます(1)
        try (Reader r = Resources.getResourceAsReader("mybatis-config.xml");) {
            // 読み込んだ設定ファイルからSqlSessionFactoryを生成します(2)
            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(r);

            // SQLセッションを取得します(3)
            try (SqlSession session = factory.openSession(true)) {
                 // テーブルのMapperを取得します(4)
                CitiesMapper map = session.getMapper(CitiesMapper.class);
                // テーブルの主キー（actor_id)が１であるレコードを検索します(5)
                Cities cities = map.selectByPrimaryKey(args[0]);

                // 取得した内容を確認します
                System.out.println("cities.getCityId     = " + cities.getCityId());
                System.out.println("cities.getCityName   = " + cities.getCityName());
                System.out.println("cities.getLocation   = " + cities.getLocation());

                int val;
                if (Integer.parseInt(args[0])>120) {
                	val = 100;
                }
                else {
                	val = Integer.parseInt(args[0]);
                }

                boolean bRet = testBol(val);
            	if (bRet) {
            		System.out.println("testBol--->true");
            	}
            	else {
            		System.out.println("testBol--->false");
            	}

            	WeatherMapper wm = session.getMapper(WeatherMapper.class);
            	Weather prm = new Weather();
            	prm.setCityId("120");
            	prm.setCityName("横浜市");
            	prm.setWeatherNo(1);
            	prm.setTempLo(101);
            	prm.setTempHi( 201);
             	prm.setPrcp(151);
             	prm.setDate(new Date());
                //int iRet = wm.insert(prm);
                int iRet = wm.updateByPrimaryKey(prm);
                //System.out.println("Insert--->"+iRet+"件");
                System.out.println("Update--->"+iRet+"件");


//                CitiesExample ex = new CitiesExample();
//                ex.setDistinct(false);
//                ex.setOrderByClause("city_Id");
//                List<Cities> cities2 = map.selectByExample(ex);
//                for(int i=0;i<cities2.size();i++) {
//	            	System.out.println("cities.getCityId     = " + cities2.get(i).getCityId());
//	            	System.out.println("cities.getCityName   = " + cities2.get(i).getCityName());
//	            	System.out.println("cities.getLocation   = " + cities2.get(i).getLocation());
//                }
            }
        } catch (Exception e) {
            e.printStackTrace();
       }
    }
    private static boolean testBol(int mode) {
    	if (mode >120) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
}
