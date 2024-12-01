<script lang="ts">
  import type { FlightDTO } from '../types'

  type Props = FlightDTO & {
    onClick: (destination: string) => void
    selected?: boolean
  }

  const { airport, price, weather, onClick, selected = false }: Props = $props()
</script>

<button onclick={() => onClick(airport.iata)}>
  {airport.city_name} - {airport.name} - {airport.iata}
</button>
