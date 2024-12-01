<script lang="ts">
  import { ArcLayer } from '@deck.gl/layers'
  import { DeckGlLayer } from 'svelte-maplibre'

  type Props = {
    arc: {
      source: [number, number] | number[]
      target: [number, number] | number[]
    }[]
    onUpdate: (bbox: [[number, number], [number, number]]) => void
  }

  let props: Props = $props()

  $effect(() => {
    // Calculate southwest and northeast corners
    const minLat = Math.min(...props.arc.map((d) => Math.min(d.source[1], d.target[1])))
    const maxLat = Math.max(...props.arc.map((d) => Math.max(d.source[1], d.target[1])))
    const minLng = Math.min(...props.arc.map((d) => Math.min(d.source[0], d.target[0])))
    const maxLng = Math.max(...props.arc.map((d) => Math.max(d.source[0], d.target[0])))

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
  data={props.arc}
  getSourcePosition={(d: Props['arc'][number]) => d.source}
  getTargetPosition={(d: Props['arc'][number]) => d.target}
  getSourceColor={() => [29, 0, 51]}
  getTargetColor={() => [29, 0, 51]}
  autoHighlight={true}
  highlightColor={[30, 255, 30]}
  getWidth={5}
  getHeight={1}
></DeckGlLayer>
