import { getAllAirports } from '$lib/airports.js'

export const load = async ({ fetch }) => {
  return {
    airports: await getAllAirports(fetch)
  }
}
