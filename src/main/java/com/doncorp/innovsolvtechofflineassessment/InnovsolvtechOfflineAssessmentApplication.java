package com.doncorp.innovsolvtechofflineassessment;

import com.doncorp.innovsolvtechofflineassessment.model.Planet;
import com.doncorp.innovsolvtechofflineassessment.model.Route;
import com.doncorp.innovsolvtechofflineassessment.model.Traffic;
import com.doncorp.innovsolvtechofflineassessment.repository.PlanetRepository;
import com.doncorp.innovsolvtechofflineassessment.repository.RouteRepository;
import com.doncorp.innovsolvtechofflineassessment.repository.TrafficRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;

@SpringBootApplication
@Slf4j
@EnableSwagger2
public class InnovsolvtechOfflineAssessmentApplication {
    @Autowired
    private static PlanetRepository planetRepository;
    @Autowired
    private static RouteRepository routeRepository;
    @Autowired
    private static TrafficRepository trafficRepository;

    private static String EXCEL_FILE="Worksheet-in-HR-OffsiteAssignmentV30.xlsx";

    public static void main(String args[]) throws IOException {
        SpringApplication.run(InnovsolvtechOfflineAssessmentApplication.class, args);

    }

    @Bean
    public Docket SwaggerConfiguration(){

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.ant("/api/planets/*"))
                .apis(RequestHandlerSelectors.basePackage("com.doncorp"))
                .build()
                .apiInfo(apiDetails());

    }
    private ApiInfo apiDetails(){
        return new ApiInfo(
                "Planets API",
                "Sample API for Planets ",
                "1.0",
                "Free to use ",
                new springfox.documentation.service.Contact("Iggy ", "http://discovery.co.za","imdondo@gmail.com"),
                "API License",
                "www.discovery.co.za",
                Collections.emptyList());
    }
    @Bean
    @Order(value = 1)
    public CommandLineRunner demo(PlanetRepository planetRepository, TrafficRepository trafficRepository, RouteRepository routeRepository) {
        return (args) -> {
            createPlanets(planetRepository);
            createTraffic(trafficRepository);
            createRoutes(routeRepository);
            return;
        };
    }

    private void createPlanets(PlanetRepository planetRepository) throws IOException {
        // save a few planets
        try (FileInputStream excelFile = new FileInputStream(InnovsolvtechOfflineAssessmentApplication.class.getClassLoader().getResource(EXCEL_FILE).getFile())) {
            XSSFSheet sheet;
            try (XSSFWorkbook workbook = new XSSFWorkbook(excelFile)) {
                sheet = workbook.getSheetAt(0);
            }
            Row row;
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {  //points to the starting of excel i.e excel first row
                row = sheet.getRow(i);  //sheet number

                String node;
                if (row.getCell(0) == null) {
                    node = "null";
                }  //suppose excel cell is empty then its set to 0 the variable
                else node = row.getCell(0).toString();   //else copies cell data to name variable

                String name;
                if (row.getCell(1) == null) {
                    name = "null";
                } else name = row.getCell(1).toString();
                Planet planet = new Planet();
                planet.setPlanetNode(node);
                planet.setPlanetName(name);
                log.info(planet.getPlanetNode() + " " + planet.getPlanetName());
                log.info(planet.toString());
                planetRepository.save(planet);
            }
        }
    }

    private void createTraffic(TrafficRepository trafficRepository) throws IOException {
        // save a few traffic items
        try (FileInputStream excelFile = new FileInputStream(InnovsolvtechOfflineAssessmentApplication.class.getClassLoader().getResource(EXCEL_FILE).getFile())) {

            XSSFSheet sheet;
            try (XSSFWorkbook workbook = new XSSFWorkbook(excelFile)) {
                sheet = workbook.getSheetAt(2);
            }
            Row row;
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {  //points to the starting of excel i.e excel first row
                row = sheet.getRow(i);  //sheet number

                int routeId;
                if (row.getCell(2) == null) routeId = 0;
                routeId = (int) row.getCell(0).getNumericCellValue();   //else copies cell data to name variable

                String origin;
                if (row.getCell(1) == null) {
                    origin = "null";
                }  //suppose excel cell is empty then its set to 0 the variable
                else origin = row.getCell(1).toString();   //else copies cell data to name variable
                log.info("This is the origin " + origin);
                String destination;
                if (row.getCell(2) == null) {
                    destination = "null";
                } else destination = row.getCell(2).toString();
                log.info("This is the destination " + destination);
                Double distance;
                if (row.getCell(3) == null) {
                    distance = 0.0;
                } else distance = row.getCell(3).getNumericCellValue();
                log.info("This is the delay" + distance);
                Traffic traffic = new Traffic();

                traffic.setPlanetOrigin(origin);
                traffic.setPlanetDestination(destination);
                traffic.setTrafficDelay(distance);
                log.info(traffic.getPlanetOrigin() + " " + traffic.getPlanetDestination() + "" + traffic.getTrafficDelay());
                log.info(traffic.toString());
                trafficRepository.save(traffic);
            }
        }
    }

    private void createRoutes(RouteRepository routeRepository) throws IOException {
        // save a few routes
        try (FileInputStream excelFile = new FileInputStream(InnovsolvtechOfflineAssessmentApplication.class.getClassLoader().getResource(EXCEL_FILE).getFile())) {
            XSSFSheet sheet;
            try (XSSFWorkbook workbook = new XSSFWorkbook(excelFile)) {
                sheet = workbook.getSheetAt(1);
            }
            Row row;
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {  //points to the starting of excel i.e excel first row
                row = sheet.getRow(i);  //sheet number

                int routeId;
                //suppose excel cell is empty then its set to 0 the variable
                if (row.getCell(0) == null) routeId = 0;
                else {
                    routeId = (int) row.getCell(0).getNumericCellValue();   //else copies cell data to name variable
                }

                String origin;
                if (row.getCell(1) == null) {
                    origin = "null";
                } else origin = row.getCell(1).toString();

                String destination;
                if (row.getCell(2) == null) {
                    destination = "null";
                } else destination = row.getCell(2).toString();

                Double distance;
                if (row.getCell(3) == null) {
                    distance = 0.0;
                } else distance = row.getCell(3).getNumericCellValue();
                Route route = new Route();
                route.setPlanetOrigin(origin);
                route.setPlanetDestination(destination);
                route.setDistance(distance);
                log.info(route.getPlanetOrigin() + " " + route.getPlanetDestination() + route.getDistance());
                log.info(route.toString());
                routeRepository.save(route);
            }
        }
    }
}


