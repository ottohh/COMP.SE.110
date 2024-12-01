<script lang="ts">
  import { twMerge } from 'tailwind-merge'
  import Airlines from '../assets/airlines.json'
  import EmissionsPNG from '../assets/emissions.png'
  import type { FlightDTO } from '../types'
  import Weather from './Weather.svelte'

  type Props = FlightDTO & {
    onClick: (destination: string) => void
    selected?: boolean
  }

  const props: Props = $props()

  let { airport, price, weather, onClick, flight, selected = false } = $derived(props)

  function parseISO8601Duration(duration: string): string {
    const match = duration.match(/PT(\d+H)?(\d+M)?/)

    if (!match) {
      return '0 min'
    }

    const hours = match[1] ? parseInt(match[1].replace('H', '')) : 0
    const minutes = match[2] ? parseInt(match[2].replace('M', '')) : 0

    return `${hours} hr ${minutes} min`
  }

  const getOperator = (carrierCode: string) => {
    const airline = Airlines.find((airline) => airline.iata === carrierCode)
    return airline?.name || carrierCode
  }
</script>

<button
  class={twMerge(
    'ticket flex w-full flex-col gap-2 rounded-xl bg-blue-300 p-4 py-8',
    selected ? 'bg-indigo-400' : 'hover:bg-indigo-300'
  )}
  onclick={() => onClick(airport.iata)}
>
  <div class="flex w-full items-center justify-between gap-4">
    <div class="flex-col">
      <span class="flex items-center gap-2 text-xl font-bold">
        {airport.city_name}
        <span class="text-sm text-gray-500">
          ({airport.iata})
        </span>
      </span>

      <div class="flex items-center gap-4">
        <span class="text-sm">{getOperator(flight?.carrierCode)}</span>

        {#if flight?.co2Emissions > 0}
          <div class="flex items-center gap-1">
            <img src={EmissionsPNG} alt="CO2 Emissions" class="h-4 w-4 mix-blend-multiply" />
            <span class="text-xs text-gray-500">{flight?.co2Emissions} kg CO2</span>
          </div>
        {/if}
      </div>
    </div>
    <span class="text-center text-sm font-semibold text-gray-800">{parseISO8601Duration(flight?.duration)}</span>
  </div>

  <!-- Horizontal break -->
  <div class="my-4 h-0.5 w-full bg-gray-200 opacity-20"></div>

  <div class="flex w-full items-center justify-between gap-4">
    <span class="text-3xl font-bold text-indigo-950">${price}</span>
    <Weather {...weather} />
  </div>
</button>

<style lang="scss">
  // Create small white circles before and after the ticket horizontally in its middle

  .ticket {
    position: relative;

    &::before,
    &::after {
      content: '';
      position: absolute;
      left: 70%;
      transform: translateX(-50%);
      width: 2rem;
      height: 1.5rem;
      border-radius: 50%;
      background-color: #f7fafc;
    }

    &::before {
      top: -0.9rem; // Positioned above the ticket
    }

    &::after {
      bottom: -0.9rem; // Positioned below the ticket
    }
  }
</style>
