<script lang="ts">
  import { goto } from '$app/navigation'
  import { fly } from 'svelte/transition'

  const { data } = $props()

  let airports = $derived(data.airports.sort((a, b) => a.name.localeCompare(b.name)))

  const reasons = ['ORDINARY', 'EVERYDAY', 'COMMON', 'REGULAR', 'NORMAL']

  // Random reason
  let reason = $state(reasons[Math.floor(Math.random() * reasons.length)])

  let visible = $state(false)

  $effect(() => {
    visible = true
  })

  const handleFormSubmit = (event: Event) => {
    event.preventDefault()

    const from = (event.target as HTMLFormElement).from.value

    if (from) {
      goto(`/${from}`)
    }
  }
</script>

<main class="flex h-full w-full">
  <div class="flex flex-1 items-center justify-center">
    <form class="flex flex-col gap-8 rounded-md px-4 py-5 sm:p-6" onsubmit={handleFormSubmit}>
      <h1 class="text-center text-lg font-medium leading-6 text-gray-900">
        Escape from
        <span class="font-bold text-indigo-600">{reason}</span>
      </h1>
      <fieldset class="flex flex-col">
        <label for="from" class="block text-sm font-medium leading-6 text-gray-900">From</label>
        <select
          id="from"
          name="from"
          class="mt-2 block w-full rounded-md border-0 py-1.5 pl-3 pr-10 text-gray-900 ring-1 ring-inset ring-gray-300 focus:ring-2 focus:ring-indigo-600 sm:text-sm sm:leading-6"
        >
          <option value="">Select an airport</option>
          {#each airports as airport}
            <option value={airport.iata}>{airport.name}</option>
          {/each}
        </select>
      </fieldset>

      <div class="max-w-40">
        <button
          type="submit"
          class="rounded-full bg-indigo-600 px-4 py-2.5 text-sm font-semibold text-white shadow-sm hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600"
        >
          Fly
        </button>
      </div>
    </form>
  </div>
  <div class="hidden flex-1 md:flex">
    {#if visible}
      <img
        src="/plane.webp"
        alt="plane"
        in:fly={{ x: -100, duration: 1000 }}
        out:fly={{ x: 100, duration: 1000 }}
        class="absolute left-1/2 top-1/2 h-1/2 w-1/2 -translate-x-1/4 -translate-y-1/3 transform object-cover"
      />
    {/if}

    <img src="/clouds.jpg" alt="Clouds" class="h-full w-full object-cover" />
  </div>
</main>
