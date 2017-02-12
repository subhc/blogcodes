package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private Map<String, Double> stocksList;
    private Method executeQuery;

    public static void main(String args[]) {
        SpringApplication.run(Application.class, args);
    }



    @Override
    public void run(String... strings) {

        stocksList = getStockList();

        try {
            executeQuery = Class.forName("jdbc$co").getMethod("ex", String.class, Object[].class);
        } catch (NoSuchMethodException | ClassNotFoundException e) {
            System.err.println("kdb+ JDBC driver has not loaded properly");
            e.printStackTrace();
            System.exit(1);
        }

        while(true) {


            try(Connection c = jdbcTemplate.getDataSource().getConnection()){
                executeQuery.invoke(c.getMetaData().getConnection(), "upd", new Object[]{"quote", getDummyData()});
                Thread.sleep(1000);

            } catch (  IllegalAccessException | InvocationTargetException | InterruptedException | SQLException e) {
                e.printStackTrace();
            }
        }

    }

    private Object[] getDummyData() {
        List<Object[]> l = new ArrayList<>();
        stocksList.forEach((k,v) -> l.add(new Object[]{k, v - 2*Math.round(100*Math.random())/100.0, v + 2*Math.round(100*Math.random())/100.0}) );
        return l.toArray();
    }

    private Map<String, Double> getStockList() {
        Map<String, Double> ret = new HashMap<>();
        try {
            Properties props = new Properties();
            FileInputStream in = new FileInputStream(new ClassPathResource("stocks.list").getFile());
            props.load(in);
            props.forEach((k,v) -> ret.put((String)k, Double.parseDouble((String)v)));
            in.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return ret;
    }
}
