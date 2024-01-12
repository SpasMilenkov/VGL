<template>
    <div class="modal w-[80vw] w-max- fixed rounded-lg">
        <img class="w-12 absolute top-2 right-2 z-50 cursor-pointer" src="../../assets/icons/close-button-icon.svg"
            alt="Exit" @click="closeModal">
        <div class="relative">
            <img :src="game?.bannerUrl" alt="" class="game-cover rounded-lg w-full">
            <div class="modal-img-overlay"></div>
        </div>
        <div class="game-info p-16 gap-4 flex flex-col items-start justify-start rounded-lg">
            <div class="flex flex-col">
                <h1 class="game-title">{{ game?.name }}</h1>
                <h2 class="game-studio">Studio: <span class="white">{{ game?.studio }}</span></h2>
            </div>

            <p class="description">{{ game?.shortDescription }}</p>
            <h3 class="section-title">Reviews</h3>
            <div class="reviews flex flex-row p-2 gap-4 justify-center flex-wrap">
                <ReviewComponent v-for="(review, index) in reviews" :key="index" :data="review" />
            </div>
        </div>
    </div>  
</template>
<script lang="ts" setup>
import type ReviewContent from '../../interfaces/ReviewContent';
import ReviewComponent from './ReviewComponent.vue';
import axios from "../../axios/axios";
import { onMounted, ref, type Ref } from 'vue';
import type ModalContent from '../../interfaces/ModalContent';

const emitEvent = defineEmits<{
    (event: 'closeModal', data: string): void;
}>();

const closeModal = () => {
    emitEvent('closeModal', 'someData');
}
const props = defineProps<{ steamId: number }>()
const game: Ref<ModalContent | undefined> = ref();
const reviews = ref()
onMounted(async () => {
    game.value = (await axios.get('/steam/game-details',
        {
            params:
            {
                gameId: props.steamId
            }
        }
    )).data;
    reviews.value = (await axios.get(`/reviews/reviews-by-game-id/${props.steamId}`)).data
    console.log(reviews.value)

})
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
.game-cover{
    height: 45rem;
    object-position: top;
    object-fit: cover;
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