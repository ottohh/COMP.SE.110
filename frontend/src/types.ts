export type Airport = {
  city_name: string
  country: string
  country_code: string
  latitude: number
  longitude: number
  no_routes: number
  name: string
  iata: string
}

export type FlightDTO = {
  price: string
  flight: {
    id: number
    co2Emissions: number
    totalPrice: number
    lastTicketingDate: string
    duration: string
    carrierCode: string
    aircraft: string
  }
  weather: {
    dt: number
    main: {
      temp: number
      feelsLike: number
      tempMin: number
      tempMax: number
      pressure: number
      seaLevel: number
      groundLevel: number
      humidity: number
    }
    weather: {
      id: number
      main: string
      description: string
      icon: string
    }[]
    clouds: {
      all: number
    }
    wind: {
      speed: number
      deg: number
      gust: number
    }
    visibility: number
    pop: number
    rain: null | Record<string, unknown> // Adjust based on rain data structure if available
    dt_txt: string
  }
  airport: {
    city_name: string
    country: string
    country_code: string
    latitude: number
    longitude: number
    no_routes: number
    name: string
    iata: string
  }
}
