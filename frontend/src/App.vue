<script setup lang="ts">
import { RouterLink, RouterView } from 'vue-router'
import HelloWorld from './components/HelloWorld.vue'
import Library from './components/Library.vue'
</script>

<template>
  <!-- <header>
    <img alt="Vue logo" class="logo" src="@/assets/logo.svg" width="125" height="125" />

    <div class="wrapper">
      <HelloWorld msg="You did it!" />

      <nav>
        <RouterLink to="/">Home</RouterLink>
        <RouterLink to="/about">About</RouterLink>
      </nav>
    </div>
  </header>

  <RouterView /> -->

  <section class="section">
    <div class="container has-text-centered">
      <h1 class="title">Book loan tracker</h1>
      <p class="subtitle">A place to keep a record of which friend borrowed which book</p>
    </div>
  </section>

  <Library />
</template>
