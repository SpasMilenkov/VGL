<template>
    <main class="home-content flex flex-row overflow-hidden">
        <div class="content-container flex flex-col p-4 gap-4 h-full">
            <section class="carousel-section h-full h-min-[50rem]">
                <h1 class="section-title">Most Played</h1>
                <HeroCarouselComponent :cards="slides" />
            </section>
            <section class="carousel-section h-full">
                <h1 class="section-title">Spring 2024 Releases</h1>
                <CarouselComponent :cards="slides" cardType="horizontal" />
            </section>
            <section class="carousel-section h-full">
                <h1 class="section-title">Popular among reviewers</h1>
                <CarouselComponent :cards="slides" cardType="vertical" />
            </section>
            <section class="carousel-section h-full">
                <h1 class="section-title">Top 10 Player’s Recommendations </h1>
                <DoubleCardComponent />
            </section>
        </div>
    </main>
</template>
<script setup lang='ts'>
import CarouselComponent from "./CarouselComponent.vue";
import HeroCarouselComponent from "./HeroCarouselComponent.vue";
import DoubleCardComponent from "./DoubleCardComponent.vue";
import axios from "../../axios/axios";
import { onMounted, ref, type Ref } from "vue";

const slides = ref()

onMounted(async () => {
   slides.value = (await axios.get("/games/random")).data
})

</script>
<style scoped></style>