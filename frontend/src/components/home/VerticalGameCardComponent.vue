<template>
    <div class="vertical-card flex w-[22rem] flex-col items-stretch content-center bg-gray-15 rounded-lg gap-5 p-3">
        <img :src="props.content.imagePath" alt="Game Image" class="game-preview w-ful rounded-xl" />
        <div class="flex gap-2 flex-col items-left justify-between flex-1">
            <h3 class="game-title ">{{ props.content.gameTitle }}</h3>
            <h4 class="game-studio">{{ props.content.gameStudio }}</h4>
            <h4 class="release-date">{{ props.content.releaseDate }}</h4>
        </div>
    </div>
</template>

<script setup lang='ts'>
import type SmallGameCardContent from '../../interfaces/SmallGameCardContent';
interface VerticalGameCardProps{
    content: SmallGameCardContent
}
const props = defineProps<VerticalGameCardProps>()
</script>
<style scoped>
.game-title {
    color: var(--color-white);
    font-size: 1.125rem;
}
.game-preview{
    max-height: 8.5rem;

}

.game-studio,
.release-date {
    color: var(--color-gray);
    font-size: 0.875rem;
}
</style>