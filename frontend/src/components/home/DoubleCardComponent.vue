<template>
    <div class="carousel">
        <Carousel :items-to-show="3" :wrap-around="true">
            <Slide v-for="(slide, id) in cardsToPrint" :key="id">
                <div class="flex flex-col justify-start p-4 gap-4">
                    <div class="flex">
                        <h3 class="number">{{ slide.topCard.num}}</h3>
                        <HorizontalGameCardComponent :content="slide.topCard.content" />
                    </div>
                    <div class="flex" v-if="slide.bottomCard?.content !== undefined">
                        <h3 class="number">{{ slide.bottomCard.num}}</h3>
                        <HorizontalGameCardComponent  :content="slide.bottomCard.content" />
                    </div>
                </div>
            </Slide>

            <template #addons>
                <Navigation />
            </template>
        </Carousel>
    </div>
</template>

<script setup lang="ts">
import { Carousel, Slide, Navigation } from 'vue3-carousel';
import 'vue3-carousel/dist/carousel.css';

// Import dynamic components
import HorizontalGameCardComponent from './HorizontalGameCardComponent.vue';

import type SmallGameCardContent from '../../interfaces/SmallGameCardContent';
import { onMounted, ref, type Ref } from 'vue';
interface CarouselProps {
    cards: SmallGameCardContent[]
}

interface CardPair {
    topCard: {
        num: string,
        content: SmallGameCardContent
    },
    bottomCard?: {
        num: string,
        content: SmallGameCardContent
    }
}

const props = defineProps<CarouselProps>();
const cardsToPrint: Ref<CardPair[]> = ref([])
onMounted(() => {
    for (let i = 0; i < props.cards.length; i++) {
        const card = props.cards[i];
        let cardPair: CardPair = {
            topCard: {
                num:`${i + 1}.`,
                content: card
            },
        }
        if (i < props.cards.length - 1) {
            i++;
            const nextCard = props.cards[i]

            cardPair.bottomCard = {
                num: `${i + 1}.`,
                content: nextCard
            }
        }
        cardsToPrint.value.push(cardPair)
    }
})

</script>
<style scoped>
.carousel {
    width: 110rem;
    overflow: hidden;
}

.carousel__item {
    min-height: 200px;
    max-width: 30rem;
    background-color: green;
    color: white;
    font-size: 20px;
    border-radius: 8px;
    display: flex;
    justify-content: center;
    align-items: center;
}

.carousel__slide {
    padding: 10px;
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
    flex: 1;

}

.carousel__prev,
.carousel__next {
    box-sizing: content-box;
    color: var(--color-orange);
    background-color: rgba(0, 0, 0, 0.5);
    border-radius: 1rem;
}

.carousel__prev:hover,
.carousel__next:hover {
    color: var(--color-orange);
}
.number{
  padding: 0 1.25rem 0 0.5rem;
  font-weight: 700;
  font-size: 2rem;
  color: #ff9838;
}
</style>