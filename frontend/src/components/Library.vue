<script setup lang="ts">
import { onMounted, type Ref } from 'vue'
import { ref } from 'vue'
import type { Book, Query } from '../types/generated'

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

  <div class="section">
    <div class="container">
      <div class="grid is-col-min-10">
        <div class="cell" v-for="book in books" :key="book.id">
          <div class="card" :class="book.loan != undefined ? 'has-background-warning-light' : ''">
            <div class="card-content">
              <p class="title is-4">
                {{ book.title }}
              </p>
              <p class="subtitle is-5">{{ book.author.firstName }} {{ book.author.lastName }}</p>
              <p v-if="book.loan != undefined">
                lent on {{ book.loan?.date.toDateString() }} to {{ book.loan?.person }}
              </p>
              <div class="button" @click="recover(book.id)" v-if="book.loan != undefined">
                Recover book
              </div>
              <div class="button" @click="lend(book.id)" v-else>Lend book</div>
            </div>
            <!-- <footer class="card-footer">
              <p class="card-footer-item" v-if="book.loan != undefined">
                <a href="#" class="card-footer-item" @click="recover(book.id)">Recover book</a>
              </p>
              <p class="card-footer-item" v-else>
                <a href="#" class="card-footer-item" @click="lend(book.id)">Lend book</a>
              </p>
            </footer> -->
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<!-- <style scoped>
.card {
    display:flex;
    flex-direction: column;
}
.cart-footer {
    margin-top: auto;
}
</style> -->
