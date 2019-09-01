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
        /*Map<Transport, Double> transportWithPrice = getSortedTransportsWithPrices(deliveryTask, filteredTransports);
        Optional<Transport> cheapest = transportWithPrice.keySet().stream().findFirst();
        return optionalTransport.orElseThrow(() -> new RuntimeException("Нет подходящего транспорта"));*/
        return getTransportsWithMinPrice(deliveryTask, filteredTransports);
    }

    /**
     * Получить map транспорта с ценами, отсортированный по увеличению цены
     */
    /*private Map<Transport, Double> getSortedTransportsWithPrices(DeliveryTask deliveryTask, List<Transport> transports) {
        return IntStream.range(0, transports.size()).boxed()
                .collect(Collectors.toMap(transports::get, transports.stream()
                        .map(transport -> getTransportPrice(deliveryTask, transport))
                        .collect(Collectors.toList())::get))
                .entrySet()
                .stream()
                .sorted(comparingByValue())
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                        LinkedHashMap::new));
    }*/

    private Map<Transport, Double> getSortedTransportsWithPrices(DeliveryTask deliveryTask, List<Transport> transports) {
        return transports.stream()
                .collect(toMap(t -> t,
                        transport -> getTransportPrice(deliveryTask, transport)))
                .entrySet()
                .stream()
                .sorted(comparingByValue())
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                        LinkedHashMap::new));
    }

    private Transport getTransportsWithMinPrice(DeliveryTask deliveryTask, List<Transport> transports) {
        return transports.stream()
                .collect(toMap(t -> t,
                        transport -> getTransportPrice(deliveryTask, transport)))
                .entrySet()
                .stream()
                .min(Comparator.comparingDouble(Map.Entry::getValue))
                .orElseThrow(() -> new RuntimeException("Нет подходящего транспорта"))
                .getKey();
    }

    /**
     * Отфильтровать транспорт по типу маршрута и объему груза из таски
     */
    private List<Transport> getFilteredTransports(DeliveryTask deliveryTask, List<Transport> transports) {
        List<RouteType> routeTypes = getRouteTypes(deliveryTask);
        return transports.stream()
                .filter(transport -> routeTypes.contains(transport.getType()))
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
        double length = task.getRoutes().stream()
                .filter(route -> route.getType().equals(transport.getType()))
                .map(Route::getLength)
                .findFirst().orElse((double) 0);

        return price * length;
    }
}
