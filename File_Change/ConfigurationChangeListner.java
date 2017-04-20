import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

public class ConfigurationChangeListner implements Runnable {
    private String configFileName = null;
    private String fullFilePath = null;

    public ConfigurationChangeListner(final String filePath) {
        this.fullFilePath = filePath;
    }

    public void run() {
        try {
            register(this.fullFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void register(final String file) throws IOException {
        final int lastIndex = file.lastIndexOf("/");
        String dirPath = file.substring(0, lastIndex + 1);
        String fileName = file.substring(lastIndex + 1, file.length());
        this.configFileName = fileName;

        configurationChanged(file);
        startWatcher(dirPath, fileName);
    }

    private void startWatcher(String dirPath, String file) throws IOException {
        final WatchService watchService = FileSystems.getDefault()
                .newWatchService();
        Path path = Paths.get(dirPath);
        path.register(watchService, ENTRY_MODIFY);

        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                try {
                    watchService.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        WatchKey key = null;
        while (true) {
            try {
                key = watchService.take();
                for (WatchEvent<?>  event : key.pollEvents()) {
                    if (event.context().toString().equals(configFileName)) {
                        configurationChanged(dirPath + file);
                    }
                }
                boolean reset = key.reset();
                if (!reset) {
                    System.out.println("Could not reset the watch key.");
                    break;
                }
            } catch (Exception e) {
                System.out.println("InterruptedException: " + e.getMessage());
            }
        }
    }

    public void configurationChanged(final String file) {
        System.out.println("Refreshing the configuration.");
        ApplicationConfiguration.getInstance().initilize(file);
    }
}
