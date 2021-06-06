package com.health.SchoolHealth.controlers;

import com.health.SchoolHealth.controlers.formPOJOs.AnalysisForm;
import com.health.SchoolHealth.model.DAOs.UserDao;
import com.health.SchoolHealth.services.*;
import com.health.SchoolHealth.util.SchemaPDFExporter;
import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.health.SchoolHealth.util.ControllerUtil.authorizedForAnalysisData;

@RestController
public class AnalysisSchemaExportController {

    // Services
    @Autowired
    private SchoolService schoolService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private SchoolMedicsService schoolMedicsService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private AnthropologicalService anthropologicalService;

    @Autowired
    private PhysicalCapacityService physicalCapacityService;

    @Autowired
    private HealthConditionService healthConditionService;

    @Autowired
    private StudentDispensaryObservationService studentDispensaryObservationService;

    @Autowired
    private DispensaryObservationIllnessTypeService dispensaryObservationIllnessTypeService;

    @Autowired
    private StudentDiseasesAndAbnormService studentDiseasesAndAbnormService;

    @Autowired
    private  DiseasesAndAbnormTypeService diseasesAndAbnormTypeService;

    //Daos
    @Autowired
    private UserDao userDao;


    // ModelAndViews
    ModelAndView modelAndView;


    // Forms
    AnalysisForm analysisForm = new AnalysisForm();

    @GetMapping("/export/pdf")
    public void exportToPDF(HttpServletResponse response, HttpSession httpSession) throws DocumentException, IOException {

        String userTypeCode = String.valueOf(httpSession.getAttribute("userTypeCode"));

        if (authorizedForAnalysisData.contains(userTypeCode)) {

            Integer schoolId = (Integer) httpSession.getAttribute("schoolId");
            response.setContentType("application/pdf");
            DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
            String currentDateTime = dateFormatter.format(new Date());

            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename=schema_" + currentDateTime + ".pdf";
            response.setHeader(headerKey, headerValue);

            SchemaPDFExporter exporter = new SchemaPDFExporter();
            exporter.export(response, httpSession, schoolService, addressService, studentService,
                    anthropologicalService, physicalCapacityService, healthConditionService, studentDispensaryObservationService,
                    dispensaryObservationIllnessTypeService, studentDiseasesAndAbnormService,
                    diseasesAndAbnormTypeService);
        }

    }

}


