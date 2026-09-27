package export;

public class ExportEngineSelector {

    public ExportEngine select(String filename)
            throws ExportException {

        String lower = filename.toLowerCase();

        if (lower.endsWith(".pdf")) {
            return new PdfEngine();
        }

        if (lower.endsWith(".html")) {
            return new HtmlEngine();
        }

        if (lower.endsWith(".txt")) {
            return new LegacyExportAdapter(
                    new LegacyTextExporter()
            );
        }

        throw new ExportException(
                "Unsupported file format"
        );
    }
}