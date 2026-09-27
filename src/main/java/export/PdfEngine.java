package export;
public class PdfEngine implements ExportEngine {

    @Override 
    public void export(String content, String filename)
            throws ExportException {

        System.out.println("PDF export: " + filename);
            }
}