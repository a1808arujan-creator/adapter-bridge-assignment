package export;

public class Main {

    public static void main(String[] args) {

        try {
            ExportEngineSelector selector =
                    new ExportEngineSelector();

            String reportFile = "report.pdf";

            ExportEngine reportEngine =
                    selector.select(reportFile);

            DocumentExporter report =
                    new ReportExporter(reportEngine);

            report.exportDocument(
                    "Monthly sales: 150000 KZT",
                    reportFile
            );

            System.out.println();

            String invoiceFile = "invoice.txt";

            ExportEngine invoiceEngine =
                    selector.select(invoiceFile);

            DocumentExporter invoice =
                    new InvoiceExporter(invoiceEngine);

            invoice.exportDocument(
                    "Order #102, total: 25000 KZT",
                    invoiceFile
            );

        } catch (ExportException e) {

            System.out.println(
                    "Export failed: " + e.getMessage()
            );
        }
    }
}