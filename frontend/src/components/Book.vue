<script setup lang="ts">
import { ref } from 'vue'
import type { Book } from '../types/generated'

const props = defineProps<{
  book: Book
}>()

const emit = defineEmits<{
  (e: 'updateBook', newBook: Book): void
}>()

const friend = ref()

function lend() {
  console.log('lend')
  const newBook = {
    ...props.book,
    loan: { person: friend.value, date: new Date() },
  }
  emit('updateBook', newBook)
}

function recover(bookId: number) {
  const newBook = {
    ...props.book,
    loan: undefined,
  }
  emit('updateBook', newBook)
}
</script>

<template>
  <div class="card" :class="book.loan != undefined ? 'has-background-warning-light' : ''">
    <div class="card-content">
      <p class="title is-4">
        {{ book.title }}
      </p>
      <p class="subtitle is-5">{{ book.author.firstName }} {{ book.author.lastName }}</p>
      <p v-if="book.loan != undefined">
        lent on {{ book.loan?.date.toDateString() }} to {{ book.loan?.person }}
      </p>
      <div class="button" @click="recover(book.id)" v-if="book.loan != undefined">Recover book</div>
      <div class="button" @click="lend(book.id)" v-else>Lend book</div>
    </div>
  </div>
</template>
