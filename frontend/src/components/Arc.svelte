<script lang="ts">
  import { ArcLayer } from '@deck.gl/layers'
  import { DeckGlLayer } from 'svelte-maplibre'

  type Props = {
    arc: {
      source: [number, number] | number[]
      target: [number, number] | number[]
    }
    onUpdate: (bbox: [[number, number], [number, number]]) => void
  }

  let props: Props = $props()

  $effect(() => {
    const source = props.arc.source as [number, number]
    const target = props.arc.target as [number, number]

    // Calculate southwest and northeast corners
    const minLng = Math.min(source[0], target[0])
    const minLat = Math.min(source[1], target[1])
    const maxLng = Math.max(source[0], target[0])
    const maxLat = Math.max(source[1], target[1])

    // Construct proper bbox
    const bbox: [[number, number], [number, number]] = [
      [minLng, minLat], // Southwest corner
      [maxLng, maxLat] // Northeast corner
    ]

    props.onUpdate(bbox)
  })
</script>

<DeckGlLayer
  type={ArcLayer}
  data={[props.arc]}
  getSourcePosition={(d: Props['arc']) => d.source}
  getTargetPosition={(d: Props['arc']) => d.target}
  getSourceColor={() => [29, 0, 51]}
  getTargetColor={() => [29, 0, 51]}
  autoHighlight={true}
  highlightColor={[30, 255, 30]}
  getWidth={5}
  getHeight={1}
></DeckGlLayer>
