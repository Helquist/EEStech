import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Session;
import com.datastax.spark.connector.cql.CassandraConnector;
import com.datastax.spark.connector.japi.CassandraJavaUtil;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.sql.SparkSession;

public class BatchRunner {

    public static final String CASSANDRA_KEYSPACE = "cassandra_keyspace";

    public static void main(String[] args) {

        SparkConf sparkConf = new SparkConf(true);
        sparkConf.setAppName("twitter-hackaton-mashup");
        sparkConf.setMaster("local[*]");
        sparkConf.set("spark.cassandra.connection.host", "localhost");
        sparkConf.set("spark.cassandra.connection.port", "9042");


        SparkContext sparkContext = new SparkContext(sparkConf);

        exampleJob(sparkConf, sparkContext);

    }

    public static void exampleJob(SparkConf sparkConf, SparkContext sparkContext) {
        long counter = CassandraJavaUtil.javaFunctions(sparkContext)
                .cassandraTable(CASSANDRA_KEYSPACE, "sample_table")
                .count();

        System.out.println("Size of processed resultset: " + counter);

        SparkSession sparkSession = SparkSession
                .builder()
                .config(sparkConf)
                .getOrCreate();

        CassandraConnector connector = CassandraConnector.apply(sparkSession.sparkContext().conf());
        Session session = connector.openSession();
        BoundStatement bs = session.prepare("insert into cassandra_keyspace.sample_table " +
                "(column1, column2) " +
                "values (?, ?)")
                .bind("value for column 1", "value for column 2");
        session.execute(bs);
    }
}
