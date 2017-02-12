package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.BufferedReader;




import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
//tab:flip `items`sales`prices!(`nut`bolt`cam`cog;6 8 0 3;10 20 15 20)
@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public static void main(String args[]) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... strings) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		while (true) {
			System.out.print("q) ");
			String sql = "q)" + br.readLine();
			try {
				jdbcTemplate.query(sql, (ResultSet rowSet) -> {
						ResultSetMetaData m;
						m = rowSet.getMetaData();
						int columnCount = m.getColumnCount();
						String fmt[] = new String[columnCount + 1];
						int width = 0;

						for (int i = 1; i <= columnCount; i++) {
							fmt[i] = "%-" + m.getColumnDisplaySize(i) + "s";
							System.out.format(fmt[i], m.getColumnLabel(i));
							width += rowSet.getMetaData().getColumnDisplaySize(i);
						}
						System.out.print("\n");

						for (int i = 1; i <= width; i++) {
							System.out.print("-");
						}
						System.out.print("\n");

						do{
							for (int i = 1; i <= columnCount; i++) {
								System.out.format(fmt[i], rowSet.getString(i));
							}
							System.out.print("\n");
							if (rowSet.getRow() > 10) {
								System.out.println("...");
								rowSet.afterLast();
							}
						}while (rowSet.next());
					});
			} catch (RuntimeException e) {
				if(e.getCause() instanceof SQLException) {
					System.out.println(e.getCause().getMessage());
				}
			}
		}
	}
}
