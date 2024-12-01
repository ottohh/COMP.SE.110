<script lang="ts">
  import { twMerge } from 'tailwind-merge'
  import type { FlightDTO } from '../types'
  import Weather from './Weather.svelte'

  type Props = FlightDTO & {
    onClick: (destination: string) => void
    selected?: boolean
  }

  const { airport, price, weather, onClick, selected = false }: Props = $props()
</script>

<button
  class={twMerge(
    'ticket flex w-full flex-col gap-2 rounded-xl bg-blue-300 p-4 py-8',
    selected ? 'bg-indigo-400' : 'hover:bg-indigo-300'
  )}
  onclick={() => onClick(airport.iata)}
>
  <div class="flex w-full items-center justify-between gap-4">
    <span class="flex items-center gap-2 text-3xl font-bold">
      {airport.city_name}
      <span class="text-sm text-gray-500">
        ({airport.iata})
      </span>
    </span>
    <span class="text-center text-sm font-semibold text-gray-800">8hrs 15mins</span>
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
