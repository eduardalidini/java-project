package java.ryan;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.ryan.flightclass.Flight;
import java.ryan.flightclass.FlightRequest;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RestController
public class RyanApplication {

	public static void main(String[] args) {
		SpringApplication.run(RyanApplication.class, args);
	}
	@PostMapping("/flights")
	public List<Flight> getFlights(@RequestBody FlightRequest request) {
		List<Flight> flights = new ArrayList<>();

		try {
			String url = "https://www.ryanair.com/gb/en/booking/home/" +
					request.getDeparture() + "/" +
					request.getArrival() + "/" +
					request.getDepartureDate() + "/1/0/0/0";

			// Fetch the website's HTML using Jsoup
			Document doc = Jsoup.connect(url).get();

			// Find all flight rows
			Elements flightRows = doc.select(".flight-header:not(.headline):not(.past)");
			for (Element row : flightRows) {
				// Extract flight details from each row
				String flightNumber = row.select(".flight-number").text();
				String departureTime = row.select(".departure-time").text();
				String arrivalTime = row.select(".arrival-time").text();
				String price = row.select(".price-value").text();

				// Create a Flight object and add it to the flights list
				flights.add(new Flight(flightNumber, departureTime, arrivalTime, price));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		//Return the list of flights as the API response
		return flights;
	}

//		try {
//			// Construct the URL with the provided parameters
//			String url = "https://www.ryanair.com/gb/en/flights-from-"
//					+ request.getDeparture() + "/to-" + request.getArrival()
//					+ "/" + request.getDepartureDate();
//
//			// Set proxy host and port
//			String proxyHost = "YOUR_PROXY_HOST";
//			int proxyPort = YOUR_PROXY_PORT;
//
//			// Create a proxy for Jsoup connection
//			Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost, proxyPort));
//
//			// Fetch the web page using Jsoup with the proxy
//			Connection connection = Jsoup.connect(url).proxy(proxy);
//			Document document = connection.get();
//
//			// Extract flight data from the search results
//			Elements flightElements = document.select("CSS_SELECTOR_FOR_FLIGHTS");
//			for (Element flightElement : flightElements) {
//				String flightNumber = flightElement.select("CSS_SELECTOR_FOR_FLIGHT_NUMBER").text();
//				String departureTime = flightElement.select("CSS_SELECTOR_FOR_DEPARTURE_TIME").text();
//				String arrivalTime = flightElement.select("CSS_SELECTOR_FOR_ARRIVAL_TIME").text();
//				String price = flightElement.select("CSS_SELECTOR_FOR_PRICE").text();
//
//				// Create Flight object and add it to the 'flights' list
//				Flight flight = new Flight(flightNumber, departureTime, arrivalTime, price);
//				flights.add(flight);
//			}
//
//		} catch (IOException e) {
//			e.printStackTrace();
//			return (List<Flight>) new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//
//		return new ResponseEntity<>(flights, HttpStatus.OK).getBody();
//	}
}
