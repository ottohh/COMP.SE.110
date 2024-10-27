<script lang="ts">
  const { data } = $props()

  let airports = $derived(data.airports.sort((a, b) => a.name.localeCompare(b.name)))
</script>

<main class="flex h-full w-full flex-col items-center justify-center">
  <div class="overflow-hidden rounded-lg bg-white shadow">
    <form class="flex flex-col gap-8 px-4 py-5 sm:p-6">
      <h1 class="text-center text-lg font-medium leading-6 text-gray-900">
        Your next
        <span class="font-bold text-indigo-600">DESTINATION</span>
        will be?
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
</main>
