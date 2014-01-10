package de.hdm.thies.bankProjekt.shared.report;

import java.util.Vector;

/**
 * Ein <code>ReportWriter</code>, der Reports mittels HTML formatiert. Das im
 * Zielformat vorliegende Ergebnis wird in der Variable <code>reportText</code>
 * abgelegt und kann nach Aufruf der entsprechenden Prozessierungsmethode mit
 * <code>getReportText()</code> ausgelesen werden.
 * 
 * @author Thies
 */
public class HTMLReportWriter extends ReportWriter {

  /**
   * Diese Variable wird mit dem Ergebnis einer Umwandlung (vgl.
   * <code>process...</code>-Methoden) belegt. Format: HTML-Text
   */
  private String reportText = "";

  /**
   * ZurÃ¼cksetzen der Variable <code>reportText</code>.
   */
  public void resetReportText() {
    this.reportText = "";
  }

  /**
   * Umwandeln eines <code>Paragraph</code>-Objekts in HTML.
   * 
   * @param p der Paragraph
   * @return HTML-Text
   */
  public String paragraph2HTML(Paragraph p) {
    if (p instanceof CompositeParagraph) {
      return this.paragraph2HTML((CompositeParagraph) p);
    }
    else {
      return this.paragraph2HTML((SimpleParagraph) p);
    }
  }

  /**
   * Umwandeln eines <code>CompositeParagraph</code>-Objekts in HTML.
   * 
   * @param p der CompositeParagraph
   * @return HTML-Text
   */
  public String paragraph2HTML(CompositeParagraph p) {
    StringBuffer result = new StringBuffer();

    for (int i = 0; i < p.getNumParagraphs(); i++) {
      result.append("<p>" + p.getParagraphAt(i) + "</p>");
    }

    return result.toString();
  }

  /**
   * Umwandeln eines <code>SimpleParagraph</code>-Objekts in HTML.
   * 
   * @param p der SimpleParagraph
   * @return HTML-Text
   */
  public String paragraph2HTML(SimpleParagraph p) {
    return "<p>" + p.toString() + "</p>";
  }

  /**
   * HTML-Header-Text produzieren.
   * 
   * @return HTML-Text
   */
  public String getHeader() {
    StringBuffer result = new StringBuffer();

    result.append("<html><head><title></title></head><body>");
    return result.toString();
  }

  /**
   * HTML-Trailer-Text produzieren.
   * 
   * @return HTML-Text
   */
  public String getTrailer() {
    return "</body></html>";
  }

  /**
   * Prozessieren des Ã¼bergebenen Reports und Ablage im Zielformat. Ein Auslesen
   * des Ergebnisses kann spÃ¤ter mittels <code>getReportText()</code> erfolgen.
   * 
   * @param r der zu prozessierende Report
   */
  public void process(AllAccountsOfCustomerReport r) {
    // ZunÃ¤chst lÃ¶schen wir das Ergebnis vorhergehender Prozessierungen.
    this.resetReportText();

    /*
     * In diesen Buffer schreiben wir wÃ¤hrend der Prozessierung sukzessive
     * unsere Ergebnisse.
     */
    StringBuffer result = new StringBuffer();

    /*
     * Nun werden Schritt fÃ¼r Schritt die einzelnen Bestandteile des Reports
     * ausgelesen und in HTML-Form Ã¼bersetzt.
     */
    result.append("<H1>" + r.getTitle() + "</H1>");
    result.append("<table style=\"width:400px;border:1px solid silver\"><tr>");
    result.append("<td valign=\"top\"><b>" + paragraph2HTML(r.getHeaderData())
        + "</b></td>");
    result.append("<td valign=\"top\">" + paragraph2HTML(r.getImprint())
        + "</td>");
    result.append("</tr><tr><td></td><td>" + r.getCreated().toString()
        + "</td></tr></table>");

    Vector<Row> rows = r.getRows();
    result.append("<table style=\"width:400px\">");

    for (int i = 0; i < rows.size(); i++) {
      Row row = rows.elementAt(i);
      result.append("<tr>");
      for (int k = 0; k < row.getNumColumns(); k++) {
        if (i == 0) {
          result.append("<td style=\"background:silver;font-weight:bold\">" + row.getColumnAt(k)
              + "</td>");
        }
        else {
          if (i > 1) {
            result.append("<td style=\"border-top:1px solid silver\">"
                + row.getColumnAt(k) + "</td>");
          }
          else {
            result.append("<td valign=\"top\">" + row.getColumnAt(k) + "</td>");
          }
        }
      }
      result.append("</tr>");
    }

    result.append("</table>");

    /*
     * Zum Schluss wird unser Arbeits-Buffer in einen String umgewandelt und der
     * reportText-Variable zugewiesen. Dadurch wird es mÃ¶glich, anschlieÃŸend das
     * Ergebnis mittels getReportText() auszulesen.
     */
    this.reportText = result.toString();
  }

  /**
   * Prozessieren des Ã¼bergebenen Reports und Ablage im Zielformat. Ein Auslesen
   * des Ergebnisses kann spÃ¤ter mittels <code>getReportText()</code> erfolgen.
   * 
   * @param r der zu prozessierende Report
   */
  public void process(AllAccountsOfAllCustomersReport r) {
    // ZunÃ¤chst lÃ¶schen wir das Ergebnis vorhergehender Prozessierungen.
    this.resetReportText();

    /*
     * In diesen Buffer schreiben wir wÃ¤hrend der Prozessierung sukzessive
     * unsere Ergebnisse.
     */
    StringBuffer result = new StringBuffer();

    /*
     * Nun werden Schritt fÃ¼r Schritt die einzelnen Bestandteile des Reports
     * ausgelesen und in HTML-Form Ã¼bersetzt.
     */
    result.append("<H1>" + r.getTitle() + "</H1>");
    result.append("<table><tr>");

    if (r.getHeaderData() != null) {
      result.append("<td>" + paragraph2HTML(r.getHeaderData()) + "</td>");
    }

    result.append("<td>" + paragraph2HTML(r.getImprint()) + "</td>");
    result.append("</tr><tr><td></td><td>" + r.getCreated().toString()
        + "</td></tr></table>");

    /*
     * Da AllAccountsOfAllCustomersReport ein CompositeReport ist, enthÃ¤lt r
     * eine Menge von Teil-Reports des Typs AllAccountsOfCustomerReport. FÃ¼r
     * jeden dieser Teil-Reports rufen wir processAllAccountsOfCustomerReport
     * auf. Das Ergebnis des jew. Aufrufs fÃ¼gen wir dem Buffer hinzu.
     */
    for (int i = 0; i < r.getNumSubReports(); i++) {
      /*
       * AllAccountsOfCustomerReport wird als Typ der SubReports vorausgesetzt.
       * Sollte dies in einer erweiterten Form des Projekts nicht mehr gelten,
       * so mÃ¼sste hier eine detailliertere Implementierung erfolgen.
       */
      AllAccountsOfCustomerReport subReport = (AllAccountsOfCustomerReport) r
          .getSubReportAt(i);

      this.process(subReport);

      result.append(this.reportText + "\n");

      /*
       * Nach jeder Ãœbersetzung eines Teilreports und anschlieÃŸendem Auslesen
       * sollte die Ergebnisvariable zurÃ¼ckgesetzt werden.
       */
      this.resetReportText();
    }

    /*
     * Zum Schluss wird unser Arbeits-Buffer in einen String umgewandelt und der
     * reportText-Variable zugewiesen. Dadurch wird es mÃ¶glich, anschlieÃŸend das
     * Ergebnis mittels getReportText() auszulesen.
     */
    this.reportText = result.toString();
  }

  /**
   * Auslesen des Ergebnisses der zuletzt aufgerufenen Prozessierungsmethode.
   * 
   * @return ein String im HTML-Format
   */
  public String getReportText() {
    return this.getHeader() + this.reportText + this.getTrailer();
  }
}