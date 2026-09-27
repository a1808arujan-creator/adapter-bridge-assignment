package export;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DocumentExporterTest {

    static class StubExportEngine implements ExportEngine {

        String receivedContent;
        String receivedFilename;

        @Override
        public void export(String content, String filename) {
            this.receivedContent = content;
            this.receivedFilename = filename;
        }
    }

    @Test
    void reportExporterDelegatesToEngine() throws ExportException {

        StubExportEngine stub = new StubExportEngine();

        DocumentExporter exporter =
                new ReportExporter(stub);

        exporter.exportDocument(
                "Sales: 1000",
                "report.pdf"
        );

        assertEquals(
                "REPORT\n------\nSales: 1000",
                stub.receivedContent
        );

        assertEquals(
                "report.pdf",
                stub.receivedFilename
        );
    }

    @Test
    void invoiceExporterDelegatesToEngine() throws ExportException {

        StubExportEngine stub = new StubExportEngine();

        DocumentExporter exporter =
                new InvoiceExporter(stub);

        exporter.exportDocument(
                "Order #10",
                "invoice.html"
        );

        assertEquals(
                "INVOICE\n-------\nOrder #10",
                stub.receivedContent
        );

        assertEquals(
                "invoice.html",
                stub.receivedFilename
        );
    }
}