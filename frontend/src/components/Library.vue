<script setup lang="ts">
import { onMounted, type Ref } from 'vue'
import { ref } from 'vue'
import type { Book, Query } from '../types/generated'
import BookComponent from './Book.vue'

import { ApolloClient, gql, InMemoryCache } from '@apollo/client/core'

const books: Ref<Array<Book>> = ref([])

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
})

const updateBook = (newBook: Book) => {
  console.log('updateBook')
  books.value = books.value.map((b) => {
    if (newBook.id === b.id) return newBook
    else return b
  })
}
</script>

<template>
  <div class="section">
    <div class="container">
      <div class="grid is-col-min-10">
        <div class="cell" v-for="book in books" :key="book.id">
          <BookComponent :book="book" @updateBook="updateBook" />
        </div>
      </div>
    </div>
  </div>
</template>
