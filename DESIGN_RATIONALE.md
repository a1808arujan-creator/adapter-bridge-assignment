# Design Rationale – Document Export System

## 1. Project Overview

The project represents a document export system. It supports two types of
documents: reports and invoices.

Documents can be exported using different export engines:
- PDF
- HTML
- Legacy Text

The main goal of the design is to allow document types and export engines
to change independently.

## 2. Bridge Pattern

The Bridge pattern is used to separate document types from export
implementations.

`DocumentExporter` is the Abstraction. It contains a reference to
`ExportEngine`, which is the Implementor interface.

`ReportExporter` and `InvoiceExporter` are Refined Abstractions.

`PdfEngine`, `HtmlEngine`, and `LegacyExportAdapter` are implementations
of the `ExportEngine` interface.

Because of this design, a report or invoice can work with different
export engines without changing the document classes.

For example:

ReportExporter + PdfEngine

or

ReportExporter + HtmlEngine

or

InvoiceExporter + LegacyExportAdapter

This avoids creating separate classes for every possible combination.

## 3. Adapter Pattern

The existing `LegacyTextExporter` cannot directly implement
`ExportEngine` because its interface is incompatible with the new system.

The new system expects:

`export(String content, String filename)`

but the legacy class provides:

`saveLegacy(String path, byte[] data, boolean overwrite)`

The method name, parameters, data type, and error handling are different.

`LegacyExportAdapter` solves this incompatibility.

It implements `ExportEngine`, converts the String content to byte data,
calls `LegacyTextExporter.saveLegacy()`, and translates legacy error
codes into `ExportException`.

Therefore, the rest of the application can use the legacy exporter
through the same `ExportEngine` interface.

## 4. Bridge and Adapter Integration

The Adapter is not implemented as a separate example.

`LegacyExportAdapter` directly implements the Bridge Implementor
interface, `ExportEngine`.

Therefore, from the point of view of `DocumentExporter`,
`LegacyExportAdapter` behaves like any other export engine.

The structure is:

DocumentExporter
        |
        v
ExportEngine
   /       |        \
PdfEngine  HtmlEngine  LegacyExportAdapter
                            |
                            v
                    LegacyTextExporter

This allows the incompatible legacy system to participate directly in
the Bridge structure.

## 5. Dynamic Implementor Selection

The project also uses dynamic implementor selection.

`ExportEngineSelector` selects an implementation at runtime according
to the file extension.

`.pdf` -> `PdfEngine`

`.html` -> `HtmlEngine`

`.txt` -> `LegacyExportAdapter`

This means the client does not need to manually choose a concrete
export engine for every operation.

## 6. Benefits of the Design

The design keeps the document hierarchy independent from the export
engine hierarchy.

New document types can be added without changing the existing export
engines, and new export engines can be added without changing the
document classes.

The Adapter also allows the existing legacy code to be reused without
modifying `LegacyTextExporter`.