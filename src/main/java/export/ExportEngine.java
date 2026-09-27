package export;

public interface ExportEngine {

    void export(String content, String filename)
            throws ExportException;
}