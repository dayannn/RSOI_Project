<template>
    <div class = "song-item shadow"
         v-on:mouseover="dropdownActive = true"
         v-on:mouseout="dropdownActive = false"
         style="margin-bottom: 10px; padding-top: 10px">
        <router-link :to="artist_base_url + this.song.artistId" style="padding-left: 20px; color: #009999">{{song.artistName}}</router-link> - {{song.name}}<br/>
        <b-row>
            <b-col>
                <audio
                        controls
                        :src="basic_url + this.song.filePath">
                    Your browser does not support the
                    <code>audio</code> element.
                </audio>
            </b-col>
            <b-col>
                <b-dropdown :text="dropdownText" v-show="dropdownActive" style="float: right; margin-right: 30px">
                    <b-dropdown-item v-bind:key="playlist.id"
                                     v-for="playlist in this.$parent.playlists"
                                     v-on:click="addToPlaylist(playlist)"
                    >
                        {{playlist.name}}</b-dropdown-item>
                </b-dropdown>
            </b-col>
        </b-row>

    </div>
</template>

<script>
    import axios from 'axios';
    export default{
        name: "SongItem",
        props: ["song"],
        methods:{
            addToPlaylist(playlist){
                axios.post("/api/playlist/" + playlist.id + "/" + this.song.id)
                    .then(this.makeToast("Песня \"" + this.song.name + "\" добавлена в плейлист \"" + playlist.name + "\""))
                    .catch(err => console.log(err))
            },
            makeToast(text) {
                this.$bvToast.toast(text, {
                    title: 'Песня добавлена',
                    autoHideDelay: 5000,
                    appendToast: false,
                    toastClass: 'toast-item'
                })
            }
        },
        data() {
            return {
                basic_url: "http://localhost:8088/audiosrc",
                dropdownText: "Добавить в плейлист",
                dropdownActive: false,
                artist_base_url: "/artist/"
            }
        }
    }
</script>

<style scoped>
    .song-item{
        background: #f4f4f4;
        padding: 5px;
        border-bottom: 1px #ccc solid;
        text-align: left;
        width: 70%;
        margin: auto;
    }



</style>
