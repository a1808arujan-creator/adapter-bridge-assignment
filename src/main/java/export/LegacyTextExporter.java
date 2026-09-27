package export;

import java.nio.charset.StandardCharsets;

public class LegacyTextExporter {

    public static final int SUCCESS = 0;
    public static final int INVALID_PATH = -1;
    public static final int WRITE_ERROR = -2;

    public int saveLegacy(
            String path,
            byte[] data,
            boolean overwrite) {

        if (path == null || path.isBlank()) {
            return INVALID_PATH;
        }

        if (data == null || data.length == 0) {
            return WRITE_ERROR;
        }

        System.out.println("Legacy text export: " + path);

        System.out.println(
                new String(data, StandardCharsets.UTF_8)
        );

        return SUCCESS;
    }
}