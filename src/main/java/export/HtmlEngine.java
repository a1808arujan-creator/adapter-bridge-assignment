package export;
public class HtmlEngine implements ExportEngine {

    @Override 
    public void export(String content, String filename)
            throws ExportException {

        System.out.println("HTML export: " + filename);
        System.out.println("<html><body>" + content + "</body></html>");
    }
}