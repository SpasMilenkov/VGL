<template>
    <div class="review flex flex-col gap-4 items-center w-min-40 w-full p-4 h-max-[30rem] h-min-[15rem] rounded-lg">
        <h4 class="user-name">{{ data.author }}</h4>
        <p class="review-content">{{ data.text }}</p>
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
.review {
  background-color: rgba(255, 255, 255, 0.1);
}
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