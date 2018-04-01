package ms.MyBatisSample;

import java.io.File;

import org.dbunit.Assertion;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import mockit.Mock;


/**
 * Unit test for simple App.
 */
public class AppTest
    extends TestCase
{

	//@InjectMocks
	private App ap1;
	private App ap2;
	private static IDatabaseTester databaseTester;
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }


    /**
     * [前処理]<br>
     * DBに事前データを準備する。<br>
     * <br>
     * @throws java.lang.Exception
     */
    @Before
	protected void setUp() throws Exception{
    	//MockitoAnnotations.initMocks(this);
		ap1 = new App();	// case1
		//ap2 = new App();	// case2
		databaseTester = new JdbcDatabaseTester("org.postgresql.Driver",
                "jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres", "jsv");

        // --------------------------------------
        // テストデータ投入
        // --------------------------------------
        IDataSet dataSet = new FlatXmlDataSetBuilder().build(new File("./data/Before.xml"));
        databaseTester.setDataSet(dataSet);
        // DELETE→INSERTで事前準備データを用意する
        databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
                databaseTester.onSetup();

	}
    /**
     * [後処理]<br>
     * テスト後の後処理を行う。<br>
     * DBUnitの後片付けを行う。<br>
     * <br>
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        databaseTester.setTearDownOperation(DatabaseOperation.NONE);
        databaseTester.onTearDown();
    }

    /**
     * [テスト]<br>
     * DBUnitを使用して、DBの更新結果を検証する。<br>
     */
    //@Test
    public void test() {
    	System.out.println("JUnit + DBUnitによるテスト開始。");

		String[] prm1 = {"120"};
		ap1.main(prm1);
       //TestMain.main(null);

        try {
            // ----------------------------------
            // DBUnitで更新後データチェック
            // ----------------------------------
            IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("./data/After.xml"));
            ITable expectedTable = expectedDataSet.getTable("weather");

            IDataSet databaseDataSet = databaseTester.getConnection().createDataSet();
            ITable actualTable = databaseDataSet.getTable("weather");

            // 時間に対するAssertionはほぼ確実に失敗するので検証対象から除外する
            ITable filteredExpectedTable = DefaultColumnFilter.excludedColumnsTable(
                    expectedTable, new String[]{"date"});
            ITable filteredActualTable;
            filteredActualTable = DefaultColumnFilter.excludedColumnsTable(
                    actualTable, new String[]{"date"});

            // ---------------------------------------------------------------
            // 更新結果の検証はJUnitではなくDBUnitのAssertionを使用する
            // ---------------------------------------------------------------
            Assertion.assertEquals(filteredExpectedTable, filteredActualTable);
//            assertEquals(ap1.testBol(Integer.parseInt(prm1[0])), 120);

        } catch (Exception e) {
        	System.out.println("エラー"+ e);
            fail("予期しないエラーでテストが失敗しました。");
        }

        System.out.println("JUnit + DBUnitによるテスト終了。");
    }

    @Mock
    private boolean methodTestBol(int mode) {
        return true;
    }

//	public void testMain01() {
//		String[] prm1 = {"123"};
//		ap1.main(prm1);
//		//assertTrue(ap1.main(prm1));
//	}
//	public void testMain02() {
//		String[] prm1 = {"120"};
//		ap2.main(prm1);
//		//assertTrue(ap1.main(prm1));
//	}

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
}
