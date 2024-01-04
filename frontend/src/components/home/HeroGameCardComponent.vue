<template>
    <div class="slide rounded-lg ">
        <img class="slide-img h-full w-full rounded-3xl" :src="content.imageUrl" alt="Minecraft">
        <div class="slide-overlay p-4 w-full h-full flex flex-col-reverse">
            <div class="slide-info flex flex-col w-full items-start">
                <div class="slide-title">{{ content.gameTitle }}</div>
                <div class="slide-dev">{{ content.gameStudio }}</div>
            </div>
        </div>
    </div>
</template>
<script setup lang='ts'>
import type { HeroGameCardContent } from '../../interfaces/HeroGameCardContent';


interface HeroGameCardProps {
    content: HeroGameCardContent
}
defineProps<HeroGameCardProps>()
</script>
<style scoped>
.slide {
    position: relative;
    overflow: hidden;
    cursor: pointer;

    width: 70rem;
    height: 40rem;
}

.slide-img {
    width: 100%;
    height: 100%;
    border-radius: 1.5rem;
    object-fit: cover;
}

.slide-overlay {
    position: absolute;
    bottom: 0;
    left: 0;
    z-index: 10;
    background: linear-gradient(180deg,
            rgba(0, 0, 0, 0) 0%,
            rgba(0, 0, 0, 0.5) 86.82%);
}

.slide-title {
    color: var(--color-white);
    font-weight: 600;
    font-size: 3rem;
    text-transform: uppercase;
}

.slide-dev {
    color: var(--color-white);
    font-weight: 400;
    font-size: 1rem;
    padding: 0 0 0 0.3rem;
}

@media (max-width: 1250px) {
    .slide {
        width: 30rem;
        height: 20rem;
    }

    .slide-title {
        color: var(--color-white);
        font-weight: 600;
        font-size: 1.5rem;
        text-transform: uppercase;
    }

}
</style>