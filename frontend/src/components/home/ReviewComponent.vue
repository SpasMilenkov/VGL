<template>
    <div class="flex flex-col gap-4 items-center w-1/3 h-max-[30rem] h-min-[15rem]">
        <h4 class="user-name">{{ data.author }}</h4>
        <p class="review-content">{{ data.content }}</p>
        <div class="flex">
            <img v-for="(index) in data.rating" :key="index" src="../../assets/icons/solid-star.svg" alt="Solid star" class="w-[1.5rem]">
            <img v-for="(index) in (5 - data.rating)" :key="index" src="../../assets/icons/star.svg" alt="Empty star"  class="w-[1.5rem]">
        </div>
    </div>
</template>
<script setup lang='ts'>
import type ReviewContent from '../../interfaces/ReviewContent';

defineProps<{
    data: ReviewContent
}>()
</script>
<style scoped>
.user-name,
.review-content{
  color: var(--color-white);
  text-align: center;
  font-weight: 500;
  font-size: 1.25rem;
  width: 50%;
  line-height: 1.25rem;
}
</style>