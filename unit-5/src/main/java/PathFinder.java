import entity.DeliveryTask;
import entity.Route;
import entity.RouteType;
import entity.Transport;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.toMap;

public class PathFinder {

    public Transport getOptimalTransport(DeliveryTask deliveryTask, List<Transport> transports) {
        List<Transport> filteredTransports = getFilteredTransports(deliveryTask, transports);
        Map<Transport, Double> transportWithPrice = getSortedTransportsWithPrices(deliveryTask, filteredTransports);
        Optional<Transport> cheapest = transportWithPrice.keySet().stream().findFirst();
        return cheapest.orElseThrow(() -> new RuntimeException("Нет подходящего транспорта"));
    }

    /**
     * Получить map транспорта с ценами, отсортированный по увеличению цены
     */
    private Map<Transport, Double> getSortedTransportsWithPrices(DeliveryTask deliveryTask, List<Transport> transports) {
        List<Double> prices = transports
                .stream()
                .map(transport -> getTransportPrice(deliveryTask, transport))
                .collect(Collectors.toList());

        Map<Transport, Double> transportWithPrice = new HashMap<>();
        for (int i=0; i < transports.size(); i++) {
            transportWithPrice.put(transports.get(i), prices.get(i));
        }

        return transportWithPrice
                .entrySet()
                .stream()
                .sorted(comparingByValue())
                .collect(
                        toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                                LinkedHashMap::new));
    }

    /**
     * Отфильтровать транспорт по типу маршрута и объему груза из таски
     */
    private List<Transport> getFilteredTransports(DeliveryTask deliveryTask, List<Transport> transports) {
        return transports.stream()
                .filter(transport -> getRouteTypes(deliveryTask).contains(transport.getType()))
                .filter(transport -> transport.getVolume() >= deliveryTask.getVolume())
                .collect(Collectors.toList());
    }

    /**
     * Получить список маршрутов из таски
     */
    private List<RouteType> getRouteTypes(DeliveryTask task) {
        return task.getRoutes().stream()
                .map(Route::getType)
                .collect(Collectors.toList());
    }

    /**
     * Получить цену транспорта для маршрута
     */
    private double getTransportPrice(DeliveryTask task, Transport transport) {
        double price = transport.getPrice();
        double length = 0;
        for (Route route : task.getRoutes()) {
            if (route.getType().equals(transport.getType())) {
                length = route.getLength();
            }
        }
        return price * length;
    }
}
