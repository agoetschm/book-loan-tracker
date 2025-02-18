<script setup lang="ts">
import {ref, computed} from 'vue'

type Book = {
  id: Id
  title: string
  author: Person
  loan?: Loan
}
type Id = number
type Loan = {
  person: Person
  date: Date
}
type Person = string

let id = 0
const books = ref([
  {id: id++, title: "Hitch hiker's guide of the galaxy", author: "Douglas Adams"},
  {id: id++, title: "Mistborn", author: "Brandon Sanderson", loan: {person: "John", date: new Date("12 Feb 2025")}},
])

const friend = ref("friend")

function lend(bookId: Id) {
    books.value = books.value.map((b) => {
      if (bookId === b.id)
        return {
          ...b,
          loan: {person: friend.value, date: new Date()},
        }
      else
        return b
  })
}

function recover(bookId: Id) {
    books.value = books.value.map((b) => {
      if (bookId === b.id)
        return {
          ...b,
          loan: undefined,
        }
      else
        return b
  })
}
</script>

<template>
  Friend: <input v-model="friend" required placeholder="Friend name">

  <ul>
    <li v-for="book in books" :key="book.id">
      {{ book.author }}: {{ book.title }}
      <div v-if="book.loan != undefined">
        lent on {{ book.loan?.date.toDateString() }} to {{ book .loan?.person }} 
        <br/>
        <button @click="recover(book.id)">Recover book</button>
      </div>
      <div v-else>
        <!-- <form @submit.prevent="lend(book.id)">
          <input v-model="friend" required placeholder="Friend name">
          <button>Lend book</button>
        </form> -->
        <button @click="lend(book.id)">Lend book</button>
      </div>
      
    </li>
  </ul>
</template>