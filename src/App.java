import java.io.FileInputStream;
import java.util.Properties;

public class App {
    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/App.Properties"));
        final String OPENAI_API_KEY = properties.getProperty("OPENAI_API_KEY");
        System.out.println(OPENAI_API_KEY);
    }
}
