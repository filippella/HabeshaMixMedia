package org.dalol.habeshamixmedia.utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Filippo
 * @version 1.0.0
 * @since Sun, 10/06/2018 at 22:42.
 */
public class FileUtils {

    private FileUtils() {
    }

    public static boolean createDirectory(String path) {
        boolean directoryCreated = false;
        File directory = new File(path);
        if (!directory.exists()) {
            directoryCreated = directory.mkdir();
        }
        return directoryCreated;
    }

    public static void createFile(InputStream in, File file) {
        if (file.exists()) {
            return;
        }
        try {
            OutputStream out = new FileOutputStream(file);
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            out.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String readAsString(String path) {
        String json = "";
        try{
            BufferedReader reader = new BufferedReader(new FileReader(new File(path)));
            String line;
            StringBuilder builder = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            reader.close();
            json = builder.toString();
        } catch (Exception e) {}

        return json;
    }
}
