import { getFlightsFrom } from '$lib/flights.js'

export const load = async ({ params, parent }) => {
  const originIATA = params.IATA

  const { airports } = await parent()

  return {
    from: airports.find((airport) => airport.iata === originIATA)!,
    flights: getFlightsFrom(originIATA)
  }
}
