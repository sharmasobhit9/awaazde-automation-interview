package managers;
import DataProviders.ConfigReader;

public class FileReader {
    private static FileReader fileReaderManager = new FileReader();
    private static ConfigReader configFileReader;

    public FileReader() {
    }

    public static FileReader getInstance( ) {
        return fileReaderManager;
    }

    public ConfigReader getConfigReader() {
        return (configFileReader == null) ? new ConfigReader() : configFileReader;
    }
}
