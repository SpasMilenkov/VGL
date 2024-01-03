<template>
    <div class="flex w-[22rem] flex-row items-stretch content-center bg-gray-15 rounded-lg gap-5 p-3">
        <img :src="props.content.imagePath" alt="Game Image" class="w-40 rounded-xl game-preview" />
        <div class="flex gap-2 w-full flex-col items-left justify-between flex-1">
            <h3 class="game-title ">{{ props.content.gameTitle }}</h3>
            <h4 class="game-studio">{{ props.content.gameStudio }}</h4>
            <h4 class="release-date">{{ props.content.releaseDate }}</h4>
        </div>
    </div>
</template>

<script setup lang='ts'>
import type SmallGameCardContent from '../../interfaces/SmallGameCardContent';
interface HorizontalGameCardProps {
    content: SmallGameCardContent
}
const props = defineProps<HorizontalGameCardProps>()
</script>
<style scoped>
.game-title {
    color: var(--color-white);
    font-size: 1.125rem;
}

.game-preview {
    object-fit: fill;
}

.game-studio,
.release-date {
    color: var(--color-gray);
    font-size: 0.875rem;
}
</style>