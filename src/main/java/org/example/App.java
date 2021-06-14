package org.example;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.example.generated.cbr.DailyInfo;
import org.example.generated.cbr.GetCursDynamicResponse;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

import javax.xml.datatype.DatatypeFactory;

/**
 * This is a "proof-of-concept" example made only for demonstration purpose,
 * so no proper type casting checks are made
 */
@Slf4j
public class App {

    @SneakyThrows
    public static void main(String[] args) {
        var dailyInfoService = new DailyInfo();

        var datatypeFactory = DatatypeFactory.newInstance();
        var from = datatypeFactory.newXMLGregorianCalendar(2020, 1, 1, 0, 0, 0, 0, 3);
        var to = datatypeFactory.newXMLGregorianCalendar(2020, 1, 31, 23, 59, 59, 0, 3);

        GetCursDynamicResponse.GetCursDynamicResult result = dailyInfoService.getDailyInfoSoap()
                .getCursDynamic(from, to, "R01235");

        NodeList rates = ((Element) result.getAny()).getFirstChild().getChildNodes();

        for (int i = 0; i < rates.getLength(); i++) {
            var rateItem = (Element) rates.item(i);

            String date = extractTextElementByName(rateItem, "CursDate");
            String currencyCode = extractTextElementByName(rateItem, "Vcode");
            String nominal = extractTextElementByName(rateItem, "Vnom");
            String curs = extractTextElementByName(rateItem, "Vcurs");

            log.info("Curs item: date={} currencyCode={} nominal={} curs={}", date, currencyCode, nominal, curs);
        }
    }

    private static String extractTextElementByName(Element element, String name) {
        return ((Text) element.getElementsByTagName(name).item(0).getFirstChild()).getWholeText();
    }
}
