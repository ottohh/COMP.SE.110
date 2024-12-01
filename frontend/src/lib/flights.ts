import type { FlightDTO } from '../types'
import { baseUrl } from './api'

export async function getFlightsFrom(originIATA: string, get: typeof window.fetch = fetch) {
  const page = 1

  const query = new URLSearchParams({
    originIATA: originIATA,
    page: page.toString()
  })

  const res = await get(`${baseUrl}/getFlights?${query}`)

  const flights = await res.json()
  return flights as { data: FlightDTO[] }
}
