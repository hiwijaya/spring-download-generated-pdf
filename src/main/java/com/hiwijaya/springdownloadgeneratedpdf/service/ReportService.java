package com.hiwijaya.springdownloadgeneratedpdf.service;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author Happy Indra Wijaya
 */
@Service
public class ReportService {

    @Value("${app.baseuri}")
    private String baseUri;

    @Autowired
    private SpringTemplateEngine templateEngine;


    public byte[] generateReport(String name) throws IOException {

        // passing parameter
        Context context = new Context();
        context.setVariable("name", name);

        String processedHtml = templateEngine.process("report", context);

        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        ConverterProperties converterProperties = new ConverterProperties();
        converterProperties.setBaseUri(baseUri);

        HtmlConverter.convertToPdf(processedHtml, stream, converterProperties);

        byte[] bytes = stream.toByteArray();
        stream.flush();

        return bytes;
    }

}
