<script setup lang="ts">
import { onMounted, type Ref } from 'vue'
import { ref } from 'vue'
import type { Book, Query } from '../types/generated'

import { ApolloClient, gql, InMemoryCache } from '@apollo/client/core'

let idBook = 0
const books: Ref<Array<Book>> = ref([])

let idAuthor = 0

const client = new ApolloClient({
  uri: 'http://localhost:8080/graphql',
  cache: new InMemoryCache(),
  connectToDevTools: true,
})

const getBooks = async () => {
  const { data } = await client.query<Query>({
    query: gql`
      query getBooks {
        books {
          id
          title
          author {
            id
            firstName
            lastName
          }
        }
      }
    `,
  })

  return data.books ?? []
}

onMounted(() => {
  console.log(`the component is now mounted.`)

  getBooks().then((bs) => {
    console.log(bs)
    books.value = bs
  })

  // books.value = [
  //   {
  //     id: idBook++,
  //     title: "Hitchhiker's guide to the galaxy",
  //     author: { id: idAuthor++, firstName: 'Douglas', lastName: 'Adams' },
  //   },
  //   {
  //     id: idBook++,
  //     title: 'Mistborn',
  //     author: { id: idAuthor++, firstName: 'Brandon', lastName: 'Sanderson' },
  //     loan: { person: 'John', date: new Date('12 Feb 2025') },
  //   },
  // ]
})

const friend = ref()

function lend(bookId: number) {
  books.value = books.value.map((b) => {
    if (bookId === b.id)
      return {
        ...b,
        loan: { person: friend.value, date: new Date() },
      }
    else return b
  })
}

function recover(bookId: number) {
  books.value = books.value.map((b) => {
    if (bookId === b.id)
      return {
        ...b,
        loan: undefined,
      }
    else return b
  })
}
</script>

<template>
  Friend: <input v-model="friend" required placeholder="Friend name" />

  <ul>
    <li v-for="book in books" :key="book.id">
      {{ book.author.lastName }}, {{ book.author.firstName }}: {{ book.title }}
      <div v-if="book.loan != undefined">
        lent on {{ book.loan?.date.toDateString() }} to {{ book.loan?.person }}
        <br />
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
