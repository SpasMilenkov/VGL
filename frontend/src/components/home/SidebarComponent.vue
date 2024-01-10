<template>
    <nav class="sidebar flex flex-col items-center">
        <ul class="sidebar-list flex flex-col w-full">
            <li class="nav-item p-4" v-for="(item, index) in items" @click="setActive(index)" :key="index" :class="{
                'previous-active': isAdjacent(index) === 'previous',
                'next-active': isAdjacent(index) === 'next',
                'active': activeIndex === index,
            }">
                <a href="#" class="nav-link">
                    <img :src="item.icon" alt="" class=" w-12">
                </a>
            </li>
        </ul>
        <div class="color"></div>
    </nav>
</template>

<script setup lang='ts'>
import { ref } from 'vue';

const activeIndex = ref<number>(1);
const items = ref([
    { icon: 'src/assets/icons/logo.webp' },
    { icon: 'src/assets/icons/controller.svg' },
    { icon: 'src/assets/icons/popular.svg' },
    { icon: 'src/assets/icons/world.svg' }
]);

const setActive = (index: number) => {
    activeIndex.value = index;
};

const isAdjacent = (index: number): 'previous' | 'next' | null => {
    if (index === activeIndex.value - 1) return 'previous';
    if (index === activeIndex.value + 1) return 'next';
    return null;
};
</script>

<style scoped>
.sidebar {
    top: 0;
    left: 0;
    background: transparent;
    min-height: 100vh;
    min-width: 7rem;
    max-width: 7rem;
    flex: 1;
    z-index: 100;
}

.nav-item {
    background-color: var(--color-orange);
    width: 100%;
    min-height: 6rem;
    display: flex;
    align-items: center;
    justify-content: center;
}

.active {
    background-color: transparent;
}

.previous-active {
    border-bottom-right-radius: 1rem;
}

.next-active {
    border-top-right-radius: 1rem;
}

.color {
    background-color: var(--color-orange);
    flex: 1;
    width: 100%;
}
</style>