<template>
    <div class="modal w-[80vw] w-max- absolute rounded-lg">
        <img class="w-12 absolute top-2 right-2 z-50 cursor-pointer" src="../../assets/icons/close-button-icon.svg" alt="Exit">
        <div class="relative">
            <img src="../../assets/images/HeroImage.png" alt="" class="game-cover rounded-lg w-full">
            <div class="modal-img-overlay"></div>
        </div>
        <div class="game-info p-16 gap-4 flex flex-col items-start justify-start rounded-lg">
            <div class="flex flex-col">
                <h1 class="game-title">Lords of the fallen</h1>
                <h2 class="game-studio">Studio: <span class="white">Misho Games</span></h2>
            </div>

            <p class="description">
                Lorem ipsum dolor sit amet, consectetur adipisicing elit. Eaque earum tempora possimus
                vero
                sit, culpa dignissimos est! Accusamus quae facere possimus, ipsa, obcaecati iste sunt a dolores numquam
                tempore
                laborum.</p>
            <h3 class="section-title">Reviews</h3>
            <div class="reviews flex flex-row p-2 gap-4 justify-center">
                <ReviewComponent v-for="(review, index) in reviews" :key="index" :data="review" />
            </div>
        </div>
    </div>
</template>
<script lang="ts" setup>
import type ReviewContent from '../../interfaces/ReviewContent';
import ReviewComponent from './ReviewComponent.vue';

const props = defineProps<{ gameId: string }>()

const reviews: ReviewContent[] = [
    {
        content: "I liked where mesho meshoed all over them",
        author: "Mesho shterev",
        rating: 3,
    },
    {
        content: "I liked where mesho meshoed all over them",
        author: "Mesho shterev",
        rating: 4,
    },
    {
        content: "I liked where mesho meshoed all over them",
        author: "Mesho shterev",
        rating: 5,
    },
]
</script>
<style scoped>
.modal {
    z-index: 100;
    left: 55%;
    top: 2rem;
    transform: translateX(-55%);
    display: flex;
    flex-direction: column;
    background-color: var(--color-black);
    width: 95%;
    height: 100%;
    min-height: fit-content;
    overflow: auto;
}

.modal-img-overlay {
    position: absolute;
    width: 100%;
    height: 100%;
    background: linear-gradient(180deg, rgba(0, 0, 0, 0) 0%, #1d1d1f 100%);
    top: 0;
}

.game-title {
    color: var(--color-white);
    font-weight: 600;
    font-size: 3rem;
    text-transform: uppercase;
}

.game-info {
    background-color: var(--color-black);
}

.game-studio,
.release-date {
    color: var(--color-gray);
    font-weight: 300;
    font-size: 1.25rem;
}

.description {
    color: var(--color-white);
    font-weight: 400;
    font-size: 1.5rem;
}

.white {
    color: var(--color-white);
}

.section-title {
    color: var(--color-white);
    font-weight: 500;
    font-size: 2rem;
}
</style>