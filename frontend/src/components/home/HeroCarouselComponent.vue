
<template>
    <div class="carousel">
        <Carousel :autoplay="3500" :transition="650" :wrap-around="true">
            <Slide v-for="(slide, k) in cards" :key="k">
                <HeroGameCardComponent :content="slide"/>
            </Slide>

            <template #addons>
                <Pagination />
            </template>
        </Carousel>
    </div>
</template>
<script setup lang='ts'>
import { Carousel, Slide} from 'vue3-carousel';
import 'vue3-carousel/dist/carousel.css';
import type { HeroGameCardContent } from '../../interfaces/HeroGameCardContent';
import HeroGameCardComponent from './HeroGameCardComponent.vue';

interface HeroCarouselProps{
    cards: HeroGameCardContent[]
}
defineProps<HeroCarouselProps>()
</script>
<style scoped>
@media (max-width: 1250px){

}
</style>