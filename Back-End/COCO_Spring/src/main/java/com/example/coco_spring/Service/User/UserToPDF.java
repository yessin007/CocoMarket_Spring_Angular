package com.example.coco_spring.Service.User;

import com.example.coco_spring.Entity.User;
import com.lowagie.text.Document;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public class UserToPDF extends AbstractPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest request,
                                    HttpServletResponse response) throws Exception {

        response.addHeader("Content-Dispostion", "attachment; filename=\"user_list.pdf\"");

        @SuppressWarnings("unused")
        List<User> list = (List<User>) model.get("usersList");

        Table table = new Table(5);//5 colonnes
        table.addCell("NOM");
        table.addCell("PRENOM");
        table.addCell("EMAIL");
        table.addCell("ROLE");
        for (User user : list) {
            table.addCell(user.getName());
            table.addCell(user.getLastName());
            table.addCell(user.getEmail());
            table.addCell(user.getRoles().name());
        }

        document.add(table);
    }
}