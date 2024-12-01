<script lang="ts">
  import { getSearchHistory, starPress } from '$lib/history'
  import { onMount } from 'svelte'
  import { MapLibre, Marker } from 'svelte-maplibre'
  import Arc from '../../components/Arc.svelte'
  import Loading from '../../components/Loading.svelte'
  import Flights from './Flights.svelte'

  const { data } = $props()

  let selectedFlight = $state<string | null>(null)

  let history = $state<string[]>([])

  onMount(async () => {
    history = await getSearchHistory()
  })

  let loading = $state(false)
  let favorite = $derived(history.includes(data.from?.iata || ''))

  const starToggle = async () => {
    loading = true
    try {
      await starPress(data.from?.iata || '')
      history = await getSearchHistory()
    } catch (error) {
      console.error(error)
    } finally {
      loading = false
    }
  }

  $effect(() => {
    // Log flights
    console.log(data.flights)
  })
</script>

{#await data.flights}
  <Loading />
{:then { data: flights }}
  <div class="flex h-full w-full">
    <div class="flex max-h-[100vh] flex-1 flex-col items-stretch justify-start gap-8 overflow-y-auto p-4">
      <div class="flex items-center justify-between">
        <a href="/" class="text-blue-500" aria-label="Go back">
          <!-- Arrow left -->
          <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M10 19l-7-7m0 0l7-7m-7 7h18" />
          </svg>
        </a>
        <h1 class="ml-4 text-3xl font-bold">{data.from?.name || 'Flights'}</h1>

        <!-- Star icon that is filled / is not filled. On click it will call the starPress -->
        <button
          disabled={loading}
          onclick={starToggle}
          title={favorite ? 'Remove from favorites' : 'Add to favorites'}
          aria-label={favorite ? 'Remove from favorites' : 'Add to favorites'}
        >
          <svg
            xmlns="http://www.w3.org/2000/svg"
            class="h-6 w-6"
            fill={favorite ? 'gold' : 'none'}
            viewBox="0 0 24 24"
            stroke="rgb(29, 0, 51)"
          >
            <path
              stroke-linecap="round"
              stroke-linejoin="round"
              stroke-width="2"
              d="M12 2l2.371 7.128h7.629l-6.171 4.502 2.371 7.128-6.371-4.752-6.371 4.752 2.371-7.128-6.171-4.502h7.629z"
            />
          </svg>
        </button>
      </div>
      <Flights {flights} from={data.from} bind:selectedFlight />
    </div>
    <div class="flex-[2]">
      <MapLibre
        style="https://basemaps.cartocdn.com/gl/positron-gl-style/style.json"
        class="relative h-full w-full"
        pitch={30}
        zoom={2}
        standardControls
      >
        {#snippet children({ map })}
          {#if selectedFlight}
            {@const to = flights.find((f) => f.airport.iata === selectedFlight)}
            {@const arc = {
              source: [data.from.longitude, data.from.latitude],
              target: [to?.airport.longitude || 0, to?.airport.latitude || 0]
            }}
            <Arc
              arc={[arc]}
              onUpdate={(bbox) => {
                console.log('bbox', bbox)
                map.fitBounds(bbox, { padding: 100 })
              }}
            />
          {:else}
            <Arc
              arc={flights
                .filter((f) => f.flight)
                .map((f) => ({
                  source: [data.from.longitude, data.from.latitude],
                  target: [f.airport.longitude, f.airport.latitude]
                }))}
              onUpdate={(bbox) => {
                console.log('bbox', bbox)
                map.fitBounds(bbox, { padding: 100 })
              }}
            />
          {/if}

          <!-- Origin airport geomarker on map -->
          {#if data.from}
            <Marker
              lngLat={[data.from.longitude, data.from.latitude]}
              class="grid h-8 w-8 place-items-center rounded-full border border-gray-200 bg-green-300 text-black shadow-2xl focus:outline-2 focus:outline-black"
            >
              <span>
                {data.from.iata}
              </span>
            </Marker>
          {/if}
        {/snippet}
      </MapLibre>
    </div>
  </div>
{/await}
