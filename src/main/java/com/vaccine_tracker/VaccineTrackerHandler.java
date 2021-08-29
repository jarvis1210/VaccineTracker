package com.vaccine_tracker;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vaccine_tracker.domain.FinalResponse;
import com.vaccine_tracker.domain.FindByPinResponse;
import com.vaccine_tracker.domain.Session;
import com.vaccine_tracker.domain.VaccineInfo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class VaccineTrackerHandler implements RequestHandler<Map<String, String>, FinalResponse> {

    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public FinalResponse handleRequest(Map<String, String> event, Context context) {
        //envDetails(event, context);
        FinalResponse response = callCowinApi(event, context);

        return response;
    }

    private FinalResponse callCowinApi(Map<String, String> event, Context context) {

//        LambdaLogger logger = context.getLogger();
        LocalDate date = LocalDate.now().plusDays(1);
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d-MM-uuuu");
        String nextDay = date.format(formatters);
        String pin = "848114";
        FinalResponse finalResponse = new FinalResponse();
        finalResponse.setDate(nextDay);
        finalResponse.setIsAvailable("NOT AVAILABLE");
        URL url;
        HttpURLConnection conn = null;
        try {

            String queryParams = "?pincode=" + pin + "&date=" + nextDay;//"28-08-2021";
            url = new URL("https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/findByPin" + queryParams);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            StringBuffer output = new StringBuffer();
            System.out.println("Output from Server .... \n");
            String line;
            while ((line = br.readLine()) != null) {
                output.append(line);
            }
            FindByPinResponse response = gson.fromJson(output.toString(), FindByPinResponse.class);

            if (response != null && response.getSessions() != null && !response.getSessions().isEmpty()) {

                List<VaccineInfo> vaccineInfoList = response.getSessions().stream()
                        .map(this::mapToVaccineInfo)
                        .collect(Collectors.toList());

                int totalAvailableDose = vaccineInfoList.stream()
                        .mapToInt(VaccineInfo::getTotalAvailable)
                        .reduce(0, Integer::sum);

                if (totalAvailableDose > 0) {
                    finalResponse.setIsAvailable("AVAILABLE");
                }
                finalResponse.setVaccineInfos(vaccineInfoList);

            }
//            logger.log(output.toString());
//            logger.log(finalResponse.toString());
            System.out.println(finalResponse);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
        return finalResponse;

    }

    private VaccineInfo mapToVaccineInfo(Session session) {

        VaccineInfo vaccineInfo = new VaccineInfo();
        vaccineInfo.setVaccineName(session.getVaccine());
        vaccineInfo.setTotalAvailable(session.getAvailable_capacity());
        vaccineInfo.setDose1(session.getAvailable_capacity_dose1());
        vaccineInfo.setDose2(session.getAvailable_capacity_dose2());
        vaccineInfo.setVaccineCenterName(session.getName());
        vaccineInfo.setVaccineCenterAddress(session.getAddress());
        vaccineInfo.setFreeType(session.getFee_type());

        return vaccineInfo;
    }

    private void envDetails(Map<String, String> event, Context context) {
        LambdaLogger logger = context.getLogger();

        // log execution details
        logger.log("ENVIRONMENT VARIABLES: " + gson.toJson(System.getenv()));
        logger.log("CONTEXT: " + gson.toJson(context));
        // process event
        logger.log("EVENT: " + gson.toJson(event));
        logger.log("EVENT TYPE: " + event.getClass());
    }

//    public static void main(String[] args) {
//        VaccineTrackerHandler handler = new VaccineTrackerHandler();
//        handler.handleRequest(null,null);
//    }
}
