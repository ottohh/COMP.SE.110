<script lang="ts">
  import { MapLibre } from 'svelte-maplibre'
  import Arc from '../../components/Arc.svelte'
  import Flight from '../../components/Flight.svelte'

  const { data } = $props()

  let selectedFlight = $state<string | null>(null)

  $inspect({ selectedFlight, from: data.from, flights: data.flights })
</script>

{#await data.flights}
  <div>Loading flights...</div>
{:then { data: flights }}
  <div class="flex h-full w-full">
    <div class="flex flex-1 flex-col items-stretch justify-start">
      {#each flights as flight}
        <Flight selected={flight.airport.iata === selectedFlight} onClick={(d) => (selectedFlight = d)} {...flight} />
      {/each}
    </div>
    <div class="flex-[2]">
      <MapLibre
        style="https://basemaps.cartocdn.com/gl/positron-gl-style/style.json"
        class="relative h-full w-full"
        pitch={30}
        standardControls
      >
        {#snippet children({ map })}
          {#if data.from && selectedFlight}
            {@const from = data.from}
            {@const to = flights.find((f) => f.airport.iata === selectedFlight)}
            {@const arc = {
              source: [from.longitude, from.latitude],
              target: [to?.airport.longitude || 0, to?.airport.latitude || 0]
            }}
            <Arc
              {arc}
              onUpdate={(bbox) => {
                console.log('bbox', bbox)
                map.fitBounds(bbox, { padding: 100 })
              }}
            />
          {/if}
        {/snippet}
      </MapLibre>
    </div>
  </div>
{/await}
