<template>
    <div
        class="card vertical-card flex w-[22rem] h-[20rem] flex-col items-stretch content-center bg-gray-15 rounded-lg gap-5 p-3">
        <img :src="props.content.imagePath" alt="Game Image" class="game-preview w-full rounded-xl" />
        <div class="flex gap-2 flex-col items-start justify-between flex-1">
            <h3 class="game-title ">{{ props.content.gameTitle }}</h3>
            <div class="flex justify-between w-full">
                <h4 class="game-studio">{{ props.content.gameStudio }}</h4>
                <h4 class="release-date">{{ props.content.releaseDate }}</h4>
            </div>
        </div>
        <img src="../../assets/icons/icon-game-add.png" alt="Card Icon" class="card-icon">
        <div class="card-mask"></div>
    </div>
</template>

<script setup lang='ts'>
import type SmallGameCardContent from '../../interfaces/SmallGameCardContent';
interface VerticalGameCardProps {
    content: SmallGameCardContent
}
const props = defineProps<VerticalGameCardProps>()
</script>
<style scoped>
.card {
    overflow: hidden;
    position: relative;
}

.vertical-card {
    transition: all 0.3s;
}

.vertical-card:hover,
.vertical-card:focus,
.vertical-card:focus-within {
    transform: scale(1.10);
}

.game-title {
    color: var(--color-white);
    font-size: 1.125rem;
}

.game-preview {
    max-height: 8.5rem;
    height: 65%;
    object-fit: center;
    object-position: center 60%;
    border-radius: 10px;
}

.game-studio,
.release-date {
    color: var(--color-gray);
    font-size: 0.875rem;
}
.card-mask,
.card-icon {
    position: absolute;
    top: 5px;
    right: 5px;
    width: 30px;
    height: 30px;
    transition: opacity 0.3s ease;
    z-index: 10;
    opacity: 0;
    cursor: pointer;
}
.card-mask {
    width: 100%;
    height: 10rem;
    background: linear-gradient(180deg, rgba(29, 29, 31, 0.9) 15.1%, rgba(29, 29, 31, 0) 100%);
}

.card-icon {
    z-index: 11;
}

.vertical-card:hover .card-mask,
.vertical-card:focus .card-mask,
.vertical-card:hover .card-icon,
.vertical-card:focus .card-icon {
    opacity: 1;
}
</style>