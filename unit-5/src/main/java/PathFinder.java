import entity.DeliveryTask;
import entity.Route;
import entity.RouteType;
import entity.Transport;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PathFinder {

    public Transport getOptimalTransport(DeliveryTask deliveryTask, List<Transport> transports) {
        transports.stream()
                //.map(Transport::getType)
                .filter(transport -> getRouteTypes(deliveryTask).contains(transport.getType()))
                .collect(Collectors.toList());
        return transports.get(0);
        //deliveryTask.getRoutes().stream().map(Route::getType).collect(Collectors.toList()).contains() //получился список с типами маршрутов из таски
        //проверить что тип полученного транспорта есть в этом списке

    }

    public List<Transport> getFilteredTransport (DeliveryTask deliveryTask, List<Transport> transports) {
        return transports.stream()
                //.map(Transport::getType)
                .filter(transport -> getRouteTypes(deliveryTask).contains(transport.getType()))
                .collect(Collectors.toList());
    }


    //получить список маршрутов из таски
    public static List<RouteType> getRouteTypes(DeliveryTask task) {
        return task.getRoutes().stream()
                .map(Route::getType)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<String> test1 = new ArrayList<>();
        test1.add("First");
        test1.add("Second");

        List<String> test2 = new ArrayList<>();
        test2.add("Second");
        test2.add("Third");

        System.out.println(test2.stream()
                //.filter(test -> test1.contains(test))
                .filter(test1::contains)
                .collect(Collectors.toList()));
    }

}
