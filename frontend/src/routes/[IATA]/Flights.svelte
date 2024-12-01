<script lang="ts">
  import { flip } from 'svelte/animate'
  import { cubicOut } from 'svelte/easing'
  import Flight from '../../components/Flight.svelte'
  import type { Airport, FlightDTO } from '../../types'

  type Props = {
    flights: FlightDTO[]
    from: Airport
    selectedFlight: string | null
  }

  let { flights, from, selectedFlight = $bindable() }: Props = $props()

  const euclideanDistance = (a: Airport, b: Airport) => {
    const aLatLng = [a.latitude, a.longitude]
    const bLatLng = [b.latitude, b.longitude]

    return Math.sqrt(aLatLng.reduce((acc, val, i) => acc + Math.pow(val - bLatLng[i], 2), 0))
  }

  type sortable = 'price' | 'distance' | 'weather'

  let sorting = $state<sortable>('price')

  let sortedFlights = $derived.by(() =>
    flights
      .filter((f) => f.flight)
      .sort((a, b) => {
        switch (sorting) {
          case 'price':
            return parseFloat(a.price) - parseFloat(b.price)
          case 'distance':
            return euclideanDistance(a.airport, from) - euclideanDistance(b.airport, from)
          case 'weather':
            // score by temperature and sunny weather
            const aScore = a.weather.main.temp + (a.weather.weather[0].main === 'Clear' ? 10 : 0)
            const bScore = b.weather.main.temp + (b.weather.weather[0].main === 'Clear' ? 10 : 0)

            return bScore - aScore
        }
      })
  )
</script>

<div class="flex w-full items-center justify-center gap-2">
  Sort by:
  <select class="min-w-28 rounded-lg p-2" bind:value={sorting}>
    <option value="price">Price</option>
    <option value="distance">Distance</option>
    <option value="weather">Weather</option>
  </select>
</div>

{#if flights.length === 0}
  <div class="flex h-full w-full items-center justify-center">
    <h1 class="text-3xl font-bold">No flights tomorrow :(</h1>
  </div>
{/if}
{#each sortedFlights as flight (flight.airport.iata)}
  <div animate:flip={{ easing: cubicOut, delay: 50 }}>
    <Flight selected={flight.airport.iata === selectedFlight} onClick={(d) => (selectedFlight = d)} {...flight} />
  </div>
{/each}
