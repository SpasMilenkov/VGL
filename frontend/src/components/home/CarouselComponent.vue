<template>
    <div class="carousel">
        <Carousel snap-align="start" :items-to-show="4" :wrap-around="true" v-if="isHorizontal">
            <Slide v-for="(slide, id) in cards" :key="id">
                <HorizontalGameCardComponent :content="slide"></HorizontalGameCardComponent>
            </Slide>

            <template #addons>
                <Navigation />
            </template>
        </Carousel>
        <Carousel snap-align="start"  :items-to-show="4" :wrap-around="true" v-if="!isHorizontal">
            <Slide v-for="(slide, id) in cards" :key="id">
                <VerticalGameCardComponent :content="slide"></VerticalGameCardComponent>
            </Slide>

            <template #addons>
                <Navigation />
            </template>
        </Carousel>
    </div>
</template>

<script setup lang="ts">
import { onMounted, ref } from 'vue';
import { Carousel, Slide, Navigation } from 'vue3-carousel';
import 'vue3-carousel/dist/carousel.css';

// Import dynamic components
import HorizontalGameCardComponent from './HorizontalGameCardComponent.vue';
import VerticalGameCardComponent from './VerticalGameCardComponent.vue';

import type SmallGameCardContent from '../../interfaces/SmallGameCardContent';
import type { CarouselSlideType } from '../../types/CarouselSlideType'
interface CarouselProps {
    cards: SmallGameCardContent[],
    cardType: CarouselSlideType
}

const props = defineProps<CarouselProps>();
const isHorizontal = ref(true)
onMounted(() => {
    if (props.cardType === "vertical")
        isHorizontal.value = false
})

</script>


<style>
.carousel {
    width: 107rem;
    overflow: hidden;
}

.carousel__item {
    min-height: 200px;
    width: 80%;
    /* width: 100vw; */
    max-width: 30rem;
    background-color: green;
    color: white;
    font-size: 20px;
    border-radius: 8px;
    display: flex;
    justify-content: center;
    align-items: center;
}

.carousel__slide {
    padding: 10px;
}

.carousel__prev{
    box-sizing: content-box;
    color: var(--color-orange);
    width: 35px;
    display: flex;
    justify-content: flex-start;
    background-color: rgba(0, 0, 0, 0.5);
    border-radius: 1rem;
    margin: 0;
}

.carousel__next {
    box-sizing: content-box;
    color: var(--color-orange);
    background-color: rgba(0, 0, 0, 0.5);
    border-radius: 1rem;
    width: 35px;
    display: flex;
    justify-content: flex-end;
    margin: 0;
}

.carousel__prev:hover,
.carousel__next:hover {
    color: var(--color-orange);
}

</style>
