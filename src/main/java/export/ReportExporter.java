package export;

public class ReportExporter extends DocumentExporter {

    public ReportExporter(ExportEngine engine) {
        super(engine);
    }

    @Override
    public void exportDocument(
            String data,
            String filename
    ) throws ExportException {

        String content =
                "REPORT\n" +
                "------\n" +
                data;

        engine.export(content, filename);
    }
}