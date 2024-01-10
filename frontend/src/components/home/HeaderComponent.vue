<template>
    <div class="home-header h-[5rem] w-full flex justify-end items-center p-8">
        <img :src="imgUrl" alt="Profile operation" class="w-14 h-14 profile-header-icon-container">
    </div>
</template>
<script setup lang='ts'>

const imgUrl = "src/assets/images/pfp.webp"


</script>


<style scoped>
.home-header {
    height: 5.1rem;
    position: sticky;
    top: 0;
    background: rgba(0, 0, 0, 0.2);
    box-shadow: 0 4px 30px rgba(0, 0, 0, 0.1);
    backdrop-filter: blur(5px);
    -webkit-backdrop-filter: blur(5px);
    z-index: 100;
}

.profile-header-icon-container {
    transition: transform 0.3s ease-in-out;
    cursor: pointer;
}

.profile-header-icon-container:hover {
    transform: scale(1.25);
}
</style>