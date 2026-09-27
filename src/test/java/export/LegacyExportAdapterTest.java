package export;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LegacyExportAdapterTest {

    static class FailingLegacyExporter
            extends LegacyTextExporter {

        @Override
        public int saveLegacy(
                String path,
                byte[] data,
                boolean overwrite) {

            return INVALID_PATH;
        }
    }

    @Test
    void adapterTranslatesLegacyFailure() {

        LegacyTextExporter legacy =
                new FailingLegacyExporter();

        ExportEngine adapter =
                new LegacyExportAdapter(legacy);

        ExportException exception =
                assertThrows(
                        ExportException.class,
                        () -> adapter.export(
                                "Hello",
                                "file.txt"
                        )
                );

        assertEquals(
                "Invalid export destination",
                exception.getMessage()
        );
    }
}