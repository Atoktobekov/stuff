import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import controller.GenerateReportController;
import controller.GetPlaneInfoController;
import repository.PassengerRepository;
import repository.PlaneRepository;
import service.ParsePlaneInfoJsonService;
import service.PrepareReportService;
import view.FinalSinavView;

public class Main {
    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        System.out.println("Jackson подключён успешно!");


        PlaneRepository planeRepo = new PlaneRepository();
        PassengerRepository passengerRepo = new PassengerRepository();

        ParsePlaneInfoJsonService parseService = new ParsePlaneInfoJsonService(planeRepo);
        PrepareReportService reportService = new PrepareReportService(passengerRepo);

        GetPlaneInfoController getCtrl = new GetPlaneInfoController(parseService);
        GenerateReportController reportCtrl = new GenerateReportController(reportService);

        new FinalSinavView(getCtrl, reportCtrl);
    }
}
