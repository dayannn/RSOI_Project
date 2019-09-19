<template>
    <div style="padding-top: 75px; padding-bottom: 25px">
        <div class="card shadow" style="width: 70%; margin: auto; padding: 20px; margin-bottom: 20px; text-align: left">
            <h4 class="card-title"><b>Плейлист «{{playlist.name}}»</b></h4>
        </div>
        <div class = "songs-panel">
            <div v-bind:key="song.id" v-for="song in playlist.songs" >
                <SongItem v-bind:song="song" v-on:del-song-from-playlist="deleteSong"></SongItem>
            </div>
        </div>
        <b-button class="btn btn-m shadowed-button"
                  style="horiz-align: center;  margin-top: 20px"
                  @click="deletePlaylist">Удалить плейлист</b-button>
    </div>
</template>

<script>
    import axios from 'axios';
    import SongItem from "../components/SongItem";
    export default {
        name:"Playlist",
        components: {SongItem},
        computed:{},
        methods: {
            updateData(){
                axios.get("/api/playlist/" + this.$route.params.id)
                    .then(res => {
                        this.playlist = res.data;
                    })
                    .catch(err => console.log(err));
            },
            deleteSong(id){
                axios.delete("/api/playlist/" + this.$route.params.id + "/" + id)
                    .then(this.updateData)
                    .catch(err => console.log(err));
            },
            deletePlaylist(){
                axios.delete("/api/playlists/" + this.playlist.id)
                    .then(() => this.$router.push("/playlists"))
                    .catch(err => console.log(err));
            }

        },
        data(){
            return {
                playlist: {
                    name: "",
                    songs: ""
                },
            }
        },
        created() {
            this.updateData();
        }
    }

</script>

<style scoped>
    .card-item{
        width: 70%;
        margin-top: 25px;
        margin-left: auto;
        margin-right: auto;
        text-align: left
    }

    .card-top{
        background: #f5f5f5;
        margin: 0;
        padding-left: 20px;
        padding-right: 20px;
        padding-top: 10px
    }

    .card-content{
        margin: 0;
        padding: 20px
    }

    .songs-panel{
        padding: 20px;
        background-color: #d1d1d1;
    }

</style>
