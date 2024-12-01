<script lang="ts">
  import type { FlightDTO } from '../types'

  type Props = FlightDTO['weather']

  let weatherData: Props = $props()

  const formatTemperature = (temp: number) => `${temp.toFixed(1)}°C`
</script>

<div class="flex items-center">
  <div class="flex flex-col items-end">
    <div class="text-sm font-semibold">
      {formatTemperature(weatherData.main.temp)}
    </div>
    <div class="text-xs text-gray-700">{weatherData.weather[0].description}</div>
  </div>
  <img
    src={`http://openweathermap.org/img/wn/${weatherData.weather[0].icon}.png`}
    alt={weatherData.weather[0].description}
    class="ml-2 h-10 w-10"
  />
</div>
