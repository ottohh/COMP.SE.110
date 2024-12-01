<script lang="ts">
  import PlaneGIF from '../assets/plane.gif'

  let dots = $state('')

  $effect(() => {
    const interval = setInterval(() => {
      dots = dots.length < 3 ? dots + '.' : ''
    }, 500)

    return () => clearInterval(interval)
  })
</script>

<!-- Full screen loading screen -->

<div class="fixed inset-0 z-50 flex items-center justify-center bg-white bg-opacity-90">
  <div class="flex flex-col items-center gap-4">
    <img src={PlaneGIF} alt="Loading" class="w-h-72 h-72 mix-blend-hard-light" />
    <span class="text-lg font-semibold text-gray-800">Scanning sky for planes{dots}</span>
  </div>
</div>
