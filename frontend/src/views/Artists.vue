<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
    <div class="container">
        <h1>Исполнители</h1>
        <div class="card-group">
            <div class="artists-list" v-bind:key="artist.id" v-for="artist in artists">
                <ArtistItem  v-bind:artist="artist"/>
            </div>
        </div>
    </div>
</template>

<script>
    import ArtistItem from '../components/ArtistItem';
    import axios from 'axios';
    export default {
        name: "artists",
        components: {ArtistItem},
        data() {
            return {
                artists: [],
            }
        },
        created() {
            this.updateData()
        },
        methods:{
            updateData(){
               axios.get("/api/artist")
                    .then(res => this.artists = res.data)
                    .catch(err => console.log(err))
            }
        }
    }

</script>

<style scoped>
    .artists-list{
        width: 60%;
        margin: auto;
        padding: 8px;
    }

    .card-group{
        display: grid;
        /*grid-template-columns: repeat(4, 1fr);*/
        grid-template-columns: repeat(auto-fill, 300px);
        grid-gap: 10px;
        /*display: flex;*/
        justify-content: center;
    }


</style>
