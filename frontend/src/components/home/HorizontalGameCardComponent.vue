<template>
    <div
        class="horizontal-card flex w-[22rem] h-[17.5rem] flex-row items-stretch content-center bg-gray-15 rounded-lg gap-5 p-3">
        <img :src="imgUrl" alt="Game Image" class="w-40 rounded-xl game-preview" />
        <div class="flex gap-2 w-full flex-col items-start justify-between flex-1">
            <div class="flex items-start flex-col">
                <h3 class="game-title ">{{ content.name }}</h3>
                <h4 class="game-studio">{{ content.studio }}</h4>
            </div>
            <h4 class="release-date">{{ content.releaseDate }}</h4>
        </div>
        <div class="card-mask"></div>
        <img src="../../assets/icons/icon-game-add.png" alt="Card Icon" class="card-icon">
    </div>
</template>

<script setup lang='ts'>
import { ref } from 'vue';
import type CardContent from '../../interfaces/CardContent';
interface HorizontalGameCardProps {
    content: CardContent
}
const props = defineProps<HorizontalGameCardProps>()
const imgUrl = ref(`https://cdn.cloudflare.steamstatic.com/steam/apps/${props.content.steamId}/header.jpg`)
</script>
<style scoped>
.horizontal-card {
    transition: all 0.3s;
    position: relative;
    overflow: hidden;
}

.horizontal-card:hover,
.horizontal-card:focus,
.horizontal-card:focus-within {
    transform: scale(1.10);
    z-index: 1;
}

.game-title {
    font-weight: 500;
    font-size: 1.25rem;
    color: var(--color-white);
}

.game-preview {
    object-fit:cover;
}

.game-studio,
.release-date {
    font-weight: 300;
    font-size: 1rem;
    color: var(--color-gray);
}

.card-mask {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    pointer-events: none;
    background: linear-gradient(180deg, rgba(29, 29, 31, 0.9) 15.1%, rgba(29, 29, 31, 0) 100%);
    opacity: 0;
    transition: opacity 0.3s ease;
    z-index: 2;
}

.card-icon {
    position: absolute;
    top: 10px;
    right: 10px;
    width: 30px;
    height: 30px;
    opacity: 0;
    transition: opacity 0.3s ease;
    z-index: 10;
    cursor: pointer;
}

.horizontal-card:hover .card-mask,
.horizontal-card:hover .card-icon {
    opacity: 1;
}
</style>