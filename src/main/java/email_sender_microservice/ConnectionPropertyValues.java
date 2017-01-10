package email_sender_microservice;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

public class ConnectionPropertyValues {

    private HashMap<String, String> connectionProperties = new HashMap<>();
    private InputStream inputStream;

    /**
     * Reads properties from connection.properties file which should be located under resources/connection.
     * <p>
     * Holds the properties in a HashMap, every value is using the same key as its in the connection.properties.
     *
     * @return HashMap containing the properties for the database and the email.
     * @exception IOException
     * @see IOException
     * @see HashMap
     * @see InputStream
     */
    // get the email connection properties from connection/connection.properties config file
    public HashMap<String, String> getPropValuesOfEmail() {
        try {
            Properties prop = new Properties();
            String propFileName = "connection/connection.properties";

            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {
                prop.load(inputStream);
            }

            // Database properties
            connectionProperties.put("url", prop.getProperty("url"));
            connectionProperties.put("database", prop.getProperty("database"));
            connectionProperties.put("user", prop.getProperty("user"));
            connectionProperties.put("password", prop.getProperty("password"));

            // Email properties
            connectionProperties.put("sender_email", prop.getProperty("sender_email"));
            connectionProperties.put("host", prop.getProperty("host"));
            connectionProperties.put("sender_password", prop.getProperty("sender_password"));

        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return connectionProperties;
    }
}
