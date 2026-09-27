package export;

public class InvoiceExporter extends DocumentExporter {

    public InvoiceExporter(ExportEngine engine) {
        super(engine);
    }

    @Override
    public void exportDocument(
            String data,
            String filename
    ) throws ExportException {

        String content =
                "INVOICE\n" +
                "-------\n" +
                data;

        engine.export(content, filename);
    }
}