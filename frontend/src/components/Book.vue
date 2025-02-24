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
const lending = ref(false)

function lend() {
  const newBook = {
    ...props.book,
    loan: { person: friend.value, date: new Date() },
  }
  emit('updateBook', newBook)
  lending.value = false
  friend.value = ""
}

function recover() {
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
        Lent to <strong>{{ book.loan?.person }}</strong> on {{ book.loan?.date.toLocaleDateString() }}
      </p>
      <div class="button" @click="recover()" v-if="book.loan != undefined">Recover book</div>
      <div v-else>
        <div v-if="lending">
          <div class="field has-addons">
          <p class="control">
            <input class="input" type="text" placeholder="Friend name" v-model="friend"/>
          </p>
          <p class="control">
            <button class="button" @click="lend()">Lend</button>
          </p>
        </div>
        </div>
        <div v-else>
          <div class="button" @click="lending = true">Lend book</div>
        </div>
      </div>
    </div>
  </div>
</template>
