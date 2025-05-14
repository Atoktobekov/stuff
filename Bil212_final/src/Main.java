import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import controller.GenerateReportController;
import controller.GetPlaneInfoController;
import repository.IPassengerReportSaver;
import repository.IPlaneRepository;
import repository.PassengerRepository;
import repository.PlaneRepository;
import service.IParsePlaneInfoJsonService;
import service.IPrepareReportService;
import service.ParsePlaneInfoJsonService;
import service.PrepareReportService;
import view.FinalSinavView;

public class Main {
    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        System.out.println("Jackson подключён успешно!");


        IPlaneRepository planeRepo = new PlaneRepository();
        IPassengerReportSaver passengerRepo = new PassengerRepository();

        IParsePlaneInfoJsonService parseService = new ParsePlaneInfoJsonService(planeRepo);
        IPrepareReportService reportService = new PrepareReportService(passengerRepo);

        GetPlaneInfoController getCtrl = new GetPlaneInfoController(parseService);
        GenerateReportController reportCtrl = new GenerateReportController(reportService);

        new FinalSinavView(getCtrl, reportCtrl);
    }
}
