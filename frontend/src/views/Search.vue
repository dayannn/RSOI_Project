<template>
    <div style="padding-top: 75px">
        <b-row style="padding: 20px 40px 40px;">
            <b-form-input
                    id="input0"
                    type="text"
                    class="shadow-sm"
                    v-model="searchString"
                    required
                    placeholder="Введите название песни"
                    style="margin-bottom: 20px">
            </b-form-input>
            <b-button class="btn btn-m shadowed-button"
                      style="horiz-align: center"
                      @click="searchSongs">Искать</b-button>
        </b-row>
        <div class = "songs-panel">
            <div v-bind:key="song.id" v-bind:playlists="playlists" v-for="song in songs" >
                <SongItemSearch v-bind:song="song"></SongItemSearch>
            </div>
        </div>
    </div>
</template>

<script>
    import axios from 'axios';
    import SongItemSearch from "../components/SongItemSearch";
    export default{
        name: "Search",
        components: {SongItemSearch},
        methods:{
            searchSongs(){
                axios.get("/api/search?name=" + this.searchString)
                    .then(res => this.songs = res.data)
                    .catch(err => console.log(err));
                axios.get("/api/playlists")
                    .then(res => this.playlists = res.data)
                    .catch(err => console.log(err))
            }
        },
        data() {
            return {
                searchString: "",
                songs:[],
                playlists:[]
            }
        }
    }
</script>

<style scoped>

</style>
