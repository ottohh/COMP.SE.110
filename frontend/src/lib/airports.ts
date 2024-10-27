import type { Airport } from '../types'
import { baseUrl } from './api'

export async function getAllAirports(get: typeof window.fetch = fetch) {
  const res = await get(`${baseUrl}/airports/all`)
  const airports = await res.json()
  return airports as Airport[]
}
