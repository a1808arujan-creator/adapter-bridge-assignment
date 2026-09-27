package export;

import java.nio.charset.StandardCharsets;

public class LegacyExportAdapter implements ExportEngine {

    private final LegacyTextExporter legacyExporter;

    public LegacyExportAdapter(
            LegacyTextExporter legacyExporter) {

        this.legacyExporter = legacyExporter;
    }

    @Override
    public void export(String content, String filename)
            throws ExportException {

        byte[] data =
                content.getBytes(StandardCharsets.UTF_8);

        int result = legacyExporter.saveLegacy(
                filename,
                data,
                true
        );

        if (result == LegacyTextExporter.INVALID_PATH) {
            throw new ExportException(
                    "Invalid export destination"
            );
        }

        if (result == LegacyTextExporter.WRITE_ERROR) {
            throw new ExportException(
                    "Unable to export document"
            );
        }

        if (result != LegacyTextExporter.SUCCESS) {
            throw new ExportException(
                    "Unknown export failure"
            );
        }
    }
}