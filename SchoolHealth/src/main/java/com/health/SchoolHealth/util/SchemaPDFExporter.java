package com.health.SchoolHealth.util;

import com.health.SchoolHealth.controlers.formPOJOs.DispensaryObservationIllnessTypesResults;
import com.health.SchoolHealth.model.entities.*;
import com.health.SchoolHealth.services.*;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPRow;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class SchemaPDFExporter {

    public SchemaPDFExporter() {
    }

    private void writeTable1Header(PdfPTable table) {

        Font font = getFont();
        font.setStyle(Paragraph.ALIGN_CENTER);
        font.setStyle(Font.NORMAL);

        PdfPCell cell1 = new PdfPCell();
        cell1.setPadding(5);
        cell1.setPhrase(new Phrase("      Общ\n      брой\n паралелки", font));

        PdfPCell cell2 = new PdfPCell();
        cell2.setPadding(5);
        cell2.setPhrase(new Phrase("     Общ\n     брой\n   ученици", font));

        PdfPCell cell3 = new PdfPCell();
        cell3.setPadding(5);
        cell3.setPhrase(new Phrase("                                От тях на възраст", font));


        PdfPTable nesting = new PdfPTable(1);
        nesting.addCell(cell3);
        nesting.completeRow();

        PdfPTable nesting2 = new PdfPTable(2);

        PdfPCell cell4 = new PdfPCell();
        cell4.setPadding(5);
        cell4.setPhrase(new Phrase("                  7-14 г.", font));
        nesting2.addCell(cell4);

        PdfPCell cell5 = new PdfPCell();
        cell5.setPadding(5);
        cell5.setPhrase(new Phrase("                  14-18 г.", font));
        nesting2.addCell(cell5);

        PdfPTable nesting4 = new PdfPTable(2);

        PdfPCell cell7 = new PdfPCell();
        cell7.setPadding(5);
        cell7.setPhrase(new Phrase("     момчета", font));
        nesting4.addCell(cell7);

        PdfPCell cell8 = new PdfPCell();
        cell8.setPadding(5);
        cell8.setPhrase(new Phrase("    момичета", font));
        nesting4.addCell(cell8);

        PdfPCell cell9 = new PdfPCell(nesting4);

        nesting2.addCell(cell9);

        PdfPTable nesting5 = new PdfPTable(2);

        PdfPCell cell10 = new PdfPCell();
        cell10.setPadding(5);
        cell10.setPhrase(new Phrase("    момчета", font));
        nesting5.addCell(cell10);

        PdfPCell cell11 = new PdfPCell();
        cell11.setPadding(5);
        cell11.setPhrase(new Phrase("    момичета", font));
        nesting5.addCell(cell11);

        PdfPCell cell12 = new PdfPCell(nesting5);

        nesting2.addCell(cell12);

        PdfPCell cell = new PdfPCell(nesting2);
        cell.setBorder(PdfPCell.NO_BORDER);
        nesting.addCell(cell);

        PdfPCell cell6 = new PdfPCell(nesting);

        PdfPCell[] cels = {cell1, cell2, cell6};

        PdfPRow row = new PdfPRow(cels);
        table.getRows().add(row);
    }

    private void writeTable1Data(PdfPTable table, StudentService studentService, HttpSession httpSession) {

        Integer schoolId = (Integer) httpSession.getAttribute("schoolId");


        Font font = getFont();
        font.setStyle(Paragraph.ALIGN_CENTER);
        font.setStyle(Font.NORMAL);

        PdfPCell cell = new PdfPCell();
        cell.setPadding(5);

//        Общ брой паралелки
        String numberOfClassesFofSchool = String.valueOf(studentService.countAllClassesFofSchool(schoolId));
        cell.setPhrase(new Phrase(numberOfClassesFofSchool, font));
        table.addCell(cell);
//        Общ брой ученици
        String numberOfStudentsForSchool = String.valueOf(studentService.countAllStudentsFofSchool(schoolId));
        cell.setPhrase(new Phrase(numberOfStudentsForSchool, font));
        table.addCell(cell);

        PdfPTable nesting = new PdfPTable(4);

        String allBoysFrom7to14FofSchool = String.valueOf(studentService.countAllBoysFrom7to14FofSchool(schoolId));
        cell.setPhrase(new Phrase(allBoysFrom7to14FofSchool, font));
        nesting.addCell(cell);
        String allGirlsFrom7to14FofSchool = String.valueOf(studentService.countAllGirlsFrom7to14FofSchool(schoolId));
        cell.setPhrase(new Phrase(allGirlsFrom7to14FofSchool, font));
        nesting.addCell(cell);
        String allBoysFrom14to18FofSchool = String.valueOf(studentService.countAllBoysFrom14to18FofSchool(schoolId));
        cell.setPhrase(new Phrase(allBoysFrom14to18FofSchool, font));
        nesting.addCell(cell);
        String allGirlsFrom14to18FofSchool = String.valueOf(studentService.countAllGirlsFrom14to18FofSchool(schoolId));
        cell.setPhrase(new Phrase(allGirlsFrom14to18FofSchool, font));
        nesting.addCell(cell);
        PdfPCell cell1 = new PdfPCell(nesting);
        cell1.setBorder(PdfPCell.NO_BORDER);
        table.addCell(cell1);
    }

    private void writeTable2Header(PdfPTable table) {

        Font font = getFont();
        font.setStyle(Paragraph.ALIGN_CENTER);
        font.setStyle(Font.NORMAL);

        PdfPCell cell1 = new PdfPCell();
        cell1.setPadding(5);
        cell1.setColspan(4);
        cell1.setPhrase(new Phrase("                     І група\n" +
                "                      норма", font));
        table.addCell(cell1);

        PdfPCell cell2 = new PdfPCell();
        cell2.setPadding(5);
        cell2.setColspan(4);
        cell2.setPhrase(new Phrase("                  ІI група\n" +
                "          разширена норма", font));
        table.addCell(cell2);

        PdfPCell cell3 = new PdfPCell();
        cell3.setPadding(5);
        cell3.setColspan(8);
        cell3.setPhrase(new Phrase(
                "                                             ІІІ група\n" +
                "                                          извън норма", font));
        table.addCell(cell3);

        PdfPCell cell4 = new PdfPCell();
        cell4.setPadding(5);
        cell4.setColspan(4);
        cell4.setPhrase(new Phrase("             брой ученици\n" +
                "                 Х +/- 1S", font));
        table.addCell(cell4);

        PdfPCell cell5 = new PdfPCell();
        cell5.setPadding(5);
        cell5.setColspan(4);
        cell5.setPhrase(new Phrase("         брой ученици между\n" +
                "           Х +/- 1S и Х +/- 2S", font));
        table.addCell(cell5);
        PdfPCell cell6 = new PdfPCell();
        cell6.setPadding(5);
        cell6.setColspan(4);
        cell6.setPhrase(new Phrase("           брой ученици под\n" +
                "                    Х - 2S", font));
        table.addCell(cell6);

        PdfPCell cell7 = new PdfPCell();
        cell7.setPadding(5);
        cell7.setColspan(4);
        cell7.setPhrase(new Phrase("           брой ученици над\n" +
                "                   Х + 2S", font));
        table.addCell(cell7);

        font.setSize(8);
        for (int i = 1; i <= 8; i++) {
            PdfPCell cell8 = new PdfPCell();

            cell8.setPhrase(new Phrase(" Мом-\n  чета", font));
            table.addCell(cell8);
            PdfPCell cell9 = new PdfPCell();

            cell9.setPhrase(new Phrase(" Моми-\n  чета", font));
            table.addCell(cell9);
        }

    }

    private void writeTable2Data(PdfPTable table, StudentService studentService, HttpSession httpSession) {

        Font font = getFont();
        font.setStyle(Paragraph.ALIGN_CENTER);
        font.setStyle(Font.NORMAL);

        PdfPCell cell = new PdfPCell();
        cell.setPadding(5);
        String heightIforAged7to14yearsBoys = String.valueOf(AnalysisUtil.heightIforAged7to14yearsBoys);
        cell.setPhrase(new Phrase(heightIforAged7to14yearsBoys, font));
        table.addCell(cell);
        String heightIforAged7to14yearsGirls = String.valueOf(AnalysisUtil.heightIforAged7to14yearsGirls);
        cell.setPhrase(new Phrase(heightIforAged7to14yearsGirls, font));
        table.addCell(cell);
        String heightIforAged14to18yearsBoys = String.valueOf(AnalysisUtil.heightIforAged14to18yearsBoys);
        cell.setPhrase(new Phrase(heightIforAged14to18yearsBoys, font));
        table.addCell(cell);
        String heightIforAged14to18yearsGirls = String.valueOf(AnalysisUtil.heightIforAged14to18yearsGirls);
        cell.setPhrase(new Phrase(heightIforAged14to18yearsGirls, font));
        table.addCell(cell);
        String heightIIforAged7to14yearsBoys = String.valueOf(AnalysisUtil.heightIIforAged7to14yearsBoys);
        cell.setPhrase(new Phrase(heightIIforAged7to14yearsBoys, font));
        table.addCell(cell);
        String heightIIforAged7to14yearsGirls = String.valueOf(AnalysisUtil.heightIIforAged7to14yearsGirls);
        cell.setPhrase(new Phrase(heightIIforAged7to14yearsGirls, font));
        table.addCell(cell);
        String heightIIforAged14to18yearsBoys = String.valueOf(AnalysisUtil.heightIIforAged14to18yearsBoys);
        cell.setPhrase(new Phrase(heightIIforAged14to18yearsBoys, font));
        table.addCell(cell);
        String heightIIforAged14to18yearsGirls = String.valueOf(AnalysisUtil.heightIIforAged14to18yearsGirls);
        cell.setPhrase(new Phrase(heightIIforAged14to18yearsGirls, font));
        table.addCell(cell);
        String heightIIIUnderNormforAged7to14yearsBoys = String.valueOf(AnalysisUtil.heightIIIUnderNormforAged7to14yearsBoys);
        cell.setPhrase(new Phrase(heightIIIUnderNormforAged7to14yearsBoys, font));
        table.addCell(cell);
        String heightIIIUnderNormforAged7to14yearsGirls = String.valueOf(AnalysisUtil.heightIIIUnderNormforAged7to14yearsGirls);
        cell.setPhrase(new Phrase(heightIIIUnderNormforAged7to14yearsGirls, font));
        table.addCell(cell);
        String heightIIIUnderNormforAged14to18yearsBoys = String.valueOf(AnalysisUtil.heightIIIUnderNormforAged14to18yearsBoys);
        cell.setPhrase(new Phrase(heightIIIUnderNormforAged14to18yearsBoys, font));
        table.addCell(cell);
        String heightIIIUnderNormforAged14to18yearsGirls = String.valueOf(AnalysisUtil.heightIIIUnderNormforAged14to18yearsGirls);
        cell.setPhrase(new Phrase(heightIIIUnderNormforAged14to18yearsGirls, font));
        table.addCell(cell);
        String heightIIIOverNormforAged7to14yearsBoys = String.valueOf(AnalysisUtil.heightIIIOverNormforAged7to14yearsBoys);
        cell.setPhrase(new Phrase(heightIIIOverNormforAged7to14yearsBoys, font));
        table.addCell(cell);
        String heightIIIOverNormforAged7to14yearsGirls = String.valueOf(AnalysisUtil.heightIIIOverNormforAged7to14yearsGirls);
        cell.setPhrase(new Phrase(heightIIIOverNormforAged7to14yearsGirls, font));
        table.addCell(cell);
        String heightIIIOverNormforAged14to18yearsBoys = String.valueOf(AnalysisUtil.heightIIIOverNormforAged14to18yearsBoys);
        cell.setPhrase(new Phrase(heightIIIOverNormforAged14to18yearsBoys, font));
        table.addCell(cell);
        String heightIIIOverNormforAged14to18yearsGirls = String.valueOf(AnalysisUtil.heightIIIOverNormforAged14to18yearsGirls);
        cell.setPhrase(new Phrase(heightIIIOverNormforAged14to18yearsGirls, font));
        table.addCell(cell);
    }

    private void writeTable3Data(PdfPTable table, StudentService studentService, HttpSession httpSession) {

        Integer schoolId = (Integer) httpSession.getAttribute("schoolId");

        Font font = getFont();
        font.setStyle(Paragraph.ALIGN_CENTER);
        font.setStyle(Font.NORMAL);

        PdfPCell cell = new PdfPCell();
        cell.setPadding(5);
        String weightIforAged7to14yearsBoys = String.valueOf(AnalysisUtil.weightIforAged7to14yearsBoys);
        cell.setPhrase(new Phrase(weightIforAged7to14yearsBoys, font));
        table.addCell(cell);
        String weightIforAged7to14yearsGirls = String.valueOf(AnalysisUtil.weightIforAged7to14yearsGirls);
        cell.setPhrase(new Phrase(weightIforAged7to14yearsGirls, font));
        table.addCell(cell);
        String weightIforAged14to18yearsBoys = String.valueOf(AnalysisUtil.weightIforAged14to18yearsBoys);
        cell.setPhrase(new Phrase(weightIforAged14to18yearsBoys, font));
        table.addCell(cell);
        String weightIforAged14to18yearsGirls = String.valueOf(AnalysisUtil.weightIforAged14to18yearsGirls);
        cell.setPhrase(new Phrase(weightIforAged14to18yearsGirls, font));
        table.addCell(cell);
        String heightIforAged7to14yearsBoys = String.valueOf(AnalysisUtil.heightIforAged7to14yearsBoys);
        cell.setPhrase(new Phrase(heightIforAged7to14yearsBoys, font));
        table.addCell(cell);
        String weightIIforAged7to14yearsBoys = String.valueOf(AnalysisUtil.weightIIforAged7to14yearsBoys);
        cell.setPhrase(new Phrase(weightIIforAged7to14yearsBoys, font));
        table.addCell(cell);
        String weightIIforAged7to14yearsGirls = String.valueOf(AnalysisUtil.weightIIforAged7to14yearsGirls);
        cell.setPhrase(new Phrase(weightIIforAged7to14yearsGirls, font));
        table.addCell(cell);
        String weightIIforAged14to18yearsBoys = String.valueOf(AnalysisUtil.weightIIforAged14to18yearsBoys);
        cell.setPhrase(new Phrase(weightIIforAged14to18yearsBoys, font));
        table.addCell(cell);
        String weightIIforAged14to18yearsGirls = String.valueOf(AnalysisUtil.weightIIforAged14to18yearsGirls);
        cell.setPhrase(new Phrase(weightIIforAged14to18yearsGirls, font));
        table.addCell(cell);
        String weightIIIUnderNormforAged7to14yearsBoys = String.valueOf(AnalysisUtil.weightIIIUnderNormforAged7to14yearsBoys);
        cell.setPhrase(new Phrase(weightIIIUnderNormforAged7to14yearsBoys, font));
        table.addCell(cell);
        String weightIIIUnderNormforAged7to14yearsGirls = String.valueOf(AnalysisUtil.weightIIIUnderNormforAged7to14yearsGirls);
        cell.setPhrase(new Phrase(weightIIIUnderNormforAged7to14yearsGirls, font));
        table.addCell(cell);
        String weightIIIUnderNormforAged14to18yearsBoys = String.valueOf(AnalysisUtil.weightIIIUnderNormforAged14to18yearsBoys);
        cell.setPhrase(new Phrase(weightIIIUnderNormforAged14to18yearsBoys, font));
        table.addCell(cell);
        String weightIIIUnderNormforAged14to18yearsGirls = String.valueOf(AnalysisUtil.weightIIIUnderNormforAged14to18yearsGirls);
        cell.setPhrase(new Phrase(weightIIIUnderNormforAged14to18yearsGirls, font));
        table.addCell(cell);
        String weightIIIOverNormforAged7to14yearsBoys = String.valueOf(AnalysisUtil.weightIIIOverNormforAged7to14yearsBoys);
        cell.setPhrase(new Phrase(weightIIIOverNormforAged7to14yearsBoys, font));
        table.addCell(cell);
        String weightIIIOverNormforAged7to14yearsGirls = String.valueOf(AnalysisUtil.weightIIIOverNormforAged7to14yearsGirls);
        cell.setPhrase(new Phrase(weightIIIOverNormforAged7to14yearsGirls, font));
        table.addCell(cell);
        String weightIIIOverNormforAged14to18yearsBoys = String.valueOf(AnalysisUtil.weightIIIOverNormforAged14to18yearsBoys);
        cell.setPhrase(new Phrase(weightIIIOverNormforAged14to18yearsBoys, font));
        table.addCell(cell);
        String weightIIIOverNormforAged14to18yearsGirls = String.valueOf(AnalysisUtil.weightIIIOverNormforAged14to18yearsGirls);
        cell.setPhrase(new Phrase(weightIIIOverNormforAged14to18yearsGirls, font));
        table.addCell(cell);

    }

    private void writeTable4Header(PdfPTable table) {

        Font font = getFont();
        font.setStyle(Paragraph.ALIGN_CENTER);
        font.setStyle(Font.NORMAL);

        PdfPCell cell1 = new PdfPCell();
        cell1.setPadding(5);
        cell1.setColspan(3);
        cell1.setRowspan(2);
        cell1.setPhrase(new Phrase("\n                    Показател", font));
        table.addCell(cell1);

        PdfPCell cell2 = new PdfPCell();
        cell2.setPadding(5);
        cell2.setColspan(1);
        cell2.setRowspan(2);

        cell2.setPhrase(new Phrase("   Общ\n   брой" +
                "        ученици", font));
        table.addCell(cell2);

        PdfPCell cell3 = new PdfPCell();
        cell3.setPadding(5);
        cell3.setColspan(2);
        cell3.setRowspan(1);
        cell3.setPhrase(new Phrase(
                "             7 - 14 г.", font));
        table.addCell(cell3);

        PdfPCell cell4 = new PdfPCell();
        cell4.setPadding(5);
        cell4.setColspan(4);
        cell1.setRowspan(1);
        cell4.setPhrase(new Phrase("          14 - 18 г.", font));
        table.addCell(cell4);

        for (int i = 1; i <= 2; i++) {
            PdfPCell cell5 = new PdfPCell();
            cell5.setColspan(1);
            cell5.setRowspan(1);
            cell5.setPhrase(new Phrase(" Момчета", font));
            table.addCell(cell5);
            PdfPCell cell6 = new PdfPCell();
            cell6.setColspan(1);
            cell6.setRowspan(1);
            cell6.setPhrase(new Phrase(" Момичета", font));
            table.addCell(cell6);
        }

    }

    private void writeTable4Data(PdfPTable table, HttpSession httpSession, PhysicalCapacityService physicalCapacityService, HealthConditionService healthConditionService) {

        Integer schoolId = (Integer) httpSession.getAttribute("schoolId");

        Font font = getFont();
        font.setStyle(Paragraph.ALIGN_CENTER);
        font.setStyle(Font.NORMAL);

        PdfPCell cell = new PdfPCell();
        cell.setPadding(5);
        cell.setColspan(3);
        String gradeOver3 ="Брой ученици, получили оценка над среден (3) при изследване на физическата дееспособност";
        cell.setPhrase(new Phrase(gradeOver3, font));
        table.addCell(cell);
        String studentsHavingMarkGreaterThan3 = String.valueOf(physicalCapacityService.countStudentsHavingMarkGreaterThan3(schoolId));
        cell.setPhrase(new Phrase(studentsHavingMarkGreaterThan3, font));
        cell.setColspan(1);
        table.addCell(cell);
        String studentsHavingMarkGreaterThan3BoysFrom7to14FofSchool =
                String.valueOf(physicalCapacityService.countStudentsHavingMarkGreaterThan3BoysFrom7to14FofSchool(schoolId));
        cell.setPhrase(new Phrase(studentsHavingMarkGreaterThan3BoysFrom7to14FofSchool, font));
        table.addCell(cell);
        String studentsHavingMarkGreaterThan3GirlsFrom7to14FofSchool =
                String.valueOf(physicalCapacityService.countStudentsHavingMarkGreaterThan3GirlsFrom7to14FofSchool(schoolId));
        cell.setPhrase(new Phrase(studentsHavingMarkGreaterThan3GirlsFrom7to14FofSchool, font));
        table.addCell(cell);
        String studentsHavingMarkGreaterThan3BoysFrom14to18FofSchool =
                String.valueOf(physicalCapacityService.countStudentsHavingMarkGreaterThan3BoysFrom14to18FofSchool(schoolId));
        cell.setPhrase(new Phrase(studentsHavingMarkGreaterThan3BoysFrom14to18FofSchool, font));
        table.addCell(cell);
        String studentsHavingMarkGreaterThan3GirlsFrom14to18FofSchool =
                String.valueOf(physicalCapacityService.countStudentsHavingMarkGreaterThan3GirlsFrom14to18FofSchool(schoolId));
        cell.setPhrase(new Phrase(studentsHavingMarkGreaterThan3GirlsFrom14to18FofSchool, font));
        table.addCell(cell);

        cell.setColspan(3);
        String exemptFromPhysicalEducation ="Брой ученици, освободени от часовете по физическо възпитание";
        cell.setPhrase(new Phrase(exemptFromPhysicalEducation, font));
        table.addCell(cell);

        cell.setColspan(1);
        String studentsExemptFromPhysicalEducation =
                String.valueOf(healthConditionService.countStudentsExemptFromPhysicalEducation(schoolId));
        cell.setPhrase(new Phrase(studentsExemptFromPhysicalEducation, font));
        table.addCell(cell);
        String studentsExemptFromPhysicalEducationBoysFrom7to14FofSchool =
                String.valueOf(healthConditionService.countStudentsExemptFromPhysicalEducationBoysFrom7to14FofSchool(schoolId));
        cell.setPhrase(new Phrase(studentsExemptFromPhysicalEducationBoysFrom7to14FofSchool, font));
        table.addCell(cell);
        String studentsExemptFromPhysicalEducationGirlsFrom7to14FofSchool =
                String.valueOf(healthConditionService.countStudentsExemptFromPhysicalEducationGirlsFrom7to14FofSchool(schoolId));
        cell.setPhrase(new Phrase(studentsExemptFromPhysicalEducationGirlsFrom7to14FofSchool, font));
        table.addCell(cell);
        String studentsExemptFromPhysicalEducationBoysFrom14to18FofSchool =
                String.valueOf(healthConditionService.countStudentsExemptFromPhysicalEducationBoysFrom14to18FofSchool(schoolId));
        cell.setPhrase(new Phrase(studentsExemptFromPhysicalEducationBoysFrom14to18FofSchool, font));
        table.addCell(cell);
        String studentsExemptFromPhysicalEducationGirlsFrom14to18FofSchool =
                String.valueOf(healthConditionService.countStudentsExemptFromPhysicalEducationGirlsFrom14to18FofSchool(schoolId));
        cell.setPhrase(new Phrase(studentsExemptFromPhysicalEducationGirlsFrom14to18FofSchool, font));
        table.addCell(cell);

        cell.setColspan(3);
        String includInTherapeuticPhysicalEducation ="Брой ученици, включени в групи по лечебна физкултура в училище";
        cell.setPhrase(new Phrase(includInTherapeuticPhysicalEducation, font));
        table.addCell(cell);

        cell.setColspan(1);
        String studentsIncludInTherapeuticPhysicalEducation =
                String.valueOf(healthConditionService.countStudentsIncludInTherapeuticPhysicalEducation(schoolId));
        cell.setPhrase(new Phrase(studentsIncludInTherapeuticPhysicalEducation, font));
        table.addCell(cell);
        String studentsIncludInTherapeuticPhysicalEducationBoysFrom7to14FofSchool =
                String.valueOf(healthConditionService.countStudentsIncludInTherapeuticPhysicalEducationBoysFrom7to14FofSchool(schoolId));
        cell.setPhrase(new Phrase(studentsIncludInTherapeuticPhysicalEducationBoysFrom7to14FofSchool, font));
        table.addCell(cell);
        String studentsIncludInTherapeuticPhysicalEducationGirlsFrom7to14FofSchool =
                String.valueOf(healthConditionService.countStudentsIncludInTherapeuticPhysicalEducationGirlsFrom7to14FofSchool(schoolId));
        cell.setPhrase(new Phrase(studentsIncludInTherapeuticPhysicalEducationGirlsFrom7to14FofSchool, font));
        table.addCell(cell);
        String studentsIncludInTherapeuticPhysicalEducationBoysFrom14to18FofSchool =
                String.valueOf(healthConditionService.countStudentsIncludInTherapeuticPhysicalEducationBoysFrom14to18FofSchool(schoolId));
        cell.setPhrase(new Phrase(studentsIncludInTherapeuticPhysicalEducationBoysFrom14to18FofSchool, font));
        table.addCell(cell);
        String studentsIncludInTherapeuticPhysicalEducationGirlsFrom14to18FofSchool =
                String.valueOf(healthConditionService.countStudentsIncludInTherapeuticPhysicalEducationGirlsFrom14to18FofSchool(schoolId));
        cell.setPhrase(new Phrase(studentsIncludInTherapeuticPhysicalEducationGirlsFrom14to18FofSchool, font));
        table.addCell(cell);



    }

    private void writeTable5Header(PdfPTable table) {

        Font font = getFont();
        font.setStyle(Paragraph.ALIGN_CENTER);
        font.setStyle(Font.NORMAL);

        PdfPCell cell1 = new PdfPCell();
        cell1.setPadding(5);
        cell1.setColspan(1);
        cell1.setRowspan(3);
        cell1.setPhrase(new Phrase("        МКБ\n" +
                "         10\n" +
                "        код", font));
        table.addCell(cell1);

        PdfPCell cell2 = new PdfPCell();
        cell2.setPadding(5);
        cell2.setColspan(3);
        cell2.setRowspan(3);
        cell2.setPhrase(new Phrase("     Заболявания, които изискват диспансерно\n" +
                "                               наблюдение", font));
        table.addCell(cell2);

        PdfPCell cell3 = new PdfPCell();
        cell3.setPadding(5);
        cell3.setColspan(4);
        cell3.setRowspan(1);
        cell3.setPhrase(new Phrase(
                "                                    Брой диспансерни ученици", font));
        table.addCell(cell3);

        PdfPCell cell4 = new PdfPCell();
        cell4.setPadding(5);
        cell4.setColspan(2);
        cell4.setRowspan(1);
        cell4.setPhrase(new Phrase("                        7-14 г.", font));
        table.addCell(cell4);

        PdfPCell cell5 = new PdfPCell();
        cell5.setPadding(5);
        cell5.setColspan(2);
        cell5.setRowspan(1);
        cell5.setPhrase(new Phrase("                        14-18 г.", font));
        table.addCell(cell5);

        font.setSize(8);
        for (int i = 1; i <= 2; i++) {
            PdfPCell cell8 = new PdfPCell();

            cell8.setPhrase(new Phrase("       Мом-\n       чета", font));
            table.addCell(cell8);
            PdfPCell cell9 = new PdfPCell();

            cell9.setPhrase(new Phrase("      Моми-\n      чета", font));
            table.addCell(cell9);
        }

    }

    private void writeTable5Data(PdfPTable table, HttpSession httpSession,
                                 StudentDispensaryObservationService studentDispensaryObservationService,
                                 DispensaryObservationIllnessTypeService dispensaryObservationIllnessTypeService) {

        java.util.List<DispensaryObservationIllnessTypesResults> dispensaryObservationIllnessTypesResults = new ArrayList<>();
        List<DispensaryObservationIllnessType> allDispensaryObservationIllnessTypes = dispensaryObservationIllnessTypeService.getAllDispensaryObservationIllnessTypes();

        Integer schoolId = (Integer) httpSession.getAttribute("schoolId");

        for(DispensaryObservationIllnessType dispensaryObservationIllnessType : allDispensaryObservationIllnessTypes) {

            DispensaryObservationIllnessTypesResults dispensaryObservationIllnessTypesResult = new DispensaryObservationIllnessTypesResults();
            dispensaryObservationIllnessTypesResult.setDispensaryObservationIllnessType(dispensaryObservationIllnessType);

            dispensaryObservationIllnessTypesResult.setNumberOfStudentDispensaryObservationsGirlsAged7to14(
                    studentDispensaryObservationService.getNumberOfStudentDispensaryObservationsBySchoolIdAndCodeGirlsAged7to14(
                            dispensaryObservationIllnessType.getDispensaryObservIllnessTypeCode(), schoolId));

            dispensaryObservationIllnessTypesResult.setNumberOfStudentDispensaryObservationsBoysAged7to14(
                    studentDispensaryObservationService.getNumberOfStudentDispensaryObservationsBySchoolIdAndCodeBoysAged7to14(
                            dispensaryObservationIllnessType.getDispensaryObservIllnessTypeCode(), schoolId));

            dispensaryObservationIllnessTypesResult.setNumberOfStudentDispensaryObservationsGirlsAged14to18(
                    studentDispensaryObservationService.getNumberOfStudentDispensaryObservationsBySchoolIdAndCodeGirlsAged14to18(
                            dispensaryObservationIllnessType.getDispensaryObservIllnessTypeCode(), schoolId));

            dispensaryObservationIllnessTypesResult.setNumberOfStudentDispensaryObservationsBoysAged14to18(
                    studentDispensaryObservationService.getNumberOfStudentDispensaryObservationsBySchoolIdAndCodeBoysAged14to18(
                            dispensaryObservationIllnessType.getDispensaryObservIllnessTypeCode(), schoolId));

            dispensaryObservationIllnessTypesResults.add(dispensaryObservationIllnessTypesResult);
        }

        Font font = getFont();
        font.setStyle(Paragraph.ALIGN_CENTER);
        font.setStyle(Font.NORMAL);

        for (DispensaryObservationIllnessTypesResults dispensaryObservationIllnessTypesResult : dispensaryObservationIllnessTypesResults) {
            PdfPCell cell = new PdfPCell();
            cell.setPadding(5);
            cell.setColspan(1);
            String dispensaryObservIllnessTypeCode = String.valueOf(dispensaryObservationIllnessTypesResult.getDispensaryObservationIllnessType().getDispensaryObservIllnessTypeCode());
            cell.setPhrase(new Phrase(dispensaryObservIllnessTypeCode, font));
            table.addCell(cell);
            cell.setColspan(3);
            String dispensaryObservIllnessTypeName = String.valueOf(dispensaryObservationIllnessTypesResult.getDispensaryObservationIllnessType().getDispensaryObservIllnessTypeName());
            cell.setPhrase(new Phrase(dispensaryObservIllnessTypeName, font));
            table.addCell(cell);
            cell.setColspan(1);
            String numberOfStudentDispensaryObservationsBoysAged7to14 = String.valueOf(dispensaryObservationIllnessTypesResult.getNumberOfStudentDispensaryObservationsBoysAged7to14());
            cell.setPhrase(new Phrase(numberOfStudentDispensaryObservationsBoysAged7to14, font));
            table.addCell(cell);

            cell.setColspan(1);
            String numberOfStudentDispensaryObservationsGirlsAged7to14 = String.valueOf(dispensaryObservationIllnessTypesResult.getNumberOfStudentDispensaryObservationsGirlsAged7to14());
            cell.setPhrase(new Phrase(numberOfStudentDispensaryObservationsGirlsAged7to14, font));
            table.addCell(cell);

            cell.setColspan(1);
            String numberOfStudentDispensaryObservationsBoysAged14to18 = String.valueOf(dispensaryObservationIllnessTypesResult.getNumberOfStudentDispensaryObservationsBoysAged14to18());
            cell.setPhrase(new Phrase(numberOfStudentDispensaryObservationsBoysAged14to18, font));
            table.addCell(cell);

            cell.setColspan(1);
            String numberOfStudentDispensaryObservationsGirlsAged14to18 = String.valueOf(dispensaryObservationIllnessTypesResult.getNumberOfStudentDispensaryObservationsGirlsAged14to18());
            cell.setPhrase(new Phrase(numberOfStudentDispensaryObservationsGirlsAged14to18, font));
            table.addCell(cell);
        }
    }

    private void writeTable6Header(PdfPTable table) {

        Font font = getFont();
        font.setStyle(Paragraph.ALIGN_CENTER);
        font.setStyle(Font.NORMAL);
        font.setSize(8);

        PdfPCell cell1 = new PdfPCell();
        cell1.setPadding(5);
        cell1.setColspan(5);
        cell1.setRowspan(2);
        cell1.setPhrase(new Phrase("\n                                  Заболявания и аномалии\n", font));
        table.addCell(cell1);

        cell1.setColspan(1);
        cell1.setRowspan(2);
        cell1.setPhrase(new Phrase("\n    Шифър\n", font));
        table.addCell(cell1);

        cell1.setColspan(1);
        cell1.setRowspan(2);
        cell1.setPhrase(new Phrase("    Брой\n" +
                "  ученици\n" +
                "   всичко\n", font));
        table.addCell(cell1);

        cell1.setColspan(3);
        cell1.setRowspan(1);
        cell1.setPhrase(new Phrase("                      В това число:\n", font));
        table.addCell(cell1);

        cell1.setColspan(1);
        cell1.setRowspan(1);
        cell1.setPhrase(new Phrase("    І клас\n", font));
        table.addCell(cell1);

        cell1.setColspan(1);
        cell1.setRowspan(1);
        cell1.setPhrase(new Phrase("    VІІ клас\n", font));
        table.addCell(cell1);

        cell1.setColspan(1);
        cell1.setRowspan(1);
        cell1.setPhrase(new Phrase("    Х клас\n", font));
        table.addCell(cell1);


        cell1.setColspan(5);
        cell1.setRowspan(1);
        cell1.setPhrase(new Phrase(
                "                                                             а\n", font));
        table.addCell(cell1);
        cell1.setColspan(1);
        cell1.setRowspan(1);
        cell1.setPhrase(new Phrase("        б\n", font));
        table.addCell(cell1);
        cell1.setColspan(1);
        cell1.setRowspan(1);
        cell1.setPhrase(new Phrase("        1\n", font));
        table.addCell(cell1);
        cell1.setColspan(1);
        cell1.setRowspan(1);
        cell1.setPhrase(new Phrase("        2\n", font));
        table.addCell(cell1);
        cell1.setColspan(1);
        cell1.setRowspan(1);
        cell1.setPhrase(new Phrase("        3\n", font));
        table.addCell(cell1);
        cell1.setColspan(1);
        cell1.setRowspan(1);
        cell1.setPhrase(new Phrase("        4\n", font));
        table.addCell(cell1);
    }

    private void writeTable6Data(PdfPTable table, HttpSession httpSession,
                                 DiseasesAndAbnormTypeService diseasesAndAbnormTypeService,
                                 StudentDiseasesAndAbnormService studentDiseasesAndAbnormService) {

        List<DiseasesAndAbnormType> allDiseasesAndAbnormTypes = diseasesAndAbnormTypeService.getAllDiseasesAndAbnormTypes();

        Collections.sort(allDiseasesAndAbnormTypes, new Comparator<DiseasesAndAbnormType>() {
            @Override
            public int compare(DiseasesAndAbnormType diseasesAndAbnormType1, DiseasesAndAbnormType diseasesAndAbnormType2) {
                return extractInt(diseasesAndAbnormType1.getDiseasesAndAbnormTypeCode()) - extractInt(diseasesAndAbnormType2.getDiseasesAndAbnormTypeCode());
            }

            int extractInt(String s) {
                String num = s.replaceAll("\\D", "");
                // return 0 if no digits found
                return num.isEmpty() ? 0 : Integer.parseInt(num);
            }
        });

        Font font = getFont();
        font.setStyle(Paragraph.ALIGN_CENTER);
        font.setStyle(Font.NORMAL);
        font.setSize(8);

        PdfPCell cell1 = new PdfPCell();
        cell1.setPadding(5);

        Integer schoolId = (Integer) httpSession.getAttribute("schoolId");
        for (DiseasesAndAbnormType diseasesAndAbnormType : allDiseasesAndAbnormTypes) {
            cell1.setColspan(5);
            String diseasesAndAbnormTypeName = diseasesAndAbnormType.getDiseasesAndAbnormTypeName();
            cell1.setPhrase(new Phrase(diseasesAndAbnormTypeName, font));
            table.addCell(cell1);
            cell1.setColspan(1);
            String diseasesAndAbnormTypeCode = diseasesAndAbnormType.getDiseasesAndAbnormTypeCode();
            if (diseasesAndAbnormTypeCode.length() == 1) {
                diseasesAndAbnormTypeCode = "0" + diseasesAndAbnormTypeCode;
            }
            cell1.setPhrase(new Phrase(diseasesAndAbnormTypeCode, font));
            table.addCell(cell1);
            cell1.setColspan(1);
            String numberOfStudentDiseasesAndAbnormalities = String.valueOf(studentDiseasesAndAbnormService.getNumberOfStudentDiseasesAndAbnormalitiesBySchoolIdAndCode(
                    diseasesAndAbnormType.getDiseasesAndAbnormTypeCode(), schoolId));
            cell1.setPhrase(new Phrase(numberOfStudentDiseasesAndAbnormalities, font));
            table.addCell(cell1);

            cell1.setColspan(1);
            String numberOfStudentDiseasesAndAbnormalitiesBySchoolIdAndCodeIClass = String.valueOf(studentDiseasesAndAbnormService.getNumberOfStudentDiseasesAndAbnormalitiesBySchoolIdAndCodeIClass(
                    diseasesAndAbnormType.getDiseasesAndAbnormTypeCode(), schoolId));
            cell1.setPhrase(new Phrase(numberOfStudentDiseasesAndAbnormalitiesBySchoolIdAndCodeIClass, font));
            table.addCell(cell1);

            cell1.setColspan(1);
            String numberOfStudentDiseasesAndAbnormalitiesBySchoolIdAndCodeVIIClass = String.valueOf(studentDiseasesAndAbnormService.getNumberOfStudentDiseasesAndAbnormalitiesBySchoolIdAndCodeVIIClass(
                    diseasesAndAbnormType.getDiseasesAndAbnormTypeCode(), schoolId));
            cell1.setPhrase(new Phrase(numberOfStudentDiseasesAndAbnormalitiesBySchoolIdAndCodeVIIClass, font));

            table.addCell(cell1);
            cell1.setColspan(1);
            String numberOfStudentDiseasesAndAbnormalitiesBySchoolIdAndCodeXClass = String.valueOf(studentDiseasesAndAbnormService.getNumberOfStudentDiseasesAndAbnormalitiesBySchoolIdAndCodeXClass(
                    diseasesAndAbnormType.getDiseasesAndAbnormTypeCode(), schoolId));
            cell1.setPhrase(new Phrase(numberOfStudentDiseasesAndAbnormalitiesBySchoolIdAndCodeXClass, font));
            table.addCell(cell1);

        }
    }

    public void export(HttpServletResponse httpServletResponse, HttpSession httpSession, SchoolService schoolService,
                       AddressService addressService, StudentService studentService,
                       AnthropologicalService anthropologicalService, PhysicalCapacityService physicalCapacityService,
                       HealthConditionService healthConditionService, StudentDispensaryObservationService studentDispensaryObservationService,
                       DispensaryObservationIllnessTypeService dispensaryObservationIllnessTypeService, StudentDiseasesAndAbnormService studentDiseasesAndAbnormService,
                       DiseasesAndAbnormTypeService diseasesAndAbnormTypeService) throws DocumentException, IOException {

        AnalysisUtil.initCounters();

        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, httpServletResponse.getOutputStream());

        document.open();

        Font font = getFont();

        font.setSize(18);
        font.setStyle(Font.BOLD);

        Paragraph p = new Paragraph("С Х Е М А   З А   А Н А Л И З\n" +
                "НА ЗДРАВОСЛОВНОТО СЪСТОЯНИЕ НА УЧЕНИЦИТЕ\n\n", font);

        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        font.setSize(14);

        Integer schoolId = (Integer) httpSession.getAttribute("schoolId");
        School school = schoolService.findSchoolById(schoolId);

        String schoolType = StringUtils.isNotBlank(school.getSchoolType().getSchoolTypeCode()) ? school.getSchoolType().getSchoolTypeCode() : school.getSchoolTypeOther();
        p = new Paragraph("І. Име и номер на училището: " + school.getSchoolNumber() + " " +schoolType + " " + school.getSchoolName() + "\n", font);
        document.add(p);

        font.setStyle(Font.NORMAL);
        Long addressId = school.getSchoolAddress().getId();
        Address address = addressService.findAddressWithSettlementPlaceByAddressId(addressId);
        p = new Paragraph("     1. Точен адрес: " + address.getSettlementPlace().getSettlementPlaceType() + " " +
                address.getSettlementPlace().getSettlementPlaceName()+
                ", Община: " + address.getSettlementPlace().getMunicipality().getMunicipalityName() +
                ", Област: " +  address.getSettlementPlace().getRegion().getRegionName() +
                ",\nул. "+ address.getStreet() + " № " +
                address.getNumber() + ", телефон: " + school.getSchoolTelephoneNumber(),font);
        document.add(p);

        //  Само логнатото медицинско лице има право да генерира Схемата за анализ
        SchoolMedics schoolMedic = (SchoolMedics) httpSession.getAttribute("schoolMedic");
        p = new Paragraph("     2. Име на медицинския специалист, обслужващ училището: " + schoolMedic.getSchoolMedicsName() +
                ", телефон: " + schoolMedic.getSchoolMedicsTelephoneNumber() + "\n\n", font);
        document.add(p);

        //ІІ. Обслужван контингент
        font.setStyle(Font.BOLD);

        p = new Paragraph("\nІІ. Обслужван контингент \n", font);
        document.add(p);

        PdfPTable table1 = new PdfPTable(3);
        table1.setWidthPercentage(100f);
        table1.setWidths(new float[] {1.5f, 1.5f, 6.0f});
        table1.setSpacingBefore(10);


        writeTable1Header(table1);
        writeTable1Data(table1, studentService, httpSession);

        table1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

        document.add(table1);
        // ІІІ. Данни за антропометричните показатели

        font.setStyle(Font.BOLD);

        p = new Paragraph("\n\nІІІ. Данни за антропометричните показатели \n", font);
        document.add(p);
        font.setStyle(Font.NORMAL);

        AnalysisUtil.initializeSWeightMapping();
        AnalysisUtil.initializeSHeightMapping();
        AnalysisUtil.initializeXWeightMapping();
        AnalysisUtil.initializeXHeightMapping();

        AnalysisUtil.calcAndSetWeightFirstGroup(schoolId, anthropologicalService);
        AnalysisUtil.calcAndSetHeightFirstGroup(schoolId, anthropologicalService);
        AnalysisUtil.calcAndSetWeightSecondGroup(schoolId, anthropologicalService);
        AnalysisUtil.calcAndSetHeightSecondGroup(schoolId, anthropologicalService);
        AnalysisUtil.calcAndSetWeightThirdGroupUnderNorm(schoolId, anthropologicalService);
        AnalysisUtil.calcAndSetHeightThirdGroupUnderNorm(schoolId, anthropologicalService);
        AnalysisUtil.calcAndSetWeightThirdGroupOverNorm(schoolId, anthropologicalService);
        AnalysisUtil.calcAndSetHeightThirdGroupOverNorm(schoolId, anthropologicalService);

        p = new Paragraph("     1. Сборна таблица за оценка на ръста на учениците от училището \n\n", font);
        document.add(p);
        
        PdfPTable table2 = new PdfPTable(16);
        table2.setWidthPercentage(100f);

        writeTable2Header(table2);
        writeTable2Data(table2, studentService, httpSession);
        table2.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        document.add(table2);

        p = new Paragraph("\n     2. Сборна таблица за оценка на телесната маса на учениците от училището \n\n", font);
        document.add(p);

        PdfPTable table3 = new PdfPTable(16);
        table3.setWidthPercentage(100f);

        //заглавната част е еднаква с таблица 2
        writeTable2Header(table3);
        writeTable3Data(table3, studentService, httpSession);
        table3.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        document.add(table3);


        font.setSize(8);
        font.setStyle(Font.BOLD);
        p = new Paragraph(        "     Забележка: ", font);
        document.add(p);
        font.setStyle(Font.ITALIC);
        p = new Paragraph( "     Индивидуалната оценка на ръста и телесната маса за всеки ученик се определя по таблиците, посочени в Приложение № 6.\n", font);
        document.add(p);
        font.setStyle(Font.NORMAL);


        font.setSize(14);
        font.setStyle(Font.BOLD);

        document.newPage();
        p = new Paragraph( "ІV. Изследване на физическа дееспособност\n", font);
        document.add(p);

        font.setStyle(Font.NORMAL);
        p = new Paragraph( "Брой ученици, получили оценка над среден (3)\n\n", font);
        document.add(p);


        PdfPTable table4 = new PdfPTable(8);
        table4.setWidthPercentage(100f);

        writeTable4Header(table4);
        writeTable4Data(table4, httpSession, physicalCapacityService, healthConditionService);
        table4.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        document.add(table4);


        font.setSize(8);
        font.setStyle(Font.BOLD);
        p = new Paragraph(        "     Забележка: ", font);
        document.add(p);
        font.setStyle(Font.ITALIC);
        p = new Paragraph( "     Измерванията на показателите за физическа дееспособност се извършва от учителя по физическо възпитание. \n", font);
        document.add(p);
        font.setStyle(Font.NORMAL);

        font.setSize(14);
        font.setStyle(Font.BOLD);

        p = new Paragraph( "\nV. Диспансерно наблюдение (по Наредба № 39 от 2004 г. за профилактичните прегледи и диспансеризацията, публикувана в ДВ, бр. 106 от 2004 г.)\n\n", font);
        document.add(p);

        PdfPTable table5 = new PdfPTable(8);
        table5.setWidthPercentage(100f);

        writeTable5Header(table5);
        writeTable5Data(table5, httpSession, studentDispensaryObservationService, dispensaryObservationIllnessTypeService);

        table5.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        document.add(table5);

        font.setSize(8);
        font.setStyle(Font.BOLD);
        p = new Paragraph(        "     Забележка: ", font);
        document.add(p);

        font.setStyle(Font.NORMAL);
        p = new Paragraph("     Колона 1 – вписват се само заболяванията, за които има диспансеризирани ученици от училището," +
                        " като се използва списъкът на болестите, при които децата подлежат на диспансеризация " +
                        "по Приложение № 4 към чл. 12 по Наредба № 39 на МЗ от 2004 г.\n", font);
        document.add(p);
        p = new Paragraph("     Колона 2 – вписва се броят ученици, диспансеризирани за съответното заболяване " +
                "по време на профилактични прегледи, извършени в периода от месец септември до месец декември.\n", font);
        document.add(p);

        font.setSize(14);
        font.setStyle(Font.BOLD);
//        VI. Регистрирани заболявания и аномалии при основния профилактичен преглед на децата, проведен в периода септември - декември, отразен в личните им профилактични карти
                p = new Paragraph("\n     VI. Регистрирани заболявания и аномалии при основния профилактичен " +
                "преглед на децата, проведен в периода септември - декември, отразен в личните им профилактични карти \n\n", font);
        document.add(p);

        PdfPTable table6 = new PdfPTable(10);
        table6.setWidthPercentage(100f);

        writeTable6Header(table6);
        writeTable6Data(table6, httpSession, diseasesAndAbnormTypeService, studentDiseasesAndAbnormService);
        table6.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        document.add(table6);

        font.setStyle(Font.NORMAL);
        DateTimeFormatter formater = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate now = LocalDate.now();
        String formatedDate = formater.format(now);

        p = new Paragraph("     Дата: " + formatedDate + "\n", font);
        document.add(p);
        p = new Paragraph("     Изготвил анализа: " + schoolMedic.getSchoolMedicsName() +
                ", телефон: " + schoolMedic.getSchoolMedicsTelephoneNumber() + "\n\n", font);
        document.add(p);

        document.close();

    }

    private Font getFont() {
        File resourcesDirectory = new File("src/main/resources/arial.ttf");
        String absolutePath = resourcesDirectory.getAbsolutePath();

        FontFactory.register(absolutePath, "FontArial");
        return FontFactory.getFont("FontArial", "Cp1251", true);
    }
}