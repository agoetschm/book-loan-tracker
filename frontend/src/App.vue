<script setup lang="ts">
import { RouterLink, RouterView } from 'vue-router'
import HomeView from './views/HomeView.vue'
</script>

<template>

  <section class="section">
    <div class="container has-text-centered">
      <h1 class="title">Book loan tracker</h1>
      <p class="subtitle">A place to keep a record of which friend borrowed which book</p>
    </div>
  </section>

  <HomeView />
</template>
