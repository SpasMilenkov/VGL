<template>
    <div>
        <HeroGameModalComponent v-if="isModalVisible"  :steam-id="game" @close-modal="closeModal" />
        <div class="carousel">
            <Carousel :autoplay="3750" :transition="850" :wrap-around="true">
                <Slide v-for="(slide, k) in cards" :key="k">
                    <HeroGameCardComponent :content="slide" @click="showModal(slide.steamId)" />
                </Slide>

                <template #addons>
                    <Pagination />
                </template>
            </Carousel>
        </div>
    </div>
</template>

<script setup lang='ts'>
import { Carousel, Slide } from 'vue3-carousel';
import 'vue3-carousel/dist/carousel.css';
import HeroGameModalComponent from "./HeroGameModalComponent.vue"
import HeroGameCardComponent from "./HeroGameCardComponent.vue"
import { ref, type Ref } from 'vue';
import type CardContent from '../../interfaces/CardContent';

interface HeroCarouselProps {
    cards: CardContent[]
}
defineProps<HeroCarouselProps>()

const isModalVisible = ref(false);
const game = ref()

const showModal = (id: number) => {
    game.value = id
    isModalVisible.value = true;
    document.body.style.overflow = 'hidden'
};

const closeModal = () => {
    isModalVisible.value = false
    document.body.style.overflow = 'auto'
}
</script>
<style scoped>
@media (max-width: 1250px) {
    .carousel {
        width: 70rem;
    }
}

@media (max-width: 1000px) {
    .carousel {
        width: 53rem;
    }
}

@media (max-width: 850px) {
    .carousel {
        width: 40rem;
    }
}

@media (max-width: 450px) {
    .carousel {
        width: 25rem;
    }
}

@media (max-width: 320px) {
    .carousel {
        width: 20rem;
    }
}</style>