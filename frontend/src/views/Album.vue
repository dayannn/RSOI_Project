<template>
    <div style="padding-top: 75px">
        <img src="../assets/album.svg" alt="cover" style="width: 200px; height: 200px"/>
        <h5 style="margin-bottom: 20px">Альбом {{album.name}}</h5>
        <div class = "songs-panel">
            <div v-bind:key="song.id" v-bind:playlists="playlists" v-for="song in album.songs" >
                <SongItemSearch v-bind:song="song"></SongItemSearch>
            </div>
        </div>
    </div>
</template>

<script>
    import axios from 'axios';
    import SongItemSearch from "../components/SongItemSearch";
    export default{
        name: "Album",
        components: {SongItemSearch},
        methods:{
            updateData(){
                axios.get("/api/album/" + this.$route.params.id)
                    .then(res => this.album = res.data)
                    .catch(err => console.log(err));
                axios.get("/api/playlists")
                    .then(res => this.playlists = res.data)
                    .catch(err => console.log(err))
            }
        },
        data() {
            return {
                album: {},
                songs:[],
                playlists:[]
            }
        },
        created(){
            this.updateData();
        }
    }
</script>

<style scoped>

</style>
