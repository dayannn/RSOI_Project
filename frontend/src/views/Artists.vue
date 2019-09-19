<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
    <div class="container">
        <h1>Исполнители</h1>
        <div style="padding: 20px 40px 40px;">
            <b-form-input
                    id="input0"
                    type="text"
                    class="shadow-sm"
                    v-model="searchString"
                    required
                    placeholder="Введите имя исполнителя"
                    style="margin-bottom: 20px; width: 50%; margin-left: auto; margin-right: auto">
            </b-form-input>
            <b-button class="btn btn-m shadowed-button"
                      style="margin-right: 10px"
                      @click="searchArtists">Искать</b-button>
            <b-button class="btn btn-m shadowed-button"
                      @click="clearSearch">Очистить</b-button>
        </div>
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
                searchString: "",
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
            },
            searchArtists(){
                axios.get("/api/artsearch?name="+this.searchString)
                    .then(res => this.artists = res.data)
                    .catch(err => console.log(err))
            },
            clearSearch(){
                this.searchString = "";
                this.updateData();
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
