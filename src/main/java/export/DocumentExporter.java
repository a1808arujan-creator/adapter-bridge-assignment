package export;

public abstract class DocumentExporter {

    protected final ExportEngine engine;

    public DocumentExporter(ExportEngine engine) {
        this.engine = engine;
    }

    public abstract void exportDocument(
            String data,
            String filename
    ) throws ExportException;
}